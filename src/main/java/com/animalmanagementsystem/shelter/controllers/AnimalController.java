package com.animalmanagementsystem.shelter.controllers;

import com.animalmanagementsystem.shelter.dtos.AnimalDto;
import com.animalmanagementsystem.shelter.searchs.AnimalSearchRequest;
import com.animalmanagementsystem.shelter.services.AnimalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/animal")
@CrossOrigin(origins = "http://localhost:4200/")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<AnimalDto>> searchAnimal(@RequestBody AnimalSearchRequest request) {
        List<AnimalDto> animalDtoList = animalService.findAnimalByCriteria(request);
        return ResponseEntity.ok(animalDtoList);
    }

    @PostMapping
    public ResponseEntity<String> createAnimal(@Valid @RequestBody AnimalDto animalDto) {
        animalService.createAnimal(animalDto);
        return ResponseEntity.ok("Animal created");
    }

    @GetMapping
    public ResponseEntity<List<AnimalDto>> getAllAnimals() {
        return new ResponseEntity<>(animalService.getAllAnimals(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimalDto> getAnimalById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(animalService.getAnimalById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateAnimal(@PathVariable("id") Long id,
                                               @Valid @RequestBody AnimalDto animalDto) {
        animalService.updateAnimal(animalDto, id);
        return ResponseEntity.ok("Animal updated");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable("id") Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.ok("Animal deleted");
    }
}
