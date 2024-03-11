public class City {
    private String name;
    private String region;
    private String[] streets;

    public String toString(){
        return "Name: " + this.name + " Region: " + this.region;
    }

    //Complexity - O(1)
    public String getName() {return this.name;}

    public String[] getStreets(){return this.streets;}
}

