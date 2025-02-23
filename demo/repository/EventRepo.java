package event_management.demo.repository;

import java.util.List;

// import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import event_management.demo.entity.Event;

public interface EventRepo extends JpaRepository<Event, Long> {
    Page<Event> findAll(Pageable pageable);

    @Query("SELECT e FROM Event e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
       "OR LOWER(e.description) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
       "OR LOWER(e.location) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Event> searchEvents(@Param("keyword") String keyword);
}
