package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.dto.ImmobileDTO;
import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.services.ImmobileService;
import com.alissonfgc.casacerta.services.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/immobiles")
public class ImmobileResource {

    private final ImmobileService service;

    private final SellerService sellerService;

    public ImmobileResource(ImmobileService service, SellerService sellerService) {
        this.service = service;
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<List<ImmobileDTO>> findAll() {
        List<Immobile> list = service.findAll();
        List<ImmobileDTO> listDTO = list.stream().map(ImmobileDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ImmobileDTO> findById(@PathVariable Long id) {
        Immobile entity = service.findById(id);
        return ResponseEntity.ok().body(new ImmobileDTO(entity));
    }

    @PostMapping(value = "/{sellerId}")
    public ResponseEntity<Immobile> insert(@PathVariable Long sellerId, @RequestBody Immobile entity) {
        entity.setSeller(sellerService.findById(sellerId));
        entity = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
