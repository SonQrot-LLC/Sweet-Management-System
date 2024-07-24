package sweet.management;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        UserService userService = new UserService();

        if (userService.login(email, password)) {
            System.out.println("Welcome");
        } else {
            System.out.println("Not welcome");
        }
    }
}