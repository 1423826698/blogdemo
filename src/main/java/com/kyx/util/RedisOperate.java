package com.kyx.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;
@Component
public class RedisOperate {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
    * key 是否存在
    * @param key
     * @return
    */

    public boolean hasKey(String key)
    {
        return redisTemplate.hasKey(key);
    }
    /**
     * 实现命令：expire 设置过期时间，单位秒
     * @param key
     * @return
     */
    public void expire(String key,long timeout)
    {
        redisTemplate.expire(key,timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：TTL key,以秒为单位，返回给定key的剩余生存时间(TTL,time to live)
     * @param key
     * @return
     */
    public long ttl(String key)
    {
        return redisTemplate.getExpire(key);
    }

    /**
     * 实现命令：incr key，增加key一次
     * @param key
     * @param delta
     * @return
     */
    public long incr(String key,long delta)
    {
        return redisTemplate.opsForValue().increment(key,delta);
    }

    /**
     * 实现命令：keys pattern，查找所有符合给定模式pattern的key
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern)
    {
        return redisTemplate.keys(pattern);
    }

    /**
     * 实现命令：del key，删除一个key
     * @param key
     */
    public void del(String key)
    {
        redisTemplate.delete(key);
    }

    /**
     * 实现命令：set key value，设置一个key-value(将字符串值value关联到key)
     * @param key
     * @param value
     */
    public void set(String key,Object value)
    {
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 实现命令：Set key value ex seconds,设置key-value和超时时间（秒）
     * @param key
     * @param value
     * @param timeout
     */
    public void set(String key,String value, long timeout)
    {
        redisTemplate.opsForValue().set(key,value,timeout,TimeUnit.SECONDS);
    }

    /**
     * 实现命令：GET key，返回key所关联的字符串
     * @param key
     * @return
     */
    public Object get(String key)
    {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 实现命令：size key，返回key所储存的子字符串值得长度
     * @param key
     * @return
     */
    public Long size(String key)
    {
        return redisTemplate.opsForValue().size(key);
    }

    /**
     * 实现命令：判断哈希表中是否存在key，filed
     * @param key
     * @param field
     * @return
     */
    public boolean hasHkey(String key,Object field)
    {
        return  redisTemplate.opsForHash().hasKey(key,field);
    }

    /**
     * 实现命令：hset key field value，将哈希表key中得域field的
     * 值设置为value
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key,Object field,Object value)
    {
        redisTemplate.opsForHash().put(key,field,value);
    }

    /**
     * 实现命令：hashsize，将哈希表key中的数据
     * @param key
     * @return
     */
    public Long hsize(String key)
    {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 实现命令：HGET key field，返回哈希表中给定域field的值
     * @param key
     * @param field
     * @return
     */
    public Object hget(String key,Object field)
    {
        return redisTemplate.opsForHash().get(key,field);
    }

    /**
     * 实现命令:hdel key field [field...],删除哈希表key中的一个或多个指定域，
     * 不存在的域被忽略
     * @param key
     * @param fields
     */
    public void hdel(String key,Object... fields)
    {
        redisTemplate.opsForHash().delete(key,fields);
    }

    /**
     *实现命令：hgetall key，返回哈希表key中，所有的域和值
     * @param key
     * @return
     */
    public Object hgetall(String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 实现命令：lpush key value，将一个值 value插入到列表key的表头
     * @param key
     * @param value
     * @return
     */
    public long lpush(String key,Object value)
    {
        return redisTemplate.opsForList().leftPush(key,value);
    }

    /**
     *截取集合元素长度，保留长度内的数据
     *
     */
    public void ltrim(String key,long start,long end){
        redisTemplate.opsForList().trim(key,start,end);
    }

    /**
     * 实现命令：lpushall key value 把多个value值存入到list集合中
     * @param key
     * @param value
     * @return
     */
    public long lpushAll(String key,Object... value){
        return redisTemplate.opsForList().leftPushAll(key,value);
    }

    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。
     * count>0:删除等于从左到右移动得值的第一个元素
     * count<0:删除等于从右到左移动的值的第一个元素
     * conunt = 0：删除等于value的所有元素
     * @param key
     * @param count
     * @param value
     * @return
     */
    public long lremove(String key,long count,Object value)
    {
        return redisTemplate.opsForList().remove(key,0,value);
    }

    /**
     * 实现命令：lpop key，移除返回列表key的头元素
     * @param key
     * @return
     */
    public Object lpop(String key)
    {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 实现命令：rpush key value，将一个值value插入到列表key的表尾（最右边）
     * @param key
     * @param value
     * @return
     */
    public long rpush(String key,Object value)
    {
        return redisTemplate.opsForList().rightPush(key,value);
    }

    /**
     * 实现命令：range key start stop，将列表key中的值下表从start到stop取出
     * @param key
     * @param start
     * @param stop
     * @return
     */
    public Object range(String key,long start,long stop)
    {
        return redisTemplate.opsForList().range(key,start,stop);
    }

    /**
     * 实现命令：llen key，列表中key的长度
     * @param key
     * @return
     */
    public long llen(String key)
    {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 任务队列
     * 命令实现rpoplpush sourceKey destinationKey,移除列表的最后一个元素
     * ，并将该元素添加到另一个列表并返回
     * @param sourceKey
     * @param destinationKey
     */
    public void rpoplush(String sourceKey,String destinationKey)
    {
        redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,destinationKey);
    }

}

