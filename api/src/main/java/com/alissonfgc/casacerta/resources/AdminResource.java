package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.dto.AdminDTO;
import com.alissonfgc.casacerta.dto.SellerDTO;
import com.alissonfgc.casacerta.dto.UserDTO;
import com.alissonfgc.casacerta.entities.Admin;
import com.alissonfgc.casacerta.entities.Seller;
import com.alissonfgc.casacerta.entities.User;
import com.alissonfgc.casacerta.services.AdminService;
import com.alissonfgc.casacerta.services.SellerService;
import com.alissonfgc.casacerta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/admins")
public class AdminResource {

    private final AdminService service;

    private final UserService userService;

    private final SellerService sellerService;

    public AdminResource(AdminService service, UserService userService, SellerService sellerService) {
        this.service = service;
        this.userService = userService;
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> findAll() {
        List<Admin> list = service.findAll();
        List<AdminDTO> listDTO = list.stream().map(AdminDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<AdminDTO> findAdminByEmail(@PathVariable String email) {
        Admin admin = service.findByEmail(email);
        return ResponseEntity.ok().body(new AdminDTO(admin));
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String email) {
        service.delete(service.findByEmail(email));
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateAdmin(@PathVariable String id, @RequestBody AdminDTO objDTO) {
        Admin newDataObj = service.fromDTO(objDTO);
        newDataObj.setId(id);
        newDataObj = service.update(newDataObj);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> insertAdmin(@RequestBody AdminDTO objDTO) {
        Admin obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //USERS
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<User> list = userService.findAll();
        List<UserDTO> listDTO = list.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/users/{email}")
    public ResponseEntity<UserDTO> findUserByEmail(@PathVariable String email) {
        User obj = userService.findByEmail(email);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    //SELLER
    @GetMapping(value = "/seller")
    public ResponseEntity<List<SellerDTO>> findAllSellers() {
        List<Seller> list = sellerService.findAll();
        List<SellerDTO> listDTO = list.stream().map(SellerDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/seller/{email}")
    public ResponseEntity<SellerDTO> findSellerByEmail(@PathVariable String email) {
        Seller obj = sellerService.findByEmail(email);
        return ResponseEntity.ok().body(new SellerDTO(obj));
    }
}
