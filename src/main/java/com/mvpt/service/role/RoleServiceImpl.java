//package com.mvpt.service.role;
//
//import com.mvpt.model.Role;
//import com.mvpt.repository.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class RoleServiceImpl implements RoleService {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public List<Role> findAll() {
//        return roleRepository.findAll();
//    }
//
//    @Override
//    public Optional<Role> findById(Long id) {
//        return roleRepository.findById(id);
//    }
//
//    @Override
//    public Role getById(Long id) {
//        return null;
//    }
//
//    @Override
//    public Role save(Role role) {
//        return roleRepository.save(role);
//    }
//
//    @Override
//    public void remove(Long id) {
//        roleRepository.deleteById(id);
//    }
//
//    @Override
//    public Role findByName(String name) {
//        return roleRepository.findByName(name);
//    }
//}
