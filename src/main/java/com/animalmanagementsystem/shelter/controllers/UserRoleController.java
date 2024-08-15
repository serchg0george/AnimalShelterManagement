package com.animalmanagementsystem.shelter.controllers;

import com.animalmanagementsystem.shelter.dtos.UserRoleDto;
import com.animalmanagementsystem.shelter.services.UserRoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user_role")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping
    public ResponseEntity<String> createUserRole(@Valid @RequestBody UserRoleDto userRoleDto) {
        userRoleService.createUserRole(userRoleDto);
        return ResponseEntity.ok("User Role created");
    }

    @GetMapping
    public ResponseEntity<List<UserRoleDto>> getAllUserRoles() {
        return new ResponseEntity<>(userRoleService.getAllUserRoles(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserRoleDto> getUserRoleById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userRoleService.getUserRoleById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUserRole(@PathVariable("id") Long id,
                                                 @RequestBody UserRoleDto userRoleDto) {
        userRoleService.updateUserRole(userRoleDto, id);
        return ResponseEntity.ok("User Role updated");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserRole(@PathVariable("id") Long id) {
        userRoleService.deleteUserRole(id);
        return ResponseEntity.ok("User Role deleted");
    }
}
