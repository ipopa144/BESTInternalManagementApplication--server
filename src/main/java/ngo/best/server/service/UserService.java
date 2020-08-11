package ngo.best.server.service;

import ngo.best.server.clustering.kmeans.CentroidDTO;
import ngo.best.server.clustering.kmeans.Record;
import ngo.best.server.config.JwtTokenUtil;
import ngo.best.server.model.dto.UserDTO;
import ngo.best.server.model.entity.Category;
import ngo.best.server.model.entity.User;
import ngo.best.server.model.entity.UserCategory;
import ngo.best.server.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Ioana
 */

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final UserCategoryRepository userCategoryRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, CategoryRepository categoryRepository, UserCategoryRepository userCategoryRepository, JwtTokenUtil jwtTokenUtil, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
        this.userCategoryRepository = userCategoryRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(long id) { return userRepository.findById(id); }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName);
    }

    public User identifyUser(String token) {
        String[] splitResult = token.split(" ");
        return findByEmail(jwtTokenUtil.getEmailFromToken(splitResult[1]));
    }

    public User updateUser(Long userID, UserDTO userDTO) {
        final Optional<User> user = userRepository.findById(userID);
        if(user.isPresent()) {
            user.get().setFirstName(userDTO.getFirstName());
            user.get().setLastName(userDTO.getLastName());
            user.get().setPassword(userDTO.getPassword());
            user.get().setEnabled(userDTO.isEnabled());
            user.get().setRoles(userDTO.getRoles());
            userRepository.save(user.get());
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
