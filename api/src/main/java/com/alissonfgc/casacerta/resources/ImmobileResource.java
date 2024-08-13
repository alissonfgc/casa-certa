package com.alissonfgc.casacerta.resources;

import com.alissonfgc.casacerta.dto.AuxiliaryImmobileDTO;
import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.resources.util.URL;
import com.alissonfgc.casacerta.services.ImmobileService;
import com.alissonfgc.casacerta.services.exceptions.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/immobiles")
public class ImmobileResource {

    private final ImmobileService service;

    public ImmobileResource(ImmobileService service) {
        this.service = service;
    }

    //TODOS
    @GetMapping
    public ResponseEntity<List<AuxiliaryImmobileDTO>> findAll() {
        List<Immobile> list = service.findAll();
        List<AuxiliaryImmobileDTO> listDTO = list.stream().map(AuxiliaryImmobileDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    //TODOS
    @GetMapping(value = "/{id}")
    public ResponseEntity<AuxiliaryImmobileDTO> findById(@PathVariable String id) {
        Immobile entity = service.findById(id);
        return ResponseEntity.ok().body(new AuxiliaryImmobileDTO(entity));
    }

    //TODOS
    @GetMapping(value = "/equalsearch")
    public ResponseEntity<List<AuxiliaryImmobileDTO>> equalSearch(@RequestParam(value = "state", defaultValue = "") String state, @RequestParam(value = "city", defaultValue = "") String city, @RequestParam(value = "type", defaultValue = "") String type) {
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

    //TODOS
    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<AuxiliaryImmobileDTO>> fullSearch(@RequestParam(value = "title", defaultValue = "none") String title, @RequestParam(value = "description", defaultValue = "none") String description, @RequestParam(value = "neighborhood", defaultValue = "none") String neighborhood) {
        title = URL.decodeParam(title);
        description = URL.decodeParam(description);
        neighborhood = URL.decodeParam(neighborhood);
        List<Immobile> list = service.fullSearch(title, description, neighborhood);
        List<AuxiliaryImmobileDTO> listDTO = list.stream().map(AuxiliaryImmobileDTO::new).collect(Collectors.toList());

        if (listDTO.isEmpty()) {
            throw new ObjectNotFoundException("Immobile not found with the search parameters: " + title + ", " + description + ", " + neighborhood);
        } else {
            return ResponseEntity.ok().body(listDTO);
        }
    }
}
