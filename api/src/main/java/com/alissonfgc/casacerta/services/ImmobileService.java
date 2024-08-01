package com.alissonfgc.casacerta.services;

import com.alissonfgc.casacerta.dto.ImmobileDTO;
import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.entities.Seller;
import com.alissonfgc.casacerta.repository.ImmobileRepository;
import com.alissonfgc.casacerta.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImmobileService {

    private final ImmobileRepository repository;

    public ImmobileService(ImmobileRepository repository) {
        this.repository = repository;
    }

    public List<Immobile> findAll() {
        return repository.findAll();
    }

    public Immobile findById(Long id) {
        Optional<Immobile> object = repository.findById(id);
        return object.orElseThrow(() -> new ResourceNotFoundException(id + ", Immobile not found"));
    }

    public List<Immobile> findBySeller(Seller seller) {
        return repository.findBySeller(seller);
    }

    public Immobile save(Immobile immobile) {
        return repository.save(immobile);
    }

    public void delete(Long id ) {
        findById(id);
        repository.deleteById(id);
    }

//    public List<Immobile>

    public Immobile fromDTO(ImmobileDTO objDTO) {
        return new Immobile(new Seller(objDTO.getVendor().getId(), objDTO.getVendor().getName(), objDTO.getVendor().getEmail(), objDTO.getVendor().getPhoneNumber(), null, null), objDTO.getImageURL(),objDTO.getPostcode(), objDTO.getType(), objDTO.getLatitude(), objDTO.getLongitude(), objDTO.getTotalArea(), objDTO.getState(), objDTO.getCity(), objDTO.getNeighborhood(), objDTO.getCreationDate(), objDTO.getDescription(), objDTO.getTitle(), objDTO.getId());
    }
}
