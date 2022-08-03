package com.mvpt.service.type;

import com.mvpt.model.Type;
import com.mvpt.model.dto.TypeDTO;
import com.mvpt.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public Type getById(Long id) {
        return typeRepository.getById(id);
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void remove(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public List<TypeDTO> getAllTypeDTO() {
        return typeRepository.getAllTypeDTO();
    }

    @Override
    public Type findByName(String name) {
        return typeRepository.findByName(name);
    }
}
