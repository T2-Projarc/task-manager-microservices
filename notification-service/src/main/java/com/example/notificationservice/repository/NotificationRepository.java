package com.example.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.notificationservice.entity.Notification;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
  List<Notification> findByUsername(String username);
}
