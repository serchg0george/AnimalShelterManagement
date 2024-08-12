package com.animalmanagementsystem.shelter.controllers;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.searchs.RoleSearchRequest;
import com.animalmanagementsystem.shelter.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<RoleDto>> searchRole(@RequestBody RoleSearchRequest request) {
        List<RoleDto> roleDtoList = roleService.findRoleByCriteria(request);
        return ResponseEntity.ok(roleDtoList);
    }

    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody RoleDto roleDto) {
        roleService.createRole(roleDto);
        return ResponseEntity.ok("Role created");
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
                                             @RequestBody RoleDto roleDto) {
        roleService.updateRole(roleDto, id);
        return ResponseEntity.ok("Role updated");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted");
    }

}
