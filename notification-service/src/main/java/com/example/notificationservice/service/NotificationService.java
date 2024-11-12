package com.example.notificationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.notificationservice.Notification;
import com.example.notificationservice.repository.NotificationRepository;

@Service
public class NotificationService {

  @Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private JavaMailSender mailSender;

  public void sendEmailNotification(String toEmail, String subject, String message) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(toEmail);
    email.setSubject(subject);
    email.setText(message);
    email.setFrom("seu-email@example.com");

    mailSender.send(email);
  }

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
