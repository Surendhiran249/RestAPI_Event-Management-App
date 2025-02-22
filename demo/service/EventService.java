package event_management.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import event_management.demo.entity.Event;
import event_management.demo.repository.EventRepo;

@Service
public class EventService {
    @Autowired
    private EventRepo eventRepo;

    // Create or Update an Event
    public Event saveEvent(Event event) {
        return eventRepo.save(event);
    }

    //Get All Events
    public List<Event> getAllEvents(){
        return eventRepo.findAll();
    }

    //Get Event by ID
    public Event getEventById(Long id){
        Optional<Event> event = eventRepo.findById(id);
        return event.orElse(null);
    }

    // Delete Event by ID
    public String deleteEvent(Long id) {
        if (eventRepo.existsById(id)) {
            eventRepo.deleteById(id);
            return "Event deleted successfully.";
        }
        return "Event not found.";
    }

    // Update Event
    public Event updateEvent(Long id, Event updatedEvent) {
        if (eventRepo.existsById(id)) {
            updatedEvent.setId(id);
            return eventRepo.save(updatedEvent);
        }
        return null; // Could throw an exception later
    }

    // Get all events paginated
    public Page<Event> getEventsPaginated(Pageable pageable) {
        return eventRepo.findAll(pageable);
    }
}
