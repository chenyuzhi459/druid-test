package io.sugo.dataRobot;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by qwe on 17-7-13.
 */
public class Util {
    private static final String numBase = "0123456789";
    private static final String strBase = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static Random random = new Random();
    public static MessageDigest md;
    public static DecimalFormat df = new DecimalFormat("0.00");

    public static String randomNumString(int length) { //length表示生成字符串的长度
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(numBase.length());
            sb.append(numBase.charAt(number));
        }
        return sb.toString();
    }


    public static char getNumFromStr(String str,int index) {
        char[] a= str.toCharArray();
        int flag = 0;
        for (int i = 0; i < str.length(); i++) {
            if(a[i]>='0' && a[i]<='9') {
                flag++;
                if(flag == index) {
                    return a[i];
                }

            }
        }
        return '0';
    }

    public static String randomString(int length) { //length表示生成字符串的长度
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(strBase.length());
            sb.append(strBase.charAt(number));
        }
        return sb.toString();
    }

    public static String randomPhoneString() {
        StringBuffer sb = new StringBuffer();
        return sb.append(randomNumString(8)).toString();
    }

    static String[] Surname = {"赵","钱","孙","李","周","吴","郑","王","冯","陈","褚","卫","蒋","沈","韩","杨","朱","秦","尤","许",
            "何","吕","施","张","孔","曹","严","华","金","魏","陶","姜","戚","谢","邹","喻","柏","水","窦","章","云","苏","潘","葛","奚","范","彭","郎",
            "鲁","韦","昌","马","苗","凤","花","方","俞","任","袁","柳","酆","鲍","史","唐","费","廉","岑","薛","雷","贺","倪","汤","滕","殷",
            "罗","毕","郝","邬","安","常","乐","于","时","傅","皮","卞","齐","康","伍","余","元","卜","顾","孟","平","黄","和",
            "穆","萧","尹","姚","邵","湛","汪","祁","毛","禹","狄","米","贝","明","臧","计","伏","成","戴","谈","宋","茅","庞","熊","纪","舒",
            "屈","项","祝","董","梁","杜","阮","蓝","闵","席","季","麻","强","贾","路","娄","危","江","童","颜","郭","梅","盛","林","刁","钟",
            "徐","邱","骆","高","夏","蔡","田","樊","胡","凌","霍","虞","万","支","柯","万俟","司马","上官","欧阳","夏侯","诸葛",};

    public static String randomChineseName() {
        int index=random.nextInt(Surname.length-1);
        String name = Surname[index]; //获得一个随机的姓氏

        /* 从常用字中选取一个或两个字作为名 */
        if(random.nextBoolean()){
            name+=getChinese()+getChinese();
        }else {
            name+=getChinese();
        }
        return name;
    }

    public static String getChinese(){
        String str = null;
        int highPos, lowPos;
        Random random = new Random();
        highPos = (176 + Math.abs(random.nextInt(71)));//区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
        random=new Random();
        lowPos = 161 + Math.abs(random.nextInt(94));//位码，0xA0打头，范围第1~94列

        byte[] bArr = new byte[2];
        bArr[0] = (new Integer(highPos)).byteValue();
        bArr[1] = (new Integer(lowPos)).byteValue();
        try {
            str = new String(bArr, "GB2312");   //区位码组合成汉字
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


    public static String getMd5Sum(String pos) {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        StringBuffer sb = new StringBuffer();
        md.reset();
        md.update(pos.getBytes());
        byte[] digest = md.digest();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    public static String getNameByRate(String[][] strings) {
        int index = random.nextInt(100);
        for(int i=0;i<strings.length;i++) {
            if(index < Integer.parseInt(strings[i][1])) {
                return strings[i][0];
            }
        }
        return "";
    }

    public static String getOrderId(int orderCount) {
        String str = orderCount+"";
        int length = 12 - str.length();
        for(int i=0;i<length;i++) {
            str = "0"+str;
        }
        return "SG"+str;
    }

    public static float getOrderAmount() {
        return Float.parseFloat(df.format(random.nextInt(19500)/100.0+5));
    }

    public static String getCouponId(int coupon_index, float pre_amount) {

        int pre_amount_index = 0;
        if(pre_amount >35) {
            pre_amount_index = 8;
        }else {
            pre_amount_index = (int)pre_amount/5;
        }
        int num = coupon_index * 9 + pre_amount_index;


        String str = num+"";
        int length = 4 - str.length();
        for(int i=0;i<length;i++) {
            str = "0"+str;
        }
        return "SG"+str;
    }

    public static float getPaidAmount(float order_amount, float pre_amount) {
        float paidAmount = order_amount - pre_amount;
        return Float.parseFloat(df.format(paidAmount));
    }
}
