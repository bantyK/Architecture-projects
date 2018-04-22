package com.example.vuclip.mvvmlogin;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Banty on 12/04/18.
 */
public class LoginViewModel extends BaseObservable {

    public String passwordHint;
    private String emailHint;

    private String userEmail;
    private String userPassword;

    public String forgotPasswordText;
    public String loginButtonText;

    public String errorPassword;
    public String errorEmail;


    public LoginViewModel(String passwordHint, String emailHint, String forgotPasswordText, String loginButtonText) {
        this.passwordHint = passwordHint;
        this.emailHint = emailHint;
        this.forgotPasswordText = forgotPasswordText;
        this.loginButtonText = loginButtonText;
    }

    public String getEmailHint() {
        return emailHint;
    }

    public void setEmailHint(String emailHint) {
        this.emailHint = emailHint;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;

        notifyPropertyChanged(R.id.EmaileditText);
        notifyPropertyChanged(BR.errorEmail);
    }

    public String getPasswordHint() {
        return passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    @Bindable
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;

        notifyPropertyChanged(R.id.PasswordeditText);
        notifyPropertyChanged(BR.errorPassword);
    }

    public String getForgotPasswordText() {
        return forgotPasswordText;
    }

    public void setForgotPasswordText(String forgotPasswordText) {
        this.forgotPasswordText = forgotPasswordText;
    }

    public String getLoginButtonText() {
        return loginButtonText;
    }

    public void setLoginButtonText(String loginButtonText) {
        this.loginButtonText = loginButtonText;
    }

    public static boolean isPasswordValid(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        pattern = Pattern.compile(PASSWORD_PATTERN);

        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Bindable
    public String getErrorPassword() {
        if (userPassword == null) {
            return "Please Enter password";
        } else if (userPassword.length() < 8) {
            return "Too Short...!";
        } else if (!isPasswordValid(userPassword)) {
            return "Your Password Must be combination of \n Small,Captital & Special Characters";
        } else {
            return null;
        }
    }

    public static boolean isValidEmail(final String userEmail) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = android.util.Patterns.EMAIL_ADDRESS;
        matcher = pattern.matcher(userEmail);
        return matcher.matches();
    }

    @Bindable
    @Nullable
    public String getErrorEmail() {
        if (userEmail == null) {
            return "Enter Email";
        } else if (!isValidEmail(userEmail)) {
            return "Enter valid email";
        } else {
            return null;
        }
    }
}
