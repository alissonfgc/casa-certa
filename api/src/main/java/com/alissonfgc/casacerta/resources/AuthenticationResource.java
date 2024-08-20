package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.dto.AuthenticationDTO;
import com.alissonfgc.casacerta.dto.LoginResponseDTO;
import com.alissonfgc.casacerta.dto.SellerDTO;
import com.alissonfgc.casacerta.dto.UserDTO;
import com.alissonfgc.casacerta.entities.Seller;
import com.alissonfgc.casacerta.entities.User;
import com.alissonfgc.casacerta.infra.security.TokenService;
import com.alissonfgc.casacerta.services.SellerService;
import com.alissonfgc.casacerta.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationResource {

    final AuthenticationManager authenticationManager;

    final UserService userService;

    final SellerService sellerService;

    final TokenService tokenService;

    public AuthenticationResource(UserService userService, SellerService sellerService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.sellerService = sellerService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> userRegister(@RequestBody @Valid UserDTO objDTO) {
        User obj = this.userService.fromDTO(objDTO);
        String encriptedPassword = new BCryptPasswordEncoder().encode(obj.getPassword());
        obj.setPassword(encriptedPassword);
        obj = this.userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> userLogin(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    //seller
    @PostMapping(value = "/seller/register")
    public ResponseEntity<Void> insert(@RequestBody SellerDTO objDTO) {
        Seller obj = sellerService.fromDTO(objDTO);
        obj = sellerService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//    @PostMapping(value = "/seller/login")
//    public ResponseEntity<Void> sellerLogin(@RequestBody UserDTO objDTO) {
//    }

    //admin
//    @PostMapping(value = "/administrator/login")
//    public ResponseEntity<Void> login(@RequestBody AdminDTO objDTO) {
//    }
}
