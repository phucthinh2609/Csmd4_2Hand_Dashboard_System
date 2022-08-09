package com.mvpt.service.user;

import com.mvpt.model.LocationRegion;
import com.mvpt.model.User;
import com.mvpt.model.UserInfo;
import com.mvpt.model.UserPrinciple;
import com.mvpt.model.dto.UserDTO;
import com.mvpt.model.dto.UserInfoDTO;
import com.mvpt.repository.LocationRegionRepository;
import com.mvpt.repository.UserInfoRepository;
import com.mvpt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserDTO> getAllUserDTOByDeletedIsFalse() {
        return userRepository.getAllUserDTOByDeletedIsFalse();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserDTO> findProductDTOByEmailAndIdIsNot(String email, Long id) {
        return userRepository.findUserDTOByEmailAndIdIsNot(email, id);
    }

    @Override
    public Optional<UserDTO> getUserDTOById(Long id) {
        return userRepository.getUserDTOById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDTO saveDTO(UserDTO userDTO) {
        LocationRegion locationRegion = locationRegionRepository.save(userDTO.toUser().getUserInfo().getLocationRegion());
        userDTO.getUserInfo().setLocationRegion(locationRegion.toLocationRegionDTO());

        UserInfoDTO userInfoDTO = userDTO.getUserInfo();
        UserInfo userInfo = userInfoDTO.toUserInfo();

        UserInfo newUserInfo = userInfoRepository.save(userInfo);

        userDTO.setUserInfo(newUserInfo.toUserInfoDTO());

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(userDTO.toUser()).toUserDTO();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<UserDTO> findUserDTOByEmail(String email) {
        return userRepository.findUserDTOByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
//        return (UserDetails) userOptional.get();
    }
}
