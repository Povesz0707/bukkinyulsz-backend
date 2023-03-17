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
    private Date applicationDeadline;
    private Date dateOfEvent;
    private Boolean active;

    @OneToMany(mappedBy = "tourEvent")
    @Where(clause = BaseEntity.WHERE)
    private List<TourEventDistance> tourEventDistances;

    @Override
    public void edit(TourEvent item) {

    }
}
