package com.bookstore.bookstore.security;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    private int roleId;
    private String Name;

    @OneToMany(mappedBy ="role",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<UserRoles> userRole=new HashSet<>();

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<UserRoles> getUserRolesSet() {
        return userRole;
    }

    public void setUserRolesSet(Set<UserRoles> userRolesSet) {
        this.userRole = userRolesSet;
    }
}
