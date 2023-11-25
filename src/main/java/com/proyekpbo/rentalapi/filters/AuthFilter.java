package com.proyekpbo.rentalapi.filters;

import com.proyekpbo.rentalapi.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class AuthFilter extends GenericFilterBean {
    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpRequest.getHeader("Authorization");
        if(authHeader == null) {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provide");
            return;
        }

        String[] authHeaderArr = authHeader.split("Bearer ");
        if(authHeaderArr.length < 2 || authHeaderArr[1] == null) {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
            return;
        }

        String token = authHeaderArr[1];

        try {
            Claims claims = Jwts.parser().setSigningKey(Constant.API_SECRET_KEY)
                    .build().parseClaimsJws(token).getBody();
            httpRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));
        } catch (Exception e) {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
