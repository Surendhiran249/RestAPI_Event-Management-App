package event_management.demo.service;

import event_management.demo.entity.Event;
import event_management.demo.entity.Task;
import event_management.demo.entity.User;
import event_management.demo.repository.EventRepo;
import event_management.demo.repository.TaskRepo;
import event_management.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepository;

    @Autowired
    private EventRepo eventRepository;

    @Autowired
    private UserRepo userRepository;

    public Task createTask(String taskName, Long eventId, Long userId) {
        Optional<Event> event = eventRepository.findById(eventId);
        Optional<User> user = userRepository.findById(userId);

        if (event.isPresent() && user.isPresent()) {
            Task task = new Task();
            task.setTaskName(taskName);
            task.setEvent(event.get());
            task.setAssignedTo(user.get());
            return taskRepository.save(task);
        }
        return null;
    }

    public List<Task> getTasksByEvent(Long eventId) {
        return taskRepository.findByEventId(eventId);
    }

    public List<Task> getTasksByUser(Long userId) {
        return taskRepository.findByAssignedToId(userId);
    }

    public Task updateTaskStatus(Long id, String status) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setStatus(status);
            if ("COMPLETED".equals(status)) {
                task.setCompletedDate(java.time.LocalDateTime.now());
            }
            return taskRepository.save(task);
        }
        return null;
    }

    public String deleteTask(Long id) {
        taskRepository.deleteById(id);
        return "Task Deleted Successfully!";
    }

    public Page<Task> getTasksByUser(Long userId, Pageable pageable) {
        return taskRepository.findByAssignedToId(userId, pageable);
    }

    public List<Task> getTasksSorted(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
        return taskRepository.findAll(sort);
    }
}
