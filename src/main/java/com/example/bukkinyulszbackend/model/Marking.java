package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "marking")
@AttributeOverride(name = "id", column = @Column(name = "marking_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "marking_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE marking SET enabled = false WHERE marking_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
@ToString
public class Marking extends BaseEntity implements BaseEntityInterface<Marking>{
    private String name;
    private String url;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id", columnDefinition = "bigint", nullable = false, referencedColumnName = "section_id")
    @JsonBackReference(value = "marking_section")
    private Section section;

    @Override
    public void edit(Marking item) {

    }
}
