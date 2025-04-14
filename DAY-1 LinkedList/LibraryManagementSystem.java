class Book {
    String title;
    String author;
    String genre;
    int bookId;
    String status;
    Book next;
    Book prev;

    public Book(String title, String author, String genre, int bookId, String status) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.status = status;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    private Book head = null;
    private Book tail = null;

    public void addBookAtBeginning(String title, String author, String genre, int bookId, String status) {
        Book newBook = new Book(title, author, genre, bookId, status);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    public void addBookAtEnd(String title, String author, String genre, int bookId, String status) {
        Book newBook = new Book(title, author, genre, bookId, status);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    public void addBookAtPosition(int position, String title, String author, String genre, int bookId, String status) {
        if (position <= 1 || head == null) {
            addBookAtBeginning(title, author, genre, bookId, status);
            return;
        }

        Book newBook = new Book(title, author, genre, bookId, status);
        Book temp = head;
        int index = 1;

        while (index < position - 1 && temp.next != null) {
            temp = temp.next;
            index++;
        }

        if (temp.next == null) {
            addBookAtEnd(title, author, genre, bookId, status);
        } else {
            newBook.next = temp.next;
            newBook.prev = temp;
            temp.next.prev = newBook;
            temp.next = newBook;
        }
    }

    public void removeBookById(int bookId) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                if (temp.prev != null) temp.prev.next = temp.next;
                else head = temp.next;

                if (temp.next != null) temp.next.prev = temp.prev;
                else tail = temp.prev;

                System.out.println("Book ID " + bookId + " removed.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID " + bookId + " not found.");
    }

    public void searchByTitle(String title) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No book found with title: " + title);
    }

    public void searchByAuthor(String author) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No books found by author: " + author);
    }

    public void updateAvailability(int bookId, String newStatus) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                System.out.println("Updated status of Book ID " + bookId + " to " + newStatus);
                temp.status = newStatus;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID " + bookId + " not found.");
    }

    public void displayForward() {
        System.out.println("\nBooks in Forward Order:");
        Book temp = head;
        while (temp != null) {
            printBook(temp);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        System.out.println("\nBooks in Reverse Order:");
        Book temp = tail;
        while (temp != null) {
            printBook(temp);
            temp = temp.prev;
        }
    }

    public void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("\nTotal number of books in the library: " + count);
    }

    private void printBook(Book book) {
        System.out.println("ID: " + book.bookId + ", Title: " + book.title + ", Author: " + book.author +
                ", Genre: " + book.genre + ", Status: " + book.status);
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBookAtEnd("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1001, "Available");
        library.addBookAtBeginning("1984", "George Orwell", "Dystopian", 1002, "Checked Out");
        library.addBookAtEnd("To Kill a Mockingbird", "Harper Lee", "Classic", 1003, "Available");
        library.addBookAtPosition(2, "Brave New World", "Aldous Huxley", "Sci-Fi", 1004, "Available");

        library.displayForward();
        library.displayReverse();

        library.searchByTitle("1984");
        library.searchByAuthor("Harper Lee");

        library.updateAvailability(1003, "Checked Out");

        library.removeBookById(1002);

        library.displayForward();
        library.countBooks();
    }
}
