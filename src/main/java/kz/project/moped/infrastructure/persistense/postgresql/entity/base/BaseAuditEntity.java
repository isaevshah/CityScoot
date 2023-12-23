package kz.project.moped.infrastructure.persistense.postgresql.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseAuditEntity {
    @Column(name = "create_ts")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Almaty")
    private Date createTs = new Date();

    @Column(name = "update_ts")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Almaty")
    private Date updateTs;

    @Column(name = "created_by")
    private String createdBy = "SYSTEM";

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "delete_ts")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Almaty")
    private Date deleteTs;

    @Column(name = "deleted_by")
    private String deletedBy;

}
