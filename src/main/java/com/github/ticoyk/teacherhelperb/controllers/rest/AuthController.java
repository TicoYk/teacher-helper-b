package com.github.ticoyk.teacherhelperb.controllers.rest;

import com.github.ticoyk.teacherhelperb.models.JwtRequest;
import com.github.ticoyk.teacherhelperb.models.JwtResponse;
import com.github.ticoyk.teacherhelperb.services.JwtUserService;
import com.github.ticoyk.teacherhelperb.utils.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {

    private AuthenticationManager authenticationManager;
    private AuthUtil authUtil;
    private JwtUserService jwtUserService;

    AuthController(AuthenticationManager authenticationManager, AuthUtil authUtil, JwtUserService jwtUserService) {
        this.authenticationManager = authenticationManager;
        this.authUtil = authUtil;
        this.jwtUserService = jwtUserService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUserService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = authUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
