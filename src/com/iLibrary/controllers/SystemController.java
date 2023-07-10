package com.iLibrary.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iLibrary.dataaccess.*;
import com.iLibrary.exceptions.LoginException;
import com.iLibrary.models.*;


public class SystemController implements ControllerInterface {
    public static Auth currentAuth = null;

    public void login(String id, String password) throws LoginException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            throw new LoginException("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        currentAuth = map.get(id).getAuthorization();
    }

    @Override
    public List<LibraryMember> allLibraryMembers() {
        DataAccess da = new DataAccessFacade();
        return da.readMemberMap().values().stream().toList();
    }

    @Override
    public List<Book> allBooks() {
        DataAccess da = new DataAccessFacade();
        return da.readBooksMap().values().stream().toList();
    }

    @Override
    public List<Book> searchBooksByISBN(String isbn) {
        DataAccess da = new DataAccessFacade();
        return da.readBooksMap().values().stream().filter(book -> book.getIsbn().startsWith(isbn)).toList();
    }

    @Override
    public List<LibraryMember> searchMembersById(String id) {
        DataAccess da = new DataAccessFacade();
        return da.readMemberMap().values().stream().filter(lm -> lm.getMemberId().startsWith(id)).toList();
    }

    @Override
    public void saveLibraryMember(LibraryMember member) {
        DataAccess da = new DataAccessFacade();
        da.saveNewMember(member);
    }

    @Override
    public void editLibraryMember(LibraryMember updatedMember) {
        DataAccess da = new DataAccessFacade();
        da.editNewMember(updatedMember);
    }

    @Override
    public void deleteLibraryMember(String memberId) {
        DataAccess da = new DataAccessFacade();
        da.deleteMember(memberId);
    }

    @Override
    public void checkoutBook(String memberId, String bookId) {
        DataAccess da = new DataAccessFacade();
        Map<String, LibraryMember> members = da.readMemberMap();

        if (members.containsKey(memberId)) {
            LibraryMember searchedMember = members.get(memberId);
            BookCopy bookCopy = da.readBooksMap().get(bookId).getNextAvailableCopy();
            da.checkoutBook(searchedMember, bookCopy);
        } else {
            System.err.println("Library member not found");
        }
    }

    @Override
    public void addBookCopy(String isbn, int copyCount) {
        DataAccess da = new DataAccessFacade();
        da.addBookCopy(isbn, copyCount);
    }
}
