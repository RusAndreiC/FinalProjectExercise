package com.exercise.exercise.service.RESTService;

import com.exercise.exercise.dto.user.UserMapper;
import com.exercise.exercise.dto.user.UserRequest;
import com.exercise.exercise.dto.user.UserResponse;
import com.exercise.exercise.exception.NotFoundException;
import com.exercise.exercise.exception.UserNotFoundException;
import com.exercise.exercise.model.user.User;
import com.exercise.exercise.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RESTUserService implements UserService{

    private static final Logger log = LoggerFactory.getLogger(RESTUserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ObjectMapper jacksonObjectMapper;
    
    @Autowired
    public RESTUserService(UserRepository userRepository, UserMapper userMapper, ObjectMapper jacksonObjectMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    @Override
    public List<UserResponse> findAll() {
        log.debug("finding all users");

        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(Long id) {
        log.debug("finding user by id: {}", id);

        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    @Override
    public UserResponse save(UserRequest createUserRequest) {
        log.debug("saving user: {}", createUserRequest);

        boolean exists = userRepository.findByNameIgnoreCase(createUserRequest.getName()).isPresent();
        if (exists) {
            throw new NotFoundException("duplicate user");
        }

        User user = userMapper.toEntity(createUserRequest);
        User newUser = userRepository.save(user);
        return userMapper.toDto(newUser);
    }

    @Override
    public UserResponse update(Long id, UserRequest updateRequest) {
            log.debug("updating user with id: {} and request body: {}", id, updateRequest);

            return userRepository.findById(id)
                    .map(user -> userMapper.toEntity(user, updateRequest))
                    .map(userRepository::save)
                    .map(userMapper::toDto)
                    .orElseThrow(() -> new NotFoundException("user not found"));
        }

    @Override
    public UserResponse partialUpdate(Long id, Map<String, Object> updates) {
        log.debug("patching user with id: {} and request body: {}", id, updates);
        // De-serialise request body into a DTO
        // Run some sort of validation
        // Load entity being updated
        // Copy fields that change over to the entity with the help of a Model Mapper
        // Save

        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("user not found"));

            // Jackson deserializes and copies value to the already initialised DTO
            jacksonObjectMapper.readerForUpdating(user)
                    .readValue(jacksonObjectMapper.writeValueAsBytes(updates));

            User updatedUser = userRepository.save(user);

            return userMapper.toDto(updatedUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    }

    @Override
    public void delete(Long id) {
        log.debug("deleting user with id: {}", id);

        userRepository.findById(id)
                .map(user -> {
                    userRepository.deleteById(id);
                    return user;
                })
                .orElseThrow(() -> new NotFoundException("user not found"));
    }

    @Override
    public List<UserResponse> findByEmail(String email) {
        log.debug("finding users by email: {}", email);

        return userRepository.findAll()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
