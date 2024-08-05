import java.util.*;


public class CinemaTicketSystem {
    public Map<Integer, String> movies;
    private int movie_id_count = 1;

    public Map<Integer, String> users;
    private int user_id_count = 1;


    private int ticket_id_count = 1;
    private Map<Integer, Ticket> tickets;


    public CinemaTicketSystem() {
        movies = new HashMap<>();
        users = new HashMap<>();
        tickets = new HashMap<>();
    }


    public int addMovie(String movieName) {
        int movie_id = movie_id_count++;
        movies.put(movie_id, movieName);
        return movie_id;
    }

    public void showAllMovies() {
        System.out.println("Доступные фильмы:");
        for (int i = 1; i < movie_id_count; i++) {
            System.out.println(i + ") " + movies.get(i));
        }
    }

    public int addUser(String userName) {
        int user_id = user_id_count++;
        users.put(user_id, userName);
        return user_id;
    }

    public int buyTicket(int userId, int movieId) {
        if (!users.containsKey(userId)) {
            throw new IllegalArgumentException("Пользователя под таким ID не существует");
        }

        if (!movies.containsKey(movieId)) {
            throw new IllegalArgumentException("Фильма под таким ID не существует");
        }

        int ticketId = ticket_id_count++;
        tickets.put(ticketId, new Ticket(ticketId, userId, movieId));
        return ticketId;
    }


    public boolean cancelTicket(int ticketId) {
        if (tickets.containsKey(ticketId)) {
            tickets.remove(ticketId);
            return true;
        }

        return false;
    }

}
