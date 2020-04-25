public class Starter {
    public static void main(String args[]){
        /*
        args by index:
        0: Max size of the queue
        1: Number of producer threads
        2: Number of consumer threads
        3: Delay between item production
        4: Delay between item consumption


        main() will create a specified number of producer and consumer threads.
        producer threads will create random integers between 0 and 100 and push them into a shared queue.
        consumer threads will pop the first item from the queue and print its value to the console.
        If there are no elements in the queue, the consumer will print "null".
        If the queue is full, a producer will not add to it.
         */
        int[] intArgs = new int[args.length];
        for(int i=0;i<args.length;i++){
            intArgs[i] = Integer.parseInt(args[i]);
        }
        if(intArgs[1]<1){
            intArgs[1]=1;
        }
        if(intArgs[2]<1){
            intArgs[2]=1;
        }

        Queue q = new Queue();
        final int maxSize = intArgs[0];

        Thread[] producers = new Thread[intArgs[1]];
        for(Thread p: producers) {
            p = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        int data = (int) (Math.random() * 100);
                        if (q.size() < maxSize) {
                            q.push(data);
                        }
                        try {
                            Thread.sleep(intArgs[3]);
                        } catch (InterruptedException e) {

                        }
                    }
                }
            });
            p.start();
        }
        Thread[] consumers = new Thread[intArgs[2]];
        for(Thread c: consumers) {
            c = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (q.size() == 0) {
                            System.out.println("null");
                        } else {
                            System.out.println(q.pop());
                        }
                        try {
                            Thread.sleep(intArgs[4]);
                        } catch (InterruptedException e) {

                        }
                    }
                }
            });
            c.start();
        }

        while(true){
            try {
                Thread.sleep(50000);
            } catch(Exception e){
                // System.exit(-1);
            }
        }
    }
}
