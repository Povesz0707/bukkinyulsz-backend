package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "permission")
@SequenceGenerator(name = "id_seq", sequenceName = "permission_id_seq", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "permission_id"))
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE permission SET enabled = false WHERE permission_id = ?")
@Where(clause = BaseEntity.WHERE)
@Getter
@Setter
public class Permission extends BaseEntity implements BaseEntityInterface<Permission>{
    public static final String WHERE = "p.enabled = true";

    private String permission;


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id", columnDefinition = "bigint", nullable = false, referencedColumnName = "app_user_id")
    @JsonBackReference(value = "app_user-permission")
    private User user;

    public Permission() {
    }

    public Permission(User user, String permission) {
        this.user = user;
        this.permission = permission;
    }
    @Override
    public void edit(Permission item) {

    }
}
