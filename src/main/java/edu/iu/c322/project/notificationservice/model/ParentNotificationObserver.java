package edu.iu.c322.project.notificationservice.model;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
@Service
public class ParentNotificationObserver implements NotificationObserver {
    private final WebClient webClient;

    public ParentNotificationObserver() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8081") // Replace with the appropriate schedule service URL
                .build();
    }

    @Override
    public String update() {
        Mono<List<Course>> courseListResponse = webClient.get()
                .uri("/api/schedule/courses") // Replace with the appropriate schedule service API endpoint
                .retrieve()
                .onStatus(status -> status.value() >= 400 && status.value() < 500, clientResponse -> Mono.error(new IllegalStateException("4xx Client Error")))
                .onStatus(status -> status.value() >= 500 && status.value() < 600, clientResponse -> Mono.error(new IllegalStateException("5xx Server Error")))
                .bodyToMono(new ParameterizedTypeReference<List<Course>>() {});

        List<Course> courses = courseListResponse.block();
        if (courses != null) {
            // Update the course list information as needed
            // ...
            return "Course list updated successfully";
        } else {
            return "Error updating course list";
        }
    }






}
