package pl.dma.cart;

import lombok.Data;
import pl.dma.product.Product;
import pl.dma.user.User;

import java.util.HashMap;
import java.util.Map;

@Data
public class Cart {
    private Map<Product, Integer> productsMap = new HashMap<>();
    private User owner; //loggedUser

    public Cart(User owner) {
        this.owner = owner;
    }

}
