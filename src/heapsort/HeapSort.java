package heapsort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
//        int [] a = {1,8,10,5,6,10,6,2,3};
//        heapSort(a);
//        System.out.println(Arrays.toString(a));

        Person person = new Person(5);
        Person person1 = new Person(4);
        Person person2 = new Person(3);
        Person person3 = new Person(7);
        Person person4 = new Person(8);
        Person[] ps = {person,person1,person2,person3,person4};
        Person process = process(ps);
        System.out.println(process);

    }

    public static class Person{
        int count;
        String name;
        public Person(int count){
            this.count = count;
        }
    }

    public static Person process(Person[] ps){
        for(int i = ps.length - 1 ; i >= 0  ; --i){
            getMoreCount(ps,i,ps.length);
        }
        return ps[0];
    }

    public static void getMoreCount(Person[] ps,int index,int heapSize){
        int lIndex = index * 2 + 1;
        while(lIndex < heapSize){
            int rIndex = lIndex + 1;
            int largest = 0;
            if(rIndex < heapSize){
                largest = ps[lIndex].count  > ps[rIndex].count ? lIndex : rIndex;
            }else{
                largest = lIndex;
            }
            largest = ps[index].count > ps[largest].count ? index : largest;
            if(index == largest){
                return;
            }
            swp(ps,index,largest);
            index = largest;
            lIndex = index * 2 + 1;

        }

    }
    public static  void swp(Person[] ps, int a,int b){
        Person temp = ps[b];
        ps[b] = ps[a];
        ps[a] = temp;
    }




    public static void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for(int i = arr.length -1 ; i >= 0 ; --i){
            heapify(arr,i,arr.length);
        }
        int heapSize = arr.length;
//        swap(arr,0,--heapSize);
        while(heapSize > 0){
            heapify(arr,0,heapSize);
            swap(arr,0,--heapSize);
        }
    }


    public static void heapify1(int[] arr,int index,int heapSize){
        int lIndex = 2*index+1;
        while(lIndex < heapSize){
            int rIndex = lIndex + 1;
            int largest = 0;
            if(rIndex < heapSize){
                largest = arr[lIndex] > arr[rIndex] ? lIndex : rIndex;
            }else{
                largest = lIndex;
            }
            largest = arr[index] > arr[largest] ? index : largest;
            if(largest == index){
                return;
            }
            swap(arr,index,largest);
            index = largest;
            lIndex = index * 2 +1;
        }
    }



    // 最后一个开始找，变成大顶堆
    public static void heapify(int[] arr,int index,int heapSize){
        int lIndex = index * 2 + 1;
        while (lIndex < heapSize){
            int rIndex = lIndex + 1;
            int largest = 0;
            if( rIndex < heapSize){
                largest = arr[rIndex] > arr[lIndex] ? rIndex : lIndex;
            }else{
                largest = lIndex;
            }
            largest = arr[index] > arr[largest] ? index : largest;
            if(largest == index){
                return;
            }
            swap(arr,largest,index);
            index = largest;
            lIndex = index * 2 + 1;

        }
    }

    public static void swap(int[] arr, int source,int target){
//       arr[source] = arr[source] ^ arr[target];
//       arr[target] = arr[source] ^ arr[target];
//       arr[source] = arr[source] ^ arr[target];
        int temp = arr[source];
        arr[source] = arr[target];
        arr[target] = temp;
    }
}
