package kz.project.moped.domain.model;

import kz.project.moped.domain.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String code;
    private Date fromDate;
    private Date toDate;
    private String image;
    private ProductType productType;
}
