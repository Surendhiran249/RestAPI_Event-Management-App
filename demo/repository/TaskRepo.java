package event_management.demo.repository;

import event_management.demo.entity.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByEventId(Long eventId);
    List<Task> findByAssignedToId(Long userId);
    Page<Task> findByAssignedToId(Long userId, Pageable pageable);
}
