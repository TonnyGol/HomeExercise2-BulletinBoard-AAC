public class User {
    private String username;
    private String password;
    private String phoneNum;
    private boolean isBroker;

    private static final int POST_LIMIT_BROKER = 5;
    private static final int POST_LIMIT_REGULAR_USER = 2;

    //Complexity - O(1)
    public User(String username, String password, String phone, boolean isBroker) {
        this.username = username;
        this.password = password;
        this.phoneNum = phone;
        this.isBroker = isBroker;
    }

    //Complexity - 0(1)
    public String toString(){
        String userInfo = this.getUserName()+" "+this.getPhoneNumber();
        if (this.getIsBroker()){
            userInfo += " (Real estate broker).";
        }else {
            userInfo += " (Private user).";
        }
        return userInfo;
    }

    //Complexity - O(1)
    public String getUserName() {return this.username;}
    //Complexity - O(1)
    public String getPassword() {return this.password;}
    //Complexity - O(1)
    public String getPhoneNumber(){return this.phoneNum;}
    //Complexity - O(1)
    public boolean getIsBroker(){return this.isBroker;}
    //Complexity - O(1)
    public int getPostLimit(){return this.getIsBroker() ? POST_LIMIT_BROKER : POST_LIMIT_REGULAR_USER;}
    //Complexity - O(1)
    public boolean checkCredentials(String username, String password){
        boolean checkValid = false;
        if(this.getUserName().equals(username)){
            if (this.getPassword().equals(password)){
                checkValid = true;
            }
        }
        return checkValid;
    }
}
