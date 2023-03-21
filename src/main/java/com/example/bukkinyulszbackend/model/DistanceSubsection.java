package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "distance_sub_section")
@AttributeOverride(name = "id", column = @Column(name = "distance_sub_section_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "distance_sub_section_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE distance_sub_section SET enabled = false WHERE distance_sub_section_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class DistanceSubsection extends BaseEntity implements  BaseEntityInterface<DistanceSubsection>{
    @ManyToOne(optional = false)
    @JoinColumn(name = "distance_id", columnDefinition = "bigint", nullable = true, referencedColumnName = "distance_id")
    @JsonBackReference(value = "distanceSubsection-distance")
    private Distance distance;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sub_section_id", columnDefinition = "bigint", nullable = true, referencedColumnName = "sub_section_id")
    private SubSection subSection;
    @Override
    public void edit(DistanceSubsection item) {

    }
}
