package com.dl.design.iterator;

public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf=new BookShelf(4);
        bookShelf.appendBook(new Book("java"));
        bookShelf.appendBook(new Book("python"));
        bookShelf.appendBook(new Book("go"));
        bookShelf.appendBook(new Book("c"));
        Iterator iterator=bookShelf.iterator();
        while (iterator.hasNext()){
            Book book = (Book) iterator.next();
            System.out.println(book.getName());
        }
    }
}
