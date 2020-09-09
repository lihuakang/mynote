package com.dl.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SeafCache {
    //map模拟读写操作
    private final Map<String,Object> cache=new HashMap<String,Object>();
    private final ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();
    private final Lock readLock=rwLock.readLock();//读锁
    private final Lock writeLock=rwLock.writeLock();//写锁
    //在读数据库的时候加读锁
    public Object get(String key){
        readLock.lock();
        try {
            return cache.get(key);
        }finally {
            readLock.unlock();
        }
    }
    //在写数据库的时候加写锁
    public Object put(String key,Object value){
        writeLock.lock();
        try {
            return cache.put(key, value);
        }finally {
            writeLock.unlock();
        }
    }
}
