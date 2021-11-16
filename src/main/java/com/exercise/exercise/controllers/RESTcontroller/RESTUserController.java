package com.exercise.exercise.controllers.RESTcontroller;


import com.exercise.exercise.dto.user.UserRequest;
import com.exercise.exercise.dto.user.UserResponse;
import com.exercise.exercise.service.RESTService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(RESTUserController.API_USERS)
public class RESTUserController {

    public static final String API_USERS = "/api/users";

    private final UserService userService;

    @Autowired
    public RESTUserController(UserService restUserService) {
        this.userService = restUserService;
    }

    @PostMapping("/")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.save(userRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<UserResponse>> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
        @PathVariable("id") Long id,
        @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.update(id, userRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> partialUpdate(
        @PathVariable("id") Long id,
        @RequestBody Map<String, Object> userRequest) {
        return ResponseEntity.ok(userService.partialUpdate(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
