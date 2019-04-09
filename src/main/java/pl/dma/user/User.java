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
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
//    @Column(name = "address")
//    private UserAddress address;
//    String address;
//    List<Cart> cartList = new ArrayList<>();

}
