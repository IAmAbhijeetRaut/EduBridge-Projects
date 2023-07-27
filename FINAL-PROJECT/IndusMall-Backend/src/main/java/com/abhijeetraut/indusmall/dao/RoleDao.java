package com.abhijeetraut.indusmall.dao;

import com.abhijeetraut.indusmall.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Abhijeet Raut on || Date : 12-04-2023 ||  Time : 12:46 PM.
 */

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
