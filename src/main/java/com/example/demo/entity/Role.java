package com.example.demo.entity;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name="role")

public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private int id;

    @Column(name="role")
    private String roleName;

    public Role() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
