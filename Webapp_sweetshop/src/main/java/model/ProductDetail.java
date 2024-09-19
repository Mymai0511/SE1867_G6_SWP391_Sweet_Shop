package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDetail {
    private int id;
    private float price;
    private String size;
    private int productID;
}
