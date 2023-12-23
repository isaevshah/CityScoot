package kz.project.moped.infrastructure.persistense.postgresql.entity;

import jakarta.persistence.*;
import kz.project.moped.domain.enums.ProductType;
import kz.project.moped.infrastructure.persistense.postgresql.entity.base.BaseAuditEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Table(name = "products")
@Entity
public class ProductEntity extends BaseAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    private String name;

    @Column(name = "code", columnDefinition = "TEXT", nullable = false)
    private String code;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "fromDate", nullable = false)
    private Date fromDate;

    @Column(name = "toDate", nullable = false)
    private Date toDate;

    @Enumerated(EnumType.STRING)
    private ProductType productType;
}
