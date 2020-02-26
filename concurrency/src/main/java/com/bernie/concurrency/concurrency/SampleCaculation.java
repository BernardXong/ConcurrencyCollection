package com.bernie.concurrency.concurrency;

/**
 * SampleCaculation
 *
 * @Description TODO
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/20
 */
public class SampleCaculation {

    public static void main(String[] args) throws Exception{
        int x = System.in.read();

        String luckTwo = "";
        if(x<=100000||x>999998){
            System.out.println("号码必须在10^5到10^6之间！");
        }else{
            String xStr = String.valueOf(x);
            char[] chars = xStr.toCharArray();
            int total = numbersSum(chars[0],chars[1],chars[2]);
            if(numbersSum(chars[3],chars[4],chars[5])>total){
                int current = numbersSum(chars[3],chars[4],chars[5]);

                int dual = (current - total);
                if(Integer.valueOf(String.valueOf(chars[5]))+dual<10){
                    //todo
                }else{
                    //todo
                }
            }else{
                int current = numbersSum(chars[3],chars[4],chars[5]);
                int dual = (total - current);
                if(Integer.valueOf(String.valueOf(chars[5]))+dual<10){
                    int luck = Integer.valueOf(String.valueOf(chars[5]))+dual;
                    chars[5] = String.valueOf(luck).charAt(0);
                }else{
                    int luck = Integer.valueOf(String.valueOf(chars[5]))+dual;
                    chars[5] = String.valueOf(luck).charAt(1);
                    chars[4] = String.valueOf((Integer.valueOf(String.valueOf(String.valueOf(luck).charAt(0)))+Integer.valueOf(String.valueOf(chars[4])))).charAt(0);
                }
                luckTwo= String.valueOf(chars[3]+chars[4]+chars[5]);
            }
            String luckOne = String.valueOf(chars[0]+chars[1]+chars[2]);
            String luck = luckOne+luckTwo;
            System.out.println("找到的幸运号码是:"+luck);
        }
    }

    private static int numbersSum(char a,char b,char c){
        return Integer.valueOf(String.valueOf(a))+Integer.valueOf(String.valueOf(b))+Integer.valueOf(String.valueOf(c));
    }
}
