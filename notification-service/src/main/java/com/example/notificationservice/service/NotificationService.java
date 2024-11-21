package com.example.notificationservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.notificationservice.entity.Notification;
import com.example.notificationservice.repository.NotificationRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import io.jsonwebtoken.io.Decoders;

@Service
public class NotificationService {

  @Autowired
  private NotificationRepository notificationRepository;

  private static final String SECRET_KEY = "wK8gH3Dh0JUZK+GkUP0rP+lPSYwSLJJxQlX6DYwIurY=";
  private final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

  public Notification createNotification(String message, String type, String username) {
    Notification notification = new Notification();
    notification.setMessage(message);
    notification.setType(type);
    notification.setRead(false);
    notification.setUsername(username);
    return notificationRepository.save(notification);
  }

  public List<Notification> getNotificationsByUsername(String username) {
    return notificationRepository.findByUsername(username);
  }

  public void markAsRead(Long id) {
    Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
    notification.setRead(true);
    notificationRepository.save(notification);
  }

  

  public String extractUsernameFromToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      String token = header.substring(7);
      Claims claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token)
          .getBody();
      return claims.getSubject();
    } else {
      throw new RuntimeException("Token JWT não encontrado ou inválido");
    }
  }
}
