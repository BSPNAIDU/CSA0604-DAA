import java.util.*;
public class Task1_WordWrap {
    public static void main(String[] args) {
        String[] words={"Design","algorithms","carefully","for","efficient","document","layout"};
        int width=args.length>0?Integer.parseInt(args[0]):16, n=words.length, INF=1000000000;
        int[] dp=new int[n+1],next=new int[n];Arrays.fill(dp,INF);dp[n]=0;
        for(int i=n-1;i>=0;i--){int len=0;for(int j=i;j<n;j++){len+=words[j].length()+(j==i?0:1);if(len>width)break;
            int spaces=width-len, penalty=(j==n-1)?0:spaces*spaces*spaces;
            if(penalty+dp[j+1]<dp[i]){dp[i]=penalty+dp[j+1];next[i]=j+1;}
        }}
        if(dp[0]>=INF){System.out.println("A word is wider than the line width.");return;}
        System.out.println("Width="+width+" minimum penalty="+dp[0]);
        for(int i=0;i<n;){int j=next[i],len=0;StringBuilder line=new StringBuilder();for(int k=i;k<j;k++){if(k>i){line.append(' ');len++;}line.append(words[k]);len+=words[k].length();}
            int spaces=width-len,penalty=(j==n)?0:spaces*spaces*spaces;
            System.out.println(line+" | unused="+spaces+" | penalty="+penalty);i=j;}
        System.out.println("DP costs="+Arrays.toString(dp));
    }
}
