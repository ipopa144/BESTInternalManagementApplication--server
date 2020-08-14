package ngo.best.server.utils;

import ngo.best.server.model.dto.*;
import ngo.best.server.model.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ioana
 */

@Service
public class DTOConverter {

    public static UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setNickname(user.getNickname());
        userDTO.setEmail(user.getEmail());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setRoles((List<Role>) user.getRoles());
        userDTO.setLocalDateTime(user.getLocalDateTime());

        List<UserCategoryDTO> userCategoryDTOS = new ArrayList<>();
        user.getUserCategories().forEach(userCategory -> userCategoryDTOS.add(DTOConverter.convertUserCategoryToUserCategoryDTO(userCategory)));
        userDTO.setUserCategories(userCategoryDTOS);

        List<DesiredUserCategoryDTO> desiredUserCategoryDTOS = new ArrayList<>();
        user.getDesiredUserCategories().forEach(desiredUserCategory -> desiredUserCategoryDTOS.add(DTOConverter.convertDesiredUserCategoryToDesiredUserCategoryDTO(desiredUserCategory)));
        userDTO.setDesiredUserCategories(desiredUserCategoryDTOS);

        List<CoreTeamMemberDTO> coreTeamMemberDTOS = new ArrayList<>();
        user.getCoreTeamMembers().forEach(coreTeamMember -> coreTeamMemberDTOS.add(DTOConverter.convertCoreTeamMemberToCoreTeamMemberDTO(coreTeamMember)));
        userDTO.setCoreTeamMembers(coreTeamMemberDTOS);

        List<EventUserDTO> eventsMainOrganizer = new ArrayList<>();
        user.getEventsMainOrganizer().forEach(event -> eventsMainOrganizer.add(DTOConverter.convertEventToEventUserDTO(event)));
        userDTO.setEventsMainOrganizer(eventsMainOrganizer);

        return userDTO;
    }

    public static UpdateUserDTO convertUserToUpdateUserDTO(User user) {
        UpdateUserDTO userDTO = new UpdateUserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setNickname(user.getNickname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles((List<Role>) user.getRoles());
        userDTO.setEnabled(user.isEnabled());
        return userDTO;
    }

    public static User convertUpdateUserDTOToUser(UpdateUserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
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
        userAuthenticationDTO.setId(user.getId());
        userAuthenticationDTO.setFirstName(user.getFirstName());
        userAuthenticationDTO.setLastName(user.getLastName());
        userAuthenticationDTO.setEmail(user.getEmail());
        userAuthenticationDTO.setEnabled(user.isEnabled());
        userAuthenticationDTO.setLocalDateTime(user.getLocalDateTime());
        userAuthenticationDTO.setNickname(user.getNickname());
        userAuthenticationDTO.setRoles((List<Role>) user.getRoles());
        return userAuthenticationDTO;
    }

    public static EventDTO convertEventToEventDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setFirstYear(event.getFirstYear());
        eventDTO.setName(event.getName());
        if (event.getMainOrganizer() != null)
            eventDTO.setMainOrganizer(DTOConverter.convertUserToUpdateUserDTO(event.getMainOrganizer()));
        else
            eventDTO.setMainOrganizer(null);
        List<CoreTeamMemberDTO> coreTeamMemberDTOS = new ArrayList<>();
        event.getCoreTeamMembers().forEach(coreTeamMember -> coreTeamMemberDTOS.add(DTOConverter.convertCoreTeamMemberToCoreTeamMemberDTO(coreTeamMember)));
        eventDTO.setCoreTeamMembers(coreTeamMemberDTOS);
        return eventDTO;
    }

    public static Event convertEventDTOToEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setFirstYear(eventDTO.getFirstYear());
        event.setDescription(eventDTO.getDescription());
        return event;
    }

    public static EventUserDTO convertEventToEventUserDTO(Event event) {
        EventUserDTO eventUserDTO = new EventUserDTO();
        eventUserDTO.setDescription(event.getDescription());
        eventUserDTO.setFirstYear(event.getFirstYear());
        eventUserDTO.setName(event.getName());
        return eventUserDTO;
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

    public static UserCategoryDTO convertUserCategoryToUserCategoryDTO(UserCategory userCategory) {
        UserCategoryDTO userCategoryDTO = new UserCategoryDTO();
        userCategoryDTO.setGrade(userCategory.getGrade());
        userCategoryDTO.setCategoryName(userCategory.getCategory().getName());
        return userCategoryDTO;
    }

    public static DesiredUserCategoryDTO convertDesiredUserCategoryToDesiredUserCategoryDTO(DesiredUserCategory desiredUserCategory) {
        DesiredUserCategoryDTO desiredUserCategoryDTO = new DesiredUserCategoryDTO();
        desiredUserCategoryDTO.setGrade(desiredUserCategory.getGrade());
        desiredUserCategoryDTO.setCategoryName(desiredUserCategory.getCategory().getName());
        return desiredUserCategoryDTO;
    }

    public static CoreTeamMemberDTO convertCoreTeamMemberToCoreTeamMemberDTO(CoreTeamMember coreTeamMember) {
        CoreTeamMemberDTO coreTeamMemberDTO = new CoreTeamMemberDTO();
        coreTeamMemberDTO.setPosition(coreTeamMember.getPosition());
        coreTeamMemberDTO.setEvent(DTOConverter.convertEventToEventUserDTO(coreTeamMember.getEvent()));
        coreTeamMemberDTO.setUserEvent(DTOConverter.convertUserToUpdateUserDTO(coreTeamMember.getUser()));
        return coreTeamMemberDTO;
    }

    public static NotificationCategoryDTO convertNotificationCategoryToNotificationCategoryDTO(NotificationCategory notificationCategory) {
        NotificationCategoryDTO notificationCategoryDTO = new NotificationCategoryDTO();
        notificationCategoryDTO.setCategory(notificationCategory.getCategory().getName());
        notificationCategoryDTO.setGrade(notificationCategory.getGrade());
        return notificationCategoryDTO;
    }

    public static NotificationDTO convertNotificationToNotificationDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(notification.getId());
        notificationDTO.setAuthor(DTOConverter.convertUserToUpdateUserDTO(notification.getAuthor()));
        notificationDTO.setText(notification.getText());
        List<NotificationCategoryDTO> notificationCategoryDTOS = new ArrayList<>();
        notification.getNotificationCategories().forEach(notificationCategory ->
                notificationCategoryDTOS.add(DTOConverter.convertNotificationCategoryToNotificationCategoryDTO
                        (notificationCategory)));
        notificationDTO.setNotificationCategories(notificationCategoryDTOS);
        return notificationDTO;
    }

    public static SubscriptionDTO convertSubscriptionToSubscriptionDTO(Subscription subscription) {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setAuth(subscription.getAuth());
        subscriptionDTO.setEndpoint(subscription.getEndpoint());
        subscriptionDTO.setExpirationTime(subscription.getExpirationTime());
        subscriptionDTO.setP256dh(subscription.getP256dh());
        return subscriptionDTO;
    }
}
