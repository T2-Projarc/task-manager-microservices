package com.example.taskservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.taskservice.dto.TaskRequestDTO;
import com.example.taskservice.entity.Task;
import com.example.taskservice.service.TaskService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task API", description = "Endpoints para gerenciar tarefas")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PostMapping("/create")
  public Task createTask(@RequestBody TaskRequestDTO request, HttpServletRequest httpRequest) {
    String username = taskService.extractUsernameFromToken(httpRequest);
    return taskService.createTask(
        request.getDescription(),
        request.getNotificationTime(),
        request.getPriority(), // Adicionando prioridade
        request.getStatus(), // Adicionando status
        username);
  }

  @GetMapping("/all")
  public List<Task> getAllTasks(HttpServletRequest httpRequest) {
    String username = taskService.extractUsernameFromToken(httpRequest);
    return taskService.getTasksByUsername(username);
  }

  @PutMapping("/{id}")
  public Task updateTask(@PathVariable Long id, @RequestBody Map<String, String> updates) {
      return taskService.updateTask(id, updates);
  }  

  @DeleteMapping("/{id}")
  public String deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
    return "Task deleted successfully!";
  }

}

class TaskRequest {
  private String description;
  private LocalDateTime notificationTime;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getNotificationTime() {
    return notificationTime;
  }

  public void setNotificationTime(LocalDateTime notificationTime) {
    this.notificationTime = notificationTime;
  }
}
