package com.exercise.exercise.model.user;

import com.exercise.exercise.model.cartNorder.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private String street;
    private int streetNumber;
    private String block;
    private String ap;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name ="user_addresses",
            joinColumns = @JoinColumn(name="address_id"),
            inverseJoinColumns = @JoinColumn(name="user_id") )
    private User user;

    @OneToMany(mappedBy = "address")
     private List<Orders> ordersList;

}
