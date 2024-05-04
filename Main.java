import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isAvailable;
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        System.out.println("Library Catalog:");
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Available: " + (book.isAvailable() ? "Yes" : "No"));
        }
    }

    public Book searchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public void borrowBook(String title) {
        Book book = searchByTitle(title);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("You have successfully borrowed: " + book.getTitle());
        } else {
            System.out.println("Book not available for borrowing.");
        }
    }

    public void returnBook(String title) {
        Book book = searchByTitle(title);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("You have successfully returned: " + book.getTitle());
        } else {
            System.out.println("Invalid book or already returned.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search by Title");
            System.out.println("4. Search by Author");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title of the book: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author of the book: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(title, author));
                    break;
                case 2:
                    library.displayBooks();
                    break;
                case 3:
                    System.out.print("Enter title to search: ");
                    String searchTitle = scanner.nextLine();
                    Book foundBookByTitle = library.searchByTitle(searchTitle);
                    if (foundBookByTitle != null) {
                        System.out.println("Book found: " + foundBookByTitle.getTitle() + " by " + foundBookByTitle.getAuthor());
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter author to search: ");
                    String searchAuthor = scanner.nextLine();
                    ArrayList<Book> foundBooksByAuthor = library.searchByAuthor(searchAuthor);
                    if (!foundBooksByAuthor.isEmpty()) {
                        System.out.println("Books found by " + searchAuthor + ":");
                        for (Book book : foundBooksByAuthor) {
                            System.out.println(book.getTitle());
                        }
                    } else {
                        System.out.println("No books found by " + searchAuthor + ".");
                    }
                    break;
                case 5:
                    System.out.print("Enter title of the book to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowBook(borrowTitle);
                    break;
                case 6:
                    System.out.print("Enter title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}
