package de.hska.iwi.vslab.Core_Role.Interfaces;

import de.hska.iwi.vslab.Core_Role.Models.Role;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RoleDB_Repo extends CrudRepository<Role, Long> {

    List<Role> findAll();

    Role findById(int id);
}