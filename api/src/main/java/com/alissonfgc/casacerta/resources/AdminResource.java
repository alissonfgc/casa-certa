package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.dto.AdminDTO;
import com.alissonfgc.casacerta.entities.Admin;
import com.alissonfgc.casacerta.services.AdminService;
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

    @Autowired
    private AdminService service;

    @GetMapping
    public ResponseEntity<List<AdminDTO>> findAll() {
        List<Admin> list = service.findAll();
        List<AdminDTO> listDTO = list.stream().map(AdminDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<AdminDTO> findByEmail(@PathVariable String email) {
        Admin admin = service.findByEmail(email);
        return ResponseEntity.ok().body(new AdminDTO(admin));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody AdminDTO objDTO) {
        Admin obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> delete(@PathVariable String email) {
        service.delete(service.findByEmail(email));
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody AdminDTO objDTO) {
        Admin newDataObj = service.fromDTO(objDTO);
        newDataObj.setId(id);
        newDataObj = service.update(newDataObj);
        return ResponseEntity.noContent().build();
    }
}
