package com.main.project.springbootrestfulwebservices.services.serviceimpl;

import com.main.project.springbootrestfulwebservices.dto.UserDto;
import com.main.project.springbootrestfulwebservices.entity.User;
import com.main.project.springbootrestfulwebservices.exception.EmailAlreadyExistsException;
import com.main.project.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.main.project.springbootrestfulwebservices.repository.UserRepository;
import com.main.project.springbootrestfulwebservices.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user1 = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        User savedUser = userRepository.save(user1);

        return modelMapper.map(user1, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {

        // User user = modelMapper.map(userDto, User.class);
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        return modelMapper.map(user, UserDto.class);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {

        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.deleteById(id);
    }
}
