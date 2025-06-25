package com.demo.testng.program;

import java.io.ObjectInputFilter.Config;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final Map<String, Object> data = new HashMap<>();

        data.put("token", "eakfaeufbaeuifbeau");
        data.put("baseUrl", "https://example.com/api");

        System.out.println("Token: " + data.get("token"));
        System.out.println("Base URL: " + data.get("baseUrl"));

    }
}