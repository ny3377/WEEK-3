import java.util.Stack;
public class QueueUsingStack {
    static class MyQueue<T>{
        private Stack<T> stackIn;
        private Stack<T> stackOut;

        public MyQueue(){
            stackIn = new Stack<>();
            stackOut = new Stack<>();
        }

        public void enqueue(T item){
            stackIn.push(item);
        }

        public T dequeue(){
            if(isEmpty()){
                throw new RuntimeException("Queue is Empty");
            }
            shiftStacks();
            return stackOut.pop();
        }

        public T peek(){
            if (isEmpty()) {
                throw new RuntimeException("Queue is Empty");
            }
            shiftStacks();
            return stackOut.peek();
        }

        public boolean isEmpty(){
            return stackIn.isEmpty() && stackOut.isEmpty();
        }

        public int size(){
            return stackIn.size() + stackOut.size();
        }
        private void shiftStacks(){
            if(stackOut.isEmpty()){
                while (!stackIn.isEmpty()){
                    stackOut.push(stackIn.pop());
                }
            }
        }
    }

    public static void main(String[] args){
        MyQueue<Integer> queue = new MyQueue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println("Dequeue: "+ queue.dequeue());
        System.out.println("Peek: "+ queue.peek());

        queue.enqueue(4);

        System.out.println("Dequeue: "+ queue.dequeue());
        System.out.println("Dequeue: "+ queue.dequeue());
        System.out.println("Dequeue: "+ queue.dequeue());

        System.out.println("Is Empty; "+ queue.isEmpty());
    }
}