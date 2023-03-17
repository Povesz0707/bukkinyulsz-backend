package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "sub_section_marking")
@AttributeOverride(name = "id", column = @Column(name = "sub_section_marking_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "sub_section_marking_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE sub_section_marking SET enabled = false WHERE asd_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class SubSectionMarking extends BaseEntity implements BaseEntityInterface<SubSectionMarking>{
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_section_id", columnDefinition = "bigint", nullable = true, referencedColumnName = "sub_section_id")
    @JsonBackReference(value = "marking-sub_section")
    private SubSection subSection;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "marking_id", columnDefinition = "bigint", nullable = true, referencedColumnName = "marking_id")
    private Marking marking;

    @Override
    public void edit(SubSectionMarking item) {

    }
}
