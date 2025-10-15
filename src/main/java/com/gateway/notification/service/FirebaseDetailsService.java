package com.gateway.notification.service;

import com.gateway.notification.dto.FirebaseDetailsDto;
import com.gateway.notification.entity.FirebaseDetailsEntity;
import com.gateway.notification.repository.FirebaseDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FirebaseDetailsService {

    private final FirebaseDetailsRepository firebaseDetailsRepository;

    @Autowired
    public FirebaseDetailsService(FirebaseDetailsRepository firebaseDetailsRepository) {
        this.firebaseDetailsRepository = firebaseDetailsRepository;
    }

    public FirebaseDetailsEntity saveFirebaseDetails(FirebaseDetailsDto firebaseDetails) {
        FirebaseDetailsEntity existing = firebaseDetailsRepository.findByUserIdAndToken(firebaseDetails.getUserId(), firebaseDetails.getToken())
                .orElse(null);
        FirebaseDetailsEntity entity;
        if (existing == null) {
            FirebaseDetailsEntity newEntity = FirebaseDetailsEntity.builder()
                    .userId(firebaseDetails.getUserId())
                    .token(firebaseDetails.getToken())
                    .badge(0)
                    .active(true)
                    .lastModifiedDate(LocalDateTime.now())
                    .creationDate(LocalDateTime.now())
                    .build();
            entity = firebaseDetailsRepository.save(newEntity);
        } else {
            entity = FirebaseDetailsDto.toEntity(firebaseDetails, existing);
        }

        return firebaseDetailsRepository.save(entity);
    }

    public FirebaseDetailsEntity updateFirebaseDetails(FirebaseDetailsDto detailsDto) {
        FirebaseDetailsEntity firebaseDetails = firebaseDetailsRepository.findByUserIdAndToken(detailsDto.getUserId(), detailsDto.getToken())
                .orElse(null);
        if (firebaseDetails != null) {
            FirebaseDetailsEntity updated = FirebaseDetailsDto.toEntity(detailsDto, firebaseDetails);
            return firebaseDetailsRepository.save(updated);
        }
        return null;
    }

    public FirebaseDetailsEntity updateFirebaseDetails(FirebaseDetailsEntity firebaseDetails) {
        if (firebaseDetails != null) {
            return firebaseDetailsRepository.save(firebaseDetails);
        }
        return null;
    }

    public boolean deleteFirebaseDetails(Long id) {
        if (firebaseDetailsRepository.existsById(id)) {
            firebaseDetailsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<FirebaseDetailsEntity> getActiveFirebaseDetails(String token, String userId) {
        return firebaseDetailsRepository.findByUserIdAndTokenAndIsActiveTrue(userId, token);
    }

    public Optional<FirebaseDetailsEntity> getFirebaseDetailsByToken(String token) {
        return firebaseDetailsRepository.findByToken(token);
    }

    public boolean markInactive(String token) {
        List<FirebaseDetailsEntity> allTokenEntities = firebaseDetailsRepository.findAllByTokenAndIsActiveTrue(token);
        if (allTokenEntities.isEmpty()) {
            return false;
        }
        allTokenEntities.forEach(entity -> {
            entity.setActive(false);
            entity.setLastModifiedDate(LocalDateTime.now());
        });
        firebaseDetailsRepository.saveAll(allTokenEntities);
        return true;
    }

    public boolean updateBadgeCount(FirebaseDetailsDto firebaseDetailsDto, Integer count) {
        List<FirebaseDetailsEntity> allEntities = firebaseDetailsRepository.findByUserIdAndTokenAndIsActiveTrue(firebaseDetailsDto.getUserId(),
                firebaseDetailsDto.getToken());
        if (allEntities.isEmpty()) {
            return false;
        }
        allEntities.forEach(entity -> {
            entity.setBadge(count);
            entity.setLastModifiedDate(LocalDateTime.now());
        });
        firebaseDetailsRepository.saveAll(allEntities);
        return true;
    }

    public List<FirebaseDetailsEntity> getActiveFirebaseTokens(String userId) {
        return firebaseDetailsRepository.findAllByUserIdAndIsActiveTrue(userId);
    }
}
