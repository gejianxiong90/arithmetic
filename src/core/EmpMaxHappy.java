package core;

import java.util.List;

public class EmpMaxHappy {

    public static class Employee{
        int happy;
        List<Employee> next;
    }

    public static class Info{
        int yes;// 来
        int no; // 不来

        public Info(int yes,int no){
            this.yes = yes;
            this.no = no;

        }
    }

    public  static Info getMaxHappy(Employee x){
        if(x.next.isEmpty()){
            return new Info(x.happy,0);
        }
        int yes = x.happy; // x 来
        int no = 0;
        for(Employee e : x.next){
            Info maxHappy = getMaxHappy(e);
            no += Math.max(maxHappy.yes,maxHappy.no);
            yes += maxHappy.no;
        }

        return new Info(yes,no);

    }

}
