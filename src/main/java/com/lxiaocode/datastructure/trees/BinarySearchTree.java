package com.lxiaocode.datastructure.trees;

/**
 * 二叉搜索树
 *
 * @author lixiaofeng
 * @date 2021/4/1 上午9:47
 * @blog http://www.lxiaocode.com/
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;

    public int size(){
        return size(root);
    }
    private int size(Node node){
        return node != null ? node.count : 0;
    }

    /**
     * 查找、插入操作
     */
    public Value get(Key key){
        return get(root, key);
    }
    private Value get(Node node, Key key){
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.value;
    }

    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null) return new Node(key, value, 1);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;

        node.count = node.left.count + node.right.count + 1;
        return node;
    }

    /**
     * 有序性相关操作
     */
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null) return node;
        return min(node.left);
    }

    public Key max(){
        return max(root).key;
    }
    private Node max(Node node){
        if (node.right == null) return node;
        return max(node.right);
    }

    public Key floor(Key key){
        Node node = floor(root, key);
        if (node == null) return null;
        else return node.key;
    }
    private Node floor(Node node, Key key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        else if (cmp < 0) return floor(node.left, key);
        else {
            // cmp > 0
            Node tmp = floor(node.right, key);
            if (tmp == null) return node;
            return tmp;
        }
    }

    private class Node{
        // 键、值、左链接（小于该结点的子树）、右链接（大于该结点的子树）、结点计数器
        private final Key key;
        private Value value;
        private Node left, right;
        private int count;

        public Node(Key key, Value value, int count){
            this.key = key;
            this.value = value;
            this.count = count;
        }
    }
}
