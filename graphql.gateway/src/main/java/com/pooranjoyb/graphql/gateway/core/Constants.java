package com.pooranjoyb.graphql.gateway.core;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Constants {
    public static String ORDER_GET_ORDERS = "/orders";
    public static String ORDER_GET_BY_ID = "/orders/{id}";
    public static String ORDER_DELETE_BY_ID = "/orders/{id}";
    public static String PRODUCT_GET_PRODUCTS = "/products";
    public static String PRODUCT_CREATE_PRODUCT = "/products";
}
