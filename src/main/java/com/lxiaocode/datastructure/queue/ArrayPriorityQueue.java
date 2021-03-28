package com.lxiaocode.datastructure.queue;

import java.util.ArrayList;

/**
 * 基于数组实现的优先队列
 *
 * @author lixiaofeng
 * @date 2021/3/28 下午10:37
 * @blog http://www.lxiaocode.com/
 */
public class ArrayPriorityQueue<T extends Comparable<T>> {

    private T[] queue;
    private int size;
    private final static int DEFAULT_CAPACITY = 10;

    public ArrayPriorityQueue(){
        this(DEFAULT_CAPACITY);
    }

    public ArrayPriorityQueue(int capacity){
        this.queue = (T[]) new Comparable[capacity];
        this.size = 0;
    }

    public int size(){ return this.size; }

    public boolean isEmpty(){ return this.size == 0; }

    /**
     * 优先队列基本的 API：删除最大元素、插入新元素
     * 基于数组实现的优先队列有两种实现方法：
     * 1. 基于无序数组
     *      特点：使用无序数组实现是一种惰性方法，我们仅在需要找出最大元素的时候采取对应的行动。
     *      插入元素： 1
     *      删除最大元素： Ｎ
     *      实现思路：
     *          insert 方法将元素放入队列的最后的位置。
     *          delMax 方法使用类似选择排序的方式找到最大元素，并与最后队列最后一个元素交换，返回最后一个元素。
     * 2. 基于有序数组
     *      特点：使用有序数组实现是一种积极方法，我们会在插入元素时就保持元素有序。
     *      插入元素： N
     *      删除最大元素： 1
     *      实现思路：
     *          insert 方法使用类似插入排序的方式插入元素（升序）。
     *          delMax 方法返回队列最后一个元素。
     */
//    // 基于无序数组的实现
//    public void insert(T item){
//        if (this.size == this.queue.length){
//            // TODO 扩容
//        }
//        this.queue[this.size++] = item;
//    }
//
//    public T delMax(){
//        int max = 0;
//        for(int i = 1; i < this.size; i++){
//            if (less(this.queue[max], this.queue[i])) max = i;
//        }
//        exchange(max, this.size - 1);
//        T item = this.queue[--this.size];
//        this.queue[this.size] = null; // 垃圾回收
//        return item;
//    }

    // 基于有序数组的实现
    public void insert(T item){
        if (this.size == this.queue.length){
            // TODO 扩容
        }
        this.queue[this.size++] = item;
        for (int i = this.size - 1; i > 0 && less(this.queue[i], this.queue[i - 1]); i--){
            exchange(i, i - 1);
        }
    }

    public T delMax(){
        T item = this.queue[--this.size];
        this.queue[this.size] = null; // 垃圾回收
        return item;
    }

    private boolean less(T a, T b){ return a.compareTo(b) < 0; }

    private void exchange(int i, int j){
        T temp = this.queue[i];
        this.queue[i] = this.queue[j];
        this.queue[j] = temp;
    }

    public static void main(String[] args) {
        // 测试用例
        int[] array = {1,5,3,6,9,7,4,2,8,0};
        ArrayPriorityQueue<Integer> priorityQueue = new ArrayPriorityQueue<Integer>(10);
        for (int j : array) priorityQueue.insert(j);
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.delMax());
        }
    }
}
