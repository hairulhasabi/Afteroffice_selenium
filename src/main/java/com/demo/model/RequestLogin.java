package com.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestLogin {
    /*
     * Object to represent the request for employee data
     * RequestEmployee = new RequestEmployee("Andi", "Juntak", "20");
     * 
     * serialize
     * object to json
     * {
     *     "first_name": "Andi","last_name": "Juntak","age": "20"
     * }
     * 
     * deserialize
     * json to object
     * {
     *     "first_name": "Andi","last_name": "Juntak","age": "20"
     * }
     * object = new RequestEmployee("Andi", "Juntak", "20");
     * object.getFirstName();
     */

     /*
      * {\n" + //
                        "  \"email\": \"albertjuntak27@gmail.com\",\n" + //
                        "  \"password\": \"afteroffice123\"\n" + //
                        "}
      */

        @JsonProperty("email")
        public String email;

        @JsonProperty("password")
        public String password;

        public RequestLogin() {
        }

        public RequestLogin(String email, String password) {
            this.email = email;
            this.password = password;
        }
    
}
