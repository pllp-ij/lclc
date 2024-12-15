class CircularQueue {
    int capacity;
    public int[] data;
    int head;
    int tail;
    int length;
    
    public CircularQueue(int k) {
        capacity = k + 1;
        data = new int[capacity];
        head = 0;
        tail = 0;
        length = 0;
    }
    
    public boolean enQueue(int value) {
        // if full, return false
        if (isFull()) {
            return false;
        }
        data[tail] = value;
        tail = (tail + 1) % capacity;
        length++;
        return true;
    }
    
    public boolean deQueue() {
        // if empty, return false
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        length--;
        return true;
    }
    
    public int getFront() {
        // if empty, return -1
        if (isEmpty()) {
            return -1;
        }
        return data[head];
    }
    
    public int getTail() {
        // if empty, return -1
        if (isEmpty()) {
            return -1;
        }
        return data[(tail - 1 + capacity) % capacity];
    }
    
    public boolean isEmpty() {
        return head == tail;
    }
    
    public boolean isFull() {
        return ((tail + 1) % capacity) == head;
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("HEAD |  ");
        int idx = head;
        while (idx != tail) {
            str.append(data[idx] + "  ");
            idx = (idx + 1) % capacity;
        }
        str.append("| TAIL");
        return str.toString();
    }
}