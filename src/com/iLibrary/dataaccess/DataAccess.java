package com.iLibrary.dataaccess;

import java.util.HashMap;

import com.iLibrary.models.*;

public interface DataAccess {
    HashMap<String, Book> readBooksMap();

    HashMap<String, User> readUserMap();

    HashMap<String, LibraryMember> readMemberMap();

    void saveNewMember(LibraryMember member);

    void deleteMember(String memberId);
}
