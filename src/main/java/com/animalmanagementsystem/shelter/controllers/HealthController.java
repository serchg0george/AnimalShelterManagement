package com.animalmanagementsystem.shelter.controllers;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.searchs.HealthSearchRequest;
import com.animalmanagementsystem.shelter.services.HealthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/health")
@CrossOrigin(origins = "http://localhost:4200/")
public class HealthController {
    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<HealthDto>> searchHealth(@RequestBody HealthSearchRequest request) {
        List<HealthDto> healthDtoList = healthService.findHealthByCriteria(request);
        return ResponseEntity.ok(healthDtoList);
    }

    @PostMapping
    public ResponseEntity<String> createHealth(@Valid @RequestBody HealthDto healthDto) {
        healthService.createHealth(healthDto);
        return ResponseEntity.ok("Health created");
    }

    @GetMapping
    public ResponseEntity<List<HealthDto>> getAllHealth() {
        return new ResponseEntity<>(healthService.getAllHealths(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<HealthDto> getHealthById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(healthService.getHealthById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateHealth(@PathVariable("id") Long id,
                                               @Valid @RequestBody HealthDto healthDto) {
        healthService.updateHealth(healthDto, id);
        return ResponseEntity.ok("Health updated");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHealth(@PathVariable("id") Long id) {
        healthService.deleteHealth(id);
        return ResponseEntity.ok("Health deleted");
    }
}
