package ngo.best.server.controller;

import ngo.best.server.model.dto.NotificationDTO;
import ngo.best.server.model.dto.NotificationRequestDTO;
import ngo.best.server.model.dto.NotificationUsersDTO;
import ngo.best.server.model.dto.UserNotificationDTO;
import ngo.best.server.model.entity.Notification;
import ngo.best.server.model.entity.User;
import ngo.best.server.service.ClusteringService;
import ngo.best.server.service.NotificationService;
import ngo.best.server.service.UserService;
import ngo.best.server.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Ioana
 */

@RestController
@RequestMapping("/notifications")
@CrossOrigin
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClusteringService clusteringService;

    @GetMapping
    public ResponseEntity<List<Notification>> findAll() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @PostMapping("/send")
    public ResponseEntity<NotificationUsersDTO> sendNotification(@RequestBody NotificationRequestDTO notificationRequestDTO,
                                                                 @RequestHeader("Authorization") String jwtToken) {

        User user = userService.identifyUser(jwtToken);

        Notification savedNotification = notificationService.save(notificationRequestDTO, user);

        clusteringService.generateClusters(userService.findAll());

        List<UserNotificationDTO> selectedUserForNotification = notificationService.getSelectedUsersForNotification(savedNotification);

        NotificationUsersDTO notificationUsersDTO = new NotificationUsersDTO(
                DTOConverter.convertNotificationToNotificationDTO(savedNotification), selectedUserForNotification);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedNotification.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(notificationUsersDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<String> deleteNotification(@PathVariable("id") Long notificationId) {
        notificationService.delete(notificationId);
        return ResponseEntity.noContent().build();
    }
}
