package com.cssl.util;

/**
 * 产生数字
 */
public class PageUtil {
    public static  int[] pages(int totalPage,int pageNo){
        //1、如果总共的数字<=5个，无需判断，直接取这5个数字
        if(totalPage <= 5){
            int[] nums = new int[totalPage];
            for(int i=0;i<totalPage;i++){
                nums[i] = i+1;
            }
            return nums;
        }
        if(pageNo <= 3){
            int[] nums = new int[5];
            for(int i=0;i<5;i++){
                nums[i] = i+1;
            }
            return nums;
        }
        if(pageNo > totalPage - 3){
            int[] nums = new int[5];
            for(int i = totalPage - 4,index=0;i<=totalPage ;i++,index++){
                nums[index] = i;
            }
            return nums;
        }
        int[] nums = new int[5];
        for(int i=pageNo - 2,index = 0;i<=pageNo + 2;i++,index++){
            nums[index] = i;
        }
        return nums;
    }
}
