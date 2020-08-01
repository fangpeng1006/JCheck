package com.jcheck.link;

public class Link {
    private class Node{
        private String data;
        private Node next;
        public Node(){
            this(null);
        }
        public Node(String data){
            this.data = data;
        }

        //添加节点
        public void addNode(Node node){
            if(this.next == null){
                this.next = node;
            }else {
                this.next.addNode(node);
            }
        }
        //打印节点数据
        public void printNode(){
            System.out.println(this.data);
            if(this.next != null){
                this.next.printNode();
            }
        }

    }

    private Node root;

    //添加元素
    public void add(String data){
        if(data == null){
            return;
        }
        Node node = new Node(data);
        if(root == null){
            root = node;
        }else{
            root.addNode(node);
        }
    }
    //打印链表中的数据
    public void print(){
        if(this.root == null){
            return;
        }else{
            this.root.printNode();
        }

    }

}


class ListTest{
    public static void main(String[] args) {
        Link link = new Link();
        link.add("zhangsan");
        link.add("李四");
        link.print();
    }
}
