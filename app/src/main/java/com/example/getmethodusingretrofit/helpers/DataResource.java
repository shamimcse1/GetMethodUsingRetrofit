package com.example.getmethodusingretrofit.helpers;

public class DataResource<T>{

    private DataStatus status;
    private T data;
    private String message;

    public DataResource(DataStatus status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> DataResource<T> loading() {
        return new DataResource(DataStatus.LOADING, null, null);
    }

    public static <T> DataResource<T> success(T data) {
        return new DataResource(DataStatus.SUCCESS, data, null);
    }

    public static <T> DataResource<T> error(String message) {
        return new DataResource(DataStatus.ERROR, null, message);
    }

    public T getActualData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public DataStatus getStatus() {
        return status;
    }
}
