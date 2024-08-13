package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.dto.ImmobileDTO;
import com.alissonfgc.casacerta.dto.SellerDTO;
import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.entities.Seller;
import com.alissonfgc.casacerta.services.ImmobileService;
import com.alissonfgc.casacerta.services.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/vendors")
public class SellerResource {

    private final SellerService service;

    private final ImmobileService immobileService;

    public SellerResource(SellerService service, ImmobileService immobileService) {
        this.service = service;
        this.immobileService = immobileService;
    }

    //alterar para que o usuario altere somente o propio cadastro
    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> delete(@PathVariable String email) {
        service.delete(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{email}")
    public ResponseEntity<Void> update(@RequestBody SellerDTO objDTO, @PathVariable String email) {
        Seller newDataObj = service.fromDTO(objDTO);
        newDataObj.setEmail(email);
        newDataObj = service.update(newDataObj);
        return ResponseEntity.noContent().build();
    }

    //---------------------------------IMOVEIS
    //alterar para que ele so consiga alterar os propios imoveis
    @PostMapping(value = "/immobile/{email}")
    public ResponseEntity<Immobile> insertImmobile(@PathVariable String email, @RequestBody Immobile entity) {
        entity.setSeller(service.findByEmail(email));
        entity = immobileService.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //IMOVEIS
    //alterar para que ele so consiga deletar os propios imoveis
    @DeleteMapping(value = "/immobile/{id}")
    public ResponseEntity<Void> deleteImmobile(@PathVariable String id) {
        immobileService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //IMOVEIS
    //alterar para que ele so consiga alterar os propios imoveis
    @PutMapping(value = "/immobile/{email}/{id}")
    public ResponseEntity<Void> updateImmobile(@PathVariable String email, @PathVariable String id, @RequestBody ImmobileDTO entityDTO) {
        entityDTO.setSeller(service.findByEmail(email));
        Immobile newDataEntity = immobileService.fromDTO(entityDTO);
        newDataEntity.setId(id);
        immobileService.update(newDataEntity);
        return ResponseEntity.noContent().build();
    }

    //VENDEDORES
    //alterar para que ele so consiga ver os propios
    @GetMapping(value = "/immobile/{email}")
    public ResponseEntity<List<ImmobileDTO>> findImmobileBySellerEmail(@PathVariable String email) {
        List<Immobile> list = immobileService.findBySeller(service.findByEmail(email));
        List<ImmobileDTO> listDTO = list.stream().map(ImmobileDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
