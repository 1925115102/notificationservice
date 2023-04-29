package edu.iu.c322.project.notificationservice.controller;

import edu.iu.c322.project.notificationservice.model.ParentNotificationObserver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private final ParentNotificationObserver parentNotificationObserver;

    public NotificationController(ParentNotificationObserver parentNotificationObserver) {
        this.parentNotificationObserver = parentNotificationObserver;
    }

    @GetMapping("/update")
    public ResponseEntity<String> updateCourses() {
        String result = parentNotificationObserver.update();
        return ResponseEntity.ok(result);
    }
}
