package event_management.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import event_management.demo.entity.Event;
import event_management.demo.service.EventService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/events")
public class EventController {
    
    @Autowired
    private EventService eventService;

    // Create Event
    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event){
        return eventService.saveEvent(event);
    }

    @PostMapping("/createbatch")
    public List<Event> saveEvents(@RequestBody List<Event> events) {
        return eventService.saveEvents(events);
    }


    // Get All Events
    @GetMapping("/all")
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    // Get Event by ID
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    // Update Event
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    // Delete Event
    @DeleteMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }

    // Get all events paginated
    @GetMapping("/eventspage")
    public Page<Event> getEventsPaginated(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "5")int size) {
        Pageable pageable = PageRequest.of(page,size);
        return eventService.getEventsPaginated(pageable);
    }

    @GetMapping("/sorted")
    public List<Event> getEventsSorted(@RequestParam String field, @RequestParam String direction) {
        return eventService.getEventsSorted(field, direction);
    }

    @GetMapping("/search")
    public List<Event> searchEvents(@RequestParam String keyword) {
        return eventService.searchEvents(keyword);
    }
}
