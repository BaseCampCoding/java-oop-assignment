import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Book> library = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("----- book manager program -----");
        System.out.println("\n");

        boolean running = true;

        while (running) {
            System.out.println("[add] a new book");
            System.out.println("[list] all books");
            System.out.println("[mark] a book as read");
            System.out.println("[remove] a book");
            System.out.println("[search] books by author");
            System.out.println("[exit]");
            System.out.print("\nchoose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "add":
                    addBook();
                    break;
                case "list":
                    listBooks();
                    break;
                case "mark":
                    markAsRead();
                    break;
                case "remove":
                    deleteBook();
                    break;
                case "search":
                    searchByAuthor();
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("invalid choice.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void addBook() {
        System.out.print("enter book title: ");
        String title = scanner.nextLine().trim();

        System.out.print("enter author: ");
        String author = scanner.nextLine().trim();

        System.out.print("enter year published: ");
        int year;

        try {
            year = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid year. book not added.");
            return;
        }

        Book book = new Book(title, author, year, false);
        library.add(book);
    }

    private static void listBooks() {
        if (library.isEmpty()) {
            System.out.println("no books added.");
            return;
        }

        System.out.println("your library (" + library.size() + " books):");
        for (int i = 0; i < library.size(); i++)
            System.out.printf("%3d. %s%n", (i + 1), library.get(i));
    }

    private static void markAsRead() {
        listBooks();
        if (library.isEmpty())
            return;

        System.out.print("enter the number of the book to mark as read: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index >= 0 && index < library.size()) {
                Book book = library.get(index);
                if (book.isRead()) {
                    System.out.println("this book is already marked as read.");
                } else {
                    book.setRead(true);
                    System.out.println("marked as read: " + book.getTitle());
                }
            } else
                System.out.println("invalid book number.");
        } catch (NumberFormatException e) {
            System.out.println("please enter a valid number.");
        }
    }

    private static void deleteBook() {
        listBooks();
        if (library.isEmpty()) return;

        System.out.print("enter the number of the book to remove: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index >= 0 && index < library.size()) {
                Book removed = library.remove(index);
                System.out.println("removed: " + removed.getTitle());
            } else {
                System.out.println("invalid book number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("please enter a valid number.");
        }
    }

    private static void searchByAuthor() {
        System.out.print("enter author name to search: ");
        String search = scanner.nextLine().trim().toLowerCase();

        ArrayList<Book> results = new ArrayList<>();
        for (Book book : library) {
            if (book.getAuthor().toLowerCase().contains(search)) {
                results.add(book);
            }
        }

        if (results.isEmpty()) {
            System.out.println("no books found by '" + search + "'.");
        } else {
            System.out.println("found " + results.size() + " book(s) by '" + search + "':");
            for (Book b : results) {
                System.out.println("  → " + b);
            }
        }
    }
}