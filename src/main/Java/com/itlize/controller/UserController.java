package com.itlize.controller;

import com.itlize.entity.User;
import com.itlize.service.ProjectService;
import com.itlize.service.UserService;
import com.itlize.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.security.Principal;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/register")
    public User register(@RequestParam(name="username") String username, @RequestParam(name="password") String password) {
        if (userService.findByUsername(username) != null) {
            return new User();
        }
        //user.setRole(Role.USER);
        User user=new User(username,password);
        return userService.saveUser(user);
    }


    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/login")
    public User login(@RequestParam(name="username") String username, @RequestParam(name="password") String password){
        User user=userService.findByUsername(username);
        if(user==null)
        {
            return new User();
        }
        boolean isSuccessful=userService.validate(user,password);
        if(isSuccessful)
        {
            return user;
        }
        return new User();
    }
}
