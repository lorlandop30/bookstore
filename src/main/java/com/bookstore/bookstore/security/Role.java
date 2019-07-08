package com.bookstore.bookstore.security;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    private int roleId;
    private String name;

    @OneToMany(mappedBy ="role",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<UserRole> userRole=new HashSet<>();

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public Set<UserRole> getUserRolesSet() {
        return userRole;
    }

    public void setUserRolesSet(Set<UserRole> userRolesSet) {
        this.userRole = userRolesSet;
    }
}
