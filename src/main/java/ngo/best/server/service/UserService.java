package ngo.best.server.service;

import ngo.best.server.config.JwtTokenUtil;
import ngo.best.server.model.dto.DesiredUserCategoryDTO;
import ngo.best.server.model.dto.UpdateUserDTO;
import ngo.best.server.model.dto.UserCategoryDTO;
import ngo.best.server.model.dto.UserDTO;
import ngo.best.server.model.entity.*;
import ngo.best.server.repository.*;
import ngo.best.server.utils.DTOConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Ioana
 */

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final UserCategoryRepository userCategoryRepository;
    private final DesiredUserCategoryRepository desiredUserCategoryRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CategoryRepository categoryRepository,
                       UserCategoryRepository userCategoryRepository, JwtTokenUtil jwtTokenUtil,
                       DesiredUserCategoryRepository desiredUserCategoryRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.userCategoryRepository = userCategoryRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.desiredUserCategoryRepository = desiredUserCategoryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(long id) { return userRepository.findById(id); }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public List<UserDTO> findAllUserDTO() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : this.findAll()) {
            userDTOS.add(DTOConverter.convertUserToUserDTO(user));
        }
        return userDTOS;
    }

    public List<User> findAllByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName);
    }

    public List<UserDTO> findAllByLastNameDTO(String lastName) {
        List<UserDTO> userDTOS = new ArrayList<>();
        userRepository.findAllByLastName(lastName).forEach(user -> userDTOS.add(DTOConverter.convertUserToUserDTO(user)));
        return userDTOS;
    }

    public User identifyUser(String token) {
        String[] splitResult = token.split(" ");
        return findByEmail(jwtTokenUtil.getEmailFromToken(splitResult[1]));
    }

    public User updateUser(Long userID, UpdateUserDTO userDTO) {
        final Optional<User> user = userRepository.findById(userID);
        if(user.isPresent()) {
            user.get().setFirstName(userDTO.getFirstName());
            user.get().setLastName(userDTO.getLastName());
            user.get().setNickname(userDTO.getNickname());
            user.get().setEnabled(userDTO.isEnabled());
            user.get().setRoles(userDTO.getRoles());
            userRepository.save(user.get());
            return user.get();
        }
        return null;
    }

    public User changePassword(Long userId, String password) {
        final Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            user.get().setPassword(passwordEncoder.encode(password));
            userRepository.save(user.get());
            return user.get();
        }
        return null;
    }

    public User updateUserCategories(Long userID, List<UserCategoryDTO> userCategoryDTOS) {
        final Optional<User> user = userRepository.findById(userID);
        if(user.isPresent()) {
            userCategoryDTOS.forEach(userCategoryDTO -> {
                Category category = categoryRepository.findByName(userCategoryDTO.getCategoryName());
                this.updateUserCategoryGrade(userID, category.getId(), userCategoryDTO.getGrade());
            });
            return user.get();
        }
        return null;
    }

    public User updateDesiredUserCategories(Long userID, List<DesiredUserCategoryDTO> desiredUserCategoryDTOS) {
        final Optional<User> user = userRepository.findById(userID);
        if(user.isPresent()) {
            desiredUserCategoryDTOS.forEach(userCategoryDTO -> {
                Category category = categoryRepository.findByName(userCategoryDTO.getCategoryName());
                this.updateDesiredUserCategoryGrade(userID, category.getId(), userCategoryDTO.getGrade());
            });
            return user.get();
        }
        return null;
    }

    public User updateDesiredUserCategoryGrade(Long userId, Long categoryId, Double grade) {
        final Optional<Category> category = categoryRepository.findById(categoryId);
        final Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && category.isPresent()) {
            DesiredUserCategory desiredUserCategory = new DesiredUserCategory();
            desiredUserCategory.setUser(user.get());
            desiredUserCategory.setCategory(category.get());
            desiredUserCategory.setGrade(grade);
            DesiredUserCategory save = desiredUserCategoryRepository.save(desiredUserCategory);
            return user.get();
        }
        return null;
    }

    public User updateUserCategoryGrade(Long userId, Long categoryId, Double grade) {
        final Optional<Category> category = categoryRepository.findById(categoryId);
        final Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && category.isPresent()) {
            UserCategory userCategory = new UserCategory();
            userCategory.setUser(user.get());
            userCategory.setCategory(category.get());
            userCategory.setGrade(grade);
            userCategoryRepository.save(userCategory);
            return user.get();
        }
        return null;
    }
}
