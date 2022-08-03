package com.mvpt.service.situation;

import com.mvpt.model.Situation;
import com.mvpt.model.dto.SituationDTO;
import com.mvpt.repository.SituationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SituationServiceImpl implements SituationService {

    @Autowired
    SituationRepository situationRepository;

    @Override
    public List<Situation> findAll() {
        return situationRepository.findAll();
    }

    @Override
    public Optional<Situation> findById(Long id) {
        return situationRepository.findById(id);
    }

    @Override
    public Situation getById(Long id) {
        return situationRepository.getById(id);
    }

    @Override
    public Situation save(Situation situation) {
        return situationRepository.save(situation);
    }

    @Override
    public void remove(Long id) {
        situationRepository.deleteById(id);
    }

    @Override
    public List<SituationDTO> getAllTypeDTO() {
        return situationRepository.getAllRoleDTO();
    }

    @Override
    public Situation findByName(String name) {
        return situationRepository.findByName(name);
    }
}
