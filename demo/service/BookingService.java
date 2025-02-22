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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepo bookingRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private EventRepo eventRepository;

    public Booking createBooking(Long userId, Long eventId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Event> event = eventRepository.findById(eventId);

        if (user.isPresent() && event.isPresent()) {
            Booking booking = new Booking();
            booking.setUser(user.get());
            booking.setEvent(event.get());
            return bookingRepository.save(booking);
        }
        return null;
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
}
