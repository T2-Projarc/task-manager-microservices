package com.example.notificationservice.service;

import org.springframework.stereotype.Service;

import com.netflix.discovery.converters.Auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String sendMailString(String destinatario, String assunto, String mensagem){
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(remetente);
            mail.setTo(destinatario);
            mail.setSubject(assunto);
            mail.setText(mensagem);
            javaMailSender.send(mail);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            return "Erro ao enviar email.";
        }
    }
}