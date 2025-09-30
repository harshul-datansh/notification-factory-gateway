package com.gateway.notification.factory.template.inapp.type;

import com.gateway.notification.factory.NotificationTemplate;
import com.gateway.notification.factory.template.inapp.InAppNotification;
import com.gateway.notification.repository.NotificationTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SimpleInAppNotificationImpl extends NotificationTemplate implements InAppNotification {

    private final NotificationTemplateRepository notificationTemplateRepository;

    @Autowired
    public SimpleInAppNotificationImpl(NotificationTemplateRepository notificationTemplateRepository) {
        this.notificationTemplateRepository = notificationTemplateRepository;
    }

    public SimpleInAppNotificationImpl(String title, String description, List<String> titleDynamicVarNames,
                                       List<String> descDynamicVarNames) {
        super(title, description, titleDynamicVarNames, descDynamicVarNames);
        this.notificationTemplateRepository = null;
    }

    private List<String> getResolvedValuesSomehow() {
        // Replace this with real logic — e.g., fetch from DB, request context, etc.
        return List.of("User", "System");
    }

    @Override
    public void send(String to, NotificationTemplate message, List<String> titleVarValues, List<String> descVarValues) {

    }
}
