package com.gateway.notification.service;

import com.gateway.notification.dto.NotificationTemplateReqDTO;
import com.gateway.notification.dto.enums.NotificationType;
import com.gateway.notification.entity.NotificationTemplateEntity;
import com.gateway.notification.repository.NotificationTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.gateway.notification.util.NotificationUtil.listToCommaSeparatedString;

@Service
public class NotificationTemplateService {

    @Autowired
    private NotificationTemplateRepository repository;

    public NotificationTemplateEntity createNotification(NotificationTemplateReqDTO dto) {

        NotificationTemplateEntity entity = new NotificationTemplateEntity();
        entity.setType(dto.getType());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setTitleDynamicVarNames(listToCommaSeparatedString(dto.getTitleVarNames()));
        entity.setDescDynamicVarNames(listToCommaSeparatedString(dto.getDescVarNames()));

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


    public NotificationTemplateEntity update(Long id, NotificationTemplateReqDTO dto) {
        NotificationTemplateEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        return repository.save(existing);
    }
}
