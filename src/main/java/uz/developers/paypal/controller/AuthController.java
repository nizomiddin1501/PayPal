package uz.developers.paypal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developers.paypal.entity.User;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.service.AuthService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class AuthController {

    @Autowired
    AuthService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> answers = userService.getUsers();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(answers);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponce> addUser(@RequestBody User user) {
        ApiResponce apiResponce = userService.addUser(user);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce> editUser(@PathVariable Integer id, @RequestBody User user) {
        ApiResponce apiResponce = userService.editUser(id,user);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        ApiResponce apiResponce = userService.deleteUser(id);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);
    }


}
