package event_management.demo.controller;

import event_management.demo.entity.Ticket;
import event_management.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/generate/{bookingId}")
    public Ticket generateTicket(@PathVariable Long bookingId, @RequestParam Double price) {
        return ticketService.generateTicket(bookingId, price);
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/code/{ticketCode}")
    public Ticket getTicketByCode(@PathVariable String ticketCode) {
        return ticketService.getTicketByCode(ticketCode);
    }

    @PutMapping("/cancel/{id}")
    public String cancelTicket(@PathVariable Long id) {
        return ticketService.cancelTicket(id);
    }
}
