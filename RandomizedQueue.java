import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
        private int N = 0;
        private Item[] q;

        public RandomizedQueue() {
           q = (Item[]) new Object[1];
        }                 // construct an empty randomized queue

        public boolean isEmpty() {
            return N == 0;
        }                 // is the randomized queue empty?

        public int size() {
            return N;
        }                        // return the number of items on the randomized queue

        public void enqueue(Item item) {
            if (item == null)
                throw new java.lang.IllegalArgumentException();
            q[N++] = item;
            if (N == q.length)
                resize(2 * q.length);

        }          // add the item

        public Item dequeue() {
            if (isEmpty())
                throw new java.util.NoSuchElementException();
            int point = StdRandom.uniform(N);
            Item dequeueItem = q[point];
            swap(point, N - 1);
            q[N - 1] = null;
            N--;
            if (N > 0 && N == q.length / 4)
                resize(q.length / 2);
            return dequeueItem;
        }                                       // remove and return a random item

        private void swap(int i, int j) {
            Item temp;
            temp = q[i];
            q[i] = q[j];
            q[j] = temp;
        }

        public Item sample() {
            if (isEmpty())
                throw new java.util.NoSuchElementException();
            int point = StdRandom.uniform(N);
            return q[point];
        }                       // return a random item (but do not remove it)

        private void resize(int capacity) {
            Item[] copy = (Item[]) new Object[capacity];
            for (int i = 0; i < N; i++)
                copy[i] = q[i];
            q = copy;
        }


        public Iterator<Item> iterator() {
            return new arrayIterator();
        }// return an independent iterator over items in random order

        private class arrayIterator implements Iterator<Item> {
            private int current = 0;
            private final int[] iter;

            public arrayIterator() {
                iter = new int [N];
                for (int k = 0; k < N; k++) {
                    iter[k] = k;
                }
                StdRandom.shuffle(iter);
            }

            public boolean hasNext() {
                return current < N;
            }

            public void remove() {
                throw new java.lang.UnsupportedOperationException();
            }

            public Item next() {
                if (!hasNext())
                   throw new NoSuchElementException();
                Item item = q[iter[current++]];
                return item;
            }
        }

        public static void main(String[] args) {
            RandomizedQueue<String> dq = new RandomizedQueue<String>();
            boolean hasNext;
            String next, itemDeleted;
            dq.enqueue("we are the champion");
            dq.enqueue("my friend");
            dq.enqueue("we'll keep on fighting");

            for (String s : dq) {
                StdOut.println(s);
            }
            hasNext = dq.iterator().hasNext();
            StdOut.println(hasNext);
            itemDeleted = dq.dequeue();
            StdOut.println(itemDeleted);

            while (!StdIn.isEmpty()) {
                String s = StdIn.readString();
                if (s.equals("-")) {
                    StdOut.println("The random deleted item is--->" + dq.dequeue());
                    StdOut.println("The size of the Queue is--->" + dq.size());
                } else {
                    dq.enqueue(s);
                }
                if (s.equals("=")) break;
            }
        }
    }
