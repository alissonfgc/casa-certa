package com.alissonfgc.casacerta.services;

import com.alissonfgc.casacerta.dto.UserDTO;
import com.alissonfgc.casacerta.entities.User;
import com.alissonfgc.casacerta.repository.UserRepository;
import com.alissonfgc.casacerta.services.exceptions.ResourceNotFoundException;
import com.alissonfgc.casacerta.services.exceptions.UniqueException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> object = repository.findById(id);
        return object.orElseThrow(() -> new ResourceNotFoundException(id + ", User not found"));
    }

    public User insert(User object) {
        if (repository.findUserByEmail(object.getEmail()) != null) {
            throw new UniqueException("Email already exists");
        } else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(object.getPassword());
            object.setPassword(encryptedPassword);
            return repository.save(object);
        }
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(User newDataObj) {
        User updatedDataObj = findById(newDataObj.getId());
        updateData(updatedDataObj, newDataObj);
        return repository.save(updatedDataObj);
    }

    private void updateData(User oldDataObj, User newDataObj) {
        oldDataObj.setName(newDataObj.getName());
        oldDataObj.setEmail(newDataObj.getEmail());
        oldDataObj.setPhoneNumber(newDataObj.getPhoneNumber());
        oldDataObj.setRegistrationNumber(newDataObj.getRegistrationNumber());
        oldDataObj.setPassword(newDataObj.getPassword());
    }

    public User fromDTO(UserDTO objDTO) {
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), objDTO.getPhoneNumber(), objDTO.getRegistrationNumber(), objDTO.getPassword());
    }

    public User findByEmail(String email) {
        Optional<User> object = Optional.ofNullable(repository.findUserByEmail(email));
        return object.orElseThrow(() -> new ResourceNotFoundException(email + ", User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
}