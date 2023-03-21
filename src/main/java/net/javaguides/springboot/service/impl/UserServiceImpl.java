package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{
    private UserRepository userRepository;
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent())
            throw new EmailAlreadyExistsException("email exists");

        // convert useerdto into user JPA Entity

        // User user = UserMapper.mapToUser(userDto);
        // User user = modelMapper.map(userDto,User.class);
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        // convert User Jpa entity to user UserDto
        // UserDto savedUserDto = UserMapper.mapToUserDto(user);
        // UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User","id",userId));
        // UserDto userDto = UserMapper.mapToUserDto(user);
        // UserDto userDto = modelMapper.map(user, UserDto.class);
        UserDto userDto = AutoUserMapper.MAPPER.mapToUserDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> userList = userRepository.findAll();
//        List<UserDto> userDtoList = userList.stream()
//                .map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());

//        List<UserDto> userDtoList = userList.stream()
//                .map(user -> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());

        List<UserDto> userDtoList = userList.stream()
                .map(user -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());

        return userDtoList;
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User","id",user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);

        // UserDto updatedUserDto = UserMapper.mapToUserDto(updatedUser);
        UserDto updatedUserDto = AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
        return updatedUserDto;
    }

    @Override
    public void deleteUser(Long userId) {
        User userToDelete = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId)
        );
        userRepository.deleteById(userToDelete.getId());
    }


}
