package quicksort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args){
//        int[] arr = { 49,41, 38 };
//        quickSort1(arr, 0, arr.length - 1);
//        System.out.println("排序后:"+ Arrays.toString(arr));

//        int[] arr = {49,41};
//        swap(arr,0,1);
//        System.out.println(Arrays.toString(arr));
        System.out.println((int)(Math.random() * 9));

        int[] arr = {54,11,12,12,12,54,11,55,11};
//        quickSort3(arr,0,8);
//        System.out.println(Arrays.toString(arr));

        process(arr,0,8);
        System.out.println(Arrays.toString(arr));




    }


    public static void quickSort3(int[] arr,int low,int high){
        if(high - low < 2){
            return;
        }
//        int i = new Random().nextInt(high - low + 1);
//        swap(arr,i,high);
        int temp = low +  (int)Math.random() * (high - low + 1);
        swap(arr,temp,high);
        int[] ints = netherlandsFlag(arr, low, high);
        quickSort3(arr,low,ints[0]-1);
        quickSort3(arr,ints[1]+1,high);

    }


    public static int[] netherlandsFlag(int[] arr,int low,int high){
        if(low >high){
            return new int[]{-1,-1};
        }
        if(low == high){
            return new int[]{low,high};
        }
        int l = low -1;
        int r = high;
        int index = low;
        while(index < r){
            if(arr[index] == arr[high]){
                ++index;
            }else if(arr[index] > arr[high]){
               swap(arr,index,--r);
            }else if(arr[index] < arr[high]){
                swap(arr,++l,index++);
            }
        }
        swap(arr,r,high);

        return new int[]{l+1,r};
    }

    public static void swap(int[] arr, int source,int target){
//       arr[source] = arr[source] ^ arr[target];
//       arr[target] = arr[source] ^ arr[target];
//       arr[source] = arr[source] ^ arr[target];

        int temp = arr[source];
        arr[source] = arr[target];
        arr[target] = temp;
    }

    public static void quickSort(int [] arr,int low,int high){
        if(low < high){
            // 找寻基准数据的正确索引
            int index = getIndex(arr,low,high);
            // 进行迭代对index之前和之后的数组进行相同的操作使整个数组变成有序
            quickSort(arr,0,index -1 );
            quickSort(arr,index + 1,high );
        }

    }

    public static int getIndex(int[] arr,int low,int high){
        // 基准数据
        int tmp = arr[low];
        while( low < high){
            // 当队尾的元素大于等于基准数据时,向前挪动high指针
            while (low < high && arr[high] >= tmp){
                high--;
            }
            // 如果队尾元素小于tmp了,需要将其赋值给low
            arr[low] = arr[high];
            // 当队首元素小于等于tmp时,向前挪动low指针
            while( low <high && arr[low] <= tmp){
                low++;
            }
            // 当队首元素大于tmp时,需要将其赋值给high
            arr[high] = arr[low];
        }
        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
        arr[high] = tmp;
        // 返回tmp的正确位置
        return high;
    }





















    public static void quickSort1(int [] arr,int low,int high){
        if(high > low){
            int index1 = getIndex1(arr, low, high);
            quickSort1(arr,low,index1-1);
            quickSort1(arr,index1+1,high);
        }
    }

    public static int getIndex1(int[] arr,int low,int high){
        int temp = arr[low];
        while(high > low){
            while(high > low && arr[high] >= temp){
                high--;
            }
            arr[low] = arr[high];
            while(high >low && arr[low] <= temp){
                low ++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }

















    public static int index2(int[] arr,int low,int high){
        int temp = arr[low];
        while (high > low){
            while (high > low && arr[high] >= temp){
                high--;
            }
            arr[low] = arr[high];

            while (high > low && arr[low] <= temp){
                low--;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }






    public static void process(int[] arr,int low,int high){
        if(low >= high){
            return;
        }


        int[] ints = netherlandsFlag2(arr, low, high);
        process(arr,low,ints[0]-1);
        process(arr,ints[1]+1,high);

    }


    public static int[] netherlandsFlag2(int[] arr,int low,int high){
        if(low == high){
            return new int[]{low,high};
        }
        if(low > high){
            return new int[]{-1,-1};
        }
        int p = low -1;
        int p2 = high;
        int index = low;
        int tempNum = arr[high];
        while(index < p2){
            if(arr[index] == tempNum ){
                index++;
            }else if(arr[index] < tempNum){
                swap(arr,++p,index++);
            }else if(arr[index] > tempNum){
                swap(arr,--p2,index);
            }
        }
        swap(arr,p2,high);

        return new int[]{p+1,p2};
    }
    public static int[] netherlandsFlag1(int[] arr,int low,int high){
        if(low >high){
            return new int[]{-1,-1};
        }
        if(low == high){
            return new int[]{low,high};
        }
        int l = low -1;
        int r = high;
        int index = low;
        while(index < r){
            if(arr[index] == arr[high]){
                ++index;
            }else if(arr[index] > arr[high]){
                swap(arr,index,--r);
            }else if(arr[index] < arr[high]){
                swap(arr,++l,index++);
            }
        }
        swap(arr,r,high);

        return new int[]{l+1,r};
    }


}
