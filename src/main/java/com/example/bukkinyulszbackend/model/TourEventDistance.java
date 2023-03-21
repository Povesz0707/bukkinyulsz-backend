package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "tour_event_distance")
@AttributeOverride(name = "id", column = @Column(name = "tour_event_distance_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "tour_event_distance_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE tour_event_distance SET enabled = false WHERE tour_event_distance_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class TourEventDistance extends BaseEntity implements  BaseEntityInterface<TourEventDistance>{
    @ManyToOne(optional = false)
    @JoinColumn(name = "tour_event_id", columnDefinition = "bigint", nullable = true, referencedColumnName = "tour_event_id")
    @JsonBackReference(value = "tourEventDistance-tourEvent")
    private TourEvent tourEvent;

    @ManyToOne(optional = false)
    @JoinColumn(name = "distance_id", columnDefinition = "bigint", nullable = true, referencedColumnName = "distance_id")
    private Distance distance;
    @Override
    public void edit(TourEventDistance item) {
        this.tourEvent = item.getTourEvent();
        this.distance = item.getDistance();
    }
}
