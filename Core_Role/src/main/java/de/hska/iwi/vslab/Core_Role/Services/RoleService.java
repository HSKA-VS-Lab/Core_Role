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
        roleRepo.save(role);
    }

    public long deleteAllRoles(){
        long deleted = 0;
        for(Role role: roleRepo.findAll())
            deleted += roleRepo.deleteById(role.getId());
        return deleted;
    }

    public long deleteRole(int id){
        return roleRepo.deleteById(id);
    }
}
