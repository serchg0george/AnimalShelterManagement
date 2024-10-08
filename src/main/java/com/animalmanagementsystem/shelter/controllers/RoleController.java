package com.animalmanagementsystem.shelter.controllers;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.searchs.RoleSearchRequest;
import com.animalmanagementsystem.shelter.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/role")
@CrossOrigin(origins = "http://localhost:4200/")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<RoleDto>> searchRole(@RequestBody RoleSearchRequest request) {
        List<RoleDto> roleDtoList = roleService.findRoleByCriteria(request);
        return ResponseEntity.ok(roleDtoList);
    }

    @PostMapping
    public ResponseEntity<String> createRole(@Valid @RequestBody RoleDto roleDto) {
        roleService.createRole(roleDto);
        return ResponseEntity.ok("");
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(roleService.getRoleById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateRole(@PathVariable("id") Long id,
                                             @Valid @RequestBody RoleDto roleDto) {
        roleService.updateRole(roleDto, id);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("");
    }

}
