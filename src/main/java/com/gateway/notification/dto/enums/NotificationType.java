package com.gateway.notification.dto.enums;

import lombok.Getter;

@Getter
public enum NotificationType {
    PUSH_BANNER(ChannelType.PUSH),
    PUSH_IMAGE(ChannelType.PUSH),
    EMAIL_TEMPLATE(ChannelType.EMAIL),
    EMAIL_INVITE(ChannelType.EMAIL),
    IN_APP_CHAT(ChannelType.IN_APP),
    IN_APP_SYSTEM(ChannelType.IN_APP);

    private final ChannelType channelType;

    NotificationType(ChannelType channelType) {
        this.channelType = channelType;
    }
}
