package com.animalmanagementsystem.shelter.controllers;

import com.animalmanagementsystem.shelter.dtos.UserAnimalDto;
import com.animalmanagementsystem.shelter.services.UserAnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user_animal")
public class UserAnimalController {
    private final UserAnimalService userAnimalService;

    public UserAnimalController(UserAnimalService userAnimalService) {
        this.userAnimalService = userAnimalService;
    }

    @PostMapping
    public ResponseEntity<String> createUserAnimal(@RequestBody UserAnimalDto userAnimalDto) {
        userAnimalService.createUserAnimal(userAnimalDto);
        return ResponseEntity.ok("UserAnimal created");
    }

    @GetMapping
    public ResponseEntity<List<UserAnimalDto>> getAllUserAnimals() {
        return new ResponseEntity<>(userAnimalService.getAllUserAnimals(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserAnimalDto> getUserAnimalById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userAnimalService.getUserAnimalById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUserAnimal(@PathVariable("id") Long id,
                                                   @RequestBody UserAnimalDto userAnimalDto) {
        userAnimalService.updateUserAnimal(userAnimalDto, id);
        return ResponseEntity.ok("UserAnimal updated");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserAnimal(@PathVariable("id") Long id) {
        userAnimalService.deleteUserAnimal(id);
        return ResponseEntity.ok("UserAnimal deleted");
    }
}
