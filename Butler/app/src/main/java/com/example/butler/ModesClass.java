package com.example.butler;

import java.util.ArrayList;

public class ModesClass {
    private String name;
    private String description;
    private ArrayList<TasksClass> tk;
    private String state;
    public ModesClass(String name, String description, ArrayList<TasksClass>tk,String state){
        this.name=name;
        this.description=description;
        this.tk=tk;
        this.state=state;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public ArrayList<TasksClass> getTasks(){
        return tk;
    }
    public String getState(){
        return state;
    }
    public void changeState(){
        if (getState().equals("on")) {
            state = "off";
           changeTasks();
        }
        else{
            state="on";
          changeTasks();
        }
    }
    private void changeTasks(){
        for (int i=0;i<tk.size();i++){
            tk.get(i).changeState();
        }
    }

}
