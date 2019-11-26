package de.hska.iwi.vslab.Core_Role.Controllers;

import de.hska.iwi.vslab.Core_Role.Models.Role;
import de.hska.iwi.vslab.Core_Role.Services.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public Role[] getAllRoles() {
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

    @PostMapping(path="/role", consumes="application/json")
    public void addRole(@RequestBody(required=true) Role role) {
        roleService.addRole(role);
    }

    @PutMapping(path="/role/{id}", consumes="application/json")
    public void updateRole(@RequestBody(required=true) Role role) {
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
