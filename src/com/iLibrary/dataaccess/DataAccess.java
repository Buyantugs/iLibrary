package com.iLibrary.dataaccess;

import java.util.HashMap;

import com.iLibrary.models.*;

public interface DataAccess {
    HashMap<String, Book> readBooksMap();

    HashMap<String, User> readUserMap();

    HashMap<String, LibraryMember> readMemberMap();

    void saveNewMember(LibraryMember member);

    void editNewMember(LibraryMember updatedMember);

    void deleteMember(String memberId);

    void checkoutBook(LibraryMember member, BookCopy bookCopy);

    void addBookCopy(String isbn, int copyCount);

    HashMap<String, CheckOutRecord> readCheckOutRecordsMap();

    void saveCheckOutRecord(HashMap<String, CheckOutRecord> hmCheckOutRecords);
}
