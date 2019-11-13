package de.hska.iwi.vslab.Core_Role.Interfaces;

import de.hska.iwi.vslab.Core_Role.Models.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "role", path = "role")
public interface RoleREST_Repo extends PagingAndSortingRepository<Role, Long> {

    List<Role> findById(@Param("id") int id);

    List<Role> findAll();
}