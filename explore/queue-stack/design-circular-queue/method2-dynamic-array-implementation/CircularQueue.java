import java.util.ArrayList;
import java.util.List;

class CircularQueue {
    int capacity;
    public List<Integer> data;
    
    public CircularQueue(int k) {
        capacity = k;
        data = new ArrayList<>();
    }
    
    public int getFront() {
        // if empty, return -1
        if (isEmpty()) {
            return -1;
        }
        return data.get(0);
    }
    
    public int getTail() {
        // if empty, return -1
        if (isEmpty()) {
            return -1;
        }
        return data.get(data.size() - 1);
    }
    
    public boolean enQueue(int value) {
        // if full, return false
        if (isFull()) {
            return false;
        }
        data.add(value);
        return true;
    }
    
    public boolean deQueue() {
        // if empty, return false
        if (isEmpty()) {
            return false;
        }
        data.remove(0);
        return true;
    }
    
    public boolean isEmpty() {
        return data.size() == 0;
    }
    
    public boolean isFull() {
        return data.size() >= capacity;
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("HEAD |  ");
        for (int i = 0; i < data.size(); i++) {
            str.append(data.get(i) + "  ");
        }
        str.append("| TAIL");
        return str.toString();
    }
}