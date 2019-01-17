package com.trainingapp.trainingapp_web.models;

import org.mindrot.jbcrypt.BCrypt;

public class ViewModelPassword {

    private static final int ROUNDS = 12;

    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(ROUNDS));
    }

    public static boolean check(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    public static boolean validateLength(String password) {
        return password.length() >= 4;
    }

}
