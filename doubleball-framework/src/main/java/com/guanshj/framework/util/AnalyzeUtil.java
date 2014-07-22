package com.guanshj.framework.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:  分析双色球 工具类 包含各种 分析方法
 * 创建日期: 14-7-22  上午10:26
 *
 * @author: guanshj QQ: 928990049
 */
public class AnalyzeUtil {

    /*****************************8
     *  所有的 redList 集合 都是 从小到大顺序 6个item 过来的 （index 0-5）
     *  方法命名约定：杀红 （sRed + 方法序号）；求红 （qRed+方法序号） ；杀蓝（sBlue+方法序号）；求蓝（qBlue +方法序号）
     *  算法约定：只要是减法，就要求绝对值，防止负数
     *  sRed 12 -17 是 出号顺序
     *  ****************************
     */

    /**
     * 杀红公式 1
     * 当期开奖号码大小顺序第一位与第六位的差，计算的结果在下一期有可能不出
     * @return
     */
    public static Integer sRed1(List<Integer> redList) {
        int v1 = redList.get(0);
        int v6 = redList.get(5);
        return Math.abs(v1-v6);
    }

    /**
     * 杀红公式 2
     * 当期开奖号码大小顺序第二位与第三位的差，计算的结果在下一期有可能不出
     * @param redList
     * @return
     */
    public static Integer sRed2(List<Integer> redList){
        int v2 = redList.get(1);  // 第二位
        int v3 = redList.get(2);  // 第三位
        return Math.abs(v2-v3);
    }

    /**
     * 杀红公式 3
     * 当期开奖号码大小顺序第二位与第五位的差，计算的结果在下一期有可能不出
     * @param redList
     * @return
     */
    public static Integer sRed3(List<Integer> redList){
        int v2 = redList.get(1);  // 第2位
        int v5 = redList.get(4);  // 第5位
        return Math.abs(v2 -v5);
    }

    /**
     * 杀红公式 4
     * 当期开奖号码大小顺序第一位乘4再减掉2，计算的结果在下一期有可能不出
     * @param redList
     * @return
     */
    public static Integer sRed4(List<Integer> redList){
        int v1 = redList.get(0);
        return v1 * 4 -2 ;
    }

    /**
     * 杀红公式 5
     * 当期开奖号码大小顺序期开第一位加当奖的蓝号，计算的结果再乘3后，所得的号码在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed5(List<Integer> redList ,Integer blue){
        int v1 = redList.get(0);
        return (v1 + blue ) * 3;
    }

    /**
     * 杀红公式 6
     * 当期开奖号码大小顺序第一位加09，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed6(List<Integer> redList ){
        int v1 = redList.get(0);
        return v1 + 9;
    }

    /**
     * 杀红公式 7
     * 当期开奖号码大小顺序第二位加05，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed7(List<Integer> redList ){
        int v2 = redList.get(1);
        return v2 + 5;
    }

    /**
     * 杀红公式 8
     * 当期开奖号码大小顺序第三位加04，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed8(List<Integer> redList ){
        int v3 = redList.get(2);
        return v3 + 4;
    }

    /**
     * 杀红公式 9
     * 当期开奖号码大小顺序第三位加07，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed9(List<Integer> redList ){
        int v3 = redList.get(2);
        return v3 + 7;
    }

    /**
     * 杀红公式 10
     * 当期开奖号码大小顺序第六位加04，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed10(List<Integer> redList ){
        int v6 = redList.get(5);
        return v6 + 4;
    }

    /**
     * 杀红公式 11
     * 当期开奖号码大小顺序第四位与第五位的差，加当期的蓝号，再加01，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed11(List<Integer> redList ,Integer blue ){
        int v4 = redList.get(3);
        int v5 = redList.get(4);
        return Math.abs(v4-v5) + blue + 1;
    }

    /**
     * 杀红公式 12
     * 当期开奖号码出号顺序第一位与第二位的和，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed12 (List<Integer> redList){
        return null ;
    }

    /**
     * 杀红公式 13
     * 当期开奖号码出号顺序第二位与第三位的差，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed13 (List<Integer> redList){
        return null ;
    }

    /**
     * 杀红公式 14
     * 当期开奖号码出号顺序第三位与第五位的差，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed14 (List<Integer> redList){
        return null ;
    }

    /**
     * 杀红公式 15
     * 当期开奖号码出号顺序首尾的差，加当期开奖的蓝号，再减去03，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed15 (List<Integer> redList ,Integer blue){
        return null;
    }

    /**
     * 杀红公式 16
     * 当期开奖号码出号顺序第一位与第三位的差，加当期开奖的蓝号，再加02，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed16 (List<Integer> redList ,Integer blue){
        return null;
    }

    /**
     * 杀红公式 17
     * 当期开奖号码出号顺序一位，二位，三位相加的和，加当期开奖的蓝号，再减去01，计算的结果在下一期有可能不出。
     * （备注：如遇蓝号重复，重复的蓝号再另外减去01）
     * @param redList
     * @return
     */
    public static Integer sRed17 (List<Integer> redList ,Integer blue){
        return null;
    }

    /**
     * 杀红公式 18
     * 当期开奖的蓝号，加当期开奖号码大小顺序第一位，计算的结果在下一期有可能不出。（备注：如遇蓝号重复，重复的蓝号再另外减去01）
     * @param redList
     * @return
     */
    public static Integer sRed18 (List<Integer> redList ,Integer blue){
        return blue + redList.get(0);
    }

    /**
     * 杀红公式 19
     * 当期开奖的蓝号，加当期开奖号码大小顺序第二位，再减去01，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed19 (List<Integer> redList ,Integer blue){
        return blue + redList.get(1) - 1;
    }

    /**
     * 杀红公式 20
     * 当期开奖的蓝号，减当期开奖号码大小顺序第四位，再加上01，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed20 (List<Integer> redList ,Integer blue){
        return Math.abs(blue - redList.get(3)) + 1;
    }

    /**
     * 杀红公式 21
     * 当期开奖的蓝号，减当期开奖号码大小顺序第五位，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed21 (List<Integer> redList ,Integer blue){
        return Math.abs(blue - redList.get(4));
    }

    /**
     * 杀红公式 22
     * 当期开奖的蓝号，乘当期开奖号码大小顺序第一位，计算的结果在下一期有可能不出。
     * @param redList
     * @return
     */
    public static Integer sRed22 (List<Integer> redList ,Integer blue){
        return blue * redList.get(0);
    }

    /**
     * 杀红公式 23
     * 当期开奖的蓝号，加+07，计算的结果在下一期有可能不出。
     * @return
     */
    public static Integer sRed23 (Integer blue){
        return blue + 7;
    }

    /**
     * 杀红公式 24
     * 当期开奖的蓝号，加+09，计算的结果在下一期有可能不出。
     * @return
     */
    public static Integer sRed24 (Integer blue){
        return blue + 9;
    }

    /**
     * 杀红公式 25
     * 当期开奖的蓝号，乘05加+02，计算的结果在下一期有可能不出。
     * @return
     */
    public static Integer sRed25 (Integer blue){
        return blue * 5 + 2;
    }

    /**
     * 杀红公式 26 (原来26-27合并)
     * 当期开奖的蓝号，如为偶数时，乘上2，再加02，计算的结果在下一期有可能不出，到目前为止，此条件无错误。14*2+2=30
     * 当期开奖的蓝号，如为奇数时，乘上5，再加+02，计算的结果在下一期有可能不出。
     * @return
     */
    public static Integer sRed26 (Integer blue){

        if(blue%2==0) {
            // System.out.println("偶数");
            return blue * 2 + 2;
        }
        else {
            // System.out.println("奇数");
            return blue * 5 + 2;
        }
    }

    /**
     * 杀红公式 27
     * 开奖红号按从小到大排序(第三位+第六位)除以33的余数+14,如果结果大于33,就减去33。
     * @return
     */
    public static Integer sRed27 (List<Integer> redList,Integer blue) {
        int v3 = redList.get(2);
        int v6 = redList.get(5);
        int temp = (v3+v6)%33 +14;
        if(temp >33)temp = temp - 33;
        return temp;
    }

    /**
     * 杀红公式 28
     * 上期**（红1位+红3位+红5位）/2
     * @return
     */
    public static Integer sRed28 (List<Integer> redList) {
        return (redList.get(0) + redList.get(2) + redList.get(4)) / 2;
    }

    /**
     * 杀红公式 29
     * 上期**  红6位减蓝1位
     * @return
     */
    public static Integer sRed29 (List<Integer> redList ,Integer blue) {
        return Math.abs(redList.get(5) - blue);
    }

    /**
     * 杀红公式 30
     * 上期** 红1位*3/2
     * @return
     */
    public static Integer sRed30 (List<Integer> redList) {
        return ( redList.get(0) * 3 ) / 2 ;
    }

    /**
     * 杀红公式 31
     * 上期* 红1位尾加上期红2位尾
     * @return
     */
    public static Integer sRed31 (List<Integer> redList) {
        int red1 = redList.get(0);
        int red2 = redList.get(1);

        String redStr1 = String.valueOf(red1);
        String redStr2 = String.valueOf(red2);

        if(redStr1.length()>1){
            red1 = redStr1.charAt(1);
        }
        if(redStr2.length() > 1){
            red2 = redStr2.charAt(1);
        }

        return red1 +red2;
    }

    /*
    杀红公式：
        当期有连号时，连号相加减33下期可排除。（这个不太好算）

        (下面的看起来就不靠谱 )
        上期蓝码
        上上期蓝码
        上期蓝码+上上期蓝码
        第五位加5
        开奖日期号
    */

    /**
     * 杀蓝公式 1
     * 1、用15减去上期蓝球号码，得出的数就是下期要杀的蓝号尾数。
     * @return
     */
    public static List<Integer> sBlue1(Integer blue){
        return getBlueByUnitsDigit(Math.abs(15 - blue));
    }

    /**
     * 杀蓝公式 2
     * 用19减上期蓝号得出的数即为下期要杀的尾数
     * @return
     */
    public static List<Integer> sBlue2(Integer blue){
        return getBlueByUnitsDigit( Math.abs(19 - blue) );
    }

    /**
     * 杀蓝公式 3
     * 用21减上期蓝号得出的数就是下期要杀的尾数。
     * @return
     */
    public static List<Integer> sBlue3(Integer blue){
        return getBlueByUnitsDigit( Math.abs(21 - blue) );
    }

    /**
     * 杀蓝公式 4
     * 用上两期蓝号的头和尾相加的数即为下期要杀的蓝号尾数。
     * @return
     */
    public static List<Integer> sBlue4(Integer blue1,Integer blue2){
        String blueStr1  = String.valueOf(blue1);
        String blueStr2  = String.valueOf(blue2);

        if(blueStr1.length() <=1) {
            blueStr1 = "0" + blueStr1;
        }
        if(blueStr2.length() <=1) {
            blueStr2 = "0" + blueStr2;
        }
        ;

        return getBlueByUnitsDigit(
                Math.abs( Integer.valueOf(blueStr1.substring(0, 1)) + Integer.valueOf(blueStr2.substring(1)) )
        );
    }


/*
     杀蓝公式：

        1、用15减去上期蓝球号码，得出的数就是下期要杀的蓝号尾数。
        例如：今年第13期双色球蓝号开出：09，用15-09=06，绝杀蓝号6尾的06和16两个号码，结果第14期开蓝号02，杀号成功!再用15-2=13，杀掉3尾的03和13，结果第15期开05，杀号又正确!我们再用15-5=10，杀0尾，结果第16期开03，我们又杀对蓝号。
                2
                2、用19减上期蓝号得出的数即为下期要杀的尾数。
        例如：今年双色球第1期蓝号开04，用19-04=15，绝杀蓝号5尾的05、15两个号码，结果2期开蓝号：14，杀号成功!我们再用19-14=05，杀掉05、15，结果双色球第3期蓝号开02，杀号又成功!我们一鼓作气，再用19-02=17，杀掉07，结果第4期蓝球号码开03。
                3、用21减上期蓝号得出的数就是下期要杀的尾数。
        例如：双色球第20期蓝号开：13，用21-13=08，杀掉08，结果第21期开：09;再用21-09=12，杀2尾，结果第22期开08;再用21-08=13，杀3尾，结果第23期开：08，杀号正确。
            4、用上两期蓝号的头和尾相加的数即为下期要杀的蓝号尾数。
    例如：第18期开15，第19期开04，两期的头尾相加即1+4=5，杀掉5尾(05、15)，结果第20期开13，杀号成功!再用0+3=3，杀掉3尾，结果第21期开09;再用1+9=10，杀掉0尾，结果第22期蓝号开出：08。
            5、用上两期蓝号的尾和头相加的数即为下期要杀的尾数。
    例如：第21期开09，第22期开08，用9+0=09，杀9尾，结果23期开08;再用8+0=08，杀8尾，结果第24期开12;再用8+1=9，杀掉9尾，结果第25期开11。
            6、用上二期蓝号尾相加得出的数就是下期要杀的尾数。
    例如：第23期蓝号开08，第24期开12，8+2=10，杀0尾，结果下期开11;再用2+1=3，杀3尾，结果第26期开16。
            7、用上期蓝号尾与隔一期蓝号尾相加得出的数即为下期要杀的尾数。
    例如：第28期开10，与隔一期即26期的16相加，即为0+6=6尾，杀06和16，结果第29期开13;再用29期的13与27期的06相加得出09尾，杀9尾，结果第30期开出：07。
            8、用上期蓝号乘以2得出的数即为下期要杀的尾数。
    例如：第29期开出：13，用13×2=6，绝杀6尾，结果第30期开07;再用7×2=14，绝杀04和14，结果第31期开01。
            9、用上期蓝号尾乘4得出的数即是下期要杀的尾数。
    例如：第29期开13，用3×4=12，绝杀2尾，结果第30期开07;再用7×4=28，绝杀8尾，结果第31期开01;再用1×4=4，绝杀4尾，结果第32期开06。
            10、用上期蓝号加7或减7，注意蓝号大于14则减7，小于14则加7，得出的数即为下期要杀的尾数。
    例如：今年第6期开02，用2+7=9，杀9尾，结果第7期开15，完全正确!再用15-7=8，杀掉8尾，结果下期开02，我们再用2+7=9，杀尾，结果第9期开02。
            11、用上期蓝号加2得出的数即为下期要杀的蓝号尾数。
    例如：第29期开13，用13+2=15，杀掉5尾，结果下期开07;再用07+2=9，杀掉9尾，结果第31期开01;我们再用1+2=3，绝杀3尾的03、13，结果第32期开出06。
            12、用上期蓝号加6等于的数就是下期蓝号要杀的尾数。
    例如：第29期蓝号开13，用13+6=19，绝杀9尾，结果第30期开07;再用07+6=13，绝杀03和13，结果下期开01;再继续用01+6=7，绝杀7，结果下期蓝号开06，例子不胜枚举。
*/
    /**
     * 根据尾数获取 多个红球
     * @param unitsDigit
     * @return
     */
    public static List<Integer> getRedByUnitsDigit(int unitsDigit){
        List<Integer> list = new ArrayList<Integer>();
        if (unitsDigit != 0) {
            list.add(unitsDigit);
        }
        list.add(unitsDigit + 10);
        list.add(unitsDigit + 20);
        if (unitsDigit <= 3) {

            list.add(unitsDigit + 30);
        }
        return list;
    }
    /**
     * 根据尾数获取 多个蓝球
     * @param unitsDigit
     * @return
     */
    public static List<Integer> getBlueByUnitsDigit(int unitsDigit){
        List<Integer> list = new ArrayList<Integer>();

        if (unitsDigit != 0) {
            list.add(unitsDigit);
        }

        if (unitsDigit <= 6) {
            list.add(unitsDigit + 10);
        }
        return list;
    }

}
