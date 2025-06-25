package com.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseEmployee {
    /*
     * [
    {
        "id": 99,
        "email": "albertjuntak44@gmail.com",
        "password_hash": "2019427744ced02976c1ec9b7eeba833",
        "full_name": "Albert Simanjuntak",
        "department": "IT",
        "title": "QA",
        "create_at": "2025-05-20T13:23:56.839Z",
        "update_at": "2025-05-20T13:23:56.839Z"
    }
]
     */

    @JsonProperty("id")
    public int id;

    @JsonProperty("email")
    public String email;

    @JsonProperty("password_hash")
    public String passwordHash;

    @JsonProperty("full_name")
    public String fullName;

    @JsonProperty("department")
    public String department;

    @JsonProperty("title")
    public String title;

    @JsonProperty("create_at")
    public String createAt;

    @JsonProperty("update_at")
    public String updateAt;

    public ResponseEmployee() {};
}
