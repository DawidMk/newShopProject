package pl.dma;

import pl.dma.cart.Cart;
import pl.dma.user.*;

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
    public static UserLoginService userLoginService = new UserLoginService(userDAO);
    public static UserRegisterService userRegisterService = new UserRegisterService(userDAO);
    public static UserLoginDTO userLoginDTO = new UserLoginDTO();
    public static UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
//    public static ProductDAO productDAO = new ProductDAO();
//    public static CartService cartService = new CartService();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        productDAO.populateProducts();
        start();
    }

    private static void start() {
        userDAO.populateUserList();

        if (loggedUser == null) {
            initialMenu();
        } else {
            loggedMenu();
        }
    }

    private static void initialMenu() {
        userDAO.populateUserList();
        System.out.println("wybierz opcję: ");
        System.out.println("1. zaloguj");
        System.out.println("2. zarejestruj");
        System.out.println("3. zakończ");
        Integer choice = scanner.nextInt();

        switch (choice) {
            case 1:
                loginUser();
                break;
            case 2:
                registerUser();
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
                loggedUser = null;
                initialMenu();
        /*    case 2:
                productDAO.showListOfProducts();
            case 3:
                addToCart();
                case 4:
                    showCartProducts();
                case 5:
                    removeFromCart();*/
            default:
                System.out.println("zły wybór");
        }
        start();

    }

    private static void registerUser(){

        System.out.println("podaj login");
        String login = scanner.next();
        userRegisterDTO.setName(login);
        System.out.println("podaj hasło");
        String passw = scanner.next();
        userRegisterDTO.setPassword(passw);
        boolean ifSuccess = userRegisterService.register(userRegisterDTO);
        if(ifSuccess){
            System.out.println("zarejestrowano");
        }else {
            System.out.println("rejestracja nie powiodła się / użytkownik istnieje");
        }
    }

    private static void loginUser(){

        System.out.println("podaj login");
        String login = scanner.next();
        userLoginDTO.setName(login);
        System.out.println("podaj hasło");
        String passw = scanner.next();
        userLoginDTO.setPassword(passw);
        boolean ifSuccess = userLoginService.login(userLoginDTO);
        if(ifSuccess){
            System.out.println("zalogowano");
        }else{
            System.out.println("nie udało się zalogować");
        }

    }
/*
    private static void addToCart() {
        System.out.println("podaj id produktu");
        Long productId = scanner.nextLong();
        Optional<Product> product = productDAO.getById(productId);
        product.ifPresent(cartService::addProduct);
    }*/


}

