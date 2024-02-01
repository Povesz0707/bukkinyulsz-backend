package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.controller.interfaces.BaseControllerInterface;
import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.User;
import com.example.bukkinyulszbackend.model.payload.login.JwtResponse;
import com.example.bukkinyulszbackend.model.payload.login.LoginRequest;
import com.example.bukkinyulszbackend.model.payload.signIn.SignInRequest;
import com.example.bukkinyulszbackend.services.UserService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_USER)
public class UserController extends BaseController implements BaseControllerInterface<User> {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(AppConstant.URI_LOGIN)
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws BusinessException{
        JwtResponse response = this.userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping(AppConstant.URI_API_SIGN_IN)
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseEntity<User> signIn(@RequestBody SignInRequest signInRequest) throws BusinessException{
        User insertedUser = this.userService.signIn(signInRequest);
        return new ResponseEntity<>(insertedUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> list() throws BusinessException {
        return null;
    }

    @Override
    public ResponseEntity<User> getById(long id) throws BusinessException {
        return null;
    }

    @Override
    public ResponseEntity<User> add(User newData) throws BusinessException {
        return null;
    }

    @Override
    public ResponseEntity<User> edit(User data) throws BusinessException {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        return null;
    }
}
