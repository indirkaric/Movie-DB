package com.indir.moviesdb.controller;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.dao.UserAvgRateDao;
import com.indir.moviesdb.dto.*;
import com.indir.moviesdb.repository.filter.UserSearchFilter;
import com.indir.moviesdb.service.UserService;
import com.indir.moviesdb.service.util.PaginationUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.ROOT_PATH)
public class UserController {
    private final UserService userService;
    private final UserAvgRateDao userAvgRateDao;
    public static final String ROOT_PATH = "/api/v1/users";

    @ApiOperation(value = Constants.USER_FILTER)
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(@Valid UserSearchFilter filter, Pageable pageable){
        Page<UserDto> users = userService.getUsers(filter, pageable);
        HttpHeaders headers = PaginationUtils.generatePaginationHttpHeaders(users);
        return ResponseEntity.ok().headers(headers).body(users.getContent());
    }

    @ApiOperation(value = Constants.FIND_USER)
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
        UserDto userDto = userService.findById(id);
        return  new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @ApiOperation(value = Constants.DELETE_USER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
            userService.deleteUser(id);
            return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @ApiOperation(value = Constants.SAVE_USER)
    @PostMapping("")
    public ResponseEntity saveUser(@Valid @RequestBody UserDto userDto, BindingResult result){
        return userService.saveUser(userDto, result);
    }

    @ApiOperation(value = Constants.USERS_ORDER_INFO)
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getUsersOrdersInfo(@RequestParam boolean exist){
        List<UserDto> users = userService.getUsersOrdersInfo(exist);
        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);

    }

    @ApiOperation(value = Constants.USER_AVG_RATE)
    @GetMapping("/avg-rate")
    public ResponseEntity<List<UserAvgRateDto>> getUserAvgRate(){
        List<UserAvgRateDto> users = userAvgRateDao.getAverageRateByUser();
        return new ResponseEntity<List<UserAvgRateDto>>(users, HttpStatus.OK);

    }

    @ApiOperation(value = Constants.REQUEST_FOR_PASSWORD_RESET)
    @PostMapping("/reset-password")
    public void requestResetPassword(@RequestParam("email") String email){
        userService.requestPasswordReset(email);
    }

    @ApiOperation(value = Constants.COMPLETE_PASSWORD_RESET)
    @PostMapping("/reset/finish")
    public void finishPasswordReset(@RequestBody @Valid KeyAndNewPasswordDto keyAndPassword) throws Exception {
        userService.completePasswordReset(keyAndPassword.getPassword(), keyAndPassword.getKey());
    }

}
