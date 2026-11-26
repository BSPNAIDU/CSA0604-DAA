import java.util.*;
public class TaskB3_SetCover {
    static int exactCalls;static List<Set<Integer>> best;
    static void exact(List<Set<Integer>> sets,int index,Set<Integer> covered,List<Set<Integer>> chosen,Set<Integer> universe){
        exactCalls++;if(covered.containsAll(universe)){if(best==null||chosen.size()<best.size())best=new ArrayList<Set<Integer>>(chosen);return;}
        if(index==sets.size()||(best!=null&&chosen.size()>=best.size()))return;
        Set<Integer> next=new HashSet<Integer>(covered);next.addAll(sets.get(index));chosen.add(sets.get(index));exact(sets,index+1,next,chosen,universe);chosen.remove(chosen.size()-1);
        exact(sets,index+1,covered,chosen,universe);
    }
    static List<Set<Integer>> greedy(List<Set<Integer>> sets,Set<Integer> universe){Set<Integer> uncovered=new HashSet<Integer>(universe);List<Set<Integer>> pick=new ArrayList<Set<Integer>>();boolean[] used=new boolean[sets.size()];
        while(!uncovered.isEmpty()){int bi=-1,bestCount=0;for(int i=0;i<sets.size();i++)if(!used[i]){int count=0;for(int x:sets.get(i))if(uncovered.contains(x))count++;if(count>bestCount){bestCount=count;bi=i;}}
            if(bi<0)break;used[bi]=true;pick.add(sets.get(bi));uncovered.removeAll(sets.get(bi));}
        return uncovered.isEmpty()?pick:null;
    }
    static void run(String name,List<Set<Integer>> sets,Set<Integer> u){best=null;exactCalls=0;exact(sets,0,new HashSet<Integer>(),new ArrayList<Set<Integer>>(),u);List<Set<Integer>> g=greedy(sets,u);
        System.out.println("\\n"+name+" exact="+(best==null?"no cover":best.size())+" greedy="+(g==null?"no cover":g.size())+" exactNodes="+exactCalls);
        System.out.println("Exact sets="+best);System.out.println("Greedy sets="+g);}
    static Set<Integer> s(Integer...x){return new HashSet<Integer>(Arrays.asList(x));}
    public static void main(String[] args){Set<Integer> u=s(1,2,3,4,5,6);
        run("Case 1",Arrays.asList(s(1,2,3),s(4,5),s(6),s(1,4,6),s(2,3,5)),u);
        run("Case 2",Arrays.asList(s(1,2),s(3,4),s(5,6),s(1,3,5),s(2,4,6)),u);
        run("Greedy counterexample",Arrays.asList(s(1,2,3,4),s(1,2,5),s(3,4,6),s(5),s(6)),u);
    }
}
