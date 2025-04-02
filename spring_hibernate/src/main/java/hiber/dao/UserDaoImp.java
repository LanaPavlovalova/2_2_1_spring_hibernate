package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("from User u left join fetch u.car", User.class);
      return query.getResultList();
   }

   @Override
   public User findUserByCar(String model, int series) {
      String hql = "from User u left join fetch u.car where u.car.model = :model and u.car.series = :series";
      return sessionFactory.getCurrentSession()
              .createQuery(hql, User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .uniqueResult();
   }

   @Override
   public void clearTables() {
      sessionFactory.getCurrentSession().createQuery("delete from User").executeUpdate();
      sessionFactory.getCurrentSession().createQuery("delete from Car").executeUpdate();
   }
}