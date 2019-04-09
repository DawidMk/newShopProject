package pl.dma;

import pl.dma.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class Main {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    public static EntityManager entityManager = entityManagerFactory.createEntityManager();
    public static User loggedUser;
    public static Map<User, List<Cart>> loggedUserCartList = new HashMap<>();
    public static UserDAO userDAO = new UserDAO();
    public static ProductDAO productDAO = new ProductDAO();
    public static CartService cartService = new CartService();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        productDAO.populateProducts();
        start();
    }

    private static void start() {
        if (loggedUser == null) {
            initialMenu();
        } else {
            loggedMenu();
        }
    }

    private static void initialMenu() {
        System.out.println("wybierz opcję: ");
        System.out.println("1. zaloguj");
        System.out.println("2. zarejestruj");
        System.out.println("3. zakończ");
        Integer choice = scanner.nextInt();

        switch (choice) {
            case 1:
                UserLoginService.login(userDAO);
                break;
            case 2:
                UserRegisterService.register(userDAO);
                break;
            case 3:
                entityManagerFactory.close();
                System.exit(0);
            default:
                System.out.println("zły wybór");
        }
        start();
    }

    private static void loggedMenu() {
        System.out.println("1. wyloguj");
        System.out.println("2. wypisz listę produktów");
        System.out.println("3. dodaj produkt do koszyka");
        System.out.println("4. wyświetl zawartość koszyka");
        System.out.println("5. usuń produkt z koszyka");
        Integer choice = scanner.nextInt();

        switch (choice) {
            case 1:
                initialMenu();
            case 2:
                productDAO.showListOfProducts();
            case 3:
                addToCart();
           /*     case 4:
                    showCartProducts();
                case 5:
                    removeFromCart();*/
            default:
                System.out.println("zły wybór");
        }
        start();

    }

    private static void addToCart() {
        System.out.println("podaj id produktu");
        Long productId = scanner.nextLong();
        Optional<Product> product = productDAO.getById(productId);
        product.ifPresent(cartService::addProduct);
    }




}
}
