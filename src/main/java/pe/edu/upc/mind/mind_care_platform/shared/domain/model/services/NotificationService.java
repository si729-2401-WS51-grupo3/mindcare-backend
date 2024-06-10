package pe.edu.upc.mind.mind_care_platform.shared.domain.model.services;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendNotification(String message, String recipient) {

        System.out.println("Enviando notificación a " + recipient + ": " + message);
    }
}