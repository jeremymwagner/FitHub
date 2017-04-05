package me.jeremy.fithub;

import java.util.List;

public class People{
    private int userID;
    private String name;
    private List<Integer> followerList;
    private List<Integer> followingList;

    public People(String name, int userID, List<Integer> followerList, List<Integer> followingList) {
        this.name = name;
        this.userID = userID;
        this.followerList = followerList;
        this.followingList = followingList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Integer> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(List<Integer> followerList) {
        this.followerList = followerList;
    }

    public List<Integer> getFollowingList() {
        return followingList;
    }

    public void setFollowingList(List<Integer> followingList) {
        this.followingList = followingList;
    }
}