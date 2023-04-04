package com.example.bukkinyulszbackend.model.payload.login;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String displayName;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String displayName, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        JwtResponse that = (JwtResponse) o;
        return new EqualsBuilder()
                .append(token, that.token)
                .append(type, that.type)
                .append(id, that.id)
                .append(username, that.username)
                .append(displayName, that.displayName)
                .append(email, that.email)
                .append(roles, that.roles)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(token)
                .append(type)
                .append(id)
                .append(username)
                .append(displayName)
                .append(email)
                .append(roles)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("token", token)
                .append("type", type)
                .append("id", id)
                .append("username", username)
                .append("displayName", displayName)
                .append("email", email)
                .append("roles", roles)
                .toString();
    }
}
