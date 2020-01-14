package de.hska.iwi.vslab.Core_Role.Controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import de.hska.iwi.vslab.Core_Role.Models.Role;
import de.hska.iwi.vslab.Core_Role.Services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;


@RestController
@EnableCircuitBreaker
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    @HystrixCommand(fallbackMethod = "getFallbackRoles")
    public Role[] getAllRoles() throws EmptyResultDataAccessException
    {
        try
        {
            return roleService.getAllRoles();
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
        // return roleService.getAllRoles();
        //throw new RuntimeException("Throwing exception deliberately for asynchronous testing");
    }

    public Role[] getFallbackRoles() {
        Role role1 = new Role("adminFallback",0);
        Role role2 = new Role("userFallback",1);
        Role[] roleA = new Role[2];
        roleA[0] = role1;
        roleA[1] = role2;
        return roleA;
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

    @RequestMapping(path="/role/{id}", method=RequestMethod.PUT, consumes="application/json")
    public void updateRole(@PathVariable int id, @RequestBody(required=true) Role role) {
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
