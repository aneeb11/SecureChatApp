package model;

public class User {
    private String Username;
    private String Password;
    private String Status;
    public User (String Username,String Password,String Status)
    {
        this.Username=Username;
        this.Password=Password;
        this.Status=Status;
    }
    public void setStatus(String Status) {
        this.Status = Status;
    }
    public String getUsername(){
        return this.Username;
    }

    public String getPassword() {
        return this.Password;
    }
    public String getStatus(){
        return this.Status;
    }
    public Boolean isBanned(){
        return "Banned".equalsIgnoreCase(this.Status);
    }
}

