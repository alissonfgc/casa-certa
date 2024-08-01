package com.alissonfgc.casacerta.services;

import com.alissonfgc.casacerta.dto.ClientDTO;
import com.alissonfgc.casacerta.entities.Client;
import com.alissonfgc.casacerta.repository.ClientRepository;
import com.alissonfgc.casacerta.services.exceptions.ResourceNotFoundException;
import com.alissonfgc.casacerta.services.exceptions.UniqueException;
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
        Optional<Client> object = repository.findById(id);
        return object.orElseThrow(() -> new ResourceNotFoundException(id + ", Client not found"));
    }

    public Client insert(Client object) {
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

    public Client update(Client newDataObj) {
        Client updatedDataObj = findById(newDataObj.getId());
        updateData(updatedDataObj, newDataObj);
        return repository.save(updatedDataObj);
    }

    private void updateData(Client oldDataObj, Client newDataObj) {
        oldDataObj.setName(newDataObj.getName());
        oldDataObj.setEmail(newDataObj.getEmail());
        oldDataObj.setPhoneNumber(newDataObj.getPhoneNumber());
        oldDataObj.setIndividualRegistration(newDataObj.getIndividualRegistration());
        oldDataObj.setPassword(newDataObj.getPassword());
    }

    public Client fromDTO(ClientDTO objDTO) {
        return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), objDTO.getPhoneNumber(), objDTO.getIndividualRegistration(), objDTO.getPassword());
    }
}