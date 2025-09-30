package com.gateway.notification.repository;

import com.gateway.notification.dto.enums.NotificationType;
import com.gateway.notification.entity.NotificationTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplateEntity, Long> {

    List<NotificationTemplateEntity> findAllByType(NotificationType type);
}
