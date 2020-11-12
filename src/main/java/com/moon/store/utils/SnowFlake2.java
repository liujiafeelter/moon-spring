package com.moon.store.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 雪花算法
 */
public class SnowFlake2 {

    /**
     * 起始的时间戳
     */
    private long twepoch = 1585644268888L;

    /**
     * 每一部分占用的位数
     */
    private long workerIdBits = 5L;     //5位的机器id
    private long datacenterIdBits = 5L; //5位的机房id
    private long sequenceBits = 12L;    //12位的序列号

    /**
     * 每一部分的最大值
     */
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 每一部分向左的位移
     */
    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private long workerId;           //机器ID
    private long datacenterId;       //机房ID
    private long standbyDatacenterId;//备用的数据中心ID(0~31)，当时钟回拨时，为了不抛异常，启用备用ID
    private long lastTimestamp = -1L;//记录产生时间毫秒数，判断是否是同1毫秒
    private long sequence = 0L;      //代表一毫秒内生成的多个id的最新序号
    private boolean isTimestampBack = false;//是否时钟回拨

    public SnowFlake2(long workerId, long datacenterId, long standbyDatacenterId) {
        // 检查机房id和机器id是否超过31 不能小于0
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        if (standbyDatacenterId > maxDatacenterId || standbyDatacenterId < 0) {
            throw new IllegalArgumentException(String.format("standby datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        if(datacenterId == standbyDatacenterId){
            throw new IllegalArgumentException("datacenter Id can't equal to standby datacenter Id.");
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.standbyDatacenterId = standbyDatacenterId;
    }

    public synchronized long nextId() {
        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过，这时启用备用的datacenterId
        if (timestamp < lastTimestamp) {
            isTimestampBack = true;
        } else {
            isTimestampBack = false;
        }

        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
        // 这个时候就得把seqence序号给递增1，最多就是4096
        if (timestamp == lastTimestamp) {
            //这个位运算保证始终就是在4096这个范围内
            sequence = (sequence + 1) & sequenceMask;
            //当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;

        //要使用的datacenterId
        long datacenterIdToUse = isTimestampBack ? standbyDatacenterId : datacenterId;

        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return  ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterIdToUse << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    /**
     * 当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    //获取当前时间戳
    private long timeGen(){
        return System.currentTimeMillis();
    }

    /**
     * 获取id的创建时间
     * @param id
     * @return
     */
    public String idCreateTime(long id){
        String idBit = Long.toBinaryString(id);
        int timeLength = idBit.length() - (int)timestampLeftShift;
        String timeBit = idBit.substring(0, timeLength);
        long time = Long.parseUnsignedLong(timeBit, 2);

        long createTime = time + twepoch;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(createTime));
    }


    public static void main(String[] args) {
        SnowFlake2 snowFlake = new SnowFlake2(1, 1, 1);
        long id = snowFlake.nextId();
        System.out.println(id);
        System.out.println(snowFlake.idCreateTime(id));
    }


}
