package ngo.best.server.controller;

import ngo.best.server.model.dto.CoreTeamMemberDTO;
import ngo.best.server.model.dto.EventDTO;
import ngo.best.server.model.entity.Event;
import ngo.best.server.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/events")
@CrossOrigin
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<Event>> findAll() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<Event> saveEvent(@RequestBody EventDTO eventDTO) {

        Event savedEvent = eventService.save(eventDTO);

        if (savedEvent == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedEvent.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(savedEvent);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") Long eventId) {

        eventService.delete(eventId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{eventId}/coreTeam/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT') or hasRole('ROLE_MAIN_ORGANIZER')")
    public ResponseEntity<Event> deleteCoreTeamMember(@PathVariable("eventId") Long eventId,
                                                      @PathVariable("userId") Long userId) {

        Event updatedEvent = eventService.deleteCoreTeamMember(eventId, userId);

        if(updatedEvent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedEvent);
        }
    }

    @PostMapping("/{eventId}/coreTeam/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT') or hasRole('ROLE_MAIN_ORGANIZER')")
    public ResponseEntity<Event> addCoreTeamMember(@PathVariable("eventId") Long eventId,
                                                   @PathVariable("userId") Long userId,
                                                      @RequestBody String position) {

        Event updatedEvent = eventService.addCoreTeamMember(eventId, userId, position);

        if(updatedEvent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedEvent);
        }
    }

    @PutMapping("/{eventId}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<Event> updateEvent(@PathVariable("eventId") Long eventId,
                                                 @RequestBody EventDTO eventDTO) {

        Event updatedEvent = eventService.updateEvent(eventId, eventDTO);

        if(updatedEvent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedEvent);
        }
    }

    @PutMapping("/{eventId}/mainOrganizer/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<Event> updateMainOrganizer(@PathVariable("eventId") Long eventId,
                                                     @PathVariable("userId") Long userId) {

        Event updatedEvent = eventService.updateMainOrganizer(eventId, userId);

        if(updatedEvent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedEvent);
        }
    }
}
