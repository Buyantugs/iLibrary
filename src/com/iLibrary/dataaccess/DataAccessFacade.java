package com.iLibrary.dataaccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.iLibrary.models.*;


public class DataAccessFacade implements DataAccess {

    enum StorageType {
        BOOKS, MEMBERS, USERS, CHECKOUTRECORD
    }

    public static final String OUTPUT_DIR = System.getProperty("user.dir")
            + "//src//com//iLibrary//dataaccess//storage";
    public static final String DATE_PATTERN = "MM/dd/yyyy";

    //implement: other save operations
    public void saveNewMember(LibraryMember member) {
        HashMap<String, LibraryMember> members = readMemberMap();
        String memberId = member.getMemberId();
        members.put(memberId, member);
        saveToStorage(StorageType.MEMBERS, members);
    }

    public void editNewMember(LibraryMember updatedMember) {
        HashMap<String, LibraryMember> members = readMemberMap();
        String memberId = updatedMember.getMemberId();
        members.replace(memberId, updatedMember);
        saveToStorage(StorageType.MEMBERS, members);
    }

    @Override
    public void deleteMember(String memberId) {
        HashMap<String, LibraryMember> mems = readMemberMap();
        mems.remove(memberId);
        saveToStorage(StorageType.MEMBERS, mems);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, Book> readBooksMap() {
        return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, LibraryMember> readMemberMap() {
        return (HashMap<String, LibraryMember>) readFromStorage(
                StorageType.MEMBERS);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, User> readUserMap() {
        return (HashMap<String, User>) readFromStorage(StorageType.USERS);
    }


    static void saveBookMap(List<Book> bookList) {
        HashMap<String, Book> books = new HashMap<String, Book>();
        bookList.forEach(book -> books.put(book.getIsbn(), book));
        saveToStorage(StorageType.BOOKS, books);
    }

    static void saveUserMap(List<User> userList) {
        HashMap<String, User> users = new HashMap<String, User>();
        userList.forEach(user -> users.put(user.getId(), user));
        saveToStorage(StorageType.USERS, users);
    }

    static void saveMemberMap(List<LibraryMember> memberList) {
        HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
        memberList.forEach(member -> members.put(member.getMemberId(), member));
        saveToStorage(StorageType.MEMBERS, members);
    }

    static void saveCheckoutRecordMap(CheckOutRecord checkoutRecord) {
        HashMap<String, CheckOutRecord> checkoutRecords = new HashMap<String, CheckOutRecord>();
        checkoutRecords.put(checkoutRecord.getMember().getMemberId(), checkoutRecord);
        saveToStorage(StorageType.CHECKOUTRECORD, checkoutRecords);
    }

    @Override
    public void checkoutBook(LibraryMember member, BookCopy bookCopy) {
        System.out.println(member + "\t" + bookCopy);
        bookCopy.changeAvailability();

        LocalDate dueDate = LocalDate.now().plusDays(bookCopy.getBook().getMaxCheckoutLength());
        LocalDate checkoutDate = LocalDate.now();

        CheckOutRecordEntry checkoutEntry = new CheckOutRecordEntry(dueDate, checkoutDate, bookCopy);

        HashMap<String, CheckOutRecord> checkoutRecords = readCheckOutRecordsMap();

        CheckOutRecord checkOutRecord;

        if (checkoutRecords.get(member.getMemberId()) != null) {
            checkOutRecord = checkoutRecords.get(member.getMemberId());
            checkOutRecord.addCheckOutRecordEntry(checkoutEntry);
        } else {
            List<CheckOutRecordEntry> recordEntries = new ArrayList<>();
            recordEntries.add(checkoutEntry);

            checkOutRecord = new CheckOutRecord(member, recordEntries);
        }

        checkoutRecords.put(member.getMemberId(), checkOutRecord);
        saveCheckOutRecord(checkoutRecords);
    }

    @Override
    public void addBookCopy(String isbn, int copyCount) {
        HashMap<String, Book> books = readBooksMap();
        Book searchedBook = books.get(isbn);
        for (int i = 0; i < copyCount; i++) {
            searchedBook.addCopy();
        }
        books.put(searchedBook.getIsbn(), searchedBook);
        saveToStorage(StorageType.BOOKS, books);
    }

    @SuppressWarnings("unchecked")
    @Override
    public HashMap<String, CheckOutRecord> readCheckOutRecordsMap() {
        return (HashMap<String, CheckOutRecord>) readFromStorage(StorageType.CHECKOUTRECORD);
    }

    @Override
    public void saveCheckOutRecord(HashMap<String, CheckOutRecord> checkoutRecords) {
        saveToStorage(StorageType.CHECKOUTRECORD, checkoutRecords);
    }

    static void saveToStorage(StorageType type, Object ob) {
        ObjectOutputStream out = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            out = new ObjectOutputStream(Files.newOutputStream(path));
            out.writeObject(ob);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
        }
    }

    static Object readFromStorage(StorageType type) {
        ObjectInputStream in = null;
        Object retVal = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            in = new ObjectInputStream(Files.newInputStream(path));
            retVal = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }
        return retVal;
    }


    final static class Pair<S, T> implements Serializable {

        S first;
        T second;

        Pair(S s, T t) {
            first = s;
            second = t;
        }

        @Override
        public boolean equals(Object ob) {
            if (ob == null) return false;
            if (this == ob) return true;
            if (ob.getClass() != getClass()) return false;
            @SuppressWarnings("unchecked")
            Pair<S, T> p = (Pair<S, T>) ob;
            return p.first.equals(first) && p.second.equals(second);
        }

        @Override
        public int hashCode() {
            return first.hashCode() + 5 * second.hashCode();
        }

        @Override
        public String toString() {
            return "(" + first.toString() + ", " + second.toString() + ")";
        }

        private static final long serialVersionUID = 5399827794066637059L;
    }
}
