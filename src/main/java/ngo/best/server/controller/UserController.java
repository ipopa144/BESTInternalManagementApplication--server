package ngo.best.server.controller;

import ngo.best.server.model.dto.UpdateUserDTO;
import ngo.best.server.model.dto.UserDTO;
import ngo.best.server.model.entity.User;
import ngo.best.server.service.UserService;
import ngo.best.server.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAllUserDTO());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long userId) {

        Optional<User> user = userService.findById(userId);

        if(user.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(DTOConverter.convertUserToUserDTO(user.get()));
    }

    @PostMapping("/token")
    public ResponseEntity<UserDTO> findByToken(@RequestHeader("Authorization") String jwtToken) {
        User user = userService.identifyUser(jwtToken);

        if(user == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(DTOConverter.convertUserToUserDTO(user));
    }

    @GetMapping("/{lastName}")
    public ResponseEntity<List<UserDTO>> findByLastName(@PathVariable("lastName") String lastName) {
        return ResponseEntity.ok(userService.findAllByLastNameDTO(lastName));
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") Long userId,
                                           @RequestBody UpdateUserDTO userDTO) {


        User updatedUser = userService.updateUser(userId, userDTO);

        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(DTOConverter.convertUserToUserDTO(updatedUser));
        }
    }

    @PutMapping("/{userId}/category/{categoryId}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<UserDTO> updateUserCategoryGrade(@PathVariable("userId") Long userId,
                                                        @PathVariable("categoryId") Long categoryId,
                                                        @RequestBody Double grade) {

        User updatedUser = userService.updateUserCategoryGrade(userId, categoryId, grade);

        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(DTOConverter.convertUserToUserDTO(updatedUser));
        }
    }
}
