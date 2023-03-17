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
@Table(name = "checkpoint")
@AttributeOverride(name = "id", column = @Column(name = "checkpoint_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "checkpoint_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE checkpoint SET enabled = false WHERE checkpoint_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class Checkpoint extends BaseEntity implements  BaseEntityInterface<Checkpoint>{
    private String name;
    private String ellatas;

    @OneToOne(mappedBy = "checkpointFrom")
    @JsonBackReference(value = "distance-checkpointFrom")
    private SubSection checkpointFrom;
    @OneToOne(mappedBy = "checkpointTo")
    @JsonBackReference(value = "distance-checkpointTo")
    private SubSection checkpointTo;


    @Override
    public void edit(Checkpoint item) {

    }
}
