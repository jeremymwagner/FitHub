package me.jeremy.fithub;

public class People{
    private int userID;
    private String name;
    private List<int> followerList;
    private List<int> followingList;

    public People(String name, int userID, List<int> followerList, List<int> followingList) {
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

    public List<int> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(List<int> followerList) {
        this.followerList = followerList;
    }

    public List<int> getFollowingList() {
        return followingList;
    }

    public void setFollowingList(List<int> followingList) {
        this.followingList = followingList;
    }
}