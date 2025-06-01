package ex02;

class SumThread extends Thread {
    private final int[] array;
    private final int start;
    private final int end;
    private final int threadNumber;
    private int partialSum;

    public SumThread(int[] array, int start, int end, int threadNumber) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.threadNumber = threadNumber;
        this.partialSum = 0;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            partialSum += array[i];
        }
        System.out.println("Thread " + threadNumber + ": from " + start + " to " + (end - 1) + " sum is " + partialSum);
    }

    public int getPartialSum() {
        return partialSum;
    }
}