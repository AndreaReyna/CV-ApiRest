package com.integrador.cv.controllers;

import com.integrador.cv.dto.UserDTO;
import com.integrador.cv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String save(@RequestBody UserDTO userdto) throws Exception {
        return userService.save(userdto);
    }

    @GetMapping("/")
    public String welcome(){
        return "Bienvenido a la API Rest!";
    }
}
