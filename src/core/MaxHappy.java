package core;

import java.util.List;

public class MaxHappy {

    public static class Employee{
        int happy;
        List<Employee> nexts;
    }

    public static class Info{
        int yes;
        int no;

        public Info(int yes,int no){
            this.yes = yes;
            this.no = no;
        }
    }

    public static Info maxHappy(Employee emp){
       if (emp == null){
           return new Info(0,0);
       }
       if(emp.nexts.isEmpty()){
           return new Info(emp.happy,0);
       }
       int yes = emp.happy;
       int no = 0;
       for(Employee next : emp.nexts){
           Info nextInfo = maxHappy(next);
           yes += nextInfo.no;
           no += Math.max(nextInfo.yes,nextInfo.no);
       }
       return new Info(yes,no);
    }


























    public static Info maxHappy2(Employee emp){
        if(emp == null){
            return new Info(0,0);
        }
        if(emp.nexts.isEmpty()){
            return new Info(emp.happy,0);
        }
        int yes = emp.happy;
        int no = 0;
        for(Employee next : emp.nexts){
            Info nextInfo = maxHappy2(next);
            yes += nextInfo.no;
            no += Math.max(nextInfo.no,nextInfo.yes);
        }
        return new Info(yes,no);
    }
}
