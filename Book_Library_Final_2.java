package AP_CSA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book_Library_Final_2 {

    /**
     * Represents a book with a title, author, extra details, and a rating.
     */
    static class Book {
        public String title;
        public String author;
        public BookDetails details; // Extra details include num of pages and date of publication
        private BookRating rating;

        /**
         * Constructs a new Book instance with specified title, author, number of pages, and publishing date.
         * Initializes the book's rating with a default value.
         *
         * @param title the title of the book
         * @param author the author of the book
         * @param pages the number of pages in the book
         * @param publishingDate the publication year of the book
         */
        public Book(String title, String author, int pages, int publishingDate) {
            this.title = title;
            this.author = author;
            this.details = new BookDetails(pages, publishingDate);
            this.rating = new BookRating();
        }

        /**
         * Returns the current rating of the book.
         *
         * @return the rating of the book
         */
        public int getRating() {
            return this.rating.getRating();
        }

        /**
         * Sets a new rating for the book, if the provided rating is between 1 and 5.
         *
         * @param rating the new rating to be set for the book
         */
        public void setRating(int rating) {
            this.rating.setRating(rating);
        }

        /**
         * Returns a string representation of the book, including its title, author, number of pages, and publication year.
         *
         * @return a string representation of the book
         */
        @Override // This method overrides the toString method inherited from the Object class
        public String toString() {
            return title + " by " + author + " (" + details.pages + " pages, published " + details.publishingDate + ")";
        }

        /**
         * Returns a string representation of the book with its rating.
         *
         * @return a string representation of the book with its rating
         */
        public String toStringWithRating() {
            return title + " - Rating: " + getRating() + " stars";
        }

        private static class BookDetails {

            /**
             * Represents detailed information about a book, including its number of pages and publication year.
             */
            public int pages;
            public int publishingDate;

            /**
             * Constructs a new BookDetails instance with specified number of pages and publishing date.
             *
             * @param pages the number of pages in the book
             * @param publishingDate the year the book was published
             */
            public BookDetails(int pages, int publishingDate) {
                this.pages = pages;
                this.publishingDate = publishingDate;
            }
        }

        static class BookRating {
            private int rating; // Rating of the book

            public BookRating() {
                this.rating = 5; // New BookRating instance with a default rating
            }

            public int getRating() { // Accessor method
                return rating;
            }

            /**
             * Sets a new rating for the book if the rating is between 1 and 5.
             * Prints a message if the rating is not within the valid range.
             *
             * @param rating the new rating to be set for the book
             */
            public void setRating(int rating) { // Mutator method 01
                if (rating >= 1 && rating <= 5) {
                    this.rating = rating;
                } else {
                    System.out.println("Invalid rating. Ratings should be between 1 and 5.");
                }
            }

            /**
             * Sets a new rating for the book based on a string input. Validates the input before setting the rating.
             *
             * @param rating the new rating to be set for the book in string format
             */
            public void setRating(String rating) { // Mutator method 02
                try {
                    int intRating = Integer.parseInt(rating);
                    setRating(intRating);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid rating format. Ratings should be between 1 and 5.");
                }
            }

            public void setRating() { // Mutator method 03
                this.rating = 5; // Reset rating to default value
            }
        }
    }

    /**
     * Inserts a book into a list of book lists in alphabetical order by the book's title.
     * If no appropriate sublist is found for the book, it is added to a new sublist.
     *
     * @param books the list of book lists where the book will be inserted
     * @param bookToInsert the book to be inserted into the list
     */
    private static void insertBookAlphabetically(List<List<Book>> books, Book bookToInsert) {
        for (int i = 0; i < books.size(); i++) {
            for (int j = 0; j < books.get(i).size(); j++) {
                if (bookToInsert.title.compareToIgnoreCase(books.get(i).get(j).title) < 0) {
                    books.get(i).add(j, bookToInsert);
                    return;
                }
            }
        }
        // Add to a new sublist if no suitable place is found
        List<Book> newSubList = new ArrayList<>();
        newSubList.add(bookToInsert);
        books.add(newSubList);
    }

    /**
     * Main entry point of my Book Library program.
     * Offers a client-driven menu interface for users to interact with a book collection. The interface allows users
     * to list available books, list books with their ratings, borrow books, return books, and adjust borrowed book's ratings.
     * The program operates in a loop until the user chooses to quit.
     *
     * @param args not used in this application
     */
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        List<List<Book>> myBookList = new ArrayList<>();
        List<Book> initialBooks = new ArrayList<>();
        initialBooks.add(new Book("Harry Potter", "J. K. Rowling", 223, 1997));
        initialBooks.add(new Book("Percy Jackson", "Rick Riordan", 375, 2005));
        initialBooks.add(new Book("Six of Crows", "Leigh Bardugo", 465, 2015));
        initialBooks.add(new Book("Wings of Fire", "Tui T. Sutherland", 336, 1999));
        initialBooks.add(new Book("Silver Flames", "Sarah J. Maas", 648, 2021));
        initialBooks.add(new Book("Haunting Adeline", "H. D. Carlton", 608, 2021));
        initialBooks.add(new Book("Keeper of The Lost Cities", "Shannon Messenger", 488, 2012));
        myBookList.add(initialBooks);

        List<Book> borrowedBooks = new ArrayList<>();

        while (true) {
            // Prompt user for action
            System.out.println("\nWelcome to the Nalanda Library. Please enter a value from one of the following options below: ");
            System.out.println("1. Get a list of book names\n2. Get a list of book names and their ratings\n3. Take out a book from the library\n4. Return a book to the library\n5. Adjust book rating\n6. Quit\n");

            if (myObj.hasNextInt()) {
                int choice = myObj.nextInt();
                myObj.nextLine(); // Consume newline

                switch (choice) {
                    case 1: // List all books with details
                        for (List<Book> sublist : myBookList) {
                            if (sublist.isEmpty()) {
                                System.out.println("There are no books available to borrow at the moment.");
                            } else {
                                System.out.println("List of available books:\n");
                                for (Book book : sublist) {
                                    System.out.println(book.toString());
                                }
                            }
                        }
                        break;
                    case 2: // List all books with ratings
                        for (List<Book> sublist : myBookList) {
                            System.out.println("List of books and their ratings:\n");
                            for (Book book : sublist) {
                                System.out.println(book.toStringWithRating());
                            }
                        }
                        break;
                    case 3: // Borrowing a book
                        System.out.println("Enter the name of the book you want to borrow: ");
                        String bookToBorrow = myObj.nextLine().toLowerCase();
                        boolean isBorrowed = false;
                        for (List<Book> sublist : myBookList) {
                            for (Book book : sublist) {
                                if (book.title.toLowerCase().equals(bookToBorrow)) {
                                    borrowedBooks.add(book);
                                    sublist.remove(book);
                                    System.out.println("You have successfully borrowed: " + book);
                                    isBorrowed = true;
                                    break;
                                }
                            }
                            if (isBorrowed) break;
                        }
                        if (!isBorrowed) {
                            System.out.println("The book is not available in the library.");
                        }
                        break;
                    case 4: // Returning a book
                        System.out.println("Enter the name of the book you want to return: ");
                        String bookToReturn = myObj.nextLine().toLowerCase();
                        boolean isReturned = false;
                        for (Book book : borrowedBooks) {
                            if (book.title.toLowerCase().equals(bookToReturn)) {
                                borrowedBooks.remove(book);
                                insertBookAlphabetically(myBookList, book);
                                System.out.println("You have successfully returned: " + book);
                                isReturned = true;
                                break;
                            }
                        }
                        if (!isReturned) {
                            System.out.println("You cannot return a book that is not borrowed from this library.");
                        }
                        break;
                    case 5: // Adjusting borrowed book's rating
                        System.out.println("Enter the name of the book you want to adjust the rating for: ");
                        String bookToRate = myObj.nextLine().toLowerCase();
                        boolean found = false;
                        for (Book book : borrowedBooks) {
                            if (book.title.toLowerCase().equals(bookToRate)) {
                                System.out.println("Current rating for " + book.title + " is: " + book.getRating() + ". Enter a new rating (1-5): ");
                                if (myObj.hasNextInt()) {
                                    int newRating = myObj.nextInt();
                                    myObj.nextLine(); // Consume newline
                                    if (newRating >= 1 && newRating <= 5) {
                                        book.setRating(newRating);
                                        System.out.println("New rating for " + book.title + " is: " + book.getRating() + " stars");
                                    } else {
                                        System.out.println("Invalid input. Rating must be between 1 and 5.");
                                    }
                                } else {
                                    myObj.nextLine(); // Consumes the invalid input
                                    System.out.println("Invalid input. Please enter an integer between 1 and 5 for the rating.");
                                }
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("You can only adjust the rating of borrowed books.");
                        }
                        break;
                    case 6: // Exit program
                        System.out.println("Thank you for visiting Nalanda Library. Bye!");
                        return;
                    default:
                        System.out.println("Input is not a valid choice. Please try again.");
                        break;
                }
            } else {
                System.out.println("Input is not an integer. Please try again.");
                myObj.next(); // Consumes the invalid input to avoid an infinite loop
            }
        }
    }
}