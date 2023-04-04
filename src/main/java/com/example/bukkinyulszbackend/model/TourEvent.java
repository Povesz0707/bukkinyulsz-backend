package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tour_event")
@AttributeOverride(name = "id", column = @Column(name = "tour_event_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "tour_event_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE tour_event SET enabled = false WHERE tour_event_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class TourEvent extends BaseEntity implements  BaseEntityInterface<TourEvent>{
    private String name;
    private Date applicationFrom;
    private Date applicationTo;
    private Date dateOfEvent;
    private String placeOfEvent;
    private Boolean active;
    private String bannerImage;
    private String bannerHeader;
    private String bannerText;

    @OneToMany(mappedBy = "tourEvent")
    @Where(clause = BaseEntity.WHERE)
    private List<TourEventDistance> tourEventDistances;


    @Override
    public void edit(TourEvent item) {
        this.name = item.getName();
        this.applicationFrom = fixDateTimeZone(item.getApplicationFrom());
        this.applicationTo = fixDateTimeZone(item.getApplicationTo());
        this.dateOfEvent = fixDateTimeZone(item.getDateOfEvent());
        this.placeOfEvent = item.getPlaceOfEvent();
        this.active = item.getActive();
        this.bannerImage = item.getBannerImage();
        this.bannerHeader =item.getBannerHeader();
        this.bannerText = item.getBannerText();
    }

}
