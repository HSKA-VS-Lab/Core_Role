package de.hska.iwi.vslab.Core_Role.Controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.hska.iwi.vslab.Core_Role.Models.Role;
import de.hska.iwi.vslab.Core_Role.Services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableCircuitBreaker
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    @HystrixCommand(fallbackMethod = "fallbackGetRoles")
    public Role[] getAllRoles()
    {
        try
        {
            return roleService.getAllRoles();
        } catch (
                Exception e) {
            return null;
        }
    }

    public Role[] fallbackGetRoles() {
        Role role1 = new Role("adminFallback",0);
        Role role2 = new Role("userFallback",1);
        Role[] roleA = new Role[2];
        roleA[0] = role1;
        roleA[1] = role2;
        return roleA;
    }

    @GetMapping("/role/{input}")
    //@HystrixCommand(fallbackMethod = "fallbackGetRole")
    public Role getRole(@PathVariable String input) {
        // get by type
        if(input.replaceAll("\\d","").length() > 0) // only digits in input
            return roleService.getRole(input);
            // get by id
        else
            return roleService.getRole(Integer.parseInt(input));
    }

    public Role fallbackGetRole() {
        Role role = new Role("userFallback",1);
        return role;
    }

    @PostMapping(path="/role", consumes="application/json")
    //@HystrixCommand(fallbackMethod = "fallbackAddRole")
    public void addRole(@RequestBody(required=true) Role role) {
        roleService.addRole(role);
    }

    public Role fallbackAddRole() {
        Role role = new Role("userFallback",1);
        return role;
    }

    @RequestMapping(path="/role/{id}", method=RequestMethod.PUT, consumes="application/json")
    //@HystrixCommand(fallbackMethod = "defaultFallbackWithId")
    public void updateRole(@PathVariable int id, @RequestBody(required=true) Role role) {
        roleService.updateRole(role);
    }

    @DeleteMapping("/role/{id}")
    //@HystrixCommand(fallbackMethod = "defaultFallbackWithId")
    public void deleteRole(@PathVariable int id){
        roleService.deleteRole(id);
    }

    @DeleteMapping("/role")
    //@HystrixCommand(fallbackMethod = "defaultFallback")
    public void deleteRole(){
        roleService.deleteAllRoles();
    }

    public void defaultFallback(Throwable throwable) {
        System.out.printf("DefaultFallback, exception=%s%n", throwable);
    }

    public void defaultFallbackWithId(int id, Throwable throwable) {
        System.out.printf("DefaultFallbackWithId, id=%s, exception=%s%n", id, throwable);
    }
}
