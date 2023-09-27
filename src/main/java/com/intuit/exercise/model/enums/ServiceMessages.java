package com.intuit.exercise.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceMessages {

    DB_QUERY_FAILURE(7, "Failure in DB access"),
    INVALID_INPUT(9, "זמנית לא ניתן לספק שירות זה"),
    ACTION_DECLINE(123, "זמנית לא ניתן לספק שירות זה");

    private final int messageCode;
    private final String message;

}
