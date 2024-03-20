public class User {
    private String username;
    private String password;
    private String phoneNum;
    private boolean isMediator;

    //Complexity - O(1)
    public User(String username, String password, String phone, boolean isMediator) {
        this.username = username;
        this.password = password;
        this.phoneNum = phone;
        this.isMediator = isMediator;
    }
    //Complexity - O(1)
    public String getUserName() {return this.username;}
    //Complexity - O(1)
    public String getPassword() {return this.password;}
    //Complexity - O(1)
    public String getPhoneNumber(){return this.phoneNum;}
    //Complexity - O(1)
    public boolean getIsMediator(){return this.isMediator;}
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
