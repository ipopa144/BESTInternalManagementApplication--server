package ngo.best.server.controller;

import ngo.best.server.config.JwtTokenUtil;
import ngo.best.server.model.dto.NewUserDTO;
import ngo.best.server.model.dto.UserAuthenticationDTO;
import ngo.best.server.model.entity.JwtRequest;
import ngo.best.server.model.entity.JwtResponse;
import ngo.best.server.model.entity.User;
import ngo.best.server.service.JwtUserDetailsService;
import ngo.best.server.service.UserService;
import ngo.best.server.utils.DTOConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ioana
 */

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final UserService userService;

    public JwtAuthenticationController(AuthenticationManager authenticationManager,
                                       JwtTokenUtil jwtTokenUtil, JwtUserDetailsService jwtUserDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));

        try {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequest.getEmail());
            String token = jwtTokenUtil.generateToken(userDetails);
            User user = userService.findByEmail(jwtRequest.getEmail());
            UserAuthenticationDTO userAuthenticationDTO = DTOConverter.convertUserToUserAuthenticationDTO(user);
            return ResponseEntity.ok(new JwtResponse(userAuthenticationDTO, token));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("USERNAME_NOT_FOUND");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody NewUserDTO userDTO) {

        List<Object> response = jwtUserDetailsService.registerNewUserAccount(userDTO);

        if (response.get(0).equals("USER_SAVED")) {
            return ResponseEntity.ok((User) response.get(1));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
