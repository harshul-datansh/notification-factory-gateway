package com.gateway.notification.factory;

import java.util.List;

public abstract class NotificationAbstract<T> {
    public abstract void send(
            String to,
            String notificationTemplateId,
            List<String> titleVarValues,
            List<String> descVarValues
    );

    public abstract void send(String to, String notificationTemplateId,
                              List<String> titleVarValues, List<String> descVarValues,
                              T data);
}