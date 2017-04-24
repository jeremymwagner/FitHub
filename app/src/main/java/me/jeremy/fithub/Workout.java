package me.jeremy.fithub;

import java.util.ArrayList;
import java.util.List;

public class Workout{
    private String name;
    private int wid;
    private String author;
    //CHANGE TO A DIFFERENT CLASS WITH EXERCISE ID AND SETS AND REPS
    private List<ExerciseByID> exerciseList;

    public Workout(String name, int wid, String author,List<ExerciseByID> exerciseList){
        this.name = name;
        this.wid = wid;
        this.author = author;
        this.exerciseList = exerciseList;
    }

    public Workout(){
        this.name = "";
        this.wid = -1;
        this.author = "";
        this.exerciseList = new ArrayList<ExerciseByID>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWID() {
        return this.wid;
    }

    public void setWID(int wid) {
        this.wid = wid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<ExerciseByID> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<ExerciseByID> exerciseList) {
        this.exerciseList = exerciseList;
    }
}