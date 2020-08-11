package ngo.best.server.config;

import ngo.best.server.model.entity.*;
import ngo.best.server.repository.CategoryRepository;
import ngo.best.server.repository.RoleRepository;
import ngo.best.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

/**
 * @author Ioana
 */

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        createRoleIfNotFound("ROLE_MANAGEMENT");
        createRoleIfNotFound("ROLE_MEMBER_WITH_VOTE_RIGHT");
        createRoleIfNotFound("ROLE_MEMBER");
        createRoleIfNotFound("ROLE_MAIN_ORGANIZER");
        createRoleIfNotFound("ROLE_CORE_TEAM_MEMBER");
        createRoleIfNotFound("ROLE_FRESHMEN");
        createRoleIfNotFound("ROLE_ALUMNI");

        createCategoryIfNotFound("PR");
        createCategoryIfNotFound("Design");
        createCategoryIfNotFound("FR");
        createCategoryIfNotFound("IT");
        createCategoryIfNotFound("HR");

//        Random random = new Random();
//
//        for(int i = 0; i < 200; i++) {
//            User user = new User();
//            user.setFirstName("User" + i);
//            user.setLastName("");
//            user.setEmail("email" + i + "@gmail.com");
//            user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_MEMBER")));
//            List<String> categories = new ArrayList<>();
//            categories.add("PR");
//            categories.add("Design");
//            categories.add("FR");
//            categories.add("HR");
//            categories.add("IT");
//            int finalI = i;
//            categories.forEach(category -> {
//                UserCategory userCategory = new UserCategory();
//                userCategory.setCategory(categoryRepository.findByName(category));
//                userCategory.setUser(user);
//
//                DesiredUserCategory desiredUserCategory = new DesiredUserCategory();
//                desiredUserCategory.setCategory(categoryRepository.findByName(category));
//                desiredUserCategory.setUser(user);
//
//                if (finalI <= 40) {
//                    if (category.equals("Design") || category.equals("FR")) {
//                        userCategory.setGrade((double) random.nextInt(6));
//                        desiredUserCategory.setGrade((double) random.nextInt(6));
//                    } else {
//                        userCategory.setGrade((double) random.nextInt(3));
//                        desiredUserCategory.setGrade((double) random.nextInt(2));
//                    }
//                }
//                if (finalI > 40 && finalI <= 80) {
//                    if (category.equals("PR") || category.equals("IT")) {
//                        userCategory.setGrade((double) random.nextInt(6));
//                        desiredUserCategory.setGrade((double) random.nextInt(6));
//                    } else {
//                        userCategory.setGrade((double) random.nextInt(3));
//                        desiredUserCategory.setGrade((double) random.nextInt(1));
//                    }
//                }
//                if (finalI > 80 && finalI <= 120) {
//                    if (category.equals("HR") || category.equals("Design")) {
//                        userCategory.setGrade((double) random.nextInt(6));
//                        desiredUserCategory.setGrade((double) random.nextInt(6));
//                    } else {
//                        userCategory.setGrade((double) random.nextInt(3));
//                        desiredUserCategory.setGrade((double) random.nextInt(4));
//                    }
//                }
//                if (finalI > 120 && finalI <= 160) {
//                    if (category.equals("FR") || category.equals("PR")) {
//                        userCategory.setGrade((double) random.nextInt(6));
//                        desiredUserCategory.setGrade((double) random.nextInt(2));
//                    } else {
//                        userCategory.setGrade((double) random.nextInt(3));
//                        desiredUserCategory.setGrade((double) random.nextInt(6));
//                    }
//                }
//                if (finalI > 160) {
//                    if (category.equals("IT") || category.equals("HR")) {
//                        userCategory.setGrade((double) random.nextInt(6));
//                        desiredUserCategory.setGrade((double) random.nextInt(3));
//                    } else {
//                        userCategory.setGrade((double) random.nextInt(3));
//                        desiredUserCategory.setGrade((double) random.nextInt(3));
//                    }
//                }
//                user.addDesiredUserCategory(desiredUserCategory);
//                user.addUserCategory(userCategory);
//            });
//            userRepository.save(user);
//        }

//        Role adminRole = roleRepository.findByName("ROLE_MANAGEMENT");
//        User user = new User();
//        user.setFirstName("Admin");
//        user.setLastName("Admin");
//        user.setPassword(passwordEncoder.encode("parola"));
//        user.setEmail("admin@gmail.com");
//        user.setRoles(Collections.singletonList(adminRole));
//        user.setEnabled(true);
//        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    void createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
    }

    @Transactional
    void createCategoryIfNotFound(String name) {
        Category Category = categoryRepository.findByName(name);
        if (Category == null) {
            Category = new Category(name);
            categoryRepository.save(Category);
        }
    }
}