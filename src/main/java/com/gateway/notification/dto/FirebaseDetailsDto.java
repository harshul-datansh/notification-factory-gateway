package com.gateway.notification.dto;

import com.gateway.notification.entity.FirebaseDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseDetailsDto {
    private String userId;
    private String token;
    private boolean isActive;

    public static FirebaseDetailsDto fromEntity(FirebaseDetailsEntity entity) {
        if (entity == null) {
            return null;
        }
        return new FirebaseDetailsDto(
                entity.getUserId(),
                entity.getToken(),
                entity.isActive()
        );
    }

    public static FirebaseDetailsEntity toEntity(FirebaseDetailsDto dto, FirebaseDetailsEntity entity) {
        if (dto == null) {
            return null;
        }
        if (entity == null) {
            entity = new FirebaseDetailsEntity();
        }
        entity.setUserId(dto.getUserId());
        entity.setToken(dto.getToken());
        entity.setActive(dto.isActive());
        return entity;
    }
}
