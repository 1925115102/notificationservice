package edu.iu.c322.project.notificationservice.repository;

import edu.iu.c322.project.notificationservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
