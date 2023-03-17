package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "distance")
@AttributeOverride(name = "id", column = @Column(name = "distance_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "distance_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE distance SET enabled = false WHERE distance_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class Distance extends BaseEntity implements  BaseEntityInterface<Distance>{
    private String name;
    private Float length;
    private String logoUrl;
    private String startPlace;
    private String finishPlace;
    private Date startTimeFrom;
    private Date startTimeTo;
    private Date timeLimit;
    private Integer price;
    @Column(length = 1000)
    private String description;
    private String gpxURL;
    private String descriptionURL;
    private String mapURL;
    private String receiptOfItinerary;
    private Date receiptOfItineraryFrom;
    private Date receiptOfItineraryTo;
    private String timekeepingType;
    private String services;
    @OneToMany(mappedBy = "distance")
    //@JsonManagedReference(value = "distance-sub_section")
    private Set<SubSection> subSections;

    @OneToMany(mappedBy = "distance_reward")
    //@JsonManagedReference(value = "reward-distance")
    private Set<Reward> rewards;

    @Override
    public void edit(Distance item) {

    }
}
