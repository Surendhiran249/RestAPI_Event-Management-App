package event_management.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import event_management.demo.entity.Event;

public interface EventRepo extends JpaRepository<Event, Long> {
    Page<Event> findAll(Pageable pageable);
}
