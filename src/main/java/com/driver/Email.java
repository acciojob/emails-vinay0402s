package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:

        //step 1= Check if the oldPassword matches the current password
        if(!this.password.equals(oldPassword)){
            System.out.println("The old password is incorrect");
            return;
        }

        //step 2 = Check if the newPassword meets the specified criteria
        if(!valid(newPassword)){
            System.out.println("The new password must meet all citeria");
            return;
        }

        this.password=newPassword;
        System.out.println("Password changed Successfully");
    }
    public boolean valid(String new_password){
        // 1. It contains at least 8 characters
        if(new_password.length() < 8) {
            return false;
        }

        // 2. It contains at least one uppercase letter
        if(!new_password.matches(".*[A-Z]+.*")) {
            return false; //
        }

        // 3. It contains at least one lowercase letter
        if(!new_password.matches(".*[a-z]+.*")) {
            return false; //
        }

        // 4. It contains at least one digit
        if(!new_password.matches(".*\\d+.")) {
            return false; //
        }

        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        //[^A-Za-z0-9]
        if(!new_password.matches(".*[^A-Za-z0-9]+.")) {
            return false; //
        }

        //here all citeria are met
        return true;
    }
}
