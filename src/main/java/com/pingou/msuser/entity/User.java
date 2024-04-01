package com.pingou.msuser.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;
}
