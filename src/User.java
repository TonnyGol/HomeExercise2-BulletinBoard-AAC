public class User {
    private String userName;
    private String password;
    private String phoneNum;
    private boolean isMediator;

    public User(String username, String password, String phone, boolean isMediator) {
        this.userName = username;
        this.password = password;
        this.phoneNum = phone;
        this.isMediator = isMediator;
    }

    public String getUserName() {
        return this.userName;
    }
}
