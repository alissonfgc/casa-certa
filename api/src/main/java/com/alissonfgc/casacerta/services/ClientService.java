package com.alissonfgc.casacerta.services;

import com.alissonfgc.casacerta.entities.Client;
import com.alissonfgc.casacerta.repository.ClientRepository;
import com.alissonfgc.casacerta.services.exceptions.DatabaseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client findById(Long id) {
        Optional<Client> obj = repository.findById(id);
        return obj.get();
    }

    public Client insert(Client o) {
        return repository.save(o);
    }

    public void delete(Long id) {
        try {
            if (findById(id) != null) {
                repository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
//
//    public User update(Long id, User obj) {
//        try {
//            User entity = repository.getReferenceById(id);
//            updateData(entity, obj);
//            return repository.save(entity);
//        } catch (EntityNotFoundException e) {
//            throw new ResourceNotFoundException(id);
//        }
//    }
//
//    private void updateData(User entity, User obj) {
//        entity.setName(obj.getName());
//        entity.setEmail(obj.getEmail());
//        entity.setPhone(obj.getPhone());
//    }
}