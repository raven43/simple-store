package com.raven43.simplestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Length(max = 32)
    private String username;

    @Length(max = 32)
    @JsonIgnore
    private String password;

    @Length(max = 128)
    private String imgName;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Collection<Role> authorities;

    public User() {
        //orders = new TreeSet<>();
        //orders.add(new Order());
    }

    public User(
            @Length(max = 32) String username,
            @Length(max = 32) String password
    ) {
        this.username = username;
        this.password = password;
        //orders = new TreeSet<>();
        //orders.add(new Order());
    }

    public User(
            @Length(max = 32) String username,
            @Length(max = 32) String password,
            Collection<Role> authorities
    ) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        //orders = new TreeSet<>();
        //orders.add(new Order());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        authorities = authorities != null ? authorities : new HashSet<>();
        return authorities;
    }

    public void setAuthorities(Collection<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isAdmin() {
        return getAuthorities().contains(Role.ADMIN);
    }

    public boolean isSeller() {
        return getAuthorities().contains(Role.ADMIN);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return (int) (long) id;
    }

    public enum Role implements GrantedAuthority {
        CLIENT,
        SELLER,
        ADMIN;

        @Override
        public String getAuthority() {
            return name();
        }
    }
}