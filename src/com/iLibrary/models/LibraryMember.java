package com.iLibrary.models;

import com.iLibrary.views.table.ShowableOnCTable;

import java.io.Serializable;

final public class LibraryMember extends Person implements ShowableOnCTable, Serializable {
    private static final long serialVersionUID = -2226197306790714013L;
    private final String memberId;

    public LibraryMember(String memberId, String fname, String lname, String tel, Address add) {
        super(fname, lname, tel, add);
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() +
                ", " + getTelephone() + " " + getAddress();
    }

    @Override
    public String showThisDataOnTable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return memberId;
            case 1:
                return super.getFirstName();
            case 2:
                return super.getLastName();
            default:
                return "";
        }
    }
}
