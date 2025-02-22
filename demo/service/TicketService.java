package event_management.demo.service;

import event_management.demo.entity.Booking;
import event_management.demo.entity.Ticket;
import event_management.demo.repository.BookingRepo;
import event_management.demo.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepo ticketRepository;

    @Autowired
    private BookingRepo bookingRepository;

    public Ticket generateTicket(Long bookingId, Double price) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            Ticket ticket = new Ticket();
            ticket.setBooking(booking.get());
            ticket.setPrice(price);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket getTicketByCode(String code) {
        return ticketRepository.findByTicketCode(code).orElse(null);
    }

    public String cancelTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket != null) {
            ticket.setStatus("CANCELED");
            ticketRepository.save(ticket);
            return "Ticket Canceled Successfully!";
        }
        return "Ticket Not Found!";
    }
}
