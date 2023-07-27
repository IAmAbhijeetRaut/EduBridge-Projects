package com.abhijeetraut.indusmall.service;

import com.abhijeetraut.indusmall.dao.RoleDao;
import com.abhijeetraut.indusmall.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By Abhijeet Raut on || Date : 12-04-2023 ||  Time : 12:45 PM.
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role){
        return roleDao.save(role);
    }
}
