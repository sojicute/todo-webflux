package com.github.sojicute.todowebflux.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    @Id
    private String id;
    private String text;
}
