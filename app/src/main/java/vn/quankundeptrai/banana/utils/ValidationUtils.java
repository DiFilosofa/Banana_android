package vn.quankundeptrai.banana.utils;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static vn.quankundeptrai.banana.data.constants.AppConstants.PASSWORD_MIN_LENGTH;

/**
 * Created by TQN on 1/30/18.
 */

public class ValidationUtils {
    public static boolean isValidEmail(String email) {
        return (isMatch(email.trim(), Patterns.EMAIL_ADDRESS.pattern()));
    }

    public static boolean isMatch(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }


    public static boolean isPasswordValid(String passwordInput) {
        return passwordInput.length() >= PASSWORD_MIN_LENGTH;
    }
}
