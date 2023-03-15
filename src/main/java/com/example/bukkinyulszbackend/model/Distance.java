package com.example.bukkinyulszbackend.model;

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
@ToString
public class Distance extends BaseEntity implements  BaseEntityInterface<Distance>{
    private String name;
    private Float length;
    private Float gain;
    private String startPlace;
    private String finishPlace;
    private Date startTime;
    private Date finishDate;
    private Date timeLimit;
    private Reward reward;
    private Integer price;
    private String descripcion;
    private String gpxURL;
    private String turafuzetAtvetele;
    private String idomeresModja;
    private String szolgaltatasok;
    private String utvonal;
    @OneToMany(mappedBy = "distance")
    private Set<Section> sections;

    @Override
    public void edit(Distance item) {

    }
}
