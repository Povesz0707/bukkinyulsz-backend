package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "registration")
@AttributeOverride(name = "id", column = @Column(name = "registration_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "registration_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE registration SET enabled = false WHERE registration_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class Registration extends BaseEntity implements  BaseEntityInterface<Registration>{


    @Override
    public void edit(Registration item) {

    }
}
