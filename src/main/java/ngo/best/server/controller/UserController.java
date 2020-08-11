package ngo.best.server.controller;

import ngo.best.server.model.dto.UserDTO;
import ngo.best.server.model.entity.Event;
import ngo.best.server.model.entity.User;
import ngo.best.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Ioana
 */

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<List<User>> findAll() {


        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long userId) {

        Optional<User> user = userService.findById(userId);

        if(user.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(user.get());
    }

    @PostMapping("/token")
    public ResponseEntity<User> findByToken(@RequestHeader("Authorization") String jwtToken) {
        User user = userService.identifyUser(jwtToken);

        if(user == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(user);
    }

    @GetMapping("/{lastName}")
    public ResponseEntity<List<User>> findByLastName(@PathVariable("lastName") String lastName) {
        return ResponseEntity.ok(userService.findAllByLastName(lastName));
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId,
                                           @RequestBody UserDTO userDTO) {


        User updatedUser = userService.updateUser(userId, userDTO);

        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedUser);
        }
    }

    @PutMapping("/{userId}/category/{categoryId}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<User> updateUserCategoryGrade(@PathVariable("userId") Long userId,
                                                        @PathVariable("categoryId") Long categoryId,
                                                        @RequestBody Double grade) {

        User updatedUser = userService.updateUserCategoryGrade(userId, categoryId, grade);

        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedUser);
        }
    }
}
