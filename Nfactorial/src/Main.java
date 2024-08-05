import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CinemaTicketSystem cinemaSystem = new CinemaTicketSystem();

        while (true) {
            System.out.println("Здравствуйте, у вас есть следующие доступные функции:\n" +
                    "1. Добавить новый фильм;\n" +
                    "2. Показать все доступные фильмы;\n" +
                    "3. Добавить нового пользователя;\n" +
                    "4. Купить билет;\n" +
                    "5. Отменить покупку билета;\n" +
                    "6. Выйти;\n");

            int choice = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Выберите опцию: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Неверный ввод. Пожалуйста, введите номер опции.");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    System.out.println("Введите название фильма:");
                    String movieName = scanner.nextLine();
                    int movieId = cinemaSystem.addMovie(movieName);
                    System.out.println("Фильм добавлен с идентификатором [" + movieId + "]");
                    break;
                case 2:
                    cinemaSystem.showAllMovies();
                    System.out.print("\n\n\n");
                    break;
                case 3:
                    System.out.println("Введите имя пользователя:");
                    String userName = scanner.nextLine();
                    int userId = cinemaSystem.addUser(userName);
                    System.out.println("Пользователь добавлен с идентификатором [" + userId + "]");
                    break;
                case 4:
                    validInput = false;
                    int user_id = 0;
                    while (!validInput) {
                        try {
                            System.out.print("Введите идентификатор пользователя: ");
                            user_id = scanner.nextInt();
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный ввод. Пожалуйста, введите идентификатор пользователя.");
                            scanner.nextLine();
                        }
                    }

                    validInput = false;
                    int movie_id = 0;
                    while (!validInput) {
                        try {
                            System.out.print("Введите идентификатор фильма: ");
                            movie_id = scanner.nextInt();
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный ввод. Пожалуйста, введите идентификатор фильма.");
                            scanner.nextLine();
                        }
                    }

                    try {
                        int ticketId = cinemaSystem.buyTicket(user_id, movie_id);
                        System.out.println(
                                "Билет куплен с идентификатором: [" + ticketId + "]\n" +
                                "На фильм:                       \'" + cinemaSystem.movies.get(movie_id) + "\'\n" +
                                "Для пользователя:               \'" + cinemaSystem.users.get(user_id) + "\'"
                        );
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println("\n\n\n");
                    break;
                case 5:
                    validInput = false;
                    int ticket_id = 0;
                    while (!validInput) {
                        try {
                            System.out.print("Введите идентификатор билета: ");
                            ticket_id = scanner.nextInt();
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный ввод. Пожалуйста, введите идентификатор билета.");
                            scanner.nextLine();
                        }
                    }

                    boolean isCancelled = cinemaSystem.cancelTicket(ticket_id);
                    if (isCancelled) {
                        System.out.println("Билет отменен.");
                    } else {
                        System.out.println("Билет с таким идентификатором не найден.");
                    }
                    break;
                case 6:
                    System.out.println("Выход...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }

            System.out.print("\n\n\n");
        }
    }
}