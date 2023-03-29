package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "file_store")
@AttributeOverride(name = "id", column = @Column(name = "file_store_id"))
@SequenceGenerator(name = "id_seq", sequenceName = "file_store_id_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonPropertyOrder(alphabetic = true)
@SQLDelete(sql = "UPDATE file_store SET enabled = false WHERE file_store_id = ?")
@Where(clause = "enabled = true")
@Getter
@Setter
public class FileStore extends BaseEntity implements  BaseEntityInterface<FileStore>{
    private String name;
    private String baseType;
    private String path;
    @Override
    public void edit(FileStore item) {

    }
}
