import java.util.*;
public class Task1_FractionalKnapsack {
    static class Item { String id; double weight, value; Item(String i,double w,double v){id=i;weight=w;value=v;} }
    public static void main(String[] args) {
        Item[] items={new Item("A",10,60),new Item("B",20,100),new Item("C",30,120)};
        double capacity=50, total=0;
        Arrays.sort(items,(x,y)->Double.compare(y.value/y.weight,x.value/x.weight));
        System.out.println("Greedy order by value/weight:");
        for(Item x:items) {
            if(capacity<=0) break;
            double take=Math.min(capacity,x.weight), fraction=take/x.weight;
            total += x.value*fraction; capacity-=take;
            System.out.printf(Locale.US,"%s: take %.2f/%.2f (%.2f%%)%n",x.id,take,x.weight,fraction*100);
        }
        System.out.printf(Locale.US,"Maximum value = %.2f%n",total);
    }
}
