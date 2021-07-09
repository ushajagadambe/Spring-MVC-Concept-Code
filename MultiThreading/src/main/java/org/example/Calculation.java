package org.example;

import java.util.concurrent.Callable;

public class Calculation implements Callable<Integer> {

  int start;
  int end;
   int[] array;
   public  Calculation(int start,int end,int[] array)
   {
       this.start=start;
       this.end=end;
       this.array=array;
   }
    @Override
    public Integer call() throws Exception {
        int sum=0;
        for(int i=start;i<=end;i++)
            sum=sum+array[i];
        System.out.println("start"+sum+"end"+end);
        return sum;
    }
}
