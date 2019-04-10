package pl.dma.product;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "product_table")
public class Product {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "name")
    private String name;
    @Column(name = "stock")
    private Integer stock;

    public String view() {
        return id + " " + name + ":" + price;
    }
}
