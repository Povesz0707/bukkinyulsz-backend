package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "billing_detail")
@AttributeOverride(name = "id", column = @Column(name = "billing_detail_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "billing_detail_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE billing_detail SET enabled = false WHERE billing_detail_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class BillingDetail extends BaseEntity implements  BaseEntityInterface<BillingDetail>{
    private String registrationUUID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String zipCode;
    private String city;
    private String address;
    private String billingType;
    private String taxNumber;
    private String country;

    @Override
    public void edit(BillingDetail item) {

    }
}
