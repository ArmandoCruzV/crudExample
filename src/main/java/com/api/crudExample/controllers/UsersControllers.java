package com.api.crudExample.controllers;

import com.api.crudExample.models.UserModel;
import com.api.crudExample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("localhost:5173")
@RestController
@RequestMapping("/users")
public class UsersControllers {

    @Autowired
    UserService userService;

    @GetMapping("/object")
    public Map<String, Object> obj(){
        return Map.of("nombre","Armando");
    }

    @GetMapping("/status")
    @ResponseStatus(value = HttpStatus.TEMPORARY_REDIRECT, reason = "Fue movida a la version 2 Api")
    public String hola(){
        return "Hola mundo";
    }

    @GetMapping("/hola/{saludo}")
    public String hola(@PathVariable("saludo") String saludo){
        return "hola desde / : " + saludo;
    }

    @GetMapping
    public ArrayList<UserModel> users(){
        return this.userService.getUsers();
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> userGetById(@PathVariable("id") Long id){
        return this.userService.getUserById(id);
    }

    @PutMapping(path = "/{id}")
    public UserModel updateUser(@RequestBody UserModel request, @PathVariable("id") Long id){
        return this.userService.updateUser(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public Boolean deleteUser(@PathVariable("id") Long id) {
        Boolean ok = this.userService.deleteUser(id);
        if(ok){
            return ok;
        }
        else{
            return false;
        }
    }

}
