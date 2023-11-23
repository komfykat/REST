package com.example.REST;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import net.minidev.json.writer.JsonReader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    ArrayList<User> users = new ArrayList<>();

    // curl http://localhost:8080/users
    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(users);
    }

    // curl -X DELETE http://localhost:8080/users/1
    @DeleteMapping("users/{index}")
    public ResponseEntity<Void> deleteUser(@PathVariable("index") Integer
                                                   index) {
        if (index > 0 && index < users.size()) {
            users.remove((int) index);
        }
        return ResponseEntity.noContent().build();
    }

    // curl http://localhost:8080/users/1
    @GetMapping("users/{index}")
    public ResponseEntity<User> getUser(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(users.get(index));
    }

    // curl -X PUT -H "Content-Type: application/json" -d "{\"age\" : \"17\"}" http://localhost:8080/users/1
    @PutMapping("users/{index}")
    public ResponseEntity<Void> updateUser(@PathVariable("index") Integer index, @RequestBody User user) {
        users.get(index).setAge(user.getAge());
        return ResponseEntity.accepted().build();
    }

    // curl -X POST -H "Content-Type: application/json" -d "{\"name\" : \"Max\" , \"age\" : \"17\"}" http://localhost:8080/users
    @PostMapping("users")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        users.add(user);
        return ResponseEntity.accepted().build();
    }
}
