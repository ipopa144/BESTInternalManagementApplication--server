package ngo.best.server.controller;

import ngo.best.server.model.dto.SubscriptionDTO;
import ngo.best.server.model.entity.Subscription;
import ngo.best.server.model.entity.User;
import ngo.best.server.service.SubscriptionService;
import ngo.best.server.service.UserService;
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
@RequestMapping("/subscriptions")
@CrossOrigin
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<List<Subscription>> findAll() {
        return ResponseEntity.ok(subscriptionService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Subscription> saveSubscription(@RequestBody SubscriptionDTO subscriptionDTO,
                                                         @RequestHeader("Authorization") String jwtToken) {

        User user = userService.identifyUser(jwtToken);
        Subscription savedSubscription = subscriptionService.save(subscriptionDTO, user);

        if (savedSubscription == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedSubscription.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(savedSubscription);
        }
    }
}
