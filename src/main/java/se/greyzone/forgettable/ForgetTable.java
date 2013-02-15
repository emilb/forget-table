package se.greyzone.forgettable;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ForgetTable<T> {

    private Map<T, AtomicInteger> buckets;
    private AtomicLong totalInBuckets;
    private long lastDecay;
    private float rate;

    public ForgetTable(float rate) {
        this.rate = rate;

        buckets = new HashMap<>();
        totalInBuckets = new AtomicLong(0);
    }

    public void increment(T bucket) {
        increment(bucket, 1);
    }

    public void increment(T bucket, int value) {
        getCounterCreateIfMissing(bucket).addAndGet(value);
        totalInBuckets.addAndGet(value);
    }

    public void decrement(T bucket) {
        decrement(bucket, 1);
    }

    public void decrement(T bucket, int value) {
        if (buckets.containsKey(bucket)) {
            AtomicInteger counter = buckets.get(bucket);

            totalInBuckets.addAndGet(-value);
        }
    }

    public int getValue(T bucket) {
        if (!buckets.containsKey(bucket)) {
            return 0;
        }
        return buckets.get(bucket).get();
    }

    public long getTotalInBuckets() {
        return totalInBuckets.longValue();
    }

    private AtomicInteger getCounterCreateIfMissing(T bucket) {
        if (!buckets.containsKey(bucket)) {
            AtomicInteger counter = new AtomicInteger(0);
            buckets.put(bucket, counter);
            return counter;
        }

        return buckets.get(bucket);
    }

}
