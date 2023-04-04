package com.example.bukkinyulszbackend.model;

import com.example.bukkinyulszbackend.security.util.AuthenticationUtil;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
@AttributeOverride(name = "id", column = @Column(name = "app_user_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "app_user_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE app_user SET enabled = false WHERE app_user_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
@ToString
public class User extends BaseEntity implements  BaseEntityInterface<User>{
    private String username;
    private String email;
    private String password;
    private String displayName;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "app_user-permission")
    private Set<Permission> permission = new HashSet<>();


    public static final String WHERE = "u.enabled = true";
    @Override
    public void edit(User item) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
    }

    public User() {
    }

    public User(Long id, String username, Boolean enabled , String email,  String password, String displayName ) {
        super(id, enabled);
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
    }

    public boolean verifyPassword(final String password) {
        if (!StringUtils.isEmpty(this.password)) {
            return AuthenticationUtil.passwordVerify(password, this.password);
        }
        return false;
    }
}
