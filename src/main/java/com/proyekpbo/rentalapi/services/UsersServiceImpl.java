package com.proyekpbo.rentalapi.services;

import com.proyekpbo.rentalapi.domain.User;
import com.proyekpbo.rentalapi.exceptions.AuthException;
import com.proyekpbo.rentalapi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UsersServiceImpl implements UsersService{

    @Autowired
    UsersRepository usersRepository;

    @Override
    public User validateUser(String email, String password) throws AuthException {
        if(email != null) email = email.toLowerCase();
        return usersRepository.findBYEmailPassword(email, password);
    }

    @Override
    public User registerUser(
            String nama, String alamat, String email, String password
    ) throws AuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if(email != null)
            email = email.toLowerCase();

        if(!pattern.matcher(email).matches())
            throw new AuthException("invalid format email");

        Integer count  = usersRepository.findByEmail(email);
        if(count > 0)
            throw new AuthException("Email already in use");

        Integer userId = usersRepository.create(nama, alamat, email, password);
        return usersRepository.findById(userId);
    }
}
