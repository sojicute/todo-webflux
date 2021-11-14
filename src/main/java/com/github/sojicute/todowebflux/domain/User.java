package com.github.sojicute.todowebflux.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private String id;
    private String username;
    private String password;
    private String role;

//    private List<Task> tasks;
}
