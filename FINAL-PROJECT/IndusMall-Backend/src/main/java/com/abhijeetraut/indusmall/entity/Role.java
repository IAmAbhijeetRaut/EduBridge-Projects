package com.abhijeetraut.indusmall.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created By Abhijeet Raut on || Date : 12-04-2023 ||  Time : 12:39 PM.
 */

@Entity
public class Role {

    @Id
    private String roleName;
    private String roleDescription;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
