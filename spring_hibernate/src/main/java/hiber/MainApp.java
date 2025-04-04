package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      // Очищаем таблицы перед началом
      userService.clearUsers();
      carService.clearCars();

      // Создаем машины
      Car car1 = new Car("Car1", 2013);
      Car car2 = new Car("Car2", 2010);
      Car car3 = new Car("Car3", 2020);
      Car car4 = new Car("Car4", 2024);

      // Создаем пользователей с машинами
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(car1);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(car2);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setCar(car3);

      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setCar(car4);

      // Сохраняем пользователей (каскад сохранит и машины)
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
      User foundUser = userService.getUserByCar("Car2", 2010);
      if (foundUser != null) {
         System.out.println("Found user: " + foundUser.getFirstName() + " " + foundUser.getLastName());
      } else {
         System.out.println("User not found");
      }

      context.close();
   }
}