package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.services.ImmobileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/immobiles")
public class ImmobileResource {

    private final ImmobileService service;

    public ImmobileResource(ImmobileService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Immobile>> findAll() {
        List<Immobile> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
