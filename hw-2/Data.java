import java.util.Comparator;

public class Data {
    int index;
    int values;
    int weight;
    double ratio;
    
    public Data(int index, int values, int weight, double ratio) {
        this.index = index;
        this.values = values;
        this.weight = weight;
        this.ratio = ratio;
    }

    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }

    public int getValues() { return values; }
    public void setValues(int values) { this.values = values; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public double getRatio() { return ratio; }
    public void setRatio(double ratio) { this.ratio = ratio; }

    public static Comparator<Data> itemValuesComparator = new Comparator<Data>() {
        
        public int compare(Data item1, Data item2) {
            int value1 = item1.getValues();
            int value2 = item2.getValues();

            return Integer.compare(value2, value1);

        }
    };

    public static Comparator<Data> itemWeightComparator = new Comparator<Data>() {
        
        public int compare(Data item1, Data item2) {
            int weight1 = item1.getWeight();
            int weight2 = item2.getWeight();

            return weight1 - weight2;
            
        }
    };

    public static Comparator<Data> itemRatioComparator = new Comparator<Data>() {
        
        public int compare(Data item1, Data item2) {
            double ratio1 = item1.getRatio();
            double ratio2 = item2.getRatio();

            return Double.compare(ratio2, ratio1);
            
        }
    };

}