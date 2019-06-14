import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
    private class node {
        Item content;
        node next;
        node pre;
    }

    private node first;
    private node last;
    private int count;

    public Deque ()
    {   first = null;
        last = null;
        count = 0;
    }                           // construct an empty deque

    public boolean isEmpty()
    { return first == null; }                           // is the deque empty?

    public int size()
    { return count; }                            // return the number of items on the deque

    public void addFirst(Item item)
    {   if(item == null)
        throw new java.lang.IllegalArgumentException();
        node oldFirst = first;
        first = new node();
        first.content = item;
        first.next = oldFirst;
        if (count > 0) oldFirst.pre = first;
        else last = first;
        count++;
    }                                        // add the item to the front


    public void addLast(Item item){
     if(item == null)
     {throw new java.lang.IllegalArgumentException();}
        node oldLast = last;
        last = new node();
        last.content = item;
        last.pre = oldLast;
        if (count > 0) oldLast.next = last;
        else first = last;
        count++;
    }                                        // add the item to the end

    public Item removeFirst() {
        if(isEmpty())
            throw new java.util.NoSuchElementException();
        Item content = first.content;
        if(count == 1) {
            last = null;
            first = null;}
        else {
            first = first.next;
            first.pre = null; }
        count--;
        return content;
    }                                         // remove and return the item from the front

    public Item removeLast() {
        if(isEmpty())
            throw new java.util.NoSuchElementException();
        Item content = last.content;
        if(count == 1){
            first = null;
            last = null;}
        else {
        last = last.pre;
        last.next = null;}
        count--;
        return content;
    }                                        // remove and return the item from the end

    public Iterator<Item> iterator()
    { return new dqIterator(); }                                               // return an iterator over items in order from front to end

    private class dqIterator implements Iterator<Item>
    {
        private node current = first;
        public boolean hasNext() { return current != null; }

        public void remove()
        { throw new java.lang.UnsupportedOperationException(); }

        public Item next()
        { if(current == null) throw new java.util.NoSuchElementException();
            Item value = current.content;
            current = current.next;
            return value;
        }
    }


     public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        dq.addFirst("Queen");
        dq.addFirst("We are the champion");
        dq.addLast("My friend");
        dq.addLast("Cool");
        StdOut.println("The Deque is:");
         for (String s : dq) { StdOut.println(s);}
        StdOut.println("Removing the first--->" + dq.removeFirst());
        StdOut.println("Removing the last---->" + dq.removeLast());
        StdOut.println("After removal, the size is: " + dq.size());
         StdOut.println("After removal,the Deque is:");
         for (String s : dq) { StdOut.println(s);}

        while(!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (!s.equals("-")){
                dq.addLast(s);
                StdOut.println("the size of the deque is ---> "+ dq.size()); }
            else
            { StdOut.println("The removed item is------> "+ dq.removeLast());
            StdOut.println("the size of the deque is ---> "+ dq.size());}
        }
     }
}
