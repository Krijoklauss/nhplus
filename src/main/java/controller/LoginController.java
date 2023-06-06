package controller;

import model.Caregiver;

public class LoginController {
    private static int ADMIN_POW = 10;
    public static int getAdminPow() {
        return ADMIN_POW;
    }

    private static Caregiver loggedInUser;
    public static Caregiver getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(Caregiver c) {
        loggedInUser = c;
    }
}
