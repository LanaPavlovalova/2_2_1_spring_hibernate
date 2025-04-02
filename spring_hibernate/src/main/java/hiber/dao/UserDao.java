package hiber.dao;

import hiber.model.User;
import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   User findUserByCar(String model, int series);
   void clearTables();
}