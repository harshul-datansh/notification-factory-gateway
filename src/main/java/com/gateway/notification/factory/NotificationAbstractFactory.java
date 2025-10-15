package com.gateway.notification.factory;

import com.gateway.notification.dto.enums.NotificationType;
import com.gateway.notification.factory.template.email.type.SimpleEmailNotificationImpl;
import com.gateway.notification.factory.template.inapp.type.SimpleInAppNotificationImpl;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
@AllArgsConstructor
public class NotificationAbstractFactory {

    private final Map<NotificationType, Supplier<NotificationAbstract>> registry = new HashMap<>();
    private final SimpleInAppNotificationImpl simpleInAppNotification;
    private final SimpleEmailNotificationImpl simpleEmailNotification;

    @PostConstruct
    private void initializeRegistry() {
        registry.put(NotificationType.EMAIL_TEMPLATE, () -> simpleEmailNotification);
        registry.put(NotificationType.IN_APP_SYSTEM, () -> simpleInAppNotification);
    }

    public NotificationAbstract getByType(NotificationType type) {
        Supplier<NotificationAbstract> supplier = registry.get(type);
        if (supplier != null) return supplier.get();
        throw new IllegalArgumentException("Unsupported notification type: " + type);
    }
}
