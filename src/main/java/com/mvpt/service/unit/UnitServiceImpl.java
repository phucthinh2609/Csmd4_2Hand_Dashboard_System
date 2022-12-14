package com.mvpt.service.unit;

import com.mvpt.model.Unit;
import com.mvpt.model.dto.UnitDTO;
import com.mvpt.repository.UnitRepository;
import com.mvpt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    @Override
    public List<UnitDTO> getAllUnitDTO() {
        return unitRepository.getAllUnitDTO();
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
    public Optional<UnitDTO> getUnitDTOById(Long unitId) {
        return unitRepository.getUnitDTOById(unitId);
    }

    @Override
    public Optional<UnitDTO> getUnitDTOByCodeAndIdIsNot(String code, Long id) {
        return unitRepository.getUnitDTOByCodeAndIdIsNot(code, id);
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
