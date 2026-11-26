import java.util.*;
public class Task4_WordBreak {
    static boolean canBreak(String s, Set<String> dict) {
        boolean[] dp=new boolean[s.length()+1]; dp[0]=true;
        for(int i=1;i<=s.length();i++) for(int j=0;j<i;j++)
            if(dp[j] && dict.contains(s.substring(j,i))) { dp[i]=true; break; }
        System.out.println("DP states: "+Arrays.toString(dp));
        return dp[s.length()];
    }
    public static void main(String[] args) {
        Set<String> d=new HashSet<String>(Arrays.asList("apple","pen","app","le"));
        System.out.println("applepen => "+canBreak("applepen",d));
        System.out.println("applepeer => "+canBreak("applepeer",d));
    }
}
