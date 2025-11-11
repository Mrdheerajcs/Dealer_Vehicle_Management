package com.dealer_vehicle.Util;

import com.dealer_vehicle.Response.ApiResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.http.HttpStatus;

public class ResponseUtils {

    public static <T> ApiResponse<T> createSuccessResponse(T data, TypeReference<T> tClass) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setResponse(data);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("success");
        return response;
    }

    public static <T> ApiResponse<T> createSuccessResponseWithCred(T data, TypeReference<T> tClass) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setResponse(data);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("success");
        response.setKey("4gcXBD");
        response.setSalt("uEUTeQqzQhwwHg0qZCpYVRA9ufPnR9zE");
        response.setProduction(true);
        return response;
    }

    public static <T> ApiResponse<T> createFailureResponse(T data, TypeReference<T> tClass, String msg, Integer status) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setResponse(data);
        response.setStatus(status);
        response.setMessage(msg);
        return response;
    }

    public static <T> ApiResponse<T> createNotFoundResponse(String msg, Integer status) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(status);
        response.setMessage(msg);
        return response;
    }
}
