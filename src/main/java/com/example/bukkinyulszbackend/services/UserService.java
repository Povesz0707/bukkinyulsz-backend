package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Permission;
import com.example.bukkinyulszbackend.model.User;
import com.example.bukkinyulszbackend.model.payload.login.JwtResponse;
import com.example.bukkinyulszbackend.model.payload.login.LoginRequest;
import com.example.bukkinyulszbackend.model.payload.signIn.SignInRequest;
import com.example.bukkinyulszbackend.repository.UserRepository;
import com.example.bukkinyulszbackend.security.data.CustomUserDetails;
import com.example.bukkinyulszbackend.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService extends BaseService implements BaseServiceInterface<User>{
    private UserRepository userRepository;
    private PermissionService permissionService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    private String signInKey;
    @Value("${main.signInKey}")
    public void setSignInKey(String signInKey) {
        this.signInKey = signInKey;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private PasswordEncoder encoder;
    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        return null;
    }

    @Override
    public User add(User data) throws BusinessException {
        return null;
    }

    @Override
    public List<User> list() throws BusinessException {
        return null;
    }

    @Override
    public User getById(long id) throws BusinessException {
        return null;
    }

    @Override
    public User edit(User data) throws BusinessException {
        return null;
    }

    @Transactional(rollbackFor = BusinessException.class)
    public User signIn(SignInRequest signInRequest) throws BusinessException {
        if(this.signInKey.equals(signInRequest.getSignInKey())){
        Optional<List<User>> optionalUser = this.userRepository.findAllByEmail(signInRequest.getEmail());
        if(!optionalUser.isPresent() || optionalUser.get().isEmpty()){
            User user = new User();
            user.setEmail(signInRequest.getEmail());
            user.setUsername(signInRequest.getUsername());
            user.setPassword(encoder.encode(signInRequest.getPassword()));
            User insertedUser = this.userRepository.save(user);
            permissionService.add(new Permission(insertedUser, "USER_ROLE"));
            return insertedUser;
        }
        else {
            throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_EMAIL_IS_ALREADY_USED);
        }
        }
        return null;
    }

    @Transactional(rollbackFor = BusinessException.class)
    public JwtResponse login(LoginRequest loginRequest) throws BusinessException{
        System.out.println("BEJÖN");
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(auth);
            String jwt = jwtUtil.generateJwtToken(auth);
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
             return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getDisplayName(),  userDetails.getEmail(), roles);
        }catch (Exception ex){
            throw new BusinessException(ex.getMessage(), ex);
        }
    }
}
