package com.gateway.notification.controller;

import com.gateway.notification.dto.FirebaseDetailsDto;
import com.gateway.notification.entity.FirebaseDetailsEntity;
import com.gateway.notification.service.FirebaseDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/firebase-details")
@AllArgsConstructor
public class FirebaseDetailsController {

    private final FirebaseDetailsService firebaseDetailsService;

    @GetMapping("/active")
    public ResponseEntity<List<FirebaseDetailsDto>> getActiveFirebaseDetails(String token) {
        String userId = "12345"; // fetch from security context
        List<FirebaseDetailsDto> details = firebaseDetailsService.getActiveFirebaseDetails(token, userId).stream()
                .map(FirebaseDetailsDto::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FirebaseDetailsDto> createFirebaseDetails(@RequestBody FirebaseDetailsDto firebaseDetailsDto) {
        FirebaseDetailsEntity savedEntity = firebaseDetailsService.saveFirebaseDetails(firebaseDetailsDto);
        return new ResponseEntity<>(FirebaseDetailsDto.fromEntity(savedEntity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<FirebaseDetailsDto> updateFirebaseDetails(
            @RequestBody FirebaseDetailsDto firebaseDetailsDto) {
        FirebaseDetailsEntity updatedEntity = firebaseDetailsService.updateFirebaseDetails(firebaseDetailsDto);
        if (updatedEntity != null) {
            return new ResponseEntity<>(FirebaseDetailsDto.fromEntity(updatedEntity), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFirebaseDetails(@PathVariable Long id) {
        if (firebaseDetailsService.deleteFirebaseDetails(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/mark-inactive")
    public ResponseEntity<Void> markInactive(@RequestBody FirebaseDetailsDto firebaseDetailsDto) {
        if (firebaseDetailsService.markInactive(firebaseDetailsDto.getToken())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/badge-count")
    public ResponseEntity<Void> updateBadgeCount(@RequestBody FirebaseDetailsDto firebaseDetailsDto, @RequestParam Integer count) {
        if (firebaseDetailsService.updateBadgeCount(firebaseDetailsDto, count)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
