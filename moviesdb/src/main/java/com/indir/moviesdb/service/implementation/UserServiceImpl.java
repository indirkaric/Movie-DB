package com.indir.moviesdb.service.implementation;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.domain.User;
import com.indir.moviesdb.dto.UserDto;
import com.indir.moviesdb.exception.NotFoundException;
import com.indir.moviesdb.mapper.UserMapper;
import com.indir.moviesdb.repository.*;
import com.indir.moviesdb.repository.filter.UserSearchFilter;
import com.indir.moviesdb.repository.filter.spec.UserSearchSpecification;
import com.indir.moviesdb.service.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        try{
            List<User> tempUsers = new ArrayList<>();
            userRepository.findAll().iterator().forEachRemaining(tempUsers::add);
            for(User user:tempUsers){
                UserDto userDto = userMapper.toDto(user);
                users.add(userDto);
            }
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }

        return users;
    }

    @Override
    public UserDto findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new NotFoundException(Constants.USER_NOT_FOUND + id);
        }
        return userMapper.toDto(user.get());
    }

    @Override
    public ResponseEntity<UserDto> saveUser(UserDto userDto) {
        User tempUser = userMapper.toEntity(userDto);
        tempUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserDto user = userMapper.toDto(userRepository.save(tempUser));
        return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userMapper.toEntity(findById(id));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getUsersWithOrders() {
        List<UserDto> users;
        try{
            List<User> tempUsers = new ArrayList<>();
            userRepository.getUsersWithOrders().iterator().forEachRemaining(tempUsers::add);
            users = tempUsers.stream().map(userMapper::toDto).collect(Collectors.toList());
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return users;
    }

    @Override
    public List<UserDto> getUsersWithoutOrders() {
        List<UserDto> users = new ArrayList<>();
        try{
            List<User> tempUsers = new ArrayList<>();
            userRepository.getUsersWithoutOrders().iterator().forEachRemaining(tempUsers::add);
            users = tempUsers.stream().map(userMapper::toDto).collect(Collectors.toList());
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return users;
    }

    @Override
    public Page<UserDto> getUsers(UserSearchFilter filter, Pageable pageable) {
        Specification<User> specification = new UserSearchSpecification(filter);
        return userRepository.findAll(specification, pageable).map(userMapper::toDto);
    }


    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public User requestPasswordReset(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(Constants.USER_NOT_FOUND_EMAIL + email));
        user.setResetKey(UUID.randomUUID().toString());
        user.setResetDate(new Date());
        mailService.sendPasswordResetMail(user);
        return userRepository.save(user);
    }

    @Override
    public User completePasswordReset(String newPassword, String key) throws Exception {
        User user = userRepository.findByResetKey(key).orElseThrow(()-> new NotFoundException(Constants.RESET_KEY_NOT_FOUND + key ));
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetKey(null);
        user.setResetDate(null);

        return userRepository.save(user);
    }

    @Override
    public List<UserDto> getUsersOrdersInfo(boolean exist) {
        List<UserDto> users;
        if(exist == true)
            users = getUsersWithOrders();
        else if(exist == false)
            users = getUsersWithoutOrders();
        else
            throw new IllegalArgumentException(Constants.TRUE_OR_FALSE);
        return users;
    }


}
