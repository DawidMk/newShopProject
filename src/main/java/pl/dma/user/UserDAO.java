package pl.dma.user;

import lombok.Getter;
import lombok.Setter;
import pl.dma.Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDAO {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    public List<User> usersList;
//    private User user = new User();



    public void populateUserList() {
        try {
            usersList = entityManager.createQuery("select a from User a", User.class).getResultList();
        } catch (NoResultException e) {
            System.out.println("brak zarejestrowanych użytkowników");
        }
        usersList = new ArrayList<>();
    }

    public void putIntoDB(User user) {
//todo wykluczyć konieczność podawania hasła, jeśli login jest zajęty

        entityManager.getTransaction().begin();
        user.setName(user.getName());
        user.setPassword(user.getPassword());
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        populateUserList();

    }

    public boolean getFromDB(User user) {

        try {
            user = entityManager.createQuery("SELECT u from User u WHERE u.name = :name AND u.password = :password", User.class).
                    setParameter("name", user.getName()).setParameter("password", user.getPassword()).getSingleResult();
        } catch (NoResultException nre) {
        }
        assert user != null;
        Main.loggedUser = user;
        return true;
    }

    //todo remove from DB - adminUser
    //todo edit user in DB - adminUser
}

