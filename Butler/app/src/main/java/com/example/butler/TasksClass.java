package com.example.butler;

public class TasksClass {
    private String name;
    private String device;
    private String description;
    private String state;
    public TasksClass(String name,String device,String description,String state){
       this.name=name;
       this.description=description;
       this.device=device;
       this.state=state;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public String getDevice(){
        return device;
    }
    public String getState(){
        return state;
    }
    public void changeState(){
        if (getState().equals("on")) {
            state = "off";
        }
            else{
                state="on";
            }
        }
}
