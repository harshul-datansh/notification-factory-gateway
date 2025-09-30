package com.gateway.notification.factory.template.email.type;

import com.gateway.notification.factory.NotificationTemplate;
import com.gateway.notification.factory.template.email.EmailNotification;
import com.gateway.notification.repository.NotificationTemplateRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.gateway.notification.util.NotificationUtil.resolveDynamicVarWithValues;

@Slf4j
public class SimpleEmailNotificationImpl extends NotificationTemplate implements EmailNotification {
    private NotificationTemplateRepository notificationTemplateRepository;

    public SimpleEmailNotificationImpl() {
    }

    public SimpleEmailNotificationImpl(String title, String description, List<String> titleDynamicVarNames, List<String> descDynamicVarNames) {
        super(title, description, titleDynamicVarNames, descDynamicVarNames);
    }

    @Override
    public void send(String toEmailId, NotificationTemplate message,
                     List<String> titleVarValues, List<String> descVarValues) {
        if (message == null) {
            throw new IllegalArgumentException("NotificationTemplate must not be null.");
        }

        // Extract title and dynamic vars
        String resolvedTitle = message.getTitle();
        List<String> titleVars = message.getTitleDynamicVarNames();

        resolvedTitle = resolveDynamicVarWithValues(titleVars, titleVarValues, "Title variables and values must not be null.", "Mismatch in number of title variable names and values.", resolvedTitle);

        // Extract description and dynamic vars :-
        String resolvedDescription = message.getDescription();
        List<String> descVars = message.getDescDynamicVarNames();

        // Validate description vars
        resolvedDescription = resolveDynamicVarWithValues(descVars, descVarValues, "Description variables and values must not be null.", "Mismatch in number of description variable names and values.", resolvedDescription);

        // Now send the email or notification
        // Replace this with your actual sending logic
        log.info("Sending Email:");
        log.info("Title: " + resolvedTitle);
        log.info("Description: " + resolvedDescription);

        // Example:
        // emailService.send(to, resolvedTitle, resolvedDescription);
    }
}
