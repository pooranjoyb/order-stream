package com.pooranjoyb.graphql.gateway.core;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Constants {
    public static String ORDER_GET_ORDERS = "/orders";
    public static String ORDER_GET_BY_ID = "/orders/{id}";
    public static String ORDER_CREATE = "/orders";
    public static String ORDER_UPDATE_BY_ID = "/orders/{id}";
    public static String ORDER_DELETE_BY_ID = "/orders/{id}";
}
