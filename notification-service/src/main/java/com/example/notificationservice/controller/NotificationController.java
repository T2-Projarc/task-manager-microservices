package com.example.notificationservice.controller;

import com.example.notificationservice.Notification;
import com.example.notificationservice.repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

  @Autowired
  private NotificationRepository notificationRepository;

  @PostMapping("/internal")
  public Notification createInternalNotification(@RequestBody String message) {
    Notification notification = new Notification();
    notification.setMessage(message);
    notification.setType("INFO");
    notification.setRead(false);
    return notificationRepository.save(notification);
  }

  @GetMapping("/all")
  public List<Notification> getAllNotifications() {
    return notificationRepository.findAll();
  }

  @PutMapping("/internal/{id}/read")
  public String markNotificationAsRead(@PathVariable Long id) {
    Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Notificação não encontrada."));
    notification.setRead(true);
    notificationRepository.save(notification);
    return "Notificação marcada como lida!";
  }
}
