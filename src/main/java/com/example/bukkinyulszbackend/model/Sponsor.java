package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "sponsor")
@AttributeOverride(name = "id", column = @Column(name = "sponsor_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "sponsor_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE sponsor SET enabled = false WHERE sponsor_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class Sponsor extends BaseEntity implements  BaseEntityInterface<Sponsor>{
    private String name;
    private String description;
    private String redirectUrl;
    private String imageUrl;
    private Boolean active = false;

    @Override
    public void edit(Sponsor item) {
        this.name = item.getName();
        this.description = item.getDescription();
        this.redirectUrl = item.getRedirectUrl();
        this.imageUrl = item.getImageUrl();
        this.active = item.getActive();
    }
}
