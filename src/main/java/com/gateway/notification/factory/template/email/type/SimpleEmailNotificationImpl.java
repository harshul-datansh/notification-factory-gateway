package com.gateway.notification.factory.template.email.type;

import com.gateway.notification.entity.NotificationTemplateEntity;
import com.gateway.notification.factory.NotificationAbstract;
import com.gateway.notification.repository.NotificationTemplateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gateway.notification.util.NotificationUtil.commaSeperatedToList;
import static com.gateway.notification.util.NotificationUtil.resolveDynamicVarWithValues;

@Slf4j
@Service
@AllArgsConstructor
public class SimpleEmailNotificationImpl extends NotificationAbstract {
    private NotificationTemplateRepository notificationTemplateRepository;

    @Override
    public void send(String toEmailId, String notificationTemplateId,
                     List<String> titleVarValues, List<String> descVarValues) {
        Long templateId = Long.parseLong(notificationTemplateId);
        NotificationTemplateEntity message = notificationTemplateRepository.findById(templateId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid template ID: " + notificationTemplateId));
    
        // Extract title and dynamic vars
        String resolvedTitle = message.getTitle();
        List<String> titleVars = commaSeperatedToList(message.getTitleDynamicVarNames());
    
        resolvedTitle = resolveDynamicVarWithValues(titleVars, titleVarValues, "Title variables and values must not be null.", "Mismatch in number of title variable names and values.", resolvedTitle);
    
        // Extract description and dynamic vars :-
        String resolvedDescription = message.getDescription();
        List<String> descVars = commaSeperatedToList(message.getDescDynamicVarNames());
    
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
