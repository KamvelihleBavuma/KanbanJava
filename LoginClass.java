/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logins;

import javax.swing.JOptionPane;

/**
 *
 * @author kamve
 */
public class LoginClass {
    //Login fields
    private String userName = "kyl_1";
    private String password = "Ch&&sec@ke99!";

    //Setters
    public void setUsername (String usrname, String usrn)
    {
        this.userName = usrname;
        checkUserName();
        registerUserUsername();
        loginUserUsername(usrname, usrn);
    }
    public void setPassword (String pssword, String psw)
    {
        this.password = pssword;
        checkPasswordComplexity();
        registerUserPassword();
        loginUserPassword(pssword, psw);
    }
    
    //Getters
    public String getUsername()
    {
        return this.userName;
    }
    public String getPassword()
    {
        return this.password;
    }
    
    //Work Methods
    public boolean checkUserName()
    {
        return userName.contains("_") && userName.length() <=5 && !userName.equals("");
    }
    public boolean checkPasswordComplexity()
    { 
        return password.matches(".*[@#&!$%^*-_=+;:',<.>/?|`~]{1,}.*") && password.matches(".*[0-9]{1,}.*") && password.matches(".*[A-Z]{1,}.*") && password.length() >=8 && !password.equals("");    
    }
    public String registerUserUsername()
    {
        if (userName.contains("_") && userName.length() <=5 && !userName.equals(""))
        {
            JOptionPane.showMessageDialog(null, "The username is successfully captured.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "The username is incorrectly formatted.");  
        }
        return userName;
    }
    public String registerUserPassword()
    {
        if (password.matches(".*[@#&!$%^*-_=+;:',<.>/?|`~]{1,}.*") && password.matches(".*[0-9]{1,}.*") && password.matches(".*[A-Z]{1,}.*") && password.length() >=8 && !password.equals(""))
        {
            JOptionPane.showMessageDialog(null, "The password is successfully captured.");
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "The password does not meet the complexity requirements.");
        }
        return password;
     }
    public boolean loginUserUsername(String usrname, String usrn)
    {
        return usrname.equals(usrn);
    }
    public boolean loginUserPassword(String pssword, String psw)
    {
        return pssword.equals(psw);
    }
    public String returnLoginStatusTrue(String name, String surname)
    {
        return "You have successfully logged in."+"\n"+"Welcome "+name+" "+surname+" It is great to see you again.";
    }
    public String returnLoginStatusFalse()
    {
        return "Username or password was incorrectly entered in login or the entered details did not match the details in the saved file with valid accounts, please try again by entering the correct details.";
    }
}