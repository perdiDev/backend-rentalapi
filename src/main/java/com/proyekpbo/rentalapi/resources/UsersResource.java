package com.proyekpbo.rentalapi.resources;

import com.proyekpbo.rentalapi.Constant;
import com.proyekpbo.rentalapi.domain.User;
import com.proyekpbo.rentalapi.services.UsersService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UsersResource {

    @Autowired
    UsersService usersService;

    @PostMapping("/register")
    ResponseEntity<Map<String, String>> register(
            @RequestBody Map<String, Object> userMap
    ) {
        String nama = (String) userMap.get("nama");
        String alamat = (String) userMap.get("alamat");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = usersService.registerUser(nama, alamat, email, password);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<Map<String, String>> login(
            @RequestBody Map<String, Object> userMap
    ) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = usersService.validateUser(email, password);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(
            User user
    ) {
        long timestamp = System.currentTimeMillis();

        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constant.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp+Constant.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("status", user.getStatus())
                .claim("nama", user.getNama())
                .claim("email", user.getEmail())
                .compact();

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
