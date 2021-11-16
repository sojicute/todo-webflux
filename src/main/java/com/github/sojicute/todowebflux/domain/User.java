package com.github.sojicute.todowebflux.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private String id;
    private String username;
    private String password;
    private String role;


    public UserDetails toUserDetails() {
        return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username(this.username)
                .password(this.password)
                .roles(this.role)
                .build();
    }


//    private List<Task> tasks;
}
