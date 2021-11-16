package com.exercise.exercise.service.MVCService;

import com.exercise.exercise.dto.user.UserRequest;
import com.exercise.exercise.model.user.User;
import com.exercise.exercise.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class MVCUserService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(MVCUserService.class);

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MVCUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Used by Spring Security to identify and pass and UserDetails to its classes
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List roles = new ArrayList();
        String role = "ROLE_" + user.getRoles();
        roles.add(new SimpleGrantedAuthority(role));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), roles);
    }

    // create

    public void save(User user) {
        log.info("saving user {}", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null) {
            user.setRoles("USER");
        }

        userRepository.save(user);
    }
    // find all

    public List<User> findAll() {
        log.info("finding all users");
        return userRepository.findAll();
    }
    // find by id

    public User findById(Long id) {
        log.info("finding by id");
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }
    // update

    public void update(Long userId, UserRequest userData) {
        log.info("update user {}", userData);

        userRepository.findById(userId)
                .map(existingUser -> updateEntity(userData, existingUser))
                .map(updatedUser -> userRepository.save(updatedUser))
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    private User updateEntity(UserRequest userData, User existingUser) {
        existingUser.setName(userData.getName());
        existingUser.setOrderList(userData.getOrderList());
        existingUser.setEmail(userData.getEmail());

        existingUser.setPassword(passwordEncoder.encode(userData.getPassword()));
        existingUser.setAddressList(userData.getAddressList());
        existingUser.setRoles(userData.getRoles());
        existingUser.setPreferences(userData.getPreferences());
        existingUser.setThumbnail(userData.getThumbnail());
        return existingUser;
    }

    public void updateNew(User user) {
        log.info("update user {}", user);

        String name = user.getName();
        userRepository.findByNameIgnoreCase(name)
                .filter(existingUser -> existingUser.getId().equals(user.getId()))
                .map(existingUser -> userRepository.save(user))
                .orElseThrow(() -> {
                    log.error("user with name {} already exists", name);
                    throw new ResourceAlreadyExistsException("user with name " + name + " already exists");
                });
    }
    // delete

    @Transactional
    public void delete(Long id) {
        log.info("deleting by id");
        userRepository.deleteById(id);
    }
}
