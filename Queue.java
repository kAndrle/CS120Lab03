public class Queue<T extends Comparable<T>> {
    private class DoubleLLNode{
        T value;
        DoubleLLNode next;
        DoubleLLNode prev;
        private DoubleLLNode(T value){
            this.value = value;
            this.next = null;
            this.prev=null;
        }
        private DoubleLLNode(T value, DoubleLLNode next){
            this.value = value;
            this.next=next;
            this.prev=null;
        }
        private DoubleLLNode(T value, DoubleLLNode next, DoubleLLNode prev){
            this.value=value;
            this.next=next;
            this.prev=prev;
        }
        private T getValue(){
            return value;
        }
        private DoubleLLNode getNext(){
            return next;
        }
        private void setNext(DoubleLLNode next){
            this.next = next;
        }
        private DoubleLLNode getPrev(){
            return prev;
        }
        private void setPrev(DoubleLLNode prev){
            this.prev = prev;
        }
    }

    private DoubleLLNode head;
    private DoubleLLNode tail;

    public Queue(){
        head=null;
        tail=null;
    }

    public synchronized int size(){
        if(tail==null){
            return 0;
        }
        int retVal = 1;
        DoubleLLNode current = head;
        while(current.getNext()!=null){
            retVal++;
            current = current.getNext();
        }
        return retVal;
    }

    public synchronized void push(T value){
        if(head==null){
            head = new DoubleLLNode(value);
            tail = head;
        }else {
            head.setPrev(new DoubleLLNode(value, head, null));
            head = head.getPrev();
        }
    }

    public synchronized T pop(){
        T retVal = tail.getValue();
        tail=tail.getPrev();
        if(tail==null){
            head=null;
        }
        return retVal;
    }

    public synchronized T top(){
        return tail.getValue();
    }
}
