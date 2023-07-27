package com.abhijeetraut.indusmall.dao;

import com.abhijeetraut.indusmall.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Abhijeet Raut on || Date : 20-04-2023 ||  Time : 04:15 PM.
 */

@Repository
public interface UserDao extends CrudRepository<User, String> {


}
