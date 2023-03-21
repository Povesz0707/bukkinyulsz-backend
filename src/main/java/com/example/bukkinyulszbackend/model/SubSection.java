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
@Table(name = "sub_section")
@AttributeOverride(name = "id", column = @Column(name = "sub_section_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "sub_section_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE sub_section SET enabled = false WHERE asd_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class SubSection extends BaseEntity implements BaseEntityInterface<SubSection>{
    private Integer position;
    @OneToOne()
    @JoinColumn(name = "checkpointFrom_id", columnDefinition = "bigint", nullable = true, referencedColumnName = "checkpoint_id")
    private Checkpoint checkpointFrom;
    @OneToOne()
    @JoinColumn(name = "checkpointTo_id", columnDefinition = "bigint", nullable = true, referencedColumnName = "checkpoint_id")
    private Checkpoint checkpointTo;
    private Float subLength;
    private Float subElevationGain;

/*    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "distance_id", columnDefinition = "bigint", nullable = true, referencedColumnName = "distance_id")
    @JsonBackReference(value = "distance-sub_section")
    private Distance distance;*/

    @OneToMany(mappedBy = "subSection")
    private Set<SubSectionMarking> markings;

/*    @ManyToOne(optional = true)
    @JoinColumn(name = "distance_sub_section_id", columnDefinition = "bigint", nullable = true, referencedColumnName = "distance_sub_section_id")
    private DistanceSubsection distanceSubsection;*/

    @Override
    public void edit(SubSection item) {
        this.checkpointFrom = item.getCheckpointFrom();
        this.checkpointTo = item.getCheckpointTo();

    }
}
