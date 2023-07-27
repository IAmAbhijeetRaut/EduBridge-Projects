package com.abhijeetraut.indusmall.service;

import com.abhijeetraut.indusmall.dao.RoleDao;
import com.abhijeetraut.indusmall.dao.UserDao;
import com.abhijeetraut.indusmall.entity.Role;
import com.abhijeetraut.indusmall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * Created By Abhijeet Raut on || Date : 20-04-2023 ||  Time : 04:14 PM.
 */

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public User registerNewUser(User user){
       Role role = roleDao.findById("USER").get();

       Set<Role> roles = new HashSet<>();
       roles.add(role);
       user.setRole(roles);
       user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userDao.save(user);
    }




    @PostConstruct
    public void initRolesAndUser(){
        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole.setRoleDescription("Admin Role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("USER");
        userRole.setRoleDescription("User Role | Default Role");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin");
        adminUser.setUserFirstName("admin1");
        adminUser.setUserLastName("admin1");
        adminUser.setUserPassword(getEncodedPassword("admin009"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);


    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
