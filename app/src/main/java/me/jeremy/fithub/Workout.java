package me.jeremy.fithub;

public class Workout{
    private String name;
    private String description;
    private String author;
    private List<Exercise> exerciseList;

    public Workout(String name, String desctiption, String author,List<Exercise> exerciseList){
        this.name = name;
        this.description = desctiption;
        this.author = author;
        this.exerciseList = exerciseList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}