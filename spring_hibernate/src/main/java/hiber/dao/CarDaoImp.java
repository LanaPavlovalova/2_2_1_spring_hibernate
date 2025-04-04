package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public Car getCarById(Long id) {
        return sessionFactory.getCurrentSession().get(Car.class, id);
    }

    @Override
    public void clearCars() {
        sessionFactory.getCurrentSession().createQuery("delete from Car").executeUpdate();
    }
}
