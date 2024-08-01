package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.dto.ImmobileDTO;
import com.alissonfgc.casacerta.dto.SellerDTO;
import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.entities.Seller;
import com.alissonfgc.casacerta.services.ImmobileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/immobiles")
public class ImmobileResource {

    private final ImmobileService service;

    public ImmobileResource(ImmobileService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ImmobileDTO>> findAll() {
        List<Immobile> list = service.findAll();
        List<ImmobileDTO> listDTO = list.stream().map(ImmobileDTO::new).collect(Collectors.toList());
//        for (ImmobileDTO i : list) {
//            i.getSeller().setPassword(null);
//        }
        return ResponseEntity.ok().body(listDTO);
    }


}
