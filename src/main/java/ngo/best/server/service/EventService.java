package ngo.best.server.service;

import ngo.best.server.model.dto.EventDTO;
import ngo.best.server.model.entity.*;
import ngo.best.server.repository.CoreTeamMemberRepository;
import ngo.best.server.repository.EventRepository;
import ngo.best.server.repository.UserRepository;
import ngo.best.server.utils.DTOConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Ioana
 */

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CoreTeamMemberRepository coreTeamMembersRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository, CoreTeamMemberRepository coreTeamMembersRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.coreTeamMembersRepository = coreTeamMembersRepository;
    }

    public List<Event> findAll() { return eventRepository.findAll(); }

    public List<EventDTO> findAllEventDTO() {
        List<EventDTO> eventDTOS = new ArrayList<>();
        for (Event event : eventRepository.findAll()) {
            eventDTOS.add(DTOConverter.convertEventToEventDTO(event));
        }
        return eventDTOS;
    }

    public Event findById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);
    }

    public Event save(EventDTO eventDTO) {
        Event event = DTOConverter.convertEventDTOToEvent(eventDTO);
        return eventRepository.save(event);
    }

    public Event delete(Long eventId) {
        final Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()) {
            eventRepository.delete(event.get());
            return event.get();
        }
        return null;
    }

    public Event updateMainOrganizer(Long eventId, Long userId) {
        final Optional<Event> event = eventRepository.findById(eventId);
        final Optional<User> user = userRepository.findById(userId);
        if(event.isPresent() && user.isPresent()) {
            event.get().setMainOrganizer(user.get());
            eventRepository.save(event.get());
            return event.get();
        }
        return null;
    }

    public Event deleteCoreTeamMember(Long eventId, Long userId) {
        final Optional<Event> event = eventRepository.findById(eventId);
        final Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && event.isPresent()) {
            CoreTeamMemberId coreTeamMemberId = new CoreTeamMemberId();
            coreTeamMemberId.setEvent(event.get());
            coreTeamMemberId.setUser(user.get());
            Optional<CoreTeamMember> coreTeamMember = coreTeamMembersRepository.findById(coreTeamMemberId);
            coreTeamMember.ifPresent(coreTeamMembersRepository::delete);
            return event.get();
        }
        return null;
    }
    public Event addCoreTeamMember(Long eventId, Long userId, String position) {
        final Optional<Event> event = eventRepository.findById(eventId);
        final Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && event.isPresent()) {
            CoreTeamMember coreTeamMember = new CoreTeamMember();
            coreTeamMember.setUser(user.get());
            coreTeamMember.setEvent(event.get());
            coreTeamMember.setPosition(position);
            coreTeamMembersRepository.save(coreTeamMember);
            return event.get();
        }
        return null;
    }

    public Event updateEvent(Long eventId, EventDTO eventDTO) {
        final Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()) {
            event.get().setName(eventDTO.getName());
            event.get().setFirstYear(eventDTO.getFirstYear());
            event.get().setDescription(eventDTO.getDescription());
            eventRepository.save(event.get());
            return event.get();
        }
        return null;
    }
}
