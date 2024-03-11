public class Property {
    private City city;
    private String street;
    private int rooms;
    private int price;
    private String type;
    private boolean isForRent;
    private int number;
    private int floor;
    private User publisher;

    //Complexist - O(1)
    public User getPublisher() {return this.publisher;}
}
