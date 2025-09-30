package com.gateway.notification.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class NotificationTemplate {
    private String title;
    private String description;
    private List<String> titleDynamicVarNames;
    private List<String> descDynamicVarNames;
}
