package com.xzq;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisDemo1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("120.27.161.86",6379);
        String ping = jedis.ping();
        System.out.println(ping);
    }

    @Test
    public void demo1(){
        Jedis jedis = new Jedis("120.27.161.86",6379);
        jedis.set("name","xzq");
        String name = jedis.get("name");
        System.out.println(name);
        Set<String> keys = jedis.keys("*");
        for (String key : keys){
            System.out.println(key);
        }
    }
    @Test
    public void demo2(){
        Jedis jedis = new Jedis("120.27.161.86",6379);
        double random = Math.random();
        System.out.println(random);


    }





}
