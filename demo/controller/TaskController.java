package event_management.demo.controller;

import event_management.demo.entity.Task;
import event_management.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create/{eventId}/{userId}")
    public Task createTask(@RequestBody Task task, @PathVariable Long eventId, @PathVariable Long userId) {
        return taskService.createTask(task.getTaskName(), eventId, userId);
    }

    @GetMapping("/event/{eventId}")
    public List<Task> getTasksByEvent(@PathVariable Long eventId) {
        return taskService.getTasksByEvent(eventId);
    }

    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUser(@PathVariable Long userId) {
        return taskService.getTasksByUser(userId);
    }

    @PutMapping("/update/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestParam String status) {
        return taskService.updateTaskStatus(id, status);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

    @GetMapping("/user/{userId}/taskspage")
    public Page<Task> getTasksByUser(@PathVariable Long userId,@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "5")int size) {
        Pageable pageable = PageRequest.of(page,size);
        return taskService.getTasksByUser(userId, pageable);
    }

    @GetMapping("/sorted")
    public List<Task> getTasksSorted(@RequestParam String field, @RequestParam String direction) {
        return taskService.getTasksSorted(field, direction);
    }
}
