public class LinkedList<T extends Comparable<T>> {
    private class LinkedListNode{
        T value;
        LinkedListNode next;
        private LinkedListNode(T value){
            this.value = value;
            this.next = null;
        }
        private LinkedListNode(T value,LinkedListNode next){
            this.value = value;
            this.next=next;
        }
        private T getValue(){
            return value;
        }
        private LinkedListNode getNext(){
            return next;
        }
        private void setNext(LinkedListNode next){
            this.next = next;
        }
    }


    private LinkedListNode head;
    private int size; // I prefer to track my size with a member variable

    public LinkedList(){
        head=null;
        size=0;
    }
    public synchronized int size(){
        return size;
    }

    public synchronized int add(T value){
        int index=0;
        if(head==null){
            head= new LinkedListNode(value);
        }else{
            index++;
            LinkedListNode current = head;
            while(current.getNext()!=null){
                current=current.getNext();
                index++;
            }
            current.setNext(new LinkedListNode(value));
        }
        size++;
        return index;
    }
    public synchronized int insert(T value,int index){
        size++;
        if(index==0){
            head = new LinkedListNode(value,head);
            return 0;
        }else{
            int count=1;
            LinkedListNode current = head;
            while(count<index && count<size){
                current = current.getNext();
                count++;
            }
            current.setNext(new LinkedListNode(value,current.getNext()));
            return count;
        }

    }
    public synchronized T get(int index){
        if(index<0 || index>=size){
            return null;
        }
        int count=0;
        LinkedListNode current = head;
        while(count<index && count<size-1){ //redundancy to return last item in list if an int greater than the end index is passed
            current = current.getNext();
            count++;
        }
        return current.getValue();
    }
    public synchronized String printAllValues(){
        String rtnStg = "";
        LinkedListNode current = head;
        while(current.getNext()!=null){
            rtnStg += current.getValue().toString()+", ";
            current = current.getNext();
        }
        rtnStg+=current.getValue().toString(); // This will keep the last element in the string from having a comma
        return rtnStg;

    }
    public synchronized void sort(){
        LinkedListNode current = head;
        boolean isAlreadySorted=true;
        if(current.getValue().compareTo(current.getNext().getValue())==1){
            isAlreadySorted=false;
            LinkedListNode temp=new LinkedListNode(null,current);
            LinkedListNode temp2 = new LinkedListNode(null,current.getNext().getNext());
            head=current.getNext();
            head.setNext(temp.getNext());
            head.getNext().setNext(temp2.getNext());
        }
        while(current.getNext()!=null){

            if(current.getNext().getNext()!=null && current.getNext().getValue().compareTo(current.getNext().getNext().getValue())==1){
                isAlreadySorted=false;
                LinkedListNode temp = new LinkedListNode(null,current.getNext());
                LinkedListNode temp2 = new LinkedListNode(null,current.getNext().getNext().getNext());
                current.setNext(current.getNext().getNext());
                current.getNext().setNext(temp.getNext());
                current.getNext().getNext().setNext(temp2.getNext());

            }
            current=current.getNext();
        }
        if(!isAlreadySorted){ // Recursive nature, also to make sure the list is fully sorted before moving on.
            sort();
        }
    }
    public synchronized int sortedAdd(T value){
        sort();
        size++;
        LinkedListNode current = head;
        if(value.compareTo(head.getValue())==-1 || value.compareTo(head.getValue())==0){
            head=new LinkedListNode(value,current);
            return 0;
        }
        int index=1;
        while(current.getNext()!=null){
            if(current.getValue().compareTo(value)==-1 && (current.getNext().getValue().compareTo(value)==0 || current.getNext().getValue().compareTo(value)==1)){
                current.setNext(new LinkedListNode(value,current.getNext()));
                return index;
            }
            index++;
            current=current.getNext();
        }
        current.setNext(new LinkedListNode(value,null));
        return index;

    }
    public synchronized boolean sortedSearch(T value){
        LinkedListNode current=head;
        if(current.getValue().compareTo(value)==0){
            return true;
        }
        while(current.getNext()!=null){
            if(current.getNext().getValue().compareTo(value)==0){
                return true;
            }
            current=current.getNext();
        }
        return false;
    }

}
