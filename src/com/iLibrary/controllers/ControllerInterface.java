package com.iLibrary.controllers;

import java.util.List;

import com.iLibrary.exceptions.LoginException;

public interface ControllerInterface {
    public void login(String id, String password) throws LoginException;

    public List<String> allMemberIds();

    public List<String> allBookIds();
}
