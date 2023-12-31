/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.librarymanagementsystempartb;

/**
 *
 * @author Shehzad Alam
 */
import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
interface Configuration {
    void displayInfo();
}
class Publication implements Configuration {
    private int id;
    private String title;

    public Publication(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
 @Override
    public void displayInfo() {
        System.out.println("------------");
        System.out.println("ID: " + id );
        System.out.println("Title: " + title);
    }
}
class Book extends Publication {
    private String author;
    private int year;
    private int popularityCount;
    private int price;

    public Book(int id, String title, String author, int year, int popularityCount, int price) {
        super(id, title);
        this.author = author;
        this.year = year;
        this.popularityCount = popularityCount;
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getPopularityCount() {
        return popularityCount;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Author: " + author);
        System.out.println("Year: " + year);
        System.out.println("Popularity Count: " + popularityCount);
        System.out.println("Price: $" + price);
    }
}
class Magazine extends Publication {
    private List<String> authors;
    private String publisherCompany;
    private int popularityCount;
    private int price;

    public Magazine(int id, String title, List<String> authors, String publisherCompany, int popularityCount, int price) {
        super(id, title);
        this.authors = authors;
        this.publisherCompany = publisherCompany;
        this.popularityCount = popularityCount;
        this.price = price;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getPublisherCompany() {
        return publisherCompany;
    }

    public int getPopularityCount() {
        return popularityCount;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Authors: " + String.join(", ", authors));
        System.out.println("Publisher Company: " + publisherCompany);
        System.out.println("Popularity Count: " + popularityCount);
        System.out.println("Price: $" + price);
    }
}
class Newspaper extends Publication {
    private String publisherCompany;
    private String publicationDay;
    private int popularityCount;

    public Newspaper(int id, String title, String publisherCompany, int popularityCount,String publicationDay) {
        super(id, title);
        this.publisherCompany = publisherCompany;
        this.popularityCount=popularityCount;

    try {
        // Parse the publication day string into a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
       Date parsedDate = dateFormat.parse(publicationDay);
        
            // Format the parsed date in the desired format
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            this.publicationDay = outputDateFormat.format(parsedDate);
    } catch (ParseException e) {
        e.printStackTrace();
        this.publicationDay = null;
    }
       
    }

    public String getPublisherCompany() {
        return publisherCompany;
    }

    public String getPublicationDay() {
        return publicationDay;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Publisher Company: " + publisherCompany);
        System.out.println("Popularity Count: "+ popularityCount);
        System.out.println("Publication Day: " + publicationDay);
    }
    }
 class Borrower {
    private int userId;
    private String name;
    private List<Integer> borrowedItems; // Store item IDs of borrowed items
     public Borrower() {
        borrowedItems = new ArrayList<>(); // Initialize the list here
    }

 public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Integer> getBorrowedItems() {
        return borrowedItems;
    }
    public void setBorrowedItems(List<Integer> borrowedItems) {
        this.borrowedItems = borrowedItems;
    }
    public void borrowItem(int itemId) {
        borrowedItems.add(itemId);
    }
    public void returnItem(int itemId) {
        borrowedItems.remove(Integer.valueOf(itemId));
    }
}
class LibraryItem extends Publication {
    private boolean isBorrowed;
    private int borrowerId;
    private int popularityCount;
    public LibraryItem(int id, String title, int popularityCount) {
        super(id, title);
        this.popularityCount = popularityCount;
    }
    public boolean isBorrowed() {
        return isBorrowed;
    }
    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
    public int getBorrowerId() {
        return borrowerId;
    }
    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }
    public int getPopularityCount() {
        return popularityCount;
    }
    public void setPopularityCount(int popularityCount) {
        this.popularityCount = popularityCount;
    }
}
class Library {
    private List<Publication> publications = new ArrayList<>();
  private List<LibraryItem> libraryItems = new ArrayList<>();
    private List< Borrower> borrowers;
      public Library() {
        libraryItems = new ArrayList<>();
        borrowers = new ArrayList<>();
    }
      public List<Borrower> getBorrowers() {
    return borrowers;
}
    public void addPublication(Publication publication) {
        publications.add(publication);
    }

    public boolean editPublication(int id, Publication newPublication) {
        for (int i = 0; i < publications.size(); i++) {
            if (publications.get(i).getId() == id) {
                publications.set(i, newPublication);
                return true;
            }
        }
        return false;
    }

    public boolean deletePublication(int id) {
        for (Publication publication : publications) {
            if (publication.getId() == id) {
                publications.remove(publication);
                return true;
            }
        }
        return false;
    }

    public List<Publication> getAllPublications() {
        return publications;
    }
      public void displayPublicationDetails(Publication publication) {
        if (publication != null) {
            publication.displayInfo();
        } else {
            System.out.println("Publication not found.");
        }
    }

    public Publication getPublicationById(int id) {
        for (Publication publication : publications) {
            if (publication.getId() == id) {
                return publication;
            }
        }
        return null;
    }
       public void addLibraryItem(LibraryItem item) {
        libraryItems.add(item);
    }

    public void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }
     public void borrowItem(int userId, int itemId) {
    Borrower borrower = findBorrower(userId);
    LibraryItem item = findLibraryItem(itemId);

    if (borrower != null && item != null && !item.isBorrowed()) {
        borrower.borrowItem(itemId);
        item.setBorrowed(true);
        item.setBorrowerId(userId);
        // Increase popularity count when an item is borrowed
        item.setPopularityCount(item.getPopularityCount() + 1);
    }
}
     public void returnItem(int userId, int itemId) {
    Borrower borrower = findBorrower(userId);
    LibraryItem item = findLibraryItem(itemId);

    if (borrower != null && item != null && borrower.getBorrowedItems().contains(itemId)) {
        borrower.returnItem(itemId);
        item.setBorrowed(false);
        item.setBorrowerId(0);
    }
}
   public void updatePopularityCount(int itemId, int popularityCount) {
    LibraryItem item = findLibraryItem(itemId);
    if (item != null) {
        item.setPopularityCount(popularityCount);
    }
}

    public void displayBorrowers() {
        for (Borrower borrower : borrowers) {
            System.out.println("---------------");
            System.out.println("Borrower ID: " + borrower.getUserId());
            System.out.println("Borrower Name: " + borrower.getName());
            List<String> borrowedItemTitles = borrower.getBorrowedItems().stream()
                    .map(itemId -> findLibraryItem(itemId).getTitle())
                    .collect(Collectors.toList());
            System.out.println("Borrowed Items: " + String.join(", ", borrowedItemTitles));
            System.out.println();
        }
    }

    public void displayHotPicks() {
        List<LibraryItem> hotPicks = libraryItems.stream()
                .sorted((item1, item2) -> Integer.compare(item2.getPopularityCount(), item1.getPopularityCount()))
                .collect(Collectors.toList());

        System.out.println("Hot Picks:");
        for (LibraryItem item : hotPicks) {
              System.out.println("---------------");
            System.out.println("Title: " + item.getTitle());
            System.out.println("Popularity Count: " + item.getPopularityCount());
            System.out.println();
        }
    }

    public Borrower findBorrower(int userId) {
        return borrowers.stream()
                .filter(borrower -> borrower.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    public LibraryItem findLibraryItem(int itemId) {
        for (LibraryItem item : libraryItems) {
        if (item.getId() == itemId) {
            return item;
        }
    }
    return null;
    }
}
public class LibraryManagementSystem {

    public static void main(String[] args) {
       Library library = new Library();
        loadDataFromFile(library);
        
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add a Publication");
            System.out.println("2. Edit a Publication");
            System.out.println("3. Delete a Publication");
            System.out.println("4. View All Publications");
            System.out.println("5. View Publication by ID");
            System.out.println("6. Hot Picks!");
            System.out.println("7. Borrow an Item");
            System.out.println("8. Return Book");
            System.out.println("9. View Borrowers List");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a Publication
                    addPublication(scanner, library);
                    break;
                case 2:
                    // Edit a Publication
                    editPublication(scanner, library);
                    break;
                case 3:
                    // Delete a Publication
                    deletePublication(scanner, library);
                    break;
                case 4:
                    // View All Publications
                    displayAllPublications(library);
                    break;
                case 5:
                    System.out.print("Enter Publication ID to View: ");
                    int idToView = scanner.nextInt();
                    Publication publicationToView = library.getPublicationById(idToView);
                    library.displayPublicationDetails(publicationToView);
                    break;
                case 6:
                     // Display Hot Picks
    library.displayHotPicks();
    break;
                    case 7:
    // Borrow a book
    borrowBook(scanner, library);
    break;
case 8:
    // Return a book
    returnBook(scanner, library);
    break;
case 9:
    // Display Borrowers
    library.displayBorrowers();
    break;
case 10:
   // Exit
                    System.out.println("Exiting Library Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 10);

        scanner.close();
    }

    private static void addPublication(Scanner scanner, Library library) {
         System.out.println("Enter Publication Type (1 for Book, 2 for Magazine, 3 for Newspaper): ");
    int publicationType = scanner.nextInt();
    scanner.nextLine(); 

    System.out.print("Enter Title: ");
    String title = scanner.nextLine();

    if (publicationType == 1) {
        // Book
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        System.out.print("Enter Popularity Count: ");
        int popularityCount = scanner.nextInt();
        System.out.print("Enter Price: ");
        int price = scanner.nextInt();

        int nextId = library.getAllPublications().size() + 1;
        Book newBook = new Book(nextId, title, author, year, popularityCount, price);
        library.addPublication(newBook);
         LibraryItem libraryItem = new LibraryItem(nextId, title, popularityCount);
        libraryItem.setBorrowed(false);
        library.addLibraryItem(libraryItem);
        System.out.println("Book added successfully.");
    } else if (publicationType == 2) {
        // Magazine
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Authors (comma-separated): ");
        String authorsLine = scanner.nextLine();
        // Split the input into authors using a comma followed by a space (", ")
        List<String> authors = Arrays.asList(authorsLine.split(", "));

        // Remove the period at the end of each author
        List<String> cleanedAuthors = new ArrayList<>();
        for (String author : authors) {
            if (author.endsWith(".")) {
                cleanedAuthors.add(author.substring(0, author.length() - 1)); // Remove the trailing period
            } else {
                cleanedAuthors.add(author);
            }
        }
        System.out.print("Enter Publisher Company: ");
        String publisherCompany = scanner.nextLine();
        System.out.print("Enter Popularity Count: ");
        int popularityCount = scanner.nextInt();
        System.out.print("Enter Price: ");
        int price = scanner.nextInt();

        int nextId = library.getAllPublications().size() + 1;
        Magazine newMagazine = new Magazine(nextId, title, authors, publisherCompany, popularityCount, price);
        library.addPublication(newMagazine);
         LibraryItem newLibraryItem = new LibraryItem(nextId, title, popularityCount);
        newLibraryItem.setBorrowed(false);
        library.addLibraryItem(newLibraryItem);
        System.out.println("Magazine added successfully.");
    } else if (publicationType == 3) {
        // Newspaper
        System.out.print("Enter Publisher Company: ");
        String publisherCompany = scanner.nextLine();
        System.out.print("Enter Publication Day: ");
        String publicationDay = scanner.nextLine();
        System.out.print("Enter the popularity count: ");
        int popularityCount = scanner.nextInt();
        int nextId = library.getAllPublications().size() + 1;
        Newspaper newNewspaper = new Newspaper(nextId, title, publisherCompany,popularityCount, publicationDay);
        library.addPublication(newNewspaper);
        LibraryItem newLibraryItem = new LibraryItem(nextId, title, popularityCount);
        newLibraryItem.setBorrowed(false);
        library.addLibraryItem(newLibraryItem);
        System.out.println("Newspaper added successfully.");
    } else {
        System.out.println("Invalid publication type.");
    }
    }

    private static void editPublication(Scanner scanner, Library library) {
        System.out.print("Enter Publication ID to Edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Publication publicationToEdit = library.getPublicationById(id);
        if (publicationToEdit != null) {
            System.out.println("Enter New Details:");

            System.out.print("Enter Title: ");
            String newTitle = scanner.nextLine();

            if (publicationToEdit instanceof Book) {
                // Editing a Book
                Book bookToEdit = (Book) publicationToEdit;
                System.out.print("Enter New Author: ");
                String newAuthor = scanner.nextLine();
                System.out.print("Enter New Year: ");
                int newYear = scanner.nextInt();
                System.out.print("Enter New Popularity Count: ");
                int newPopularityCount = scanner.nextInt();
                System.out.print("Enter New Price: ");
                int newPrice = scanner.nextInt();

                Book newBook = new Book(id, newTitle, newAuthor, newYear, newPopularityCount, newPrice);
                library.editPublication(id, newBook);
                System.out.println("Book edited successfully.");
            } else if (publicationToEdit instanceof Magazine) {
                // Editing a Magazine
                Magazine magazineToEdit = (Magazine) publicationToEdit;
                scanner.nextLine(); // Consume newline
                System.out.print("Enter New Authors (comma-separated): ");
                String newAuthorsLine = scanner.nextLine();
                List<String> newAuthors = List.of(newAuthorsLine.split(", "));
                System.out.print("Enter New Publisher Company: ");
                String newPublisherCompany = scanner.nextLine();
                System.out.print("Enter New Popularity Count: ");
                int newPopularityCount = scanner.nextInt();
                System.out.print("Enter New Price: ");
                int newPrice = scanner.nextInt();

                Magazine newMagazine = new Magazine(id, newTitle, newAuthors, newPublisherCompany, newPopularityCount, newPrice);
                library.editPublication(id, newMagazine);
                System.out.println("Magazine edited successfully.");
            } else if (publicationToEdit instanceof Newspaper) {
                // Editing a Newspaper
                Newspaper newspaperToEdit = (Newspaper) publicationToEdit;
                System.out.print("Enter New Publisher Company: ");
                String newPublisherCompany = scanner.nextLine();
                System.out.print("Enter New Popularity count: ");
                int popularityCount=scanner.nextInt();
                System.out.print("Enter New Publication Day: ");
                String newPublicationDay = scanner.nextLine();
                Newspaper newNewspaper = new Newspaper(id, newTitle, newPublisherCompany, popularityCount,newPublicationDay);
                library.editPublication(id, newNewspaper);
                System.out.println("Newspaper edited successfully.");
            }
        } else {
            System.out.println("Publication with ID " + id + " not found.");
        }
    }

    private static void deletePublication(Scanner scanner, Library library) {
        System.out.print("Enter Publication ID to Delete: ");
        int id = scanner.nextInt();
        boolean deleted = library.deletePublication(id);
        if (deleted) {
            System.out.println("Publication with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Publication with ID " + id + " not found.");
        }
    }

    private static void displayAllPublications(Library library) {
      System.out.println("All Publications:");

    // Load data from the file if the library is empty
    if (library.getAllPublications().isEmpty()) {
        loadDataFromFile(library);
    }

    // Display all publications
    List<Publication> allPublications = library.getAllPublications();
    for (Publication publication : allPublications) {
        publication.displayInfo();
    }
    }
    private static void borrowBook(Scanner scanner, Library library) {
      System.out.print("Enter the ID of the book you want to borrow: ");
    int bookId = scanner.nextInt();

    // Check if the book exists in the library
    LibraryItem bookToBorrow = library.findLibraryItem(bookId);
    System.out.println(bookToBorrow );
    if (bookToBorrow == null) {
        System.out.println("Book not found.");
        return;
    }

    // Check if the book is already borrowed
    if (bookToBorrow.isBorrowed()) {
        System.out.println("This book is already borrowed by another user.");
        return;
    }

    // Borrow the book
    System.out.print("Enter your name: ");
    String userName = scanner.next();
    Borrower borrower = new Borrower();
    borrower.setName(userName);
    borrower.setUserId(library.getBorrowers().size() + 1); // Generate a unique user ID
    library.addBorrower(borrower);
bookToBorrow.setPopularityCount(bookToBorrow.getPopularityCount());
    library.borrowItem(borrower.getUserId(), bookId);
    System.out.println(userName + ", you have successfully borrowed the book: " + bookToBorrow.getTitle());
}

private static void returnBook(Scanner scanner, Library library) {
    System.out.print("Enter your User ID: ");
    int userId = scanner.nextInt();

    // Check if the user exists in the library
    Borrower borrower = library.findBorrower(userId);

    if (borrower == null) {
        System.out.println("User not found. Please register as a borrower first.");
        return;
    }

    System.out.print("Enter the ID of the book you want to return: ");
    int bookId = scanner.nextInt();

    // Check if the book exists in the library
    LibraryItem bookToReturn = library.findLibraryItem(bookId);

    if (bookToReturn == null) {
        System.out.println("Book not found.");
        return;
    }

    // Check if the user has borrowed this book
    if (!borrower.getBorrowedItems().contains(bookId)) {
        System.out.println(borrower.getName() + ", you haven't borrowed this book.");
        return;
    }

    // Return the book
    library.returnItem(userId, bookId);
    System.out.println(borrower.getName() + ", you have successfully returned the book: " + bookToReturn.getTitle());
}
    private static void loadDataFromFile(Library library) {
  String filePath = "C:\\Users\\Shehzad Alam\\Desktop\\data.txt";

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            String[] parts = line.split(", "); 
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                //String type = parts[2];
                 if (id == 1 || id == 2 || id == 3) {
                int popularityCount = Integer.parseInt(parts[parts.length - 2]);
                LibraryItem libraryItem = new LibraryItem(id, title, popularityCount);
                library.addLibraryItem(libraryItem);
                 }
                if (1==id) {
                    String author = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    int popularityCount = Integer.parseInt(parts[4]);
                    int price = Integer.parseInt(parts[5]);
                    Book book = new Book(id, title, author, year, popularityCount, price);
                    library.addPublication(book);
                }
               if (2 == id) {
    // Magazine
    parts = line.split(", ");
    title = parts[1];

    String[] authorArray = Arrays.copyOfRange(parts, 2, parts.length - 3);
    List<String> authors = new ArrayList<>();

    // Process author names
    for (String author : authorArray) {
        authors.add(author);
    }

    String publisherCompany = parts[parts.length - 3];
    int popularityCount = Integer.parseInt(parts[parts.length - 2]);
    int price = Integer.parseInt(parts[parts.length - 1]);

    // Create and add the magazine to the library
    int nextId = library.getAllPublications().size() + 1;
    Magazine magazine = new Magazine(nextId, title, authors, publisherCompany, popularityCount, price);
    library.addPublication(magazine);
}
                         if (3==id) {
                    String publisherCompany = parts[2];
                    int popularityCount = Integer.parseInt(parts[3]);
                    String publicationDay=parts[4];
                    Newspaper newspaper = new Newspaper(id, title, publisherCompany, popularityCount, publicationDay);
                    library.addPublication(newspaper);
                }
            }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    }
