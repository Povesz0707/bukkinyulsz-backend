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
@Table(name = "reward")
@AttributeOverride(name = "id", column = @Column(name = "reward_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "reward_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE reward SET enabled = false WHERE rewards_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class Reward extends BaseEntity implements  BaseEntityInterface<Reward>{
    private String name;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "distance_id", columnDefinition = "bigint", nullable = true, referencedColumnName = "distance_id")
    @JsonBackReference(value = "reward-distance")
    private Distance distance_reward;

    @Override
    public void edit(Reward item) {

    }
}
