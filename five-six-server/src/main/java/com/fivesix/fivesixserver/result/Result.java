package com.fivesix.fivesixserver.result;

public class Result {

    private int code;
    private String message;

    public Result(int code, String message) {

        this.code = code;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int code) {

        this.code = code;
    }

    public int getCode() {

        return this.code;
    }
}
