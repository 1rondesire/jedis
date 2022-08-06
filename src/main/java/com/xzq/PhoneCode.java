package com.xzq;

import org.junit.platform.commons.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Random;

public class PhoneCode {
    public static void main(String[] args) {
        verifyCode("12385654937");
        getRedisCode("12385654937","647551");
    }
    //验证码校验
    public static void getRedisCode(String phone,String code){
        Jedis jedis = new Jedis("120.27.161.86",6379);
        String codeKey = "verifyCode" + phone + ":code";
        String vcode = jedis.get(codeKey);
        if (!StringUtils.isBlank(vcode)){
            if (vcode.equals(code)){
                System.out.println("验证码正确");
            }else {
                System.out.println("验证码错误");
            }
        }else {
            System.out.println("验证码已经过期");
            System.out.println("验证码已经过期");
        }
        jedis.close();
    }

    //没个手机每天只能发送三次验证码，验证码存入redis，设置过期时间
    public static void verifyCode(String phone){
        Jedis jedis = new Jedis("120.27.161.86",6379);
//        jedis.auth("Xx02368999");
        String countKey = "verifyCode" + phone + ":count";
        String codeKey = "verifyCode" + phone + ":code";
        String count = jedis.get(countKey);
        if (count == null){
            jedis.setex(countKey,24*60*60,"1");
        }else if(Integer.parseInt(count) <= 2){
            jedis.incr(countKey);
        }else if (Integer.parseInt(count) > 2){
            System.out.println("该手机发送次数已经上限");
            return;
        }
        String vcode = getCode();
        jedis.setex(codeKey,120,vcode);
        jedis.close();
    }
    //获取验证码
    public static String getCode(){
        Random random = new Random();
        String code = "";
        for (int i=0;i<6;i++){
            int nextInt = random.nextInt(10);
            code = code + nextInt ;
        }
        return code;
    }

}
