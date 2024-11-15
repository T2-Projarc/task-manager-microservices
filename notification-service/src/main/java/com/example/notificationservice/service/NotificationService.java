package com.example.notificationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notificationservice.entity.Notification;
import com.example.notificationservice.repository.NotificationRepository;

@Service
public class NotificationService {

  @Autowired
  private NotificationRepository notificationRepository;

  public Notification createNotification(String message, String type) {
    Notification notification = new Notification();
    notification.setMessage(message);
    notification.setType(type);
    notification.setRead(false);
    return notificationRepository.save(notification);
  }

  public List<Notification> getAllNotifications() {
    return notificationRepository.findAll();
  }

  public void markAsRead(Long id) {
    Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
    notification.setRead(true);
    notificationRepository.save(notification);
  }
}
