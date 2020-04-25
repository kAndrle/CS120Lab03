public class Stack<T extends Comparable<T>> {
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
    }

    private DoubleLLNode head;
    private DoubleLLNode tail;

    public Stack(){
        head=null;
        tail=null;
    }

    public synchronized void push(T value){
        if(head==null){
            head=new DoubleLLNode(value);
            tail=head;
        }else{
            tail=new DoubleLLNode(value,null,tail);
        }
    }

    public synchronized T pop(){
        T retVal = tail.getValue();
        tail=tail.getPrev();
        return retVal;
    }

    public synchronized T top(){
        return tail.getValue();
    }
}
