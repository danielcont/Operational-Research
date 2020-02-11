public class Data {
    int items;
    double capacity;
    int[] index;
    int[] values;
    int[] weight;
    
    public Data(int items, double capacity, int[] index, int[] values, int[] weight) {
        this.items = items;
        this.capacity = capacity;
        this.index = index;
        this.values = values;
        this.weight = weight;
    }

    public int getItems() { return items; }
    public void setItems(int items) { this.items = items; }

    public double getCapacity() { return capacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }

    public int[] getIndex() { return index; }
    public void setIndex(int[] index) { this.index = index; }

    public int[] getValues() { return values; }
    public void setvalues(int[] values) { this.values = values; }

    public int[] getWeight() { return weight; }
    public void setWeight(int[] weight) { this.weight = weight; }

}