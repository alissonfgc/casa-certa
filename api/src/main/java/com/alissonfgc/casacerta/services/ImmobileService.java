package com.alissonfgc.casacerta.services;

import com.alissonfgc.casacerta.dto.AuxiliaryImmobileDTO;
import com.alissonfgc.casacerta.dto.ImmobileDTO;
import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.entities.Seller;
import com.alissonfgc.casacerta.repository.ImmobileRepository;
import com.alissonfgc.casacerta.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Immobile update(Immobile newDataEntity) {
        Immobile updatedEntity = findById(newDataEntity.getId());
        updateData(updatedEntity, newDataEntity);
        return repository.save(updatedEntity);
    }

    public List<Immobile> findBySeller(Seller seller) {
        return repository.findBySeller(seller);
    }

    public List<Immobile> equalSearch(String state, String city, String type) {
        List<Immobile> searchList = new ArrayList<Immobile>();
        if (state.isEmpty() && city.isEmpty() && type.isEmpty()) {
            return findAll();
        } else if (!state.isEmpty() && !city.isEmpty() && type.isEmpty()) {
            return repository.findByStateAndCityIgnoreCase(state, city);
        } else if (!state.isEmpty() && city.isEmpty() && !type.isEmpty()) {
            return repository.findByStateAndTypeIgnoreCase(state, type);
        } else if (state.isEmpty() && !city.isEmpty() && !type.isEmpty()) {
            return repository.findByCityAndTypeIgnoreCase(city, type);
        }else if ((!(state.isEmpty() && city.isEmpty()) && type.isEmpty())) {
            return repository.findByStateAndCityAndTypeIgnoreCase(state, city, type);
        } else {
            return repository.findByStateOrCityOrTypeIgnoreCase(state, city, type);
        }
    }

    public List<Immobile> fullSearch(String title, String description, String neighborhood) {
        return repository.fullSearch(title.toUpperCase().trim(), description.toUpperCase().trim(), neighborhood.toUpperCase().trim());
    }

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
