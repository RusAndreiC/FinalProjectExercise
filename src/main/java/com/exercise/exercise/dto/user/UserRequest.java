package com.exercise.exercise.dto.user;

import com.exercise.exercise.model.cartNorder.Orders;
import com.exercise.exercise.model.user.Address;
import com.exercise.exercise.model.user.Preferences;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class UserRequest {

    private String name;
    private List<Orders> orderList;
    private String email;
    private String password;
    private List<Address> addressList;
    private String thumbnail;
    private String roles;
    private Preferences preferences;


}
