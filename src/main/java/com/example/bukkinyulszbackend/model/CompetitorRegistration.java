package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "competitor_registration")
@AttributeOverride(name = "id", column = @Column(name = "competitor_registration_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "competitor_registration_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE competitor_registration SET enabled = false WHERE competitor_registration_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class CompetitorRegistration extends BaseEntity implements  BaseEntityInterface<CompetitorRegistration>{
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date bitshDate;
    private String livingPlace;

    @OneToOne
    @JoinColumn(name = "tour_event_id")
    private TourEvent tourEvent;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "distanceid", columnDefinition = "bigint", nullable = true, referencedColumnName = "distance_id")
    private Distance distance;
    private String registrationUUID;
    private String barcode;

    private String catering;
    private String shirt;


    @Override
    public void edit(CompetitorRegistration item) {

    }
}
