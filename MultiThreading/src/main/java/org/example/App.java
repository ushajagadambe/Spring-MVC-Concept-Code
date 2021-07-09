package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter the size of array");
        int size= scanner.nextInt();
        int[] array=new int[size];
        System.out.println("enter the element of array");
      for(int i=0;i< array.length;i++)
      {
          System.out.println("enter the element");
          array[i]=scanner.nextInt();
      }
     System.out.println(" enter the block size");
      int blockSize= scanner.nextInt();
      int noOfBlock=(int) Math.ceil(array.length/blockSize);
        ExecutorService executorService= Executors.newFixedThreadPool(3);

        int start,end;
        List<Future<Integer>> futureArrayList=new ArrayList<>();
        for(int i=1;i<=noOfBlock;i++)
        {
  start=(i-1)*blockSize;
  end=start+blockSize-1;
  if(end>=array.length)
  end=array.length-1;

            Future<Integer> future=executorService.submit(new Calculation(start,end,array) );
            futureArrayList.add(future);
        }
        int totalSum=0;
        for(Future<Integer> future:futureArrayList)
        {
            totalSum=totalSum+future.get();
            System.out.println("future="+future.get());
        }
        System.out.println("final result="+totalSum);
    }
}
