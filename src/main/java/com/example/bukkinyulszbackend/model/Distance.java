package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;
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
@ToString
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
    private Integer maxNumberOfCompetitor;
    @Column(length = 1000)
    private String description;
    private String gpxURL;
    private String descriptionURL;
    private String mapURL;
    private String approach;
    private String receiptOfItinerary;
    private Date receiptOfItineraryFrom;
    private Date receiptOfItineraryTo;
    private String timekeepingType;
    private String services;

    @OneToMany(mappedBy = "distance_reward")
    //@JsonManagedReference(value = "reward-distance")
    private Set<Reward> rewards;

    @OneToMany(mappedBy = "distance")
    @Where(clause = BaseEntity.WHERE)
    private List<DistanceSubsection> distanceSubsections;

    @Override
    public void edit(Distance item) {
        this.name = item.getName();
        this.length = item.getLength();
        this.logoUrl = item.getLogoUrl();
        this.startPlace = item.getStartPlace();
        this.finishPlace = item.getFinishPlace();
        this.startTimeFrom = item.getStartTimeFrom();
        this.startTimeTo = item.getStartTimeTo();
        this.timeLimit = item.getTimeLimit();
        this.price = item.getPrice();
        this.maxNumberOfCompetitor = item.getMaxNumberOfCompetitor();
        this.description = item.getDescription();
        this.gpxURL = item.getGpxURL();
        this.descriptionURL = item.getDescriptionURL();
        this.mapURL = item.getMapURL();
        this.approach = item.getApproach();
        this.receiptOfItinerary = item.getReceiptOfItinerary();
        this.receiptOfItineraryFrom = item.getReceiptOfItineraryFrom();
        this.receiptOfItineraryTo = item.getReceiptOfItineraryTo();
        this.timekeepingType = item.getTimekeepingType();
        this.services = item.getServices();
    }


}
