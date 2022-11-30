package Pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {


    /*
    Pojo Class
    1) Create private variables for all keys
    2)Create constuctors with all parameters and without any parameters
    3)Create public getter and setter methods
    4)Create toString() method
    */
    /*
    {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
                }
    */

    private  Integer userId;
    private  String title;
    private Boolean completed;

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public JsonPlaceHolderPojo() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
