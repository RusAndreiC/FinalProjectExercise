package com.exercise.exercise.model.user;

import com.exercise.exercise.model.cartNorder.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Orders> orderList = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Address> addressList = new ArrayList<>();

    private String thumbnail;
    // ADMIN, USER, VISITOR
    // TODO SA NE DECIDEM DACA FOLOSIM ROLURI LA LIBER SAU IMPLEMENTAREA CU CLASA SI ENUM
    private String roles;
    @Enumerated(EnumType.STRING)
    private Preferences preferences;


}

