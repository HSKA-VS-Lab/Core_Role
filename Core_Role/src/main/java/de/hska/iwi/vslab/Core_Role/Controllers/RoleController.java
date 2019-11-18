package de.hska.iwi.vslab.Core_Role.Controllers;

import de.hska.iwi.vslab.Core_Role.Models.Role;
import de.hska.iwi.vslab.Core_Role.Services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/role/{input}")
    public Role getRole(@PathVariable String input) {
        // get by type
        if(input.replaceAll("\\d","").length() > 0) // only digits in input
            return roleService.getRole(input);
            // get by id
        else
            return roleService.getRole(Integer.parseInt(input));
    }

    @PostMapping("/role")
    public void addRole(@RequestBody Role role) {
        roleService.addRole(role);
    }

    @PutMapping("/role")
    public void updateRole(@RequestBody Role role) {
        roleService.updateRole(role);
    }

    @DeleteMapping("/role/{id}")
    public void deleteRole(@PathVariable int id){
        roleService.deleteRole(id);
    }

    @DeleteMapping("/role")
    public void deleteRole(){
        roleService.deleteAllRoles();
    }
}
