package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "section")
@AttributeOverride(name = "id", column = @Column(name = "section_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "section_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE section SET enabled = false WHERE distance_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
@ToString
public class Section extends BaseEntity implements BaseEntityInterface<Section>{
    private Integer position;
    private String from;
    private String to;
    private Float length;
    private Float elevationGain;
    private String jelzesek;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "distance_id", columnDefinition = "bigint", nullable = false, referencedColumnName = "distance_id")
    @JsonBackReference(value = "section_distance")
    private Distance distance;

    @OneToMany(mappedBy = "section")
    private Set<Marking> markings;

    @Override
    public void edit(Section item) {

    }
}
