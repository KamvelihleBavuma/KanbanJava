/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logins;

import java.awt.HeadlessException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JOptionPane;

/**
 *
 * @author kamvelihle
 */
public class MainKanbanClass {
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //Declarations
        //---------------------------------------------------------------------
        //Arrays
        String[] users = new String[10];
        String[] usernames = new String[10];
        String[] passwords = new String[10];
        String[] names = new String[10];
        String[] surnames = new String[10];
        //User record fields
        String username;
        String password;
        String name;
        String surname;
        //login record
        String searchUsername = null;
        String searchPassword = null;
        String loginUsername;
        String loginName;
        String loginSurname;
        String loginPassword;
        //work variables
        int selection = 0;
        int numOfAcc = 0;
        boolean valid;
        String record;     //saving all the details in this string to save them in the file
        String coma = ",";  //A coma to separate the details of records
        String line;
        String[] loginUsers;
        //loop control variables
        int x;
        int i;        
        //Classes
        LoginClass logins = new LoginClass();
        File file = new File("NewKanbanAccounts.txt"); //Add the new file name and path here
        Path path = Paths.get("NewKanbanAccounts.txt"); //Add the same new file path here again
        //-----------------------------------------------------------------------------------------------------------
        //Checking if the file already exists to create a new declared file
        if(file.createNewFile()){
            JOptionPane.showMessageDialog(null, "New file created");
        }
        if(file.exists() && file.length()>0){
            JOptionPane.showMessageDialog(null, "Note: This file exist and has accounts inside, so create a new file if you wish to add new accounts.");
        }
        
        //Operating files 
        //Code attribution 
        //this method was taken from Youtube
        //https://youtu.be/SslMi6ptwH8
        //Edureka
        //https://www.youtube.com/c/edurekaIN/featured
        //25 July 2019
        
        //Application's main page
        while (selection != 3){
            try {
                selection = Integer.parseInt(JOptionPane.showInputDialog("Enter a number only to select the following options: "+
                                                                        "\n1. Registrate a new kanban account. "+
                                                                        "\n2. Login to the kanban app using your already created account details. "+
                                                                        "\n3. Exit the application."));
                switch(selection){
                    //Registration case
                    case 1:
                        if(file.exists() && file.length() > 0){
                            JOptionPane.showMessageDialog(null, "Please create a new file in order to registrate new users.");
                            break;
                        }else{
                            //Initializing the writer and reader objects to write and read accounts from/into file
                            InputStream input = new BufferedInputStream(Files.newInputStream(path));
                            BufferedReader reader = new BufferedReader(new InputStreamReader(input)); 
                            OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, CREATE));
                            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output))) {
                            //---------------------------------------------------------------------------------------
                                    valid = false;
                                    while(!valid){
                                    try{
                                        numOfAcc = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of accounts you want to create: "));
                                        valid = true;
                                    }catch(HeadlessException | NumberFormatException e){
                                        JOptionPane.showMessageDialog(null, "Please enter only a number.\n"+e);
                                    }
                                }
                                //initializing loop
                                i = 0;
                                x = 1;
                                reader.readLine();
                                while (i < numOfAcc) {
                                    valid = false;
                                    while(!valid){
                                        try{
                                            JOptionPane.showMessageDialog(null, "Please note: The username should include an underscore and should be no more than 5 characters in length.");
                                            username = JOptionPane.showInputDialog("Enter your specified username for the account no."+x+":");
                                            logins.setUsername(username, null);
                                            logins.getUsername();
                                            valid = logins.checkUserName();
                                            usernames[i] = username;                                            
                                        }catch(NullPointerException npe){
                                            valid = false;
                                        }
                                    }
                                    valid = false;
                                    while(!valid){
                                        try{
                                            JOptionPane.showMessageDialog(null, "Please note: The password should include a special character, a number and should be atleast 8 characters in length.");
                                            password = JOptionPane.showInputDialog("Enter your specified password for the account no."+x+":");
                                            logins.setPassword(password, null);
                                            logins.getPassword();
                                            valid = logins.checkPasswordComplexity();
                                            passwords[i] = password;                                            
                                        }catch(NullPointerException npe){
                                            valid = false;
                                        }
                                    }
                                    valid = false;
                                    while(!valid){
                                        JOptionPane.showMessageDialog(null, "Please note: The firstname should not include a number.");
                                        name = JOptionPane.showInputDialog("Enter your firstname for the account no."+x+":");
                                        valid = !name.matches(".*[0-9]{1,}.*");
                                        names[i] = name;
                                    }
                                    valid = false;
                                    while(!valid){
                                        JOptionPane.showMessageDialog(null, "Please note: The lastname should not include a number.");
                                        surname = JOptionPane.showInputDialog("Enter your lastname for the account no."+x+":");
                                        valid = !surname.matches(".*[0-9]{1,}.*");
                                        surnames[i] = surname;
                                    }
                                    //adding details to file
                                    record = surnames[i] + coma + names[i] + coma + usernames[i] + coma + passwords[i];
                                    users[i] = record;
                                    writer.write(users[i], 0, record.length());
                                    writer.newLine();
                                    //incremating the loop variable
                                    i = i + 1;
                                    x = x + 1;
                                }
                            }
                        }
                        break;
                        
                    //Login case 
                    case 2:
                            valid = false;
                            while(!valid){
                                try{
                                    searchUsername = JOptionPane.showInputDialog("Enter the username of the user's account: ");
                                    searchPassword = JOptionPane.showInputDialog("Enter the password of the user's account: ");
                                    valid = true;
                                }catch(NullPointerException npe){
                                    JOptionPane.showMessageDialog(null, "Please enter a valid name.\n"+npe);
                                }
                            }   
                            InputStream input = new BufferedInputStream(Files.newInputStream(path));
                            BufferedReader reader = new BufferedReader(new InputStreamReader(input)); 
                            valid = false;
                            line = reader.readLine();
                            while(line != null && !valid){
                                loginUsers = line.split(coma);
                                //Surname,name,username,passord
                                loginSurname = loginUsers[0];
                                loginName = loginUsers[1];
                                loginUsername = loginUsers[2];
                                loginPassword = loginUsers[3];
                                if(logins.loginUserUsername(loginUsername, searchUsername) && logins.loginUserPassword(loginPassword, searchPassword)){
                                    //user can then enter the app tasks page
                                    JOptionPane.showMessageDialog(null, logins.returnLoginStatusTrue(loginName, loginSurname));
                                    AppTaskFiles appTasks = new AppTaskFiles();
                                    appTasks.workingMethod();
                                    valid = true;
                                }
                                line = reader.readLine();
                            }
                            if(!valid){
                                JOptionPane.showMessageDialog(null, logins.returnLoginStatusFalse());
                            }
                            reader.close();
                    break;
                    
                    //Exit case 
                    case 3:
                        JOptionPane.showMessageDialog(null, "Thank you for choosing this program.");
                        System.exit(0);                        
                    break;

                    //if selection does not match any required input
                    default:
                        JOptionPane.showMessageDialog(null, "Please choose an option from only the declared options of 1, 2 and 3.");
                }
            }catch(HeadlessException | NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please choose an option from only the declared options of 1, 2 and 3.\n"+e);
            }
            
        //Exception handling  
        //Code attribution 
        //this method was taken from Youtube
        //https://youtu.be/W-N2ltgU-X4
        //Edureka
        //https://www.youtube.com/c/edurekaIN/featured
        //12 November 2018

        //Using switch cases  
        //Code attribution 
        //this method was taken from Youtube
        //https://youtu.be/O4KGYGQvHmw
        //Alex Lee
        //https://www.youtube.com/c/AlexLeeYT
        //11 April 2019
        
        }
    }
    //-------------------------------------------------------------------------------------------
}
