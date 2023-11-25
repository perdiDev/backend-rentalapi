package com.proyekpbo.rentalapi.repositories;

import com.proyekpbo.rentalapi.domain.User;
import com.proyekpbo.rentalapi.exceptions.AuthException;

public interface UsersRepository {
    Integer findByEmail(String email);
    User findById(Integer userId);
    Integer create(String nama, String alamat, String email, String password) throws AuthException;
    User findBYEmailPassword(String email, String password) throws AuthException;
}
