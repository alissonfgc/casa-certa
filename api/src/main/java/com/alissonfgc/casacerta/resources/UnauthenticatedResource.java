package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.dto.SellerDTO;
import com.alissonfgc.casacerta.dto.UserDTO;
import com.alissonfgc.casacerta.entities.Seller;
import com.alissonfgc.casacerta.entities.User;
import com.alissonfgc.casacerta.services.SellerService;
import com.alissonfgc.casacerta.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/unauth")
public class UnauthenticatedResource {

    private final UserService userService;

    private final SellerService sellerService;

    public UnauthenticatedResource(UserService userService, SellerService sellerService) {
        this.userService = userService;
        this.sellerService = sellerService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) {
        User obj = userService.fromDTO(objDTO);
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//    @PostMapping(value = "login")
//    public ResponseEntity<Void> usereLogin(@RequestBody UserDTO objDTO) {}

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
