package com.dl.java8;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD=8;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers,0,numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length=end-start;
        if (length<=THRESHOLD){
            return computeSequentially(); //小于阈值顺序执行
        }
        ForkJoinSumCalculator leftTask=new ForkJoinSumCalculator(numbers,start,start+length/2);
        leftTask.fork(); //利用另一个FoekJoinPool线程异步执行新创建的子任务
        ForkJoinSumCalculator rightTask=new ForkJoinSumCalculator(numbers,start+length/2,end);
        Long rightResult = rightTask.compute(); //同步执行，可能允许进一步递归
        Long leftResult = leftTask.join();
        return leftResult+rightResult;
    }

    private Long computeSequentially() {
        long sum=0;
        for (int i=start;i<end;i++){
            sum+=numbers[i];
        }
        return sum;
    }


    public static void main(String[] args) {
        long[] nums={1,2,3,4,5,6,7,8,9,10};
        ForkJoinSumCalculator forkJoinSumCalculator=new ForkJoinSumCalculator(nums,0, nums.length);
        Long compute = forkJoinSumCalculator.compute();
        System.out.println(compute);
    }
}
