import java.util.*;

public class SuperCoolGuitar implements Instrument {
    private LinkedList<LinkedList<Double>> guitarStrings;

    Random random = new Random();
    final double CONCERT_A = 440.0;
    final double DECAY_FACTOR = 0.994;
    final int strings = 37;
    SuperCoolGuitar() {
        guitarStrings = new LinkedList<>();
        for (int i = 0; i < strings; i++) {
            double frequency = CONCERT_A * Math.pow(2.0, (i - 24) / 12.0);
            int size = (int)(44100 / frequency);
            LinkedList<Double> tempLinkedList = new LinkedList<>();
            for (int j = 0; j < size; j++) {
                tempLinkedList.add(0.0);
            }
            guitarStrings.add(tempLinkedList);
        }
    }
        
    @Override
    public void pluck(char key) {
        double frequency = CONCERT_A * Math.pow(2.0, ((key % 37) - 24) / 12.0);
        int size = (int)(44100.0 / frequency);
        for (int i = 0; i < size; i++) {
            double rand = -.5 + random.nextDouble(); //smallest is 1 * -.5, can randomly generate 0-1.0, should work, maybe *
            guitarStrings.get(key % 37).set(i, rand);
        }
    }

    @Override
    public void tick() {
        for (int i = 0; i < strings; i++) {
            LinkedList<Double> tempLinkedList = guitarStrings.get(i);
            double average = (DECAY_FACTOR * (tempLinkedList.get(0) + tempLinkedList.get(1))) / 2.0; //steps 1 and 2
            tempLinkedList.add(average); //step 3
            tempLinkedList.remove(); //step 4, it removes the head which is the original and keeps the newly added value
        }//step 5 repeats for all 37 strings        
    }

    @Override
    public double superposition() {
        double sum = 0;
        for (int i = 0; i < strings; i++) {
            LinkedList<Double> buffer = guitarStrings.get(i); //have to add this becuase the sum needs to accept a double
            sum += buffer.getFirst();
        }
        return sum;
    }
}
