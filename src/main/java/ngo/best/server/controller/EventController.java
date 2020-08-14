package ngo.best.server.controller;

import ngo.best.server.model.dto.EventDTO;
import ngo.best.server.model.entity.Event;
import ngo.best.server.service.EventService;
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
@RequestMapping("/events")
@CrossOrigin
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<EventDTO>> findAll() {
        return ResponseEntity.ok(eventService.findAllEventDTO());
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<EventDTO> saveEvent(@RequestBody EventDTO eventDTO) {

        Event savedEvent = eventService.save(eventDTO);

        if (savedEvent == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedEvent.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(DTOConverter.convertEventToEventDTO(savedEvent));
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
    public ResponseEntity<EventDTO> deleteCoreTeamMember(@PathVariable("eventId") Long eventId,
                                                      @PathVariable("userId") Long userId) {

        Event updatedEvent = eventService.deleteCoreTeamMember(eventId, userId);

        if(updatedEvent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(DTOConverter.convertEventToEventDTO(updatedEvent));
        }
    }

    @PostMapping("/{eventId}/coreTeam/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT') or hasRole('ROLE_MAIN_ORGANIZER')")
    public ResponseEntity<EventDTO> addCoreTeamMember(@PathVariable("eventId") Long eventId,
                                                   @PathVariable("userId") Long userId,
                                                      @RequestBody String position) {

        Event updatedEvent = eventService.addCoreTeamMember(eventId, userId, position);

        if(updatedEvent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(DTOConverter.convertEventToEventDTO(updatedEvent));
        }
    }

    @PutMapping("/{eventId}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable("eventId") Long eventId,
                                                 @RequestBody EventDTO eventDTO) {

        Event updatedEvent = eventService.updateEvent(eventId, eventDTO);

        if(updatedEvent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(DTOConverter.convertEventToEventDTO(updatedEvent));
        }
    }

    @PutMapping("/{eventId}/mainOrganizer/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<EventDTO> updateMainOrganizer(@PathVariable("eventId") Long eventId,
                                                     @PathVariable("userId") Long userId) {

        Event updatedEvent = eventService.updateMainOrganizer(eventId, userId);

        if(updatedEvent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(DTOConverter.convertEventToEventDTO(updatedEvent));
        }
    }
}
