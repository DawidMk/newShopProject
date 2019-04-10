package pl.dma.product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;

public class ProductDAO {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public Product getFromDB(Integer id){
        Product product = new Product();
        try {
            product = entityManager.createQuery("SELECT p from Product u WHERE p.id = :id", Product.class).
                    setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> productList(){
        return entityManager.createQuery("select a from Product a", Product.class).getResultList();
    }

    public void showListOfProducts() {
        entityManager.createQuery("select a from Product a", Product.class).getResultList()
                .stream()
                .map(Product::view)
                .forEach(System.out::println);
    }
}
