package com.gateway.notification.factory.template.push.type;

import com.gateway.notification.dto.Data;
import com.gateway.notification.factory.NotificationAbstract;
import com.gateway.notification.factory.template.push.FirebaseComm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SimplePushNotificationImpl extends NotificationAbstract<Data> {

    private final FirebaseComm firebaseComm;

    private List<String> getResolvedValuesSomehow() {
        // Replace this with real logic — e.g., fetch from DB, request context, etc.
        return List.of("User", "System");
    }

    @Override
    public void send(String to, String notificationTemplateId, List<String> titleVarValues, List<String> descVarValues, Data data) {
        firebaseComm.sendToUser(to, notificationTemplateId, titleVarValues, descVarValues, data);
    }

    @Override
    public void send(String to, String notificationTemplateId, List<String> titleVarValues, List<String> descVarValues) {
        firebaseComm.sendToUser(to, notificationTemplateId, titleVarValues, descVarValues);
    }
}
