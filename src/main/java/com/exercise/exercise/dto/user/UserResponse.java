package com.exercise.exercise.dto.user;

import com.exercise.exercise.model.cartNorder.Orders;
import com.exercise.exercise.model.user.Address;
import com.exercise.exercise.model.user.Preferences;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class UserResponse {

    private Long id;

    private String name;
    private String email;
    private List<Orders> orderList;
    private String password;
    private List<Address> addressList;
    private String thumbnail;
    private String roles;
    private Preferences preferences;

}
