class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie prev;
    Movie next;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.prev = null;
        this.next = null;
    }
}
class MovieList {
    private Movie head;
    private Movie tail;
    public void addMovieAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }
    public void addMovieAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }
    public void addMovieAtPosition(int position, String title, String director, int year, double rating) {
        if (position <= 0) {
            addMovieAtBeginning(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        Movie temp = head;
        int index = 0;
        while (temp != null && index < position) {
            temp = temp.next;
            index++;
        }
        if (temp == null) {
            addMovieAtEnd(title, director, year, rating);
        } else {
            newMovie.prev = temp.prev;
            newMovie.next = temp;
            if (temp.prev != null) {
                temp.prev.next = newMovie;
            }
            temp.prev = newMovie;
            if (position == 0) {
                head = newMovie;
            }
        }
    }
    public void removeMovieByTitle(String title) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                if (temp.prev != null) temp.prev.next = temp.next;
                else head = temp.next;

                if (temp.next != null) temp.next.prev = temp.prev;
                else tail = temp.prev;

                System.out.println("Removed: " + title);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found: " + title);
    }
    public void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                printMovie(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found for director: " + director);
    }
    public void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                printMovie(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found with rating: " + rating);
    }
    public void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                System.out.println("Updating rating of " + title + " from " + temp.rating + " to " + newRating);
                temp.rating = newRating;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found: " + title);
    }
    public void displayForward() {
        System.out.println("\nMovies in Forward Order:");
        Movie temp = head;
        while (temp != null) {
            printMovie(temp);
            temp = temp.next;
        }
    }
    public void displayReverse() {
        System.out.println("\nMovies in Reverse Order:");
        Movie temp = tail;
        while (temp != null) {
            printMovie(temp);
            temp = temp.prev;
        }
    }
    private void printMovie(Movie m) {
        System.out.println("Title: " + m.title + ", Director: " + m.director +
                ", Year: " + m.year + ", Rating: " + m.rating);
    }
}
public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieList movieList = new MovieList();
        movieList.addMovieAtEnd("Inception", "Christopher Nolan", 2010, 8.8);
        movieList.addMovieAtBeginning("The Matrix", "The Wachowskis", 1999, 8.7);
        movieList.addMovieAtEnd("Interstellar", "Christopher Nolan", 2014, 8.6);
        movieList.addMovieAtPosition(1, "The Prestige", "Christopher Nolan", 2006, 8.5);

        movieList.displayForward();
        movieList.displayReverse();

        movieList.removeMovieByTitle("The Matrix");
        movieList.displayForward();

        movieList.searchByDirector("Christopher Nolan");
        movieList.searchByRating(8.6);

        movieList.updateRating("Inception", 9.0);
        movieList.displayForward();
    }
}
