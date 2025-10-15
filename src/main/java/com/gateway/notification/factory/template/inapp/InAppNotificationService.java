package com.gateway.notification.factory.template.inapp;

import com.gateway.notification.entity.InAppNotificationEntity;
import com.gateway.notification.entity.NotificationTemplateEntity;
import com.gateway.notification.repository.InAppNotificationRepository;
import com.gateway.notification.repository.NotificationTemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.gateway.notification.util.NotificationUtil.listToCommaSeparatedString;

@Service
@AllArgsConstructor
public class InAppNotificationService {

    private final NotificationTemplateRepository notificationTemplateRepository;
    private final InAppNotificationRepository inAppNotificationRepository;

    public void saveInAppNotification(String to, String notificationTemplateId, List<String> titleVarValues, List<String> descVarValues) {
        Long templateId = Long.parseLong(notificationTemplateId);
        NotificationTemplateEntity message = notificationTemplateRepository.findById(templateId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid template ID: " + notificationTemplateId));

        InAppNotificationEntity inAppNotification = InAppNotificationEntity.builder()
            .toUser(to)
            .template(message)
            .titleDynamicVarValues(listToCommaSeparatedString(titleVarValues))
            .descDynamicVarValues(listToCommaSeparatedString(descVarValues))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
//                .createdBy("system") replace with actual user -> security context . get current logged in user
//                .updatedBy("system")
            .build();

        inAppNotificationRepository.save(inAppNotification);
    }
}
