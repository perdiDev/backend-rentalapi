package com.proyekpbo.rentalapi.services;

import com.proyekpbo.rentalapi.domain.User;
import com.proyekpbo.rentalapi.exceptions.AuthException;

public interface UsersService {
    User validateUser(String email, String password) throws AuthException;
    User registerUser(String nama, String alamat, String email, String password) throws AuthException;
}
