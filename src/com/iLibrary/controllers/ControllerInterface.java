package com.iLibrary.controllers;

import java.util.List;

import com.iLibrary.exceptions.LoginException;
import com.iLibrary.models.Book;
import com.iLibrary.models.LibraryMember;

public interface ControllerInterface {
    void login(String id, String password) throws LoginException;

    List<LibraryMember> allLibraryMembers();

    List<Book> allBooks();

    List<Book> searchBooksByISBN(String isbn);

    List<LibraryMember> searchMembersById(String id);
}
