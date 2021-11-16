package com.exercise.exercise.dto.user;

import com.exercise.exercise.model.user.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;




@Component
public class UserMapper {

    public UserResponse toDto(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setOrderList(user.getOrderList());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setAddressList(user.getAddressList());
        dto.setRoles(user.getRoles());
        dto.setPreferences(user.getPreferences());
        dto.setThumbnail(user.getThumbnail());
        return dto;
    }

    public User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setOrderList(userRequest.getOrderList());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setAddressList(userRequest.getAddressList());
        user.setRoles(userRequest.getRoles());
        user.setPreferences(userRequest.getPreferences());
        user.setThumbnail(userRequest.getThumbnail());
        return user;
    }

    public User toEntity(User userToUpdate, UserRequest updateRequest) {
        if (!StringUtils.isEmpty(updateRequest.getOrderList())) {
            userToUpdate.setOrderList(updateRequest.getOrderList());
        }

        if (!StringUtils.isEmpty(updateRequest.getName())) {
            userToUpdate.setName(updateRequest.getName());
        }

        if (!StringUtils.isEmpty(updateRequest.getEmail())) {
            userToUpdate.setEmail(updateRequest.getEmail());
        }

        if (!StringUtils.isEmpty(updateRequest.getPassword())) {
            userToUpdate.setPassword(updateRequest.getPassword());
        }

        if (!StringUtils.isEmpty(updateRequest.getAddressList())) {
            userToUpdate.setAddressList(updateRequest.getAddressList());
        }

        if (!StringUtils.isEmpty(updateRequest.getThumbnail())) {
            userToUpdate.setThumbnail(updateRequest.getThumbnail());
        }

        if (!StringUtils.isEmpty(updateRequest.getRoles())) {
            userToUpdate.setRoles(updateRequest.getRoles());
        }

        if (!StringUtils.isEmpty(updateRequest.getPreferences())) {
            userToUpdate.setPreferences(updateRequest.getPreferences());
        }

        
//        if (updateRequest.getPublished() != null && updateRequest.getPublished().isBefore(LocalDate.now())) {
//            userToUpdate.setPublished(updateRequest.getPublished());
//        }
        return userToUpdate;
    }
}
