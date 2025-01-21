package hiber.service;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface UserService {
    
    @Autowired
    SessionFactory sessionFactory = null;
    
    default void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }
    
    default List<User> listUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("From User", User.class).list();
    }
 
    default User findUserByCarModelAndSerial(String model, int series) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "From User u WHERE u.car.model = :model AND u.car.series = :series";
        return session.createQuery(hql, User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .uniqueResult();
    }
}

