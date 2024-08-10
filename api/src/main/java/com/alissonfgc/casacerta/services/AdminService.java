package com.alissonfgc.casacerta.services;

import com.alissonfgc.casacerta.dto.AdminDTO;
import com.alissonfgc.casacerta.entities.Admin;
import com.alissonfgc.casacerta.repository.AdminRepository;
import com.alissonfgc.casacerta.services.exceptions.ResourceNotFoundException;
import com.alissonfgc.casacerta.services.exceptions.UniqueException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
final public class AdminService {

    private final AdminRepository repository;

    public AdminService(AdminRepository adminRepository) {
        this.repository = adminRepository;
    }

    public List<Admin> findAll() {
        return repository.findAll();
    }

    public Admin findByEmail(String email) {
        Optional<Admin> object = Optional.ofNullable(repository.findByEmail(email));
        return object.orElseThrow(() -> new ResourceNotFoundException(email + ", Admin not found"));
    }

    private Admin findById(String id) {
        Optional<Admin> object = repository.findById(id);
        return object.orElseThrow(() -> new ResourceNotFoundException(id + ", Admin not found"));
    }

    public Admin insert(Admin admin) {
        if (repository.existsById(admin.getId())) {
            throw new UniqueException("Admin already exists, Admin Email: " + admin.getEmail());
        } else {
            return repository.save(admin);
        }
    }

    public void delete(Admin admin) {
        repository.delete(admin);
    }

    public Admin update(Admin newDataObj) {
        Admin updatedDataObj = findById(newDataObj.getId());
        updateData(updatedDataObj, newDataObj);
        return repository.save(updatedDataObj);
    }

    private void updateData(Admin oldDataObj, Admin newDataObj) {
        oldDataObj.setName(newDataObj.getName());
        oldDataObj.setEmail(newDataObj.getEmail());
        oldDataObj.setPhoneNumber(newDataObj.getPhoneNumber());
        oldDataObj.setRegistrationNumber(newDataObj.getRegistrationNumber());
        oldDataObj.setPassword(newDataObj.getPassword());
    }

    public Admin fromDTO(AdminDTO objDTO) {
        return new Admin(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), objDTO.getPhoneNumber(), objDTO.getRegistrationNumber(), objDTO.getPassword());
    }
}
