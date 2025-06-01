package ex04;

import java.util.UUID;

class TransactionsLinkedList implements TransactionsList {
    int size = 0;
    private Node head;
    private Node tail;

    @Override
    public void add(Transaction data) {
        Node a = new Node(data);
        if (tail == null) {
            head = a;
        } else {
            tail.next = a;
        }
        tail = a;
        size++;
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] result = new Transaction[size];
        int i = 0;
        Node t = head;
        while (t != null) {
            result[i] = t.data;
            t = t.next;
            i++;
        }
        return result;
    }

    @Override
    public Transaction remove(UUID identifier) {
        if (head == null) throw new TransactionNotFoundException("list is empty! transaction not found!");
        if (head == tail) {
            if (head.data.getIdentifier().equals(identifier)) {
                Transaction result = head.data;
                head = null;
                tail = null;
                size--;
                return result;
            } else {
                throw new TransactionNotFoundException("user with a non-existent ID, transaction not found!");
            }
        }
        if (head.data.getIdentifier().equals(identifier)) {
            Transaction result = head.data;
            head = head.next;
            size--;
            return result;
        }
        Node t = head;
        while (t.next != null) {
            if (t.next.data.getIdentifier().equals(identifier)) {
                if (tail == t.next) {
                    tail = t;
                }
                Transaction result = t.next.data;
                t.next = t.next.next;
                size--;
                return result;
            }
            t = t.next;
        }
        throw new TransactionNotFoundException("user with a non-existent ID, transaction not found!");
    }

    @Override
    public String toString() {
        String result = "";
        Node t = head;
        while (t != null) {
            result += t.data + "\n";
            t = t.next;
        }
        return "List{" + "size=" + size + "}\n" + result;
    }

    private static class Node {
        Transaction data;
        Node next;

        public Node(Transaction data) {
            this.data = data;
            this.next = null;
        }
    }
}