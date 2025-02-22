package event_management.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(unique = true, nullable = false)
    private String ticketCode;

    @Column(nullable = false)
    private LocalDateTime issueDate;

    @Column(nullable = false)
    private String status; // VALID, CANCELED, EXPIRED

    @Column(nullable = false)
    private Double price;

    @PrePersist
    public void generateTicketCode() {
        this.ticketCode = UUID.randomUUID().toString();
        this.issueDate = LocalDateTime.now();
        this.status = "VALID";
    }
}
