package com.guanshj.framework.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Description: 模拟工具类
 * 创建日期: 14-7-22  上午10:26
 *
 * @author: guanshj QQ: 928990049
 *
 *                          _oo0oo_
 *                         o8888888o
 *                         88" . "88
 *                         (| -_- |)
 *                         0\  =  /0
 *                       ___/'---'\___
 *                     .' \\|     |// '.
 *                    / \\|||  :  |||// \
 *                   / _||||| -:- |||||_ \
 *                  |   | \\\  -  /// |   |
 *                  | \_|  ''\---/''  |_/ |
 *                  \  .-\__  '-'  __/-.  /
 *               ____'. .'  /--.--\  '. .'____
 *            .""  '<  `.___\_<|>_/___.'  >'  "".
 *           | | :   `_ \`.;`\ _ /`;.`/ _'   : | |
 *           \  \  `_.   \_ __\ /__ _/   ._'  /  /
 *       =====`-._____`.___ \_____/ ___.`_____.-`=====
 *                          '=---='
 *
 *       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *                     佛祖保佑   中500万
 *
 */
public class SimulationUtil {

    /**
     * 模拟 随机获取红球
     * @return
     */
    public static List<Integer> getRedRandom() {
        List<Integer> resultArr = new ArrayList<Integer>();  // 返回的 size  等于6 的数组

        /*Integer[] baseArr = new Integer[33];
        for(int i = 0 ; i < 33 ; i++){
            baseArr[i] = i+1 ;
        }*/

        Random random = new Random();
        for(int i = 0; i < 6;i++) {
            Integer ra = 0;
            do {
                ra = Math.abs(random.nextInt())%33 ;
                for(Integer item : resultArr){
                    if(item == ra){
                        ra = 0;  // 重复了
                    }
                }
            } while (ra == 0);

            resultArr.add(ra);
        }

        // 冒泡排序
        for (int i = 0; i < resultArr.size(); i++) {
            for (int j = 0; j < resultArr.size()-i-1; j++) {
                if(resultArr.get(j) > resultArr.get(j+1) ){
                    int temp = resultArr.get(j);
                    resultArr.set(j , resultArr.get(j+1));
                    resultArr.set(j+1 , temp);
                }
            }
        }

        return resultArr;
    }

    /**
     * 排除杀球的 随机模拟 红球
     * @param killRed
     * @return
     */
    public static List<Integer> getRedRandom(List<Integer> killRed) {
        List<Integer> resultArr = new ArrayList<Integer>();  // 返回的 size  等于6 的数组

        Random random = new Random();
        for(int i = 0; i < 6;i++) {
            Integer ra = 0;
            do {
                ra = Math.abs(random.nextInt())%33 ;
                for(Integer item : resultArr){
                    if(item == ra){
                        ra = 0;  // 重复了
                    }
                }

                for(Integer item2 : killRed){
                    if(item2 == ra){
                        ra = 0;  // is kill
                    }
                }
            } while (ra == 0);

            resultArr.add(ra);
        }

        // 冒泡排序
        for (int i = 0; i < resultArr.size(); i++) {
            for (int j = 0; j < resultArr.size()-i-1; j++) {
                if(resultArr.get(j) > resultArr.get(j+1) ){
                    int temp = resultArr.get(j);
                    resultArr.set(j , resultArr.get(j+1));
                    resultArr.set(j+1 , temp);
                }
            }
        }

        return resultArr;

    }

    /**
     * 在一个集合内 随机生成 红球
     * @param redList
     * @return
     */
    public static List<Integer> getRedRandomByList(List<Integer> redList) {
        List<Integer> resultArr = new ArrayList<Integer>();  // 返回的 size  等于6 的数组

        Random random = new Random();
        for(int i = 0; i < 6;i++) {
            Integer ra = 0;
            do {
                ra = Math.abs(random.nextInt())%33 ;
                for(Integer item : resultArr){
                    if(item == ra){
                        ra = 0;  // 重复了
                    }
                }

                boolean flag = false;
                for(Integer item2 : redList){
                    if(item2 == ra){
                        flag = true;   // 在范围内； 无需循环
                    }
                }
                if(!flag){
                    ra = 0;  //
                }

            } while (ra == 0);

            resultArr.add(ra);
        }

        // 冒泡排序
        for (int i = 0; i < resultArr.size(); i++) {
            for (int j = 0; j < resultArr.size()-i-1; j++) {
                if(resultArr.get(j) > resultArr.get(j+1) ){
                    int temp = resultArr.get(j);
                    resultArr.set(j , resultArr.get(j+1));
                    resultArr.set(j+1 , temp);
                }
            }
        }

        return resultArr;

    }

    /**
     * 模拟 随机获取蓝球
     * @return
     */
    public static Integer getBlueRandom () {
        Random random = new Random();
        return Math.abs(random.nextInt())%16;
    }

    /**
     * 排除杀球的 随机模拟 蓝球
     * @param killBlue
     * @return
     */
    public static Integer getBlueRandom (List<Integer> killBlue) {
        Random random = new Random();
        Integer result = 0;
        do {
            result = Math.abs(random.nextInt())%16 ;
            for(Integer item : killBlue){
                if(item == result){
                    result = 0;
                }
            }
        } while (result == 0);

        return result;
    }

    /**
     * 在一个集合内 随机生成 蓝球
     * @param blueList
     * @return
     */
    public static Integer getBlueRandomByList (List<Integer> blueList) {
        Random random = new Random();
        Integer result = 0;
        do {
            result = Math.abs(random.nextInt())%16 ;

            boolean flag = false;
            for(Integer item : blueList){
                if(item == result){
                    flag = true;
                }
            }
            if(!flag){
                result = 0;
            }
        } while (result == 0);

        return result;
    }

    /** test fn **/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("双色球随机模拟系统：###############################");
        System.out.println("请选择模式： 1：普通随机；2：排除杀球随机；3：祝老板5注选6注");
        Integer flag =sc.nextInt();
        if(flag ==1){
            randomAll();
        }else if(flag ==2){
            randomKill();
        }else if(flag == 3){
            randomIn();
        }else{
            consoleOver();
        }
    }
    private static void randomAll () {
        System.out.println("普通随机 -------------------------------------------");
        System.out.println("请输入随机数：");
        Scanner sc = new Scanner(System.in);
        Integer randomSize = sc.nextInt();

        for(int i2 = 1 ;i2 <=randomSize ; i2++){
            List<Integer> arr = getRedRandom();
            System.out.println("随机结果："+i2);
            for(Integer i : arr){
                System.out.print(i);
                System.out.print("  ");
            }
            System.out.print(":" + getBlueRandom());
            System.out.println("");
        }
        System.out.println("普通随机结束 -------------------------------------------");
        consoleOver();
    }
    private static void randomKill (){
        Scanner sc = new Scanner(System.in);
        System.out.println("根据杀球随机 -------------------------------------------");
        System.out.println("请输入红球定胆：（输入1-33数字，出错概不负责; 结束请输入 0 ）");
        Integer input = 0;
        List<Integer> killList = new ArrayList<Integer>();

        boolean isJx = true;
        do {
            input = sc.nextInt();
            if(input == 0){
                isJx = false;
            }else{
                killList.add(input);
                System.out.println("请输入红球定胆：（输入1-33数字，出错概不负责; 结束请输入 0 ）");
            }
        } while (isJx);

        System.out.println("红球定胆输入结束 -------------------------------------------");

        System.out.println("请输入蓝球定胆：（输入1-16数字，出错概不负责; 结束请输入 0 ）");
        List<Integer> killList2 = new ArrayList<Integer>();
        isJx = true;
        do {
            input = sc.nextInt();
            if(input == 0){
                isJx = false;
            }else{
                killList2.add(input);
                System.out.println("请输入蓝球定胆：（输入1-16数字，出错概不负责; 结束请输入 0 ）");
            }
        } while (isJx);

        System.out.println("蓝球定胆输入结束 -------------------------------------------");

        System.out.println("请输入随机数：");
        Integer randomSize = sc.nextInt();

        System.out.println("begin--------------");
        System.out.print("红球定胆：");
        for(Integer i : killList){
            System.out.print(i);
            System.out.print("  ");
        }
        System.out.print("蓝球定胆：");
        for(Integer i : killList2){
            System.out.print(i);
            System.out.print("  ");
        }
        System.out.println("开始随机：");

        for(int i = 1; i<= randomSize; i++){
            List<Integer> arr = getRedRandom(killList);
            System.out.println("随机结果：" + i);
            for(Integer it : arr){
                System.out.print(it);
                System.out.print("  ");
            }
            System.out.print(":" + getBlueRandom(killList2));
            System.out.println("");
        }
        System.out.println("随机结束：");
        System.out.println("根据杀球随机结束 -------------------------------------------");
        consoleOver();
    }
    private static void randomIn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("在输入的范围内随机 -------------------------------------------");
        System.out.println("请输入随机范围的红球：（输入1-33数字，出错概不负责; 结束请输入 0 ）");

        Integer input = 0;
        List<Integer> redList = new ArrayList<Integer>();

        boolean isJx = true;
        do {
            input = sc.nextInt();
            if(input == 0){
                isJx = false;
            }else{
                redList.add(input);
                System.out.println("请输入随机范围的红球：（输入1-33数字，出错概不负责; 结束请输入 0 ）");

            }
        } while (isJx);

        System.out.println("红球随机范围输入结束 -------------------------------------------");

        System.out.println("请输入随机范围的蓝球：（输入1-16数字，出错概不负责; 结束请输入 0 ）");
        List<Integer> blueList = new ArrayList<Integer>();
        isJx = true;
        do {
            input = sc.nextInt();
            if(input == 0){
                isJx = false;
            }else{
                blueList.add(input);
                System.out.println("请输入蓝球定胆：（输入1-16数字，出错概不负责; 结束请输入 0 ）");
            }
        } while (isJx);

        System.out.println("蓝球随机范围输入结束 -------------------------------------------");

        System.out.println("请输入随机数：");
        Integer randomSize = sc.nextInt();

        System.out.println("begin--------------");
        System.out.print("红球范围：");
        for(Integer i : redList){
            System.out.print(i);
            System.out.print("  ");
        }
        System.out.print("蓝球范围：");
        for(Integer i : blueList){
            System.out.print(i);
            System.out.print("  ");
        }
        System.out.println("开始随机：");

        for(int i = 1; i<= randomSize; i++){
            List<Integer> arr = getRedRandomByList(redList);
            System.out.println("随机结果：" + i);
            for(Integer it : arr){
                System.out.print(it);
                System.out.print("  ");
            }
            System.out.print(":" + getBlueRandomByList(blueList));
            System.out.println("");
        }
        System.out.println("随机结束：");
        System.out.println("在输入的范围内随机 结束 -------------------------------------------");
        consoleOver();
    }

    private static void consoleOver () {
        System.out.println("*                          _oo0oo_");
        System.out.println("*                         o8888888o");
        System.out.println("*                         88\" . \"88");
        System.out.println("*                         (| -_- |)");
        System.out.println("*                         0\\  =  /0");
        System.out.println("*                       ___/'---'\\___");
        System.out.println("*                     .' \\\\|     |// '.");
        System.out.println("*                    / \\\\|||  :  |||// \\");
        System.out.println("*                   / _||||| -:- |||||_ \\");
        System.out.println("*                  |   | \\\\\\  -  /// |   |");
        System.out.println("*                  | \\_|  ''\\---/''  |_/ |");
        System.out.println("*                  \\  .-\\__  '-'  __/-.  /");
        System.out.println("*               ____'. .'  /--.--\\  '. .'____");
        System.out.println("*            .\"\"  '<  `.___\\_<|>_/___.'  >'  \"\".");
        System.out.println("*           | | :   `_ \\`.;`\\ _ /`;.`/ _'   : | |");
        System.out.println("*           \\  \\  `_.   \\_ __\\ /__ _/   ._'  /  /");
        System.out.println("*       =====`-._____`.___ \\_____/ ___.`_____.-`=====");
        System.out.println("*                          '=---='");
        System.out.println("*");
        System.out.println("*       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("*                     佛祖保佑   中500万");
    }
}
