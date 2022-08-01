package com.mvpt.service.unit;

import com.mvpt.model.Unit;
import com.mvpt.repository.UnitRepository;
import com.mvpt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    @Override
    public Optional<Unit> findById(Long id) {
        return unitRepository.findById(id);
    }

    @Override
    public Unit getById(Long id) {
        return unitRepository.getById(id);
    }

    @Override
    public Unit save(Unit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public void remove(Long id) {
        unitRepository.deleteById(id);
    }
}
