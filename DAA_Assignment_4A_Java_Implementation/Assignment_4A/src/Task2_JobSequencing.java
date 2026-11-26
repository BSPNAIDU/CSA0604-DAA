import java.util.*;
public class Task2_JobSequencing {
    static class Job { String id; int deadline, profit; Job(String i,int d,int p){id=i;deadline=d;profit=p;} }
    public static void main(String[] args) {
        Job[] jobs={new Job("A",2,80),new Job("B",1,35),new Job("C",2,60),new Job("D",3,40),new Job("E",1,25)};
        Arrays.sort(jobs,(a,b)->Integer.compare(b.profit,a.profit));
        int max=0; for(Job j:jobs) max=Math.max(max,j.deadline);
        String[] slots=new String[max+1]; int total=0;
        for(Job j:jobs) {
            for(int s=Math.min(max,j.deadline);s>=1;s--) if(slots[s]==null) {
                slots[s]=j.id; total+=j.profit;
                System.out.println("Schedule "+j.id+" in slot "+s); break;
            }
        }
        System.out.println("Final schedule:");
        for(int s=1;s<=max;s++) System.out.println("Slot "+s+": "+(slots[s]==null?"Empty":slots[s]));
        System.out.println("Total profit = "+total);
    }
}
