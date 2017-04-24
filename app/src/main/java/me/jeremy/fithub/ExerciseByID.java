package me.jeremy.fithub;

/**
 * Created by Axeldama on 4/23/17.
 */

public class ExerciseByID {

    private int eid;
    private String description;
    private String muscleGroup;
    private int sets;
    private int reps;

    public ExerciseByID(int eid, String description, String muscleGroup, int sets, int reps) {
        this.eid = eid;
        this.description = description;
        this.muscleGroup = muscleGroup;
        this.sets = sets;
        this.reps = reps;
    }

    public ExerciseByID(int eid) {
        this.eid = eid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
