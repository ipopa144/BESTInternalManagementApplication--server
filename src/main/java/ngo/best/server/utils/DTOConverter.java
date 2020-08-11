package ngo.best.server.utils;

import ngo.best.server.model.dto.*;
import ngo.best.server.model.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ioana
 */

@Service
public class DTOConverter {
    public static UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setNickname(user.getNickname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public static User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());
        user.setEnabled(userDTO.isEnabled());
        return user;
    }

    public static UserAuthenticationDTO convertUserToUserAuthenticationDTO(User user) {
        UserAuthenticationDTO userAuthenticationDTO = new UserAuthenticationDTO();
        userAuthenticationDTO.setFirstName(user.getFirstName());
        userAuthenticationDTO.setLastName(user.getLastName());
        userAuthenticationDTO.setEmail(user.getEmail());
        userAuthenticationDTO.setEnabled(user.isEnabled());
        userAuthenticationDTO.setLocalDateTime(user.getLocalDateTime());
        userAuthenticationDTO.setNickname(user.getNickname());
        userAuthenticationDTO.setRoles((List<Role>) user.getRoles());
        return userAuthenticationDTO;
    }

    public static Event convertEventDTOToEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setFirstYear(eventDTO.getFirstYear());
        event.setDescription(eventDTO.getDescription());
        return event;
    }

    public static Category convertCategoryDTOToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }

    public static Role convertRoleDTOToRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return role;
    }

    public static User convertNewUserDTOToUser(NewUserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public static Subscription convertSubscriptionDTOToSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = new Subscription();
        subscription.setP256dh(subscriptionDTO.getP256dh());
        subscription.setExpirationTime(subscriptionDTO.getExpirationTime());
        subscription.setEndpoint(subscriptionDTO.getEndpoint());
        subscription.setAuth(subscriptionDTO.getAuth());
        return subscription;
    }
}
