package com.example.taskservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.taskservice.entity.Task;
import com.example.taskservice.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  private final RestTemplate restTemplate = new RestTemplate();
  private final String notificationServiceUrl = "http://localhost:8082/notifications/internal";

  public Task createTask(String description, LocalDateTime notificationTime) {
    Task task = new Task();
    task.setDescription(description);
    task.setNotificationTime(notificationTime);
    return taskRepository.save(task);
  }

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  @Scheduled(fixedRate = 60000)
  public void checkAndSendNotifications() {
    List<Task> tasksToNotify = taskRepository.findByNotifiedFalseAndNotificationTimeBefore(LocalDateTime.now());
    for (Task task : tasksToNotify) {
      // Enviar notificação para o Notification-Service
      String message = "Lembrete: " + task.getDescription();
      restTemplate.postForObject(notificationServiceUrl, message, String.class);
      // Marcar a tarefa como notificada
      task.setNotified(true);
      taskRepository.save(task);
    }

  }
}
