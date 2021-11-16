package com.exercise.exercise.model.cartNorder;


import com.exercise.exercise.model.user.Address;
import com.exercise.exercise.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private double totalPrice;

    @ManyToOne()
    @JoinTable(name ="order_addresses",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="address_id") )
    private Address address;

    private Date orderDate;

    @OneToMany(mappedBy = "orders")
    private List<OrderLine> entries = new ArrayList<OrderLine>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;



}
