package com.alissonfgc.casacerta.services;

import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.repository.ImmobileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImmobileService {

    private final ImmobileRepository repository;

    public ImmobileService(ImmobileRepository repository) {
        this.repository = repository;
    }

    public List<Immobile> findAll() {
        return repository.findAll();
    }
}
