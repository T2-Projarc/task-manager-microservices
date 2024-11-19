package com.example.taskservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskservice.entity.Task;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByNotifiedFalseAndNotificationTimeAfter(LocalDateTime time);
  
  // Novo método para buscar tarefas por username
  List<Task> findByUsername(String username);
}
