package core;

import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        BoundedBlockingQueue boundedBlockingQueue = new BoundedBlockingQueue(2);
        new Thread(()->{
            try {
                for(int i = 0 ; i < 3 ; i++){
                    boundedBlockingQueue.enqueue(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        Thread.sleep(3000);

        new Thread(()->{
            try {
                for(;;){
                    System.out.println(boundedBlockingQueue.dequeue());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    LinkedList<Integer> queue;
 //   AtomicInteger ai =  new AtomicInteger(0);
    int capacity;
    ReentrantLock lock =  new ReentrantLock(true);
    Condition producer;
    Condition consumer;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;

        queue = new LinkedList<Integer>();
        lock =  new ReentrantLock(true);
        producer = lock.newCondition();
        consumer = lock.newCondition();

    }

    public void enqueue(int element) throws InterruptedException {

        try{
            lock.lock();
            while(queue.size() >= capacity){
                producer.await();;
            }
            queue.addFirst(element);
           // ai.incrementAndGet();
            consumer.signalAll();

        }finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        try{
            lock.lock();
            while(queue.size() == 0){
                consumer.await();
            }
            Integer last = queue.pollLast();
          //  ai.decrementAndGet();
            producer.signalAll();

            return last;
        }finally {
            lock.unlock();
        }
    }

    public int size() {
      return queue.size();
    }
}
