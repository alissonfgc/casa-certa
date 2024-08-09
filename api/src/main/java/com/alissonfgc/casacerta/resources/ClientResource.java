package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.dto.ClientDTO;
import com.alissonfgc.casacerta.entities.Client;
import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.services.ClientService;
import com.alissonfgc.casacerta.services.ImmobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<Client> list = service.findAll();
        List<ClientDTO> listDTO = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable String id) {
        Client obj = service.findById(id);
        return ResponseEntity.ok().body(new ClientDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ClientDTO objDTO) {
        Client obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody ClientDTO objDTO, @PathVariable String id) {
        Client newDataObj = service.fromDTO(objDTO);
        newDataObj.setId(id);
        newDataObj = service.update(newDataObj);
        return ResponseEntity.noContent().build();
    }
}
