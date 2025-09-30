package com.gateway.notification.dto;

import com.gateway.notification.dto.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDTO {
    private NotificationType type;
    private String title;
    private String description;
}
