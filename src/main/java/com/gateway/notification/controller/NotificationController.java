package com.gateway.notification.controller;

import com.gateway.notification.dto.NotificationRequestDTO;
import com.gateway.notification.dto.enums.NotificationType;
import com.gateway.notification.entity.NotificationTemplateEntity;
import com.gateway.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @PostMapping("/admin")
    public ResponseEntity<NotificationTemplateEntity> create(@RequestBody NotificationRequestDTO dto) {
        return ResponseEntity.ok(service.createNotification(dto));
    }

    @GetMapping("/byType")
    public ResponseEntity<List<NotificationTemplateEntity>> getAll(NotificationType type) {
        return ResponseEntity.ok(service.getAll(type));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<NotificationTemplateEntity> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<NotificationTemplateEntity> update(
            @PathVariable Long id,
            @RequestBody NotificationRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
