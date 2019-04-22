package com.msu.elibrary.exceptions;

/**
 * @author VPatel
 */
public class FileExistException extends Exception {
    public FileExistException(){
        super("File already exist");
    }
}
