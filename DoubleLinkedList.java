public class DoubleLinkedList<T extends Comparable<T>> {
    /*
    Mostly the same as LinkedList. Double Linked List's
    advantages really shine in the stack and queue classes.
    */

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
    private int size;

    public DoubleLinkedList(){
        head=null;
        size=0;
    }
    public synchronized int size(){
        return size;
    }
    public synchronized T get(int index){
        if(index<0 || index>=size){
            return null;
        }
        int count=0;
        DoubleLLNode current = head;
        while(count<index && count<size-1){ //redundancy to return last item in list if an int greater than the end index is passed
            current = current.getNext();
            count++;
        }
        return current.getValue();
    }
    public synchronized int add(T value){
        int index=0;
        if(head==null){
            head= new DoubleLLNode(value);
        }else{
            index++;
            DoubleLLNode current = head;
            while(current.getNext()!=null){
                current=current.getNext();
                index++;
            }
            current.setNext(new DoubleLLNode(value,null,current));
        }
        size++;
        return index;
    }
    public synchronized int insert(T value,int index){
        size++;
        if(index==0){
            head = new DoubleLLNode(value,head);
            return 0;
        }else{
            int count=1;
            DoubleLLNode current = head;
            while(count<index && count<size){
                current = current.getNext();
                count++;
            }
            current.setNext(new DoubleLLNode(value,current.getNext(),current));
            return count;
        }

    }
    public synchronized String printAllValues(){
        String rtnStg = "";
        DoubleLLNode current = head;
        while(current.getNext()!=null){
            rtnStg += current.getValue().toString()+", ";
            current = current.getNext();
        }
        rtnStg+=current.getValue().toString();
        return rtnStg;

    }

}
