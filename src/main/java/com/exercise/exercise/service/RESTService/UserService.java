package com.exercise.exercise.service.RESTService;


import com.exercise.exercise.dto.user.UserRequest;
import com.exercise.exercise.dto.user.UserResponse;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserResponse> findAll();

    UserResponse findById(Long id);

    UserResponse save(UserRequest createUserRequest);

    UserResponse update(Long id, UserRequest updateRequest);

    UserResponse partialUpdate(Long id, Map<String, Object> updates);

    void delete(Long id);

    // find using filter
    List<UserResponse> findByEmail(String email);

}
