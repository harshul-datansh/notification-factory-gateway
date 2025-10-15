package com.gateway.notification.factory.template.push;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gateway.notification.dto.Data;
import com.gateway.notification.dto.FireBaseTokenRequest;
import com.gateway.notification.entity.FirebaseDetailsEntity;
import com.gateway.notification.entity.NotificationTemplateEntity;
import com.gateway.notification.repository.NotificationTemplateRepository;
import com.gateway.notification.service.FirebaseDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gateway.notification.util.NotificationUtil.commaSeperatedToList;
import static com.gateway.notification.util.NotificationUtil.resolveDynamicVarWithValues;

@Slf4j
@Service
@AllArgsConstructor
public class FirebaseComm {

    private final NotificationTemplateRepository notificationTemplateRepository;
    private final FirebaseDetailsService firebaseDetailsService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendToUser(String sendToUser, String notificationTemplateId, List<String> titleVarValues, List<String> descVarValues, Data data) {
        List<FirebaseDetailsEntity> allActiveTokensForUser = firebaseDetailsService.getActiveFirebaseTokens(sendToUser);
        Map<String, Boolean> alreadySentTokens = new HashMap<>();
        NotificationTemplateEntity template = notificationTemplateRepository.findById(Long.parseLong(notificationTemplateId)).orElseThrow();

        allActiveTokensForUser.forEach(tokenEntity -> {
            if (alreadySentTokens.containsKey(tokenEntity.getToken())) {
                return;
            }
            List<String> titleDynamicVarNames = commaSeperatedToList(template.getTitleDynamicVarNames());
            List<String> descDynamicVarNames = commaSeperatedToList(template.getDescDynamicVarNames());
            String resolvedTitle = resolveDynamicVarWithValues(titleDynamicVarNames, titleVarValues, "Title variables and values must not be null.", "Mismatch in number of title variable names and values.", template.getTitle());
            String resolvedDescription = resolveDynamicVarWithValues(descDynamicVarNames, descVarValues, "Description variables and values must not be null.", "Mismatch in number of description variable names and values.", template.getDescription());
            sendNotification(tokenEntity.getToken(), resolvedTitle, resolvedDescription, data, tokenEntity);
            alreadySentTokens.put(tokenEntity.getToken(), true);
        });
    }

    public void sendToUser(String sendToUser, String notificationTemplateId, List<String> titleVarValues, List<String> descVarValues) {
        List<FirebaseDetailsEntity> allActiveTokensForUser = firebaseDetailsService.getActiveFirebaseTokens(sendToUser);
        Map<String, Boolean> alreadySentTokens = new HashMap<>();
        NotificationTemplateEntity template = notificationTemplateRepository.findById(Long.parseLong(notificationTemplateId)).orElseThrow();

        allActiveTokensForUser.forEach(tokenEntity -> {
            if (alreadySentTokens.containsKey(tokenEntity.getToken())) {
                return;
            }
            List<String> titleDynamicVarNames = commaSeperatedToList(template.getTitleDynamicVarNames());
            List<String> descDynamicVarNames = commaSeperatedToList(template.getDescDynamicVarNames());
            String resolvedTitle = resolveDynamicVarWithValues(titleDynamicVarNames, titleVarValues, "Title variables and values must not be null.", "Mismatch in number of title variable names and values.", template.getTitle());
            String resolvedDescription = resolveDynamicVarWithValues(descDynamicVarNames, descVarValues, "Description variables and values must not be null.", "Mismatch in number of description variable names and values.", template.getDescription());
            sendNotification(tokenEntity.getToken(), resolvedTitle, resolvedDescription, null, tokenEntity);
            alreadySentTokens.put(tokenEntity.getToken(), true);
        });
    }

    private void sendNotification(String token, String title, String description, Data data, FirebaseDetailsEntity tokenEntity) { // data is optional
        if (tokenEntity.getBadge() == null)
            tokenEntity.setBadge(1);
        else
            tokenEntity.setBadge(tokenEntity.getBadge() + 1);

        ResponseEntity<String> fbResponse = null;
        FireBaseTokenRequest request = FireBaseTokenRequest.fireBaseTokenRequestBuilder(token, data, tokenEntity.getBadge(), title, description);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + getFireAuthToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        fbResponse = restTemplate.postForEntity(getFireBaseHitUrl(), entity, String.class);

        String json = null;
        try {
            json = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info(json);

        log.info("toV2==" + token);
        log.info("title==" + title);
        log.info("description==" + description);
        log.info("badgeCount==" + tokenEntity.getBadge());
        log.info("fireBase Id==" + tokenEntity.getId());
        log.info("FireBase request==" + request);
        log.info("User FB Response=" + fbResponse);
        HttpStatusCode resStatus = HttpStatusCode.valueOf(200);
        if (tokenEntity != null && fbResponse.getStatusCode().equals(resStatus)) {
            firebaseDetailsService.updateFirebaseDetails(tokenEntity);
        }
    }

    private String getFireBaseHitUrl() {
        return "fireFireUrl"; // replace with actual token
    }

    private String getFireAuthToken() {
        return "fireAuthToken"; // replace with actual token
    }
}
