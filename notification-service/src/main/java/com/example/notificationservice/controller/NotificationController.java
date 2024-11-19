package com.example.notificationservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.notificationservice.entity.Notification;
import com.example.notificationservice.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

  @Autowired
  private NotificationService notificationService;

  @PostMapping("/internal")
  public Notification createInternalNotification(@RequestBody NotificationRequest request) {
    return notificationService.createNotification(request.getMessage(), request.getType(), request.getUsername());
  }

  @GetMapping("/all")
  public List<Notification> getAllNotifications(HttpServletRequest httpRequest) {
    String username = notificationService.extractUsernameFromToken(httpRequest);
    return notificationService.getNotificationsByUsername(username);
  }

  @PutMapping("/internal/{id}/read")
  public String markNotificationAsRead(@PathVariable Long id) {
    notificationService.markAsRead(id);
    return "Notificação marcada como lida!";
  }
}

// Classe auxiliar para receber os dados da notificação
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
