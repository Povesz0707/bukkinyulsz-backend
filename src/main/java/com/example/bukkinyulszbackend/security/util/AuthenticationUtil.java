package com.example.bukkinyulszbackend.security.util;

import com.example.bukkinyulszbackend.security.data.CustomUserDetails;
import com.example.bukkinyulszbackend.security.data.PermissionType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.validation.constraints.NotNull;

public class AuthenticationUtil {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationUtil.class);

    public static boolean passwordVerify(final String password, final String passwordHash) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }
        if (StringUtils.isEmpty(passwordHash)) {
            return false;
        }
        if (passwordHash.length() == 32) {
            return passwordHash.equals(CommonUtil.getMd5(password));
        } else if ((passwordHash.length() == 60) && ((passwordHash.startsWith("$2a$")) || (passwordHash.startsWith("$2y$")))) {
            return BCrypt.checkpw(password, passwordHash);
        } else {
            return false;
        }
    }



    public static CustomUserDetails getAuthPrincipal(Authentication auth) {
        log.debug("param: auth: {}", auth);
        if (auth == null) {
            return null;
        }
        Object objectPrincipal = auth.getPrincipal();
        log.debug("auth.getPrincipal(): {}", objectPrincipal);
        if (objectPrincipal == null) {
            return null;
        }
        if (!(objectPrincipal instanceof CustomUserDetails)) {
            log.warn("auth.getPrincipal().getClass(): {}, auth.getPrincipal(): {}", objectPrincipal.getClass(), objectPrincipal);
            return null;
        }
        return (CustomUserDetails) objectPrincipal;
    }

    public static boolean getAuthPrincipalHasPermission(final Authentication auth, @NotNull final PermissionType permission) {
        log.debug("param: auth: {}, permission: {}", auth, permission);
        final CustomUserDetails principal = getAuthPrincipal(auth);
        if ((principal == null) || (principal.getAuthorities() == null)) {
            return false;
        }
        for (final GrantedAuthority authority : principal.getAuthorities()) {
            final PermissionType authorityPermission = PermissionType.valueOf(authority.getAuthority());
            if (authorityPermission.compareTo(permission) >= 0) {
                log.debug("return true (has {})", authorityPermission);
                return true;
            }
        }
        log.debug("return false");
        return false;
    }
}
