package com.example.notificationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificationservice.entity.Notification;
import com.example.notificationservice.repository.NotificationRepository;

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
