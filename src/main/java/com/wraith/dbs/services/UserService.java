package com.wraith.dbs.services;

import com.wraith.dbs.dto.UserDTO;
import com.wraith.dbs.interfaces.IService;
import com.wraith.dbs.models.User;
import com.wraith.dbs.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class UserService implements IService<User, UserDTO>
{
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserService(ModelMapper modelMapper, UserRepository userRepository)
    {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public User findFirstByEmail(String email) { return userRepository.findFirstByEmail(email); }

    @Override
    public User save(User user)
    {
        user.setUpdated_at(OffsetDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public void delete(User entity) { userRepository.delete(entity); }

    @Override
    public User convertToEntity(UserDTO dto) { return modelMapper.map(dto, User.class); }

    @Override
    public UserDTO convertToDTO(User entity) { return modelMapper.map(entity, UserDTO.class); }

    public User findFirstById(String id) { return userRepository.findFirstById(id); }

    public User findById(String id) { return userRepository.findById(id).orElse(null); }
}
