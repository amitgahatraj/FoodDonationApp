package Impl;

import java.util.HashMap;
import java.util.Map;
import Interface.AuthInterface;
import Model.User;

public class AuthService implements AuthInterface {
    private Map<String, User> users = new HashMap<>();

    @Override
    public void registerUser(String username, String password, String contactInfo, boolean isDonor) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password, contactInfo, isDonor));
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Username already exists.");
        }
    }

    @Override
    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful.");
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }
}
