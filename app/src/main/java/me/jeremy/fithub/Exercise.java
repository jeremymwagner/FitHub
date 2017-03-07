package me.jeremy.fithub;

/**
 * Created by jeremy on 3/1/17.
 */

public class Exercise {
    private String name;
    private String description;
    private String muscleGroup;
    private int sets;
    private int reps;

    public Exercise(String n, String d, String m,int s, int r){
        this.name = n;
        this.description = d;
        this.muscleGroup = m;
        this.sets = s;
        this.reps = r;
    }

    public void incSets(){
        this.sets++;
    }

    public void decSets(){
        this.sets--;
    }

    public void incReps(){
        this.reps++;
    }

    public void decReps(){
        this.reps--;
    }

}
