package com.example.bukkinyulszbackend.services;


import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.User;
import com.example.bukkinyulszbackend.repository.UserRepository;
import com.example.bukkinyulszbackend.security.data.CustomUserDetails;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    @NotNull
    public UserDetails loadUserByUsername(@NotNull final String username) throws BusinessException {
        try {
            log.debug("param: username: {}", username);
            final List<User> accounts = userRepository.queryLogin(username);
            if (accounts.size() != 1) {
                log.error("User not found with username: " + username);
                throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND);
            }
            final User account = accounts.get(0);
            this.userRepository.save(account);
            this.userRepository.flush();
            log.info("found account: {}", account);
            final CustomUserDetails userDetails = CustomUserDetails.build(account);
            System.out.println("userDetails: "+userDetails.getPassword());
            log.info("found user: {}", userDetails);
            return userDetails;
        }catch (Exception ex){
            throw new BusinessException(ex.getMessage(), ex);
        }
    }

}
