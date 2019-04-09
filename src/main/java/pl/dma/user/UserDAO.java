package pl.dma.user;

import pl.dma.Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    public List<User> usersList = new ArrayList<>();

    private void populateUserList(){
        usersList = entityManager.createQuery("select p from User p", User.class).getResultList();
    }

    private User buildUser() {
        User user = new User();
        user.setId(user.getId());
        user.setName(user.getName());
        user.setPassword(user.getPassword());
        return user;
    }

    public boolean putIntoDB(String name, String password) {
//todo wykluczyć konieczność podawania hasła, jeśli login jest zajęty
        boolean exist = false;
        try {
            exist = entityManager.contains(entityManager.createQuery("SELECT u from User u WHERE u.name = :name", User.class).
                    setParameter("name", name).getSingleResult());
        } catch (NoResultException nre) {
        }
        if (!exist) {
            entityManager.getTransaction().begin();
            User user = new User();
            entityManager.contains(user.getName());
            user.setName(name);
            user.setPassword(password);
            entityManager.persist(user);
            entityManager.getTransaction().commit();

        }
        return exist;
    }

    public boolean getFromDB(String name, String password) {
        User user = null;
        try {
            user = entityManager.createQuery("SELECT u from User u WHERE u.name = :name AND u.password = :password", User.class).
                    setParameter("name", name).setParameter("password", password).getSingleResult();
        } catch (NoResultException nre) {
        }
        assert user != null;
        user = buildUser();
        Main.loggedUser = user;
        return user != null;
    }

    //todo remove from DB - adminUser
    //todo edit user in DB - adminUser
}

