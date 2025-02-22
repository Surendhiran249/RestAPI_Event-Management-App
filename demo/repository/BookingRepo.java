package event_management.demo.repository;

import event_management.demo.entity.Booking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByEventId(Long eventId);
    Page<Booking> findByUserId(Long userId, Pageable pageable);
    Page<Booking> findByEventId(Long eventId, Pageable pageable);
}
