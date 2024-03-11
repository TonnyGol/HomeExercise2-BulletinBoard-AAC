public class User {
    private String username;
    private String password;
    private String phoneNum;
    private boolean isMediator;

    public User(String username, String password, String phone, boolean isMediator) {
        this.username = username;
        this.password = password;
        this.phoneNum = phone;
        this.isMediator = isMediator;
    }

    public String getUserName() {return this.username;}

    public String getPassword() {return this.password;}

    public boolean getIsMediator(){return this.isMediator;}
}
