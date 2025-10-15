package com.gateway.notification.repository;

import com.gateway.notification.entity.InAppNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InAppNotificationRepository extends JpaRepository<InAppNotificationEntity, Long> {
}
