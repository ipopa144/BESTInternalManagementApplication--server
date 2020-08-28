package ngo.best.server.controller;

import ngo.best.server.model.dto.DesiredUserCategoryDTO;
import ngo.best.server.model.dto.UpdateUserDTO;
import ngo.best.server.model.dto.UserCategoryDTO;
import ngo.best.server.model.dto.UserDTO;
import ngo.best.server.model.entity.JwtPreAuthenticateResponse;
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
    public ResponseEntity<JwtPreAuthenticateResponse> findByToken(@RequestHeader("Authorization") String jwtToken) {
        User user = userService.identifyUser(jwtToken);
        UserDTO userDTO = DTOConverter.convertUserToUserDTO(user);
        Boolean isUserSubscribed = user.getSubscription() != null;

        if(user == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(new JwtPreAuthenticateResponse(userDTO, isUserSubscribed));
    }

    @GetMapping("/tokenAll")
    public ResponseEntity<UserDTO> findUserByToken(@RequestHeader("Authorization") String jwtToken) {
        User user = userService.identifyUser(jwtToken);

        if(user != null) {
            UserDTO userDTO = DTOConverter.convertUserToUserDTO(user);
            return ResponseEntity.ok(userDTO);
        }
        else
            return ResponseEntity.notFound().build();
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

    @PutMapping("/changePassword")
    public ResponseEntity<UserDTO> changePassword(@RequestHeader("Authorization") String jwtToken,
                                              @RequestBody String password) {
        User user = userService.identifyUser(jwtToken);

        if(user != null) {
            User updatedUser = userService.changePassword(user.getId(), password);
            return ResponseEntity.ok(DTOConverter.convertUserToUserDTO(updatedUser));
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{userId}/userCategories")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<UserDTO> updateUserCategories(@PathVariable("userId") Long userId,
                                              @RequestBody List<UserCategoryDTO> userCategoryDTOS) {

        User updatedUser = userService.updateUserCategories(userId, userCategoryDTOS);

        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(DTOConverter.convertUserToUserDTO(updatedUser));
        }
    }

    @PutMapping("/desiredUserCategories")
    public ResponseEntity<UserDTO> updateDesiredUserCategories(@RequestHeader("Authorization") String jwtToken,
                                                        @RequestBody List<DesiredUserCategoryDTO> desiredUserCategoryDTOS) {

        User user = userService.identifyUser(jwtToken);
        User updatedUser = userService.updateDesiredUserCategories(user.getId(), desiredUserCategoryDTOS);

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
