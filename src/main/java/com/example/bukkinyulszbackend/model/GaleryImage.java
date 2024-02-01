package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "galeryImage")
@AttributeOverride(name = "id", column = @Column(name = "galeryImage_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "galeryImage_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE galeryImage SET enabled = false WHERE galeryImage_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class GaleryImage extends BaseEntity implements  BaseEntityInterface<GaleryImage>{
    private String name;
    private String description;
    private Boolean active = false;
    private String imageUrl;
    @Override
    public void edit(GaleryImage item) {
        this.name = item.getName();
        this.description = item.getDescription();
        this.imageUrl = item.getImageUrl();
        this.active = item.getActive();
    }
}
