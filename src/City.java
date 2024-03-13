public class City {
    private String name;
    private String region;
    private String[] streets;

    public City(String name, String region){
        this.name = name;
        this.region = region;
        this.streets = new String[0];
    }
    public City(String name, String region, String[] streets){
        this.name = name;
        this.region = region;
        this.streets = streets;
    }
    //Complexity - O(1)
    public String toString(){
        return "Name: " + this.name + " Region: " + this.region;
    }

    //Complexity - O(1)
    public String getName() {return this.name;}
    //Complexity - O(1)
    public String[] getStreets(){return this.streets;}
}

