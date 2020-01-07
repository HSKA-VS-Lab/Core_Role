package de.hska.iwi.vslab.Core_Role.Services;

import de.hska.iwi.vslab.Core_Role.Interfaces.RoleRepository;
import de.hska.iwi.vslab.Core_Role.Models.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepo;

    public Role[] getAllRoles(){
        List<Role> list = roleRepo.findAll();
        Role[] roles = new Role[list.size()];
        roles = list.toArray(roles);
        return roles;
    }

    public Role getRole(String type){
        return roleRepo.findByType(type);
    }

    public Role getRole(int id){
        return roleRepo.findById(id);
    }

    public void addRole(Role role){
        roleRepo.save(role);
    }

    public void updateRole(Role role){
        System.out.println("THERE!!!!"+role);
        roleRepo.save(role);
    }

    public void deleteAllRoles(){
        for(Role role: roleRepo.findAll())
            roleRepo.delete(role);
    }

    public void deleteRole(int id){
        System.out.println("deleteRoleById: "+id);
        Role role = roleRepo.findById(id);
        roleRepo.delete(role);
    }
}
