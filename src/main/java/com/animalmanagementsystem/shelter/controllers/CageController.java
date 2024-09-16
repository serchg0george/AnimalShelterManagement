package com.animalmanagementsystem.shelter.controllers;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.searchs.CageSearchRequest;
import com.animalmanagementsystem.shelter.services.CageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cage")
@CrossOrigin(origins = "http://localhost:4200/")
public class CageController {

    private final CageService cageService;

    public CageController(CageService cageService) {
        this.cageService = cageService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<CageDto>> searchCage(@RequestBody CageSearchRequest request) {
        List<CageDto> cageDtoList = cageService.findCageByCriteria(request);
        return ResponseEntity.ok(cageDtoList);
    }

    @PostMapping
    public ResponseEntity<String> createCage(@Valid @RequestBody CageDto cageDto) {
        cageService.createCage(cageDto);
        return ResponseEntity.ok("Cage created");
    }

    @GetMapping
    public ResponseEntity<List<CageDto>> getAllCages() {
        return new ResponseEntity<>(cageService.getAllCages(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CageDto> getCageById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cageService.getCageById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCage(@PathVariable("id") Long id,
                                             @Valid @RequestBody CageDto cageDto) {
        cageService.updateCage(cageDto, id);
        return ResponseEntity.ok("Cage updated");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCage(@PathVariable("id") Long id) {
        cageService.deleteCage(id);
        return ResponseEntity.ok("Cage deleted");
    }
}
