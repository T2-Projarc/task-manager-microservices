package com.example.taskservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.taskservice.dto.TaskRequestDTO;
import com.example.taskservice.entity.Task;
import com.example.taskservice.repository.TaskRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.security.Key;
import io.jsonwebtoken.io.Decoders;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  private final RestTemplate restTemplate = new RestTemplate();
  private final String notificationServiceUrl = "http://localhost:8082/notifications/internal";

  // Sua chave secreta (deve ser a mesma usada no auth-service)
  private static final String SECRET_KEY = "wK8gH3Dh0JUZK+GkUP0rP+lPSYwSLJJxQlX6DYwIurY=";
  private final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

  public Task createTask(String description, LocalDateTime notificationTime, String priority, String status,
      String username) {
    Task task = new Task();
    task.setDescription(description);
    task.setNotificationTime(notificationTime);
    task.setPriority(priority); // Definindo prioridade
    task.setStatus(status); // Definindo status
    task.setUsername(username);
    return taskRepository.save(task);
  }

  public Task updateTask(Long id, Map<String, String> updates) {
    Task task = taskRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Task not found!"));

    // Atualiza apenas os campos enviados no payload
    updates.forEach((key, value) -> {
      switch (key) {
        case "description":
          task.setDescription(value);
          break;
        case "priority":
          task.setPriority(value);
          break;
        case "status":
          task.setStatus(value);
          break;
        default:
          throw new IllegalArgumentException("Campo inválido: " + key);
      }
    });

    return taskRepository.save(task);
  }

  public void deleteTask(Long id) {
    // Verifica se a tarefa existe antes de tentar excluir
    if (!taskRepository.existsById(id)) {
      throw new RuntimeException("Task not found!");
    }
    taskRepository.deleteById(id);
  }

  public List<Task> getTasksByUsername(String username) {
    return taskRepository.findByUsername(username);
  }

  public String extractUsernameFromToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      String token = header.substring(7);
      Claims claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token)
          .getBody();
      return claims.getSubject();
    } else {
      throw new RuntimeException("Token JWT não encontrado ou inválido");
    }
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
    NotificationRequest notificationRequest = new NotificationRequest();
    notificationRequest.setMessage(message);
    notificationRequest.setType("INFO");
    notificationRequest.setUsername(task.getUsername());

    restTemplate.postForObject(notificationServiceUrl, notificationRequest, String.class);
  }

  // Classe auxiliar para enviar a notificação
  class NotificationRequest {
    private String message;
    private String type;
    private String username;

    // Getters e Setters
    // ...

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }
  }
}
