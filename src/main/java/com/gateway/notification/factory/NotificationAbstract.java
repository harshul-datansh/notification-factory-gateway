package com.gateway.notification.factory;

import java.util.List;

public abstract class NotificationAbstract {
    public abstract void send(
            String to,
            String notificationTemplateId,
            List<String> titleVarValues,
            List<String> descVarValues
    );
}