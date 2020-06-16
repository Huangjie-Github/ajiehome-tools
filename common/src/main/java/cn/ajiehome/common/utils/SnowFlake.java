package cn.ajiehome.common.utils;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * time + dataCenterId + workerId  + sequence;
 * 41        5             5            12
 * @Author: HuangJie
 * @Date: 2020/6/16
 */
@Component
public class SnowFlake {
    @Value("${app.worker.id}")
    private long workerId;
    @Value("${app.data.center.id}")
    private long dataCenterId;
    private long sequence = 0L;

    private static final long WORKER_ID_BITS = 5L;
    private static final long DATA_CENTER_ID_BITS = 5L;
    private static final long SEQUENCE_BITS = 12L;
    /**
     * 初始状态时间  2010-04-42 09:42:54
     */
    private static final long INITIAL_TIME = 1288834974657L;
    private long maxWorkerId = ~(-1L << WORKER_ID_BITS);
    private long maxDataCenterId = ~(-1L << DATA_CENTER_ID_BITS);
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    private long lastTimestamp = -1L;

    public synchronized long nextId(){
        long timestamp = timeGen();

        if (timestamp < lastTimestamp){
            throw new ApplicationException(CodeType.TIME_POINTER_BACK);
        }
        if (timestamp == lastTimestamp){
            sequence = (sequence+1) & MAX_SEQUENCE;
            if (sequence == 0L){
                timestamp = tilNextMillis(lastTimestamp);
            }
        }else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return (timestamp - INITIAL_TIME) << TIMESTAMP_SHIFT
                | dataCenterId << DATA_CENTER_ID_SHIFT
                | workerId << WORKER_ID_SHIFT
                | sequence;
    }
    private long tilNextMillis(long lastTimestamp){
        long timestamp = timeGen();
        while (timestamp<=lastTimestamp){
            timestamp = timeGen();
        }
        return timestamp;
    }
    private long timeGen(){
        return System.currentTimeMillis();
    }
}
