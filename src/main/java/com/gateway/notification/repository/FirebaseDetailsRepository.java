package com.gateway.notification.repository;

import com.gateway.notification.entity.FirebaseDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FirebaseDetailsRepository extends JpaRepository<FirebaseDetailsEntity, Long> {
    List<FirebaseDetailsEntity> findByUserId(Long userId);
    Optional<FirebaseDetailsEntity> findByToken(String token);
    List<FirebaseDetailsEntity> findByIsActive(boolean isActive);

    List<FirebaseDetailsEntity> findByUserIdAndTokenAndIsActiveTrue(String userId, String token);

    Optional<FirebaseDetailsEntity> findByUserIdAndToken(String userId, String token);

    List<FirebaseDetailsEntity> findAllByTokenAndIsActiveTrue(String token);

    List<FirebaseDetailsEntity> findAllByUserIdAndIsActiveTrue(String userId);
}
