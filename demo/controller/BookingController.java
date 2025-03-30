package event_management.demo.controller;

import event_management.demo.entity.Booking;
import event_management.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/create/{userId}/{eventId}")
    public Booking createBooking(@PathVariable Long userId, @PathVariable Long eventId) {
        return bookingService.createBooking(userId, eventId);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking.getTicket() != null) {
            booking.getTicket().setBooking(null); // Prevent recursion issue
        }
        return booking;
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    @GetMapping("/event/{eventId}")
    public List<Booking> getBookingsByEvent(@PathVariable Long eventId) {
        return bookingService.getBookingsByEvent(eventId);
    }

    @PutMapping("/update/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestParam(required = false) String status) {
        return bookingService.updateBooking(id, status);
    }
    

    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        return bookingService.deleteBooking(id);
    }

    @GetMapping("/user/{userId}/bookingspage")
    public Page<Booking> getBookingsByUser(@PathVariable Long userId, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookingService.getBookingsByUser(userId, pageable);
    }

    @GetMapping("/event/{eventId}/bookingspage")
    public Page<Booking> getBookingsByEvent(@PathVariable Long eventId, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookingService.getBookingsByEvent(eventId, pageable);
    }

    @GetMapping("/sorted")
    public List<Booking> getBookingsSorted(@RequestParam String field, @RequestParam String direction) {
        return bookingService.getBookingsSorted(field, direction);
    }
}
