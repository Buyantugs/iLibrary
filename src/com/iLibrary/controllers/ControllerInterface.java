package com.iLibrary.controllers;

import java.util.List;

import com.iLibrary.exceptions.LoginException;

public interface ControllerInterface {
    void login(String id, String password) throws LoginException;

    List<String> allMemberIds();

    List<String> allBookIds();
}
