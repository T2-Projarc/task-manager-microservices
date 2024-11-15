package com.example.taskservice.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String description;
  private LocalDateTime notificationTime;
  private boolean notified = false;
  private boolean notified10MinutesBefore;
  private boolean notified5MinutesBefore;
  private boolean notifiedOnTime;

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getNotificationTime() {
    return notificationTime;
  }

  public void setNotificationTime(LocalDateTime notificationTime) {
    this.notificationTime = notificationTime;
  }

  public boolean isNotified() {
    return notified;
  }

  public void setNotified(boolean notified) {
    this.notified = notified;
  }

  public boolean isNotified10MinutesBefore() {
    return notified10MinutesBefore;
  }

  public void setNotified10MinutesBefore(boolean notified10MinutesBefore) {
    this.notified10MinutesBefore = notified10MinutesBefore;
  }

  public boolean isNotified5MinutesBefore() {
    return notified5MinutesBefore;
  }

  public void setNotified5MinutesBefore(boolean notified5MinutesBefore) {
    this.notified5MinutesBefore = notified5MinutesBefore;
  }

  public boolean isNotifiedOnTime() {
    return notifiedOnTime;
  }

  public void setNotifiedOnTime(boolean notifiedOnTime) {
    this.notifiedOnTime = notifiedOnTime;
  }
}
