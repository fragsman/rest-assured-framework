package POJO.test;

public class Login {

    private User user;

    public Login(){}

    public Login(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
