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
    LocalDateTime now = LocalDateTime.now();

    List<Task> tasksToNotify = taskRepository.findByNotifiedFalseAndNotificationTimeAfter(now.minusMinutes(10));
    for (Task task : tasksToNotify) {
      long minutesUntilTask = java.time.Duration.between(now, task.getNotificationTime()).toMinutes();

      if (minutesUntilTask <= 10 && !task.isNotified10MinutesBefore()) {
        sendNotification(task, "Lembrete: Faltam 10 minutos para sua tarefa!");
        task.setNotified10MinutesBefore(true);
      }
      if (minutesUntilTask <= 5 && !task.isNotified5MinutesBefore()) {
        sendNotification(task, "Lembrete: Faltam 5 minutos para sua tarefa!");
        task.setNotified5MinutesBefore(true);
      }
      if (minutesUntilTask <= 0 && !task.isNotifiedOnTime()) {
        sendNotification(task, "Lembrete: Sua tarefa está no horário!");
        task.setNotifiedOnTime(true);
        task.setNotified(true); // Marca a tarefa como completamente notificada
      }
      taskRepository.save(task); // Salva as atualizações de notificação
    }
  }

  private void sendNotification(Task task, String message) {
    restTemplate.postForObject(notificationServiceUrl, message, String.class);
  }
}
