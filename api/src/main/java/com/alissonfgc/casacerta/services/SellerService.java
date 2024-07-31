package com.alissonfgc.casacerta.services;

import com.alissonfgc.casacerta.dto.SellerDTO;
import com.alissonfgc.casacerta.entities.Seller;
import com.alissonfgc.casacerta.repository.SellerRepository;
import com.alissonfgc.casacerta.services.exceptions.DatabaseException;
import com.alissonfgc.casacerta.services.exceptions.UniqueException;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Seller findById(Long id) {
        Optional<Seller> obj = repository.findById(id);
        return obj.get();
    }

    public Seller findByEmail(Seller object) {
        Optional<Seller> obj = Optional.ofNullable(repository.findByEmail(object.getEmail()));
        return obj.get();
    }

    public Seller insert(Seller object) {
        if (repository.findByEmail(object.getEmail()) != null) {
            throw new UniqueException("Email already exists");
        } else {
            return repository.save(object);
        }
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    public Seller update(Seller newDataObj) {
        Seller updatedDataObj = findById(newDataObj.getId());
        updateData(updatedDataObj, newDataObj);
        return repository.save(updatedDataObj);
    }

    private void updateData(Seller oldDataObj, Seller newDataObj) {
        oldDataObj.setName(newDataObj.getName());
        oldDataObj.setEmail(newDataObj.getEmail());
        oldDataObj.setPhoneNumber(newDataObj.getPhoneNumber());
        oldDataObj.setRegistrationNumber(newDataObj.getRegistrationNumber());
        oldDataObj.setPassword(newDataObj.getPassword());
    }

    public Seller fromDTO(SellerDTO objDTO) {
        return new Seller(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), objDTO.getPhoneNumber(), objDTO.getRegistrationNumber(), objDTO.getPassword());
    }
}
