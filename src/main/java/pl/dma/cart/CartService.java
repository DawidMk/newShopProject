package pl.dma.cart;

import lombok.Data;
import pl.dma.product.Product;
import pl.dma.product.ProductDAO;

@Data
public class CartService {
    private Cart cart;
    ProductDAO productDAO;

    public CartService(Cart cart, ProductDAO productDAO) {
        this.cart = cart;
        this.productDAO = productDAO;
    }

    public Product productById(Long id){
        return productDAO.productList().stream()
                .filter(u -> id == u.getId())
                .findFirst()
                .orElse(null);
    }

    public void addToCart(Product product, Integer amount) {
        cart.getProductsMap().put(product, amount);
    }

    public void showCart(){
        System.out.println(cart.getProductsMap());
    }
}
