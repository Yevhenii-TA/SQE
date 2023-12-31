package org.example.pageObject.taskThree;

public class User {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;

    public User() {
    }
    public Integer getId() {
        return id;
    }
    public User setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getUsername() {
        return username;
    }
    public User setUsername(String username) {
        this.username = username;
        return this;
    }
    public String getFirstName() {
        return firstName;
    }
    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public String getLastName() {
        return lastName;
    }
    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public User setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getPassword() {
        return password;
    }
    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    public String getPhone() {
        return phone;
    }
    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }
    public Integer getUserStatus() {
        return userStatus;
    }
    public User setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }
}
