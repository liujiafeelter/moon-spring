package com.moon.store.utils;

/**
 * 雪花算法
 */
public class SnowFlake {

    /**
     * 起始的时间戳
     */
    private long twepoch = 1585644268888L;

    /**
     * 每一部分占用的位数
     */
    private long workerIdBits = 5L;     //5位的机器id
    private long datacenterIdBits = 5L; //5位的机房id
    private long sequenceBits = 12L;    //每毫秒内产生的id数 2 的 12次方

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
    private long lastTimestamp = -1L;//记录产生时间毫秒数，判断是否是同1毫秒
    private long sequence = 0L;      //代表一毫秒内生成的多个id的最新序号

    public SnowFlake(long workerId, long datacenterId) {
        // 检查机房id和机器id是否超过31 不能小于0
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
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
        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return  ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
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


    public static void main(String[] args) {
        /*SnowFlake snowFlake = new SnowFlake(1, 1);
        for(int i=0; i<100; i++){
            System.out.println(snowFlake.nextId());
        }*/
        System.out.println(System.currentTimeMillis());
    }


}
