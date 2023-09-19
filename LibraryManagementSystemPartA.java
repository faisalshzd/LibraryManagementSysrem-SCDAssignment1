/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.assignment1;

/**
 *
 * @author Shehzad Alam
 */
import java.util.Scanner;

class Book {
    private static int nextId = 1; // Static variable to auto-increment the ID and intialize the static ID counter.
    private int id;
    private String title;
    private String author;
    private int year;

    public Book(String t, String a, int y) {
        id = nextId++; // Assign the next available ID and increment it.
        title = t;
        author = a;
        year = y;
    }

    public void display() {
        System.out.println("ID: " + id + " Title: " + title + " by " + author + " (" + year + ")");
    }
}

public class LibraryManagementSystemPartA {
    public static void main(String[] args) {
        String title, author;
        int year;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the book: ");
        title = scanner.nextLine();
        System.out.print("Enter the author of the book: ");
        author = scanner.nextLine();
        System.out.print("Enter the year of publication of the book: ");
        year = scanner.nextInt();
        scanner.nextLine(); // Ignore the newline character
        Book book = new Book(title, author, year);
         //Book book1 = new Book(title, author, year);
        book.display();
        //book.display();
        //book1.display();
        scanner.close();
    }
}
