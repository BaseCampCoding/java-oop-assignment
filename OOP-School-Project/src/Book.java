public class Book {
    private final String title;
    private final String author;
    private final int yearPublished;
    private boolean isRead;
     // python-like constructor
    public Book(String title, String author, int yearPublished, boolean isRead) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isRead = isRead;
    }

    // getters/setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        String readStatus = isRead ? "Read" : "Unread";
        return String.format("\"%s\" by %s (%d) - %s", title, author, yearPublished, readStatus);
    }
}