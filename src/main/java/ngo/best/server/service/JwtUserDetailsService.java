package ngo.best.server.service;

import ngo.best.server.model.dto.NewUserDTO;
import ngo.best.server.model.entity.Role;
import ngo.best.server.model.entity.User;
import ngo.best.server.repository.RoleRepository;
import ngo.best.server.repository.UserRepository;
import ngo.best.server.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Ioana
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null)
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(), user.isEnabled(), true,
                    true, true, getAuthorities(user.getRoles()));
        else
            throw new UsernameNotFoundException("User not found with email " + email);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        return getGrantedAuthorities(getStringRoles(roles));
    }

    private List<String> getStringRoles(Collection<Role> roles) {
        List<String> rolesCollection = new ArrayList<>();
        for (Role role : roles) {
            rolesCollection.add(role.getName());
        }
        return rolesCollection;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    public List<Object> registerNewUserAccount(NewUserDTO userDTO) {
        User foundUser = userRepository.findByEmail(userDTO.getEmail());
        String message;
        List<Object> responseList = new ArrayList<>();
        Object responseObject;
        if (foundUser != null) {
            message = "EMAIL_ALREADY_USED";
            responseObject = foundUser;
            responseList.add(message);
            responseList.add(responseObject);
            return responseList;
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = DTOConverter.convertNewUserDTOToUser(userDTO);
        user.setEnabled(false);
        List<Role> userRoles = new ArrayList<>();
        for (String role : userDTO.getRoles()) {
            Role foundRole = roleRepository.findByName(role);
            if (foundRole != null) {
                userRoles.add(foundRole);
            }
        }
        user.setRoles(userRoles);
        foundUser = userRepository.save(user);
        message = "USER_SAVED";
        responseObject = foundUser;
        responseList.add(message);
        responseList.add(responseObject);
        return responseList;
    }
}
