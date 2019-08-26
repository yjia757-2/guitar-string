// Yiran Jia
// 4/12/18
// CSE143
// TA: JASON WAATAJA 
// Assignment 2: Guitar Hero - GuitarString 
//
// This program generates a ring buffer that simulates a vibrating guiter string. 

import java.util.*;
import java.util.Random;
public class GuitarString {
   private Queue<Double> ringBuffer;
   private Random randy;
   private double randomNum;
   
   public static final double DEFAULT_K_FACTOR = 0.996; //constant Karplus-Strong factor 
      
   // pre: frequency is larger than 0 and ring buffer stores at least two elements
   // (throws IllegalArgumentException if not)
   // post: construct a guiter string with the given frequency
   public GuitarString(double frequency) {
      ringBuffer = new LinkedList<Double>();
      for (int i = 0; i < (int) Math.round(StdAudio.SAMPLE_RATE/frequency); i++) {
         ringBuffer.add(0.0); 
      }
      if (frequency <= 0 || ringBuffer.size() < 2) {
         throw new IllegalArgumentException(); 
      }
   }
   
   // pre: the information stores at least two elements (throws IllegalArgumentException if not)
   // post: construct a guitar string and fill ring buffer with the values from the array
   public GuitarString(double[] init) {
      ringBuffer = new LinkedList<Double>();
      if (init.length < 2) {
         throw new IllegalArgumentException(); 
      }
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   } 
             
   // post: replace the elements of ring buffer by random numbers
   public void pluck() {
      Random randy = new Random();
      for (int i = 0; i < ringBuffer.size(); i++) {
         ringBuffer.remove();
         ringBuffer.add(-0.5 + randy.nextDouble());
      }
   }

   // post: take the average of the first two values from ring buffer and times Karplus-Strong factor
   // move the result to the back of the ring buffer and remove the first element in the ring buffer
   public void tic() {
      double lastNum = 0.5*(ringBuffer.remove() + ringBuffer.peek())*DEFAULT_K_FACTOR;
      ringBuffer.add(lastNum); 
   }   
   
   // post: return the current first element in the ring buffer
   public double sample() {
      return ringBuffer.peek();
   }
} 