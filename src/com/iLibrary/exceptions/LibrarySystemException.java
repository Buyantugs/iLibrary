package com.iLibrary.exceptions;

import java.io.Serializable;

public class LibrarySystemException extends Exception {
	public LibrarySystemException() {
		super();
	}
	public LibrarySystemException(String msg) {
		super(msg);
	}
	public LibrarySystemException(Throwable t) {
		super(t);
	}
}
