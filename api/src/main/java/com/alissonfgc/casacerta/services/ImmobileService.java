package com.alissonfgc.casacerta.services;

import com.alissonfgc.casacerta.dto.AuxiliaryImmobileDTO;
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

    public Immobile save(Immobile immobile) {
        return repository.save(immobile);
    }

    public void delete(Long id ) {
        findById(id);
        repository.deleteById(id);
    }

//    public List<Immobile>

    public Immobile update(Immobile newDataEntity) {
        Immobile updatedEntity = findById(newDataEntity.getId());
        updateData(updatedEntity, newDataEntity);
        return repository.save(updatedEntity);
    }

    public List<Immobile> findBySeller(Seller seller) {
        return repository.findBySeller(seller);
    }

//    public List<Immobile> findByState
//
//    findByNeighborhood
//    findImmobileByCity
//    findByState
//    findByType

    private void updateData(Immobile oldDataEntity, Immobile newDataEntity) {
        oldDataEntity.setTitle(newDataEntity.getTitle());
        oldDataEntity.setDescription(newDataEntity.getDescription());
        oldDataEntity.setCreationDate(newDataEntity.getCreationDate());
        oldDataEntity.setNeighborhood(newDataEntity.getNeighborhood());
        oldDataEntity.setCity(newDataEntity.getCity());
        oldDataEntity.setState(newDataEntity.getState());
        oldDataEntity.setTotalArea(newDataEntity.getTotalArea());
        oldDataEntity.setLongitude(newDataEntity.getLongitude());
        oldDataEntity.setLatitude(newDataEntity.getLatitude());
        oldDataEntity.setType(newDataEntity.getType());
        oldDataEntity.setPostcode(newDataEntity.getPostcode());
        oldDataEntity.setImageURL(newDataEntity.getImageURL());
        oldDataEntity.setSeller(newDataEntity.getSeller());
    }

    public Immobile fromDTO(ImmobileDTO objDTO) {
        return new Immobile(objDTO.getSeller(), objDTO.getImageURL(),objDTO.getPostcode(), objDTO.getType(), objDTO.getLatitude(), objDTO.getLongitude(), objDTO.getTotalArea(), objDTO.getState(), objDTO.getCity(), objDTO.getNeighborhood(), objDTO.getCreationDate(), objDTO.getDescription(), objDTO.getTitle(), objDTO.getId());
    }

    public Immobile auxiliaryFromDTO(AuxiliaryImmobileDTO objDTO) {
        return new Immobile(new Seller(objDTO.getVendor().getId(), objDTO.getVendor().getName(), objDTO.getVendor().getEmail(), objDTO.getVendor().getPhoneNumber(), null, null), objDTO.getImageURL(),objDTO.getPostcode(), objDTO.getType(), objDTO.getLatitude(), objDTO.getLongitude(), objDTO.getTotalArea(), objDTO.getState(), objDTO.getCity(), objDTO.getNeighborhood(), objDTO.getCreationDate(), objDTO.getDescription(), objDTO.getTitle(), objDTO.getId());
    }
}
