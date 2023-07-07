package com.skapps.cfweb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skapps.cfweb.dtos.CredentialDTO;
import com.skapps.cfweb.dtos.UserDTO;
import com.skapps.cfweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class RedisUtils {
    private static final JedisPool jedisPool = new JedisPool("192.168.1.100", 6379);

    private static final String USER_KEY = "user=";
    private static final String USER_FAILED_KEY = "username=";
    private static final String USER_FAILED_COUNT = "failed_attempt_count=";
    private static final String TOKEN = "token=";

    private static final int TOKEN_EXPIRE = 3600;

    @Autowired
    private UserService userService;

    public static void registerUserToken (UserDTO user) {
        Jedis jedis = jedisPool.getResource();

        String key = USER_KEY + user.getUserId();
        String token = TOKEN + user.getToken();

        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) { }

        if (jedis.exists(key)) jedis.del(key);

        jedis.sadd(key, json, token);
        jedis.expire(key, TOKEN_EXPIRE);
        jedis.close();
    }

    public boolean validateToken (long userId, String t) {
        Jedis jedis = jedisPool.getResource();
        String key = USER_KEY + userId;
        String token = TOKEN + t;
        if (jedis.sismember(key, token)) {
            jedis.expire(key, TOKEN_EXPIRE);
            jedis.close();
            return true;
        }
        jedis.close();
        return false;
    }

    public static boolean AccountIsLocked (String username) {
        Jedis jedis = jedisPool.getResource();
        String key = USER_FAILED_KEY + username;
        return getFailedLoginCount(username) == 5;
    }

    private static int getFailedLoginCount(String username) {
        Jedis jedis = jedisPool.getResource();
        String key = USER_FAILED_KEY + username;
        if (jedis.exists(key)) {
            int count = 0;
            Set<String> countSet = jedis.smembers(key);
            for (String c : countSet) {
                int v = Integer.parseInt(c);
                if (v > count) {
                    count = v;
                }
            }
            jedis.close();
            return count;
        } else {
            jedis.close();
            return 0;
        }
    }

    public static void UpdateFailedLoginCount(String username,  boolean isFailedLoginAttempt) {
        Jedis jedis = jedisPool.getResource();
        String key = USER_FAILED_KEY + username;
        if (isFailedLoginAttempt) {
            Integer count = getFailedLoginCount(username);
            count++;
            if (jedis.exists(key)) jedis.del(key);
            jedis.sadd(key, count.toString());
            if (count >= 5) {
                jedis.expire(key, TOKEN_EXPIRE);
            }
        } else {
            if (jedis.exists(key)) jedis.del(key);
        }
    }


}
