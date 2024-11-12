package com.example.taskservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.taskservice.service.TaskService;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class TaskServiceApplication {

	@Autowired
	private TaskService taskService;

	public static void main(String[] args) {
		SpringApplication.run(TaskServiceApplication.class, args);
	}

	@Scheduled(fixedRate = 60000)
	public void scheduleTaskNotifications() {
		taskService.checkAndSendNotifications();
	}
}
