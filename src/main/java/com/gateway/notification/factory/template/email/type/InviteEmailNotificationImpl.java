package com.gateway.notification.factory.template.email.type;

import com.gateway.notification.factory.NotificationTemplate;
import com.gateway.notification.factory.template.email.EmailNotification;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.gateway.notification.util.NotificationUtil.resolveDynamicVarWithValues;

@Slf4j
public class InviteEmailNotificationImpl extends NotificationTemplate implements EmailNotification {

    public InviteEmailNotificationImpl() {
    }

    public InviteEmailNotificationImpl(String title, String description, List<String> titleDynamicVarNames, List<String> descDynamicVarNames) {
        super(title, description, titleDynamicVarNames, descDynamicVarNames);
    }

    @Override
    public String getTitle() {
        return "Invited Email Title: %s1";
    }

    @Override
    public String getDescription() {
        return "You have been invited by %s1 to join %s2.";
    }

    @Override
    public void send(String to, NotificationTemplate message, List<String> titleVarValues, List<String> descVarValues) {
        if (message == null) {
            throw new IllegalArgumentException("NotificationTemplate must not be null.");
        }

        // Resolve title
        String resolvedTitle = message.getTitle();
        List<String> titleVars = message.getTitleDynamicVarNames();
        resolvedTitle = resolveDynamicVarWithValues(
                titleVars,
                titleVarValues,
                "Title variables and values must not be null.",
                "Mismatch in number of title variable names and values.",
                resolvedTitle
        );

        // Resolve description
        String resolvedDescription = message.getDescription();
        List<String> descVars = message.getDescDynamicVarNames();
        resolvedDescription = resolveDynamicVarWithValues(
                descVars,
                descVarValues,
                "Description variables and values must not be null.",
                "Mismatch in number of description variable names and values.",
                resolvedDescription
        );

        // Log the resolved content
        log.info("Sending invite Email to: {}", to);
        log.info("Title: {}", resolvedTitle);
        log.info("Description: {}", resolvedDescription);

        // Here you would call your actual email service
        // e.g., emailService.send(to, resolvedTitle, resolvedDescription);
    }
}
