/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logins;

/**
 *
 * @author kamvelihle
 */
public class TaskClass {
    
    private String taskDescription = "Task Desc";
    private String taskID = "AD:0:BYN";
    
    //Setters
    public void setTaskDes(String tDescription)
    {
        this.taskDescription = tDescription;
        checkTaskDescription();
    }
    public void setTaskID(String taskId, String taskN, int x, String devLastName)
    {
        this.taskID = taskId;
        createTaskID(taskN, x, devLastName);
    }
    
    //getters
    public String getTaskDes()
    {
        return this.taskDescription;
    }
    public String getTaskID()
    {
        return this.taskID;
    }
    
    //work methods
    public boolean checkTaskDescription()
    {
        return taskDescription.length()<50;
    }
    public String createTaskID(String taskN, int x, String devLastName)
    {
        return taskN.substring(0, 2) + ":" +x + ":" + devLastName.substring(2);
    }
        
    public String printTaskDetails(int i, String tStat, String devDetails, String tNme, String tDesc, String tId, int tDur)
    {                
        return "The following is the report of task "+i+":"
               +"\n"+"Task Status: "+tStat
               +"\n"+"Developer details: "+devDetails
               +"\n"+"Task number: "+i
               +"\n"+"Task name: "+tNme
               +"\n"+"Task description: "+tDesc
               +"\n"+"Task ID: "+tId.toUpperCase()
               +"\n"+"Task duration: "+tDur;         
    } 
    public int returnTotalHours(int totDur, int taskDur)
    {
        return totDur + taskDur;
    }

    //Manipulating strings
    //Code  attribution 
    //this method was taken from Youtube
    //https://youtu.be/vbZ85D8Hvh0
    //Alex Lee
    //https://www.youtube.com/c/AlexLeeYT
    //22 November 2018
    
}
