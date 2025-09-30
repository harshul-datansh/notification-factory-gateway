package com.gateway.notification.service;

import com.gateway.notification.dto.NotificationRequestDTO;
import com.gateway.notification.dto.enums.NotificationType;
import com.gateway.notification.entity.NotificationTemplateEntity;
import com.gateway.notification.factory.NotificationFactory;
import com.gateway.notification.factory.NotificationTemplate;
import com.gateway.notification.repository.NotificationTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationTemplateRepository repository;

    @Autowired
    private NotificationFactory factory;

    public NotificationTemplateEntity createNotification(NotificationRequestDTO dto) {
        NotificationTemplate template = factory.getByType(dto.getType());

        NotificationTemplateEntity entity = new NotificationTemplateEntity();
        entity.setType(dto.getType());
        entity.setTitle(dto.getTitle() != null ? dto.getTitle() : template.getTitle());
        entity.setDescription(dto.getDescription() != null ? dto.getDescription() : template.getDescription());
        entity.setCreatedAt(LocalDateTime.now());

        return repository.save(entity);
    }

    public List<NotificationTemplateEntity> getAll(NotificationType type) {
        return repository.findAllByType(type);
    }

    public Optional<NotificationTemplateEntity> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    public NotificationTemplateEntity update(Long id, NotificationRequestDTO dto) {
        NotificationTemplateEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        return repository.save(existing);
    }
}
