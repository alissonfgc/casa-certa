package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.dto.UserDTO;
import com.alissonfgc.casacerta.entities.User;
import com.alissonfgc.casacerta.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    //alterar para que o usuario possa alterar somente o propio cadastro
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id) {
        User newDataObj = service.fromDTO(objDTO);
        newDataObj.setId(id);
        newDataObj = service.update(newDataObj);
        return ResponseEntity.noContent().build();
    }
}
