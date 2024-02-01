package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "news")
@AttributeOverride(name = "id", column = @Column(name = "news_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "news_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE news SET enabled = false WHERE news_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class News extends BaseEntity implements  BaseEntityInterface<News>{
    private String title;
    private String subTitle;
    @Column(length = 1000)
    private String content;
    private String imageURL;
    private Boolean active = false;
    @Override
    public void edit(News item) {
        this.title = item.getTitle();
        this.subTitle = item.getSubTitle();
        this.content = item.getContent();
        this.imageURL = item.getImageURL();
        this.active = item.getActive();
    }
}
