package com.project.demo.logic;

import com.project.demo.entity.Lodge;
import com.project.demo.repository.LodgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LodgeService implements IService<Lodge,Integer>{

    @Autowired
    private LodgeRepository lodgeRepository;

    @Override
    public Lodge save(Lodge entity) {
        return lodgeRepository.save(entity);
    }

    @Override
    public List<Lodge> findAll() {
        return lodgeRepository.findAll();
    }

    @Override
    public Optional<Lodge> findById(Integer integer) {
        return lodgeRepository.findById(integer);
    }
    @Override
    public Lodge update(Lodge entity) {
        return lodgeRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        lodgeRepository.logicalDeleteById(integer);
    }

}
