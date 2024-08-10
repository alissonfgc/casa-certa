package com.alissonfgc.casacerta.services;

import com.alissonfgc.casacerta.dto.SellerDTO;
import com.alissonfgc.casacerta.entities.Seller;
import com.alissonfgc.casacerta.repository.SellerRepository;
import com.alissonfgc.casacerta.services.exceptions.ResourceNotFoundException;
import com.alissonfgc.casacerta.services.exceptions.UniqueException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private final SellerRepository repository;

    public SellerService(SellerRepository repository) {
        this.repository = repository;
    }

    public List<Seller> findAll() {
        return repository.findAll();
    }

    public Seller findByEmail(String email) {
        Optional<Seller> object = Optional.ofNullable(repository.findByEmail(email));
        return object.orElseThrow(() -> new ResourceNotFoundException(email + ", Seller not found"));
    }

    public Seller insert(Seller object) {
        if (repository.findByEmail(object.getEmail()) != null) {
            throw new UniqueException("Email already exists");
        } else {
            return repository.save(object);
        }
    }

    public void delete(String email) {
        repository.delete(repository.findByEmail(email));
    }

    public Seller update(Seller newDataObj) {
        Seller updatedDataObj = findByEmail(newDataObj.getEmail());
        updateData(updatedDataObj, newDataObj);
        return repository.save(updatedDataObj);
    }

    private void updateData(Seller oldDataObj, Seller newDataObj) {
        oldDataObj.setName(newDataObj.getName());
        oldDataObj.setEmail(newDataObj.getEmail());
        oldDataObj.setPhoneNumber(newDataObj.getPhoneNumber());
        oldDataObj.setRegistrationNumber(newDataObj.getRegistrationNumber());
        oldDataObj.setPassword(newDataObj.getPassword());
        oldDataObj.setCompanyName(newDataObj.getCompanyName());
    }

    public Seller fromDTO(SellerDTO objDTO) {
        return new Seller(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), objDTO.getPhoneNumber(), objDTO.getRegistrationNumber(), objDTO.getPassword(), objDTO.getCompanyName());
    }
}
