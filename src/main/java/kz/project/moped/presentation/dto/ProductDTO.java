package kz.project.moped.presentation.dto;

import kz.project.moped.domain.enums.ProductType;
import lombok.*;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String code;
    private Date fromDate;
    private Date toDate;
    private String image;
    private ProductType productType;
}
