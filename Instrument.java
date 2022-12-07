public interface Instrument {
    /**
     * Initializes the sample frequency buffer (represented by a LinkedList<Double>) 
     * with random numbers in a range of [-0.5; 0.5] for the string that the user wants 
     * to pluck. This methods does NOT change the size of the LinkedList<Double> that
     * was previously established inside of the constructor. It rather randomly changes
     * the values inside of the LinkedList<Double> to the new ones in the range of 
     * [-0.5; 0.5].
     * 
     * @param key Defines the key (on the keyboard) pressed by the user that YOU 
     *            interpret as an index of the specific LinkedList<Double> inside
     *            of your guitarStrings variable. The easiest way to process it is 
     *            to take this "key" and apply modulus 37 (key % 37) operation on it. 
     */
    public void pluck(char key);

    /**
     * Provides a time simulation that advances the simulation one step forward
     * according to the Karplus-Strong update algorithm that:
     * 
     * 1. Takes the average of the <b>first two</b> frequency samples for every
     *    string. In other words, you iterate through guitarStrings, 
     *    take the first two elements from the LinkedList<Double> of each string,
     *    and average those two elements. 
     * 2. Multiplies that average by the DECAY_FACTOR set to, e.g., 0.994.
     * 3. Adds the result to the end of the buffer. 
     * 4. Removes one sample frequency at the front of the buffer (LinkedList<Double>).
     * 5. Repeats for all guitarStrings.
     * 
     * Note that the DECAY_FACTOR can be changed but be careful with how much you
     * tweak that number :)
     */
    public void tick();

    /**
     * Returns the sum of the values from the front of the buffer of every available
     * string. In other words, you iterate through guitarStrings, get the first value
     * of each string from the corresponding LinkedList<Double>, and sum up all those
     * values.
     * 
     * @return the sum of the values from the front of the buffers.
     */
    public double superposition();
}
