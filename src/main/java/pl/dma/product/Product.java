package pl.dma.product;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
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

    public String view() {
        return id + " " + name + ":" + price;
    }
}
