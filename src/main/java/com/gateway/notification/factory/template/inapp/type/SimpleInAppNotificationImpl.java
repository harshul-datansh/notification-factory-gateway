package com.gateway.notification.factory.template.inapp.type;

import com.gateway.notification.dto.Data;
import com.gateway.notification.factory.NotificationAbstract;
import com.gateway.notification.factory.template.inapp.InAppNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SimpleInAppNotificationImpl extends NotificationAbstract<Data> {

    private final InAppNotificationService inAppNotificationService;

    @Override
    public void send(String to, String notificationTemplateId, List<String> titleVarValues, List<String> descVarValues, Data data) {
        inAppNotificationService.saveInAppNotification(to, notificationTemplateId, titleVarValues, descVarValues); // attach data when is relevant
    }

    @Override
    public void send(String to, String notificationTemplateId, List<String> titleVarValues, List<String> descVarValues) {
        inAppNotificationService.saveInAppNotification(to, notificationTemplateId, titleVarValues, descVarValues);
    }
}
