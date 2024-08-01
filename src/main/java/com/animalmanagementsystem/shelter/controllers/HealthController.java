package com.animalmanagementsystem.shelter.controllers;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.services.HealthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/health")
public class HealthController {
    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @PostMapping
    public ResponseEntity<String> createHealth(@RequestBody HealthDto healthDto) {
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
                                               @RequestBody HealthDto healthDto) {
        healthService.updateHealth(healthDto, id);
        return ResponseEntity.ok("Health updated");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHealth(@PathVariable("id") Long id) {
        healthService.deleteHealth(id);
        return ResponseEntity.ok("Health deleted");
    }
}
