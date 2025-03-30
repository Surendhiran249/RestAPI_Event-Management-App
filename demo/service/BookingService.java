package event_management.demo.service;

import event_management.demo.entity.Booking;
import event_management.demo.entity.Event;
import event_management.demo.entity.User;
import event_management.demo.repository.BookingRepo;
import event_management.demo.repository.EventRepo;
import event_management.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepo bookingRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private EventRepo eventRepository;

public Booking createBooking(Long userId, Long eventId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

    Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new RuntimeException("Event not found"));

    Booking booking = new Booking();
    booking.setUser(user); // ✅ Explicitly set user
    booking.setEvent(event); // ✅ Explicitly set event
    booking.setBookingDate(LocalDateTime.now());
    booking.setStatus("PENDING");

    Booking savedBooking = bookingRepository.save(booking);

    // ✅ Ensure user and event are loaded in response
    savedBooking.getUser().getUsername();
    savedBooking.getEvent().getName();

    return savedBooking;
}

    

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getBookingsByEvent(Long eventId) {
        return bookingRepository.findByEventId(eventId);
    }

    public Booking updateBooking(Long id, String status) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus(status);
            return bookingRepository.save(booking);
        }
        return null;
    }

    public String deleteBooking(Long id) {
        bookingRepository.deleteById(id);
        return "Booking Deleted Successfully!";
    }


    public Page<Booking> getBookingsByUser(Long userId, Pageable pageable) {
        return bookingRepository.findByUserId(userId, pageable);
    }

    public Page<Booking> getBookingsByEvent(Long eventId, Pageable pageable) {
        return bookingRepository.findByEventId(eventId, pageable);
    }

    public List<Booking> getBookingsSorted(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
        return bookingRepository.findAll(sort);
    }
}
