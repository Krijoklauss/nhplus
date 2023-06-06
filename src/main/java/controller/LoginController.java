package controller;

import model.Caregiver;

/**
 *  The <code>LoginController</code> contains the static context of the user state
 *  It provides information about the currently logged in user
 */

public class LoginController {
    private static int ADMIN_POW = 10;

    /**
     *
     * @return ADMIN_POW
     */
    public static int getAdminPow() {
        return ADMIN_POW;
    }

    private static Caregiver loggedInUser;

    /**
     *
     * @return loggedInUser
     */
    public static Caregiver getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Checks if the loggedInUser is an admin
     * @return boolean
     */

    public static boolean isAdmin() {
        Caregiver cUser = getLoggedInUser();
        if(cUser.getRole().getPower() == LoginController.getAdminPow()) {
            return true;
        }
        return false;
    }

    /**
     * Sets the currently logged in user
     * @param c
     */
    public static void setLoggedInUser(Caregiver c) {
        loggedInUser = c;
    }
}
