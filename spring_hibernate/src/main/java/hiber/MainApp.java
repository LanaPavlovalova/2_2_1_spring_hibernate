package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      // Очищаем таблицы перед началом
      userService.clearTables();

      // Создаем пользователей с машинами
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(new Car("Car1", 2013));

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(new Car("Car2", 2010));

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setCar(new Car("Car3", 2020));

      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setCar(new Car("Car4", 2024));

      // Сохраняем пользователей
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      // Выводим результат
      System.out.println("=== All Users ===");
      userService.listUsers().forEach(user -> {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
         }
         System.out.println();
      });

      // Поиск пользователя по машине
      System.out.println("=== Find User by Car ===");
      User foundUser = userService.findUserByCar("Car2", 2010);
      if (foundUser != null) {
         System.out.println("Found user: " + foundUser.getFirstName() + " " + foundUser.getLastName());
      } else {
         System.out.println("User not found");
      }

      context.close();
   }
}