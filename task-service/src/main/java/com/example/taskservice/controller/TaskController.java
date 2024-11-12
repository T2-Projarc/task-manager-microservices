package com.example.taskservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.taskservice.entity.Task;
import com.example.taskservice.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PostMapping("/create")
  public Task createTask(@RequestBody TaskRequest request) {
    return taskService.createTask(request.getDescription(), request.getNotificationTime());
  }

  @GetMapping("/all")
  public List<Task> getAllTasks() {
    return taskService.getAllTasks();
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
