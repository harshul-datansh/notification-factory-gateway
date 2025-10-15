package com.gateway.notification.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseDetailsEntity {

    @Id
    private Long id;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime creationDate;
    private String token;
    private String deviceNumber;
    private boolean active;
    private String userId;
    private String timeZoneOffset;
    private Integer badge;
    private String localeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirebaseDetailsEntity that = (FirebaseDetailsEntity) o;
        return Objects.equals(deviceNumber, that.deviceNumber)
                && Objects.equals(token, that.token)
                && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceNumber, token, userId);
    }
}
