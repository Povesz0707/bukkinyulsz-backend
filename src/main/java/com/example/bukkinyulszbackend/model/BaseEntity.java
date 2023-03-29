package com.example.bukkinyulszbackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(alphabetic = true)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter

public class BaseEntity implements Serializable, Comparable<BaseEntity>{
    public static final String WHERE = "(enabled = true OR enabled IS NULL)";
    @Id
    @GeneratedValue(generator = "id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Boolean enabled = true;

    @CreatedDate
    @Column(name = "inserted", columnDefinition = "timestamp with time zone")
    private Date inserted = new Date();

    @CreatedBy
    @Column(name = "inserted_by")
    private Long insertedBy;


    @LastModifiedDate
    @Column(name = "updated", columnDefinition = "timestamp with time zone")
    Date updated;


    @LastModifiedBy
    @Column(name = "updated_by")
    private Long updatedBy;

    @JsonProperty("own")
    @Transient
    private Boolean own;

    public BaseEntity() {
    }

    public BaseEntity(Long id) {
        this.id = id;
    }

    public BaseEntity(Long id, Boolean enabled) {
        this.id = id;
        this.enabled = enabled;
    }

    public BaseEntity(Long id, Boolean enabled, Date inserted, Long insertedBy, Date updated, Long updatedBy) {
        this.id = id;
        this.enabled = enabled;
        this.inserted = inserted;
        this.insertedBy = insertedBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
    }


    public Boolean isOwn() {
        return Boolean.TRUE.equals(own);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BaseEntity))
            return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(enabled, that.enabled)
                && Objects.equals(inserted, that.inserted)
                && Objects.equals(insertedBy, that.insertedBy)
                && Objects.equals(updated, that.updated)
                && Objects.equals(updatedBy, that.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enabled, inserted, insertedBy, updated, updatedBy);
    }

    @Override
    public int compareTo(@Nullable BaseEntity o) {
        if (o == null) {
            return Integer.MIN_VALUE;
        }
        if (!Objects.equals(id, o.id)) {
            return id.compareTo(o.id);
        }
        if (enabled != o.enabled) {
            return Boolean.compare(enabled, o.enabled);
        }
        if (!Objects.equals(inserted, o.inserted)) {
            return inserted.compareTo(o.inserted);
        }
        if (!Objects.equals(insertedBy, o.insertedBy)) {
            return insertedBy.compareTo(o.insertedBy);
        }
        if (!Objects.equals(updated, o.updated)) {
            return updated.compareTo(o.updated);
        }
        if (!Objects.equals(updatedBy, o.updatedBy)) {
            return updatedBy.compareTo(o.updatedBy);
        }
        return 0;
    }


    protected static final int COMPARE_TO_SYNC_CONTINUE = -2;
    public static final int COMPARE_TO_SYNC_NEGATIVE = -1;
    public static final int COMPARE_TO_SYNC_EQUALS = 0;
    public static final int COMPARE_TO_SYNC_ID = 1;
    public static final int COMPARE_TO_SYNC_UNIQUE = 2;

    public int compareToSync(BaseEntity o) {
        if (o == null) {
            return COMPARE_TO_SYNC_NEGATIVE;
        }
        if (this.equals(o)) {
            return COMPARE_TO_SYNC_EQUALS;
        }
        if (Objects.equals(id, o.id)) {
            return COMPARE_TO_SYNC_ID;
        }
        return COMPARE_TO_SYNC_CONTINUE;
    }

    public boolean isEnabled() {
        return enabled;
    }


    public Date fixDateTimeZone(Date date){
        if(date != null){
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal.setTime(date);
            return cal.getTime();
        }
        return null;
    }
}