package com.tabmedd.Controller;

import com.tabmedd.Model.User;
import com.tabmedd.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "Saved...";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updateUser = userRepo.findById(id).get();
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setCpf(user.getCpf());
        updateUser.setAge(user.getAge());
        updateUser.setOccupation(user.getOccupation());
        userRepo.save(updateUser);
        return "Updated...";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Delete user with the id: " + id;
    }

}
