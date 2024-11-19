package com.example.taskservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.taskservice.entity.Task;
import com.example.taskservice.service.TaskService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task API", description = "Endpoints para gerenciar tarefas")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PostMapping("/create")
  public Task createTask(@RequestBody TaskRequest request, HttpServletRequest httpRequest) {
    String username = taskService.extractUsernameFromToken(httpRequest);
    return taskService.createTask(request.getDescription(), request.getNotificationTime(), username);
  }

  @GetMapping("/all")
  public List<Task> getAllTasks(HttpServletRequest httpRequest) {
    String username = taskService.extractUsernameFromToken(httpRequest);
    return taskService.getTasksByUsername(username);
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
