package com.gateway.notification.dto;

import com.gateway.notification.dto.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTemplateReqDTO {
    private NotificationType type;
    private String title;
    private String description;
    private List<String> titleVarNames;
    private List<String> descVarNames;
}
