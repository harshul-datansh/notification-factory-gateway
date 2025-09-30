package com.gateway.notification.factory;

import java.util.List;

public interface NotificationInterface {
    void send(String to, NotificationTemplate message,
              List<String> titleVarValues, List<String> descVarValues);
}
