package com.indir.moviesdb.service;

import com.indir.moviesdb.domain.User;
import com.indir.moviesdb.dto.UserDto;
import com.indir.moviesdb.repository.filter.UserSearchFilter;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;


import java.util.List;


public interface UserService {
    List<UserDto> getAllUsers();
    UserDto findById(Integer id);
    ResponseEntity<UserDto> saveUser(UserDto userDto);
    void deleteUser(Integer id);
    List<UserDto> getUsersWithOrders();
    List<UserDto> getUsersWithoutOrders();
    Page<UserDto> getUsers(UserSearchFilter filter, Pageable pageable);
    User requestPasswordReset(String userEmail);
    User completePasswordReset(String newPassword, String key) throws Exception;
    List<UserDto> getUsersOrdersInfo(boolean exist);

}
