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

    public int getSets(){
        return this.sets;
    }

    public void setSets(int sets){
        this.sets = sets;
    }

    public int getReps(){
        return this.reps;
    }

    public void setReps(int reps){
        this.reps = reps;
    }

    public String getDescription(){ return this.description}

    public void setDescription(String d){ this.description = d;}

    public String getName(){return this.name;}

    public void setName(String n){this.name = n;}

    public String getMuscleGroup(){return this.muscleGroup;}

    public void setMuscleGroup(String m){ this.muscleGroup = m;}

}
