package com.example.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notificationservice.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
