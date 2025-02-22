package event_management.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String taskName;

    @Column(nullable = false)
    private String status; // PENDING, IN_PROGRESS, COMPLETED

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "assigned_to", nullable = false)
    private User assignedTo;

    @Column(nullable = false)
    private LocalDateTime assignedDate;

    @Column(nullable = true)
    private LocalDateTime completedDate;

    @PrePersist
    public void setAssignedDate() {
        this.assignedDate = LocalDateTime.now();
        this.status = "PENDING";
    }

    
}
