package com.example.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@Where(clause = "status = true")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Integer userAge;
    private boolean status;
}
