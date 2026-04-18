//package com.parking.SpotFinder_app.model;
//
//public class LoginResponse {
//    private String token;
//    private String role;
//    private Integer id;
//    private String username;
//    private String message;
//
//    public LoginResponse(String token, String role, Integer id, String username) {
//        this.token = token;
//        this.role = role;
//        this.id = id;
//        this.username = username;
//    }
//
//    public LoginResponse(String token, String role, Integer id, String username, String message) {
//        this.token = token;
//        this.role = role;
//        this.id = id;
//        this.username = username;
//        this.message = message;
//    }
//
//    public String getToken() { return token; }
//    public void setToken(String token) { this.token = token; }
//
//    public String getRole() { return role; }
//    public void setRole(String role) { this.role = role; }
//
//    public Integer getId() { return id; }
//    public void setId(Integer id) { this.id = id; }
//
//    public String getUsername() { return username; }
//    public void setUsername(String username) { this.username = username; }
//
//    public String getMessage() { return message; }
//    public void setMessage(String message) { this.message = message; }
//}


package com.parking.SpotFinder_app.model;

public class LoginResponse {
    private String token;
    private String role;
    private Integer id;
    private String username;
    private String message;

    public LoginResponse(String token, String role, Integer id, String username) {
        this.token = token;
        this.role = role;
        this.id = id;
        this.username = username;
    }

    public LoginResponse(String token, String role, Integer id, String username, String message) {
        this.token = token;
        this.role = role;
        this.id = id;
        this.username = username;
        this.message = message;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
