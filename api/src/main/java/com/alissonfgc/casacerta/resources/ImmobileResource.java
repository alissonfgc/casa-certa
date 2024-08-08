package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.dto.AuxiliaryImmobileDTO;
import com.alissonfgc.casacerta.dto.ImmobileDTO;
import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.resources.util.URL;
import com.alissonfgc.casacerta.services.ImmobileService;
import com.alissonfgc.casacerta.services.SellerService;
import com.alissonfgc.casacerta.services.exceptions.ObjectNotFoundException;
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
    public ResponseEntity<List<AuxiliaryImmobileDTO>> findAll() {
        List<Immobile> list = service.findAll();
        List<AuxiliaryImmobileDTO> listDTO = list.stream().map(AuxiliaryImmobileDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuxiliaryImmobileDTO> findById(@PathVariable Long id) {
        Immobile entity = service.findById(id);
        return ResponseEntity.ok().body(new AuxiliaryImmobileDTO(entity));
    }

    @GetMapping(value = "/equalsearch")
public ResponseEntity<List<AuxiliaryImmobileDTO>> equalSearch(@RequestParam(value = "state", defaultValue = "") String state, @RequestParam(value = "city", defaultValue = "") String city, @RequestParam(value = "type", defaultValue = "") String type){
        state = URL.decodeParam(state);
        city = URL.decodeParam(city);
        type = URL.decodeParam(type);
        List<Immobile> list = service.equalSearch(state, city, type);
        List<AuxiliaryImmobileDTO> listDTO = list.stream().map(AuxiliaryImmobileDTO::new).collect(Collectors.toList());

        if (listDTO.isEmpty()) {
            throw new ObjectNotFoundException("Immobile not found with the search parameters: " + state + ", " + city + ", " + type);
        } else {
            return ResponseEntity.ok().body(listDTO);
        }
    }

    @PostMapping(value = "/vendor/{sellerId}")
    public ResponseEntity<Immobile> insert(@PathVariable Long sellerId, @RequestBody Immobile entity) {
        entity.setSeller(sellerService.findById(sellerId));
        entity = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/vendor/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/vendor/{sellerId}/{id}")
    public ResponseEntity<Void> update(@PathVariable Long sellerId, @PathVariable Long id, @RequestBody ImmobileDTO entityDTO) {
        entityDTO.setSeller(sellerService.findById(sellerId));
        Immobile newDataEntity = service.fromDTO(entityDTO);
        newDataEntity.setId(id);
        service.update(newDataEntity);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/vendor/{sellerId}")
    public ResponseEntity<List<ImmobileDTO>> findBySellerId(@PathVariable Long sellerId) {
        List<Immobile> list = service.findBySeller(sellerService.findById(sellerId));
        List<ImmobileDTO> listDTO = list.stream().map(ImmobileDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
