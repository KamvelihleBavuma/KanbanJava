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
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author kamvelihle
 */
public class AppTaskFiles {
    
    public void workingMethod() throws IOException, ClassNotFoundException {
    
    //Declarations
    //--------------------------------------------------------------------------
    int chosenOption = 0;
    int numOfTasks = 0;
    //Arrays
    String[] allTasks = new String[4];
    String[] tNames = new String[4];
    String[] tDevelopers = new String[4];
    String[] tIDs = new String[4];
    int[] tDurations = new int[4];
    String[] tStatuses = new String[4];
    String[] tDescriptions = new String[4];
    int[] tNumbers = new int[10];
    //--------------------------------------------------------------------------
        while (chosenOption != 3){
            try{
                chosenOption = Integer.parseInt(JOptionPane.showInputDialog("Enter the following number to choose an option: \n"+"1 = Option 1 to add tasks\n"+"2 = Option 2 to show report\n"+"3 = Option 3 to exit"));
                switch(chosenOption){
                    //Adding tasks case
                    case 1:
                        addingTasks(numOfTasks, allTasks, tIDs, tNames, tStatuses, tDescriptions, tDevelopers, tDurations, tNumbers);
                    break;

                    //Show report case
                    case 2:
                        showingReport(tIDs, tNames, tStatuses, tDescriptions, tDevelopers, tDurations, tNumbers);
                    break;

                    //Exit app case
                    case 3:                        
                    break;

                    //If the user selects an option that is not 1, 2, 3
                    default:
                        JOptionPane.showMessageDialog(null, "Please choose from the declared options of 1,2 and 3.");
                }                   
            }catch(HeadlessException | NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please choose from the declared options of 1,2 and 3.");
            }
        }
    }
    
    public static void addingTasks(int num, String[] allTasks, String[] tIDs, String[] tNames, String[] tStatuses, String[] tDescriptions, String[] tDevelopers, int[] tDurations, int[] tNumbers) throws IOException, ClassNotFoundException{

    //Declarations
    //--------------------------------------------------------------------------
    boolean valid = false;
    int i = 0; //loop control variable
    String taskName;
    String taskDescription;
    String developerFirstName;
    String developerLastName;
    int taskDuration = 0;
    int taskStatus = 0;
    String setStatus = null;
    int quit = 999;
    int response = 0;
    int totalDuration = 0;
    int x = 0; //subscipt
    String developerDetails;
    String taskID = null;
    String coma = ",";
    String taskRecs;
    boolean fileFull = false;
    //Calling class
    TaskClass tasks = new TaskClass();
    File file = new File("NewKanbanTasks.txt"); //Add the new file name and path here
    Path path = Paths.get("NewKanbanTasks.txt"); //Add the same new file path here again
    //Checking if the file already exists to create a new declared file
    if(file.createNewFile()){
        JOptionPane.showMessageDialog(null, "New file created");
    }
    if(file.length() == 0){
        JOptionPane.showMessageDialog(null, "Note: This file exist, so you can add new tasks inside.");
    }       
    //Initializing the writer object to write task records into file
    OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, CREATE));
    //--------------------------------------------------------------------------
    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output))) {
            while (valid == false){
                try{
                    if(file.exists() && file.length()>0){                    
                        JOptionPane.showMessageDialog(null, "Please create a new file in order to add new tasks.");
                        fileFull = true;
                        break;
                    }
                    num = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of task features you wish to do: "));
                    if(num > 4){
                        JOptionPane.showMessageDialog(null, "Please enter a number below 4, as you cannot add more then 4 tasks.");
                    }else{
                        valid = true;
                    }
                }
                catch(HeadlessException | NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please enter only a number.");
                }
            }   valid = false;
            while (valid == false){
                try{
                    if(fileFull){
                        break;
                    }
                    response = Integer.parseInt(JOptionPane.showInputDialog("Enter 1 to continue or 999 to exit or re-enter the tasks: "));
                    if(response == 999){
                        num = 0;
                        x = 0;
                        i = 0;
                    }
                    if (response != 1 && response != 999 ){
                        JOptionPane.showMessageDialog(null, "Please choose from the declared options of 1 and 999.");
                    }else{
                        valid = true;
                    }
                }
                catch(HeadlessException | NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please choose from the declared options of 1 and 999.");
                }
            }
            while (response != quit && response == 1) {
                if(fileFull){
                    break;
                }
                taskName = JOptionPane.showInputDialog("Enter name of the task: ");
                while(taskName.isEmpty() || taskName.length() < 3){
                    JOptionPane.showMessageDialog(null, "Please declare a task name that is not empty and that contains more than 2 characters for the generating of the task ID before proceeding.");
                    taskName = JOptionPane.showInputDialog("Enter name of the task: ");
                }
                tNames[x] = taskName;
                taskDescription = JOptionPane.showInputDialog("Enter the description of the task:");
                while(taskDescription.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please declare a task description that is not empty in characters before proceeding.");
                    taskDescription = JOptionPane.showInputDialog("Enter the description of the task:");
                }
                while (taskDescription.length()>50){
                    tasks.setTaskDes(taskDescription);
                    tasks.getTaskDes();
                    if (tasks.checkTaskDescription())
                    {
                        JOptionPane.showMessageDialog(null, "Task successfully captured.");
                    }
                    else
                    {
                        taskDescription = JOptionPane.showInputDialog("Please enter a task description of less than 50 characters:");
                    }
                }
                JOptionPane.showMessageDialog(null, "Task successfully captured.");
                tDescriptions[x] = taskDescription;
                developerFirstName = JOptionPane.showInputDialog("Enter the first name of the developer assigned to the task:");
                while(developerFirstName.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please declare the developer's first name before proceeding.");
                    developerFirstName = JOptionPane.showInputDialog("Enter the first name of the developer assigned to the task:");
                }
                developerLastName = JOptionPane.showInputDialog("Enter the last name of the developer assigned to the task:");
                while(developerLastName.isEmpty() || developerLastName.length() < 4){
                    JOptionPane.showMessageDialog(null, "Please declare the developer's last name that is not empty and that contains more than 3 characters for the generating of the task ID before proceeding.");
                    developerLastName = JOptionPane.showInputDialog("Enter the last name of the developer assigned to the task:");
                }
                developerDetails = developerFirstName + " " +developerLastName;
                tDevelopers[x] = developerDetails;
                valid = false;
                while (valid == false){
                    try
                    {
                        taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter the duration of the task:"));
                        valid = true;
                    }
                    catch (HeadlessException | NumberFormatException e)
                    {
                        JOptionPane.showMessageDialog(null, "Please enter a task duration containing only integers:");
                    }
                }
                tDurations[x] = taskDuration;
                //returning total hours
                totalDuration = tasks.returnTotalHours(totalDuration, taskDuration);
                valid = false;
                while(valid == false){
                    try{
                        taskStatus = Integer.parseInt(JOptionPane.showInputDialog("Choose one of the following as a task status by entering a number as the chosen option: \n"+"1. To do\n"+"2. Doing\n"+"3. Done"));
                        if(taskStatus != 1 && taskStatus != 2 && taskStatus != 3){
                            JOptionPane.showMessageDialog(null, "Please choose from the declared option of 1, 2 and 3.");
                        }else{
                            valid = true;
                        }                               
                    }
                    catch(HeadlessException | NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Please choose from the declared option of 1, 2 and 3.");
                    }
                }                        
                switch(taskStatus){
                    case 1:
                        setStatus = "To do";
                        break;
                        
                    case 2:
                        setStatus = "Doing";
                        break;
                        
                    case 3:
                        setStatus = "Done";
                        break;
                }
                tStatuses[x] = setStatus;
                //Task ID generated
                tasks.setTaskID(taskID, taskName, x, developerLastName);
                tasks.getTaskID();
                taskID = tasks.createTaskID(taskName, x, developerLastName);
                tIDs[x] = taskID;
                //incrementing the number of tasks so tasks can start at 1 and on wards
                i = i + 1;
                tNumbers[x] = i;
                //Save into kanban file
                taskRecs = tStatuses[x] + coma + tDevelopers[x] + coma + tNumbers[x] + coma + tNames[x] + coma + tDescriptions[x] + coma + tIDs[x].toUpperCase() + coma + tDurations[x];
                allTasks[x] = taskRecs;
                writer.write(allTasks[x], 0, taskRecs.length());
                writer.newLine();
                //report of tasks
                JOptionPane.showMessageDialog(null, tasks.printTaskDetails(i, setStatus, developerDetails, taskName, taskDescription, taskID, taskDuration));
                //incrementing the array
                x = x + 1;
                if (i == num && num != 0)
                {
                    JOptionPane.showMessageDialog(null, "You have completed the number of task features to add.");
                    JOptionPane.showMessageDialog(null, "The total number of hours across all tasks is : " +totalDuration+" hours.");
                    totalDuration = 0;
                    JOptionPane.showMessageDialog(null, "You can now procceed to view the task reports.");
                    response = 999;
                }
                else
                {
                    //Prompting to continue adding tasks or quit
                    valid = false;
                    while(valid == false){
                        try{
                            response = Integer.parseInt(JOptionPane.showInputDialog("Now add tasks for another task feature by entering 1 or 999 to exit."));
                            if(response == 999){
                                //Number of tasks, arrays and loop control variable are set to zero if user exists in the midst of adding tasks
                                num = 0;
                                x = 0;
                                i = 0;
                            }
                            if(response != 1 && response != 999){
                                JOptionPane.showMessageDialog(null, "Please choose from the declared options of 1 and 999.");
                            }else{
                                valid = true;
                            }
                        }
                        catch(HeadlessException | NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "Please choose from the declared options of 1 and 999.");
                        }
                    }
                }
            }
        }
    }
    
    public static void showingReport(String[] tIDs, String[] tNames, String[] tStatuses, String[] tDescriptions, String[] tDevelopers, int[] tDurations, int[] tNumbers) throws IOException, ClassNotFoundException{

    //Show report declarations
    //--------------------------------------------------------------------------
    int cont = 99;
    int numTasks;
    String tNameSearch;
    String tIDSearch;
    String tDescSearch;
    String tDeveloperSearch;
    String tStatusSearch;
    String tDurSearch;
    String tNumSearch;
    String nameSearch;
    boolean isStatusDone;
    int selection = 0;
    String[] userTasks;
    String line;
    String coma = ",";
    int i; //loop control variable
    int sub;
    int count;
    int longestDuration;
    String tdeveloper;
    String tStatus;
    String tName;
    String tID;
    int tDuration;
    int removeTask = 0;
    String delTaskName;
    boolean taskDel = false;
    //variables used to parse strings from file
    int tNumber; 
    int tDurs;
    //Calling classes
    TaskClass tasks = new TaskClass();
    Path path = Paths.get("NewKanbanTasks.txt"); //Add the new file path here
    File file = new File("NewKanbanTasks.txt"); //Add the same new file path here again
    //--------------------------------------------------------------------------
        //Checking if file has tasks inside
        if(file.length()==0 || !file.exists()){
            JOptionPane.showMessageDialog(null, "File does not exists or has no tasks stored inside.");
        }else{
        //Reading file details into arrays
        InputStream input = new BufferedInputStream(Files.newInputStream(path));
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                i = 0;
                line = reader.readLine();
                while(line != null){
                    userTasks = line.split(coma);
                    tStatusSearch = userTasks[0];
                    tDeveloperSearch = userTasks[1];
                    tNumSearch = userTasks[2];
                    tNumber = Integer.parseInt(tNumSearch); //parsing string
                    tNameSearch = userTasks[3];
                    tDescSearch = userTasks[4];
                    tIDSearch = userTasks[5];
                    tDurSearch = userTasks[6];
                    tDurs = Integer.parseInt(tDurSearch); //parsing string

                    tStatuses[i] = tStatusSearch;
                    tDevelopers[i] = tDeveloperSearch;
                    tNumbers[i] = tNumber;
                    tNames[i] = tNameSearch;
                    tDescriptions[i] = tDescSearch;
                    tIDs[i] = tIDSearch;
                    tDurations[i] = tDurs;
                    i = i + 1;
                    line = reader.readLine();
                }
                numTasks = i;
                reader.close();
            }
            //Displaying tasks with the status of done (Detailed Loop)
            while(selection != cont){
                try{
                    selection = Integer.parseInt(JOptionPane.showInputDialog("Enter 1 to display the Developer, Task Names and Task Duration for all tasks with the status of done or enter 99 to continue: "));
                    if (selection == 1){
                        nameSearch = "Done";
                        isStatusDone = false;
                        for (sub = 0; sub < numTasks; ++sub)
                        {
                            if (nameSearch.equals(tStatuses[sub]))
                            {
                                isStatusDone = true;
                                tStatus = tStatuses[sub];
                                tdeveloper = tDevelopers[sub];
                                tName = tNames[sub];
                                tDuration = tDurations[sub];
                                if (isStatusDone)
                                {
                                    JOptionPane.showMessageDialog(null, "Task Status: "+tStatus
                                                                        +"\nDeveloper Details: "+tdeveloper
                                                                        +"\nTask Name: "+tName+
                                                                        "\nTask Duration: "+tDuration);
                                }
                            }
                        }
                        if(!isStatusDone){
                            JOptionPane.showMessageDialog(null, "Tasks with the status of done where not found.");
                        }
                    }
                    if(selection != 1 && selection != 99){
                        JOptionPane.showMessageDialog(null, "Please enter an option from the declared options of 1 and 99.");
                    }                            
                }
                catch(HeadlessException | NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please enter an option from the declared options of 1 and 99.\n"+e);
                }                            
            }
            selection = 0;
            //Displaying the task with the longest duration
            while (selection != cont){
                try{
                    selection = Integer.parseInt(JOptionPane.showInputDialog("Enter 1 to display the Developer and Duration of the class with the longest duration or enter 99 to continue: "));
                    if(selection == 1){
                        longestDuration = tDurations[0];
                        tdeveloper = tDevelopers[0];
                        for (sub = 0; sub < tDurations.length; ++sub )
                        {
                            if (tDurations[sub] > longestDuration)
                            {
                                longestDuration = tDurations[sub];
                                tdeveloper = tDevelopers[sub];
                            }
                        }
                    JOptionPane.showMessageDialog(null, "Developer Details: "+tdeveloper
                                                    +"\nTask Duration: "+longestDuration);                                
                    }
                    if(selection != 1 && selection != 99){
                        JOptionPane.showMessageDialog(null, "Please enter an option from the declared options of 1 and 99.");
                    }                            
                }
                catch(HeadlessException | NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please enter an option from the declared options of 1 and 99.\n"+e);
                }
            }
            selection = 0;
            //Search for ONE task using its task name
            isStatusDone = false;
            while (selection != cont){
                try{
                    selection = Integer.parseInt(JOptionPane.showInputDialog("Enter 1 to search for a task with a Task Name and display the Task Name, Developer and Task Status or enter 99 to continue: "));
                    if (selection == 1){
                        tNameSearch = JOptionPane.showInputDialog("Please enter the task name you wish to search: ");
                        nameSearch = tNameSearch;
                        for (sub = 0; sub < numTasks && !isStatusDone; ++sub)
                        {
                            if (nameSearch.equals(tNames[sub]))
                            {
                                isStatusDone = true;
                                tStatus = tStatuses[sub];
                                tdeveloper = tDevelopers[sub];
                                tName = tNames[sub];
                                if (isStatusDone)
                                {
                                    JOptionPane.showMessageDialog(null, "Task Name: "+tName
                                                                        +"\nDeveloper Details: "+tdeveloper
                                                                        +"\nTask Status: "+tStatus);
                                }
                            }
                        }
                        if(!isStatusDone){
                            JOptionPane.showMessageDialog(null, "The task name was not found.");
                        }                                
                    }
                    isStatusDone = false;
                    if(selection != 1 && selection != 99){
                        JOptionPane.showMessageDialog(null, "Please enter an option from the declared options of 1 and 99.");
                    }                                                        
                }
                catch(HeadlessException | NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please enter an option from the declared options of 1 and 99.\n"+e);                            
                }
            }
            selection = 0;
            //Search for ALL tasks using the developers details assigned to those tasks
            isStatusDone = false;
            while (selection != cont){
                try{
                    selection = Integer.parseInt(JOptionPane.showInputDialog("Enter 1 to search for all tasks assigned to a developer and display the Task Name and Task Status or enter 99 to continue: "));
                    if (selection == 1){
                        tDeveloperSearch = JOptionPane.showInputDialog("Please enter the developer's name you wish to search: ");
                        nameSearch = tDeveloperSearch;
                        for (sub = 0; sub < numTasks; ++sub)
                        {
                            if (nameSearch.equals(tDevelopers[sub]))
                            {
                                isStatusDone = true;
                                tStatus = tStatuses[sub];
                                tdeveloper = tDevelopers[sub];
                                tName = tNames[sub];
                                tDuration = tDurations[sub];
                                tID = tIDs[sub];
                                if (isStatusDone)
                                {
                                    JOptionPane.showMessageDialog(null, "Developer Details: "+tdeveloper
                                                                        +"\nTask Name: "+tName
                                                                        +"\nTask Status: "+tStatus
                                                                        + "\nTask Duration: "+tDuration
                                                                        +"\nTask ID: "+tID.toUpperCase());
                                }
                            }
                        }
                        if(!isStatusDone){
                            JOptionPane.showMessageDialog(null, "The developer's name was not found.");
                        }                            
                    }
                    isStatusDone = false;
                    if(selection != 1 && selection != 99){
                    JOptionPane.showMessageDialog(null, "Please enter an option from the declared options of 1 and 99.");
                    }
                }
                catch(HeadlessException | NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please enter an option from the declared options of 1 and 99.\n"+e);                            
                }
            }
            selection = 0;
            //Deleting a task using the task name
            while (selection != cont){
                selection = Integer.parseInt(JOptionPane.showInputDialog("Enter 1 to delete a task using a task name or enter 99 to continue: "));
                if (selection == 1){
                    try{
                        JOptionPane.showMessageDialog(null, "Please enter the index (number) starting from 0, of the task name you want to delete. For example, if there are 4 tasks you may enter only numbers: 0, 1, 2 and 3.");
                        JOptionPane.showMessageDialog(null, "The original task names are: ");
                        JOptionPane.showMessageDialog(null, Arrays.toString(tNames));
                        removeTask = Integer.parseInt(JOptionPane.showInputDialog("Please enter the index number of the task name you want to delete. "));
                        delTaskName = tNames[removeTask];
                        tNames[removeTask] = tNames[tNames.length - 1];
                        tDevelopers[removeTask] = tDevelopers[tDevelopers.length - 1];
                        tDurations[removeTask] = tDurations[tDurations.length - 1];
                        tStatuses[removeTask] = tStatuses[tStatuses.length - 1];
                        tDescriptions[removeTask] = tDescriptions[tDescriptions.length - 1];  
                        if(numTasks != 4){
                            tNumbers[removeTask] = tNumbers[tNumbers.length - 1];
                            taskDel = true;
                        }
                        String[] tNamesNew = new String[tNames.length - 1];
                        for (sub = 0; sub < tNamesNew.length; sub++)
                        {
                            tNamesNew[sub] = tNames[sub];
                        }
                        JOptionPane.showMessageDialog(null, "Entry "+delTaskName+" successfully deleted");
                        JOptionPane.showMessageDialog(null, "The tasks names after the specified task has been deleted are: ");
                        JOptionPane.showMessageDialog(null, Arrays.toString(tNamesNew));
                        if(numTasks == 4){
                            numTasks = numTasks - 1;
                            tIDs[removeTask] = tIDs[tIDs.length - 1];
                        }
                    }
                    catch(HeadlessException | NumberFormatException | ArrayIndexOutOfBoundsException e){
                        JOptionPane.showMessageDialog(null, "The task name was not deleted because of wrong input.\n"+e);
                    }                            
                }
                if (selection != 1 && selection != 99){
                    JOptionPane.showMessageDialog(null, "Please choose an option from the declared options of 1 and 99.");
                }
            }
            selection = 0;
            //Displaying all tasks entered 
            while (selection != cont){
                try{
                    selection = Integer.parseInt(JOptionPane.showInputDialog("Enter 1 to display a report that lists the full details of all captured tasks or enter 99 to exit: "));
                    if (selection == 1){
                        count = 0;
                        sub = 0;
                        while (count < numTasks){
                            if(taskDel && sub == removeTask){
                                tIDs[removeTask] = "none";
                            }    
                            JOptionPane.showMessageDialog(null, tasks.printTaskDetails(tNumbers[sub], tStatuses[sub], tDevelopers[sub], tNames[sub], tDescriptions[sub], tIDs[sub], tDurations[sub]));
                            count = count + 1;
                            sub = sub + 1;
                        }
                    }
                    if (selection != 1 && selection != 99){
                        JOptionPane.showMessageDialog(null, "Please choose an option from the declared options of 1 and 99.");                                
                    }
                }
                catch (NullPointerException e){
                JOptionPane.showMessageDialog(null, "Tasks were recently deleted.\n"+e);
                }
                catch (HeadlessException | NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please choose an option from the declared options of 1 and 99.\n"+e);                            
                }
            }                
        }
    }
    
    //Using static methods
    //Code  attribution 
    //this method was taken from Youtube
    //https://youtu.be/bvNU24rvhgA
    //Mike Dane
    //https://www.youtube.com/c/GiraffeAcademy
    //22 October 2017
    
    
    //Passing arrays
    //Code  attribution 
    //this method was taken from Youtube
    //https://youtu.be/_SIptqsH3dI
    //LearningLad
    //https://www.youtube.com/c/LearningLad
    //8 January 2015
    
    //Searching arrays
    //Code attribution 
    //this method was taken from Youtube
    //https://youtu.be/zHsXmZCtXtI
    //Caleb Curry
    //https://www.youtube.com/c/CalebTheVideoMaker2
    //2 August 2019

    //Deleting arrays
    //Code attribution 
    //this method was taken from Youtube
    //https://youtu.be/JD8wDJSolSM
    //Learn coding
    //https://www.youtube.com/c/LearnCodingOfficial
    //26 December 2020
    
    //Operating files 
    //Code attribution 
    //this method was taken from Youtube
    //https://youtu.be/SslMi6ptwH8
    //Edureka
    //https://www.youtube.com/c/edurekaIN/featured
    //25 July 2019

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
