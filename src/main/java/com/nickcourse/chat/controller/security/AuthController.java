package com.nickcourse.chat.controller.security;

import com.nickcourse.chat.model.dto.JwtAuthResponse;
import com.nickcourse.chat.model.dto.LoginDto;
import com.nickcourse.chat.model.dto.RegisterDto;
import com.nickcourse.chat.service.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

  private AuthService authService;
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  //Build Login REST API
  @PostMapping(value = {"/login", "/signin"})
  public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
    String token = authService.login(loginDto);

    JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
    jwtAuthResponse.setAccessToken(token);

    return ResponseEntity.ok(jwtAuthResponse);
  }

  // Build Register REST API
  @PostMapping(value = {"/register", "/signup"})
  public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
    String response = authService.register(registerDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
