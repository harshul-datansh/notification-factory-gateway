package com.gateway.notification.factory;

import com.gateway.notification.dto.enums.NotificationType;
import com.gateway.notification.factory.template.email.type.InviteEmailNotificationImpl;
import com.gateway.notification.factory.template.email.type.SimpleEmailNotificationImpl;
import com.gateway.notification.factory.template.inapp.type.SimpleInAppNotificationImpl;
import com.notificationsystem.notificationsystem.factory.template.push.type.BannerPushNotificationImpl;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class NotificationFactory {

    private static final Map<NotificationType, Supplier<NotificationTemplate>> registry = new HashMap<>();

    static {
        registry.put(NotificationType.PUSH_BANNER, BannerPushNotificationImpl::new);
        registry.put(NotificationType.EMAIL_TEMPLATE, SimpleEmailNotificationImpl::new);
        registry.put(NotificationType.EMAIL_INVITE, InviteEmailNotificationImpl::new);
        registry.put(NotificationType.INAPP_SYSTEM, SimpleInAppNotificationImpl::new);
    }

    public NotificationTemplate getByType(NotificationType type) {
        Supplier<NotificationTemplate> supplier = registry.get(type);
        if (supplier != null) return supplier.get();
        throw new IllegalArgumentException("Unsupported notification type: " + type);
    }
}
