package ohtu.services;

import ohtu.domain.User;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public String createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return "username is already taken";
        }

        var error = invalid(username, password);
        if (error != null) {
            return error;
        }

        userDao.add(new User(username, password));

        return null;
    }

    private String invalid(String username, String password) {
        if (username.length() < 3) {
            return "username must contain at least 3 characters";
        }
        if (password.length() < 8) {
            return "password must contain at least 8 characters";
        }
        for (var i = 0; i < password.length(); i++) {
            if (!Character.isLetter(password.charAt(i))) {
                return null;
            }
        }
        return "password must not consist of letters only";
    }
}
