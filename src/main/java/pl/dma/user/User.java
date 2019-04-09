package pl.dma.user;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "password")
    String password;
//    String address;
//    List<Cart> cartList = new ArrayList<>();

}
