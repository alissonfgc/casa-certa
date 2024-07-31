package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.dto.SellerDTO;
import com.alissonfgc.casacerta.entities.Seller;
import com.alissonfgc.casacerta.services.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/sellers")
public class SellerResource {

    private final SellerService service;

    public SellerResource(SellerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SellerDTO>> findAll() {
        List<Seller> list = service.findAll();
        List<SellerDTO> listDTO = list.stream().map(SellerDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SellerDTO> findById(@PathVariable Long id) {
        Seller obj = service.findById(id);
        return ResponseEntity.ok().body(new SellerDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody SellerDTO objDTO) {
        Seller obj = service.fromDTO(objDTO);
            obj = service.insert(obj);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody SellerDTO objDTO, @PathVariable Long id) {
        Seller newDataObj = service.fromDTO(objDTO);
        newDataObj.setId(id);
        newDataObj = service.update(newDataObj);
        return ResponseEntity.noContent().build();
    }
}
