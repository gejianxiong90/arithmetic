package treecode;

import java.util.ArrayList;
import java.util.List;

public class MaxHappy {
    public static void main(String[] args) {

    }

    public static class Info{
        int yes;
        int no;

        public Info(int yes,int no){
            this.yes = yes;
            this.no = no;
        }
    }

    public static class Employee{
        int happy;
        List<Employee> nexts = new ArrayList<Employee>();

        public Employee(int happy,List<Employee> nexts){
            this.happy = happy;
            this.nexts = nexts;
        }
    }

    public static Info process(Employee x){
        if(x.nexts == null || x.nexts.isEmpty()){
            return new Info(x.happy,0);
        }

        int yes = x.happy;
        int no = 0;
        for(Employee e : x.nexts){
            Info process = process(e);
            yes += process.no;
            no += Math.max(process.yes,process.no);
        }
        return new Info(yes,no);
    }
}
