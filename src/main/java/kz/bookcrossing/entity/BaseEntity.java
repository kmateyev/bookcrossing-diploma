package kz.bookcrossing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public class BaseEntity {

    @Column(name = "created_at", updatable = false, nullable = false)
    @JsonIgnore
    protected Timestamp createdAt;

    @Column(name = "created_by", updatable = false, nullable = false)
    @JsonIgnore
    protected String createdBy;

    @Column(name = "updated_at")
    @JsonIgnore
    protected Timestamp updatedAt;

    @Column(name = "updated_by")
    @JsonIgnore
    protected String updatedBy;

    @Column(name = "deleted_at")
    @JsonIgnore
    protected Timestamp deletedAt;

    @Column(name = "deleted_by")
    @JsonIgnore
    protected String deletedBy;

}
