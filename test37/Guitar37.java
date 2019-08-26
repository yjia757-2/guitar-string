// Yiran Jia
// 4/9/18
// CSE143
// TA: JASON WAATAJA 
// Assignment 2: Guitar Hero - Guitar37
//
// This program builds a musical instrument with 37 strings. Users can play sounds by either 
// typing notes on the keybord or doing different pitches to pluck the strings. It can get the 
// current sound sample from all 37 strings, and the times of tic is recored for everytime it tic. 

public class Guitar37 implements Guitar {
   private GuitarString[] allString;
   private int time;

   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
         
   // post: construct a guitar of 37 strings with different frequencies. 
   public Guitar37() {
      time = 0;
      allString = new GuitarString[KEYBOARD.length()];
      for (int i = 0; i < KEYBOARD.length(); i++) {
         allString[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24.0)/12.0));
      }
   }

   // pre: pitch is within the boundary (ignore the pitches that out of range)
   // post: playing a note by passing the given pitch  
   public void playNote(int pitch) {
      if (pitch >= -24 && pitch < KEYBOARD.length() - 24) {
         allString[pitch + 24].pluck(); 
      } 
   }
 
   // post: if this key has a corresponding guitar string
   public boolean hasString(char key) {
      return KEYBOARD.indexOf(key) != -1;
   }
    
   // pre: the key has a corresponding guitar string (throw an IllegalArgumentException if not)
   // post: pluck the string to play by passing the given key
   public void pluck(char key) {
      if (!hasString(key)) {
         throw new IllegalArgumentException();
      } else {
         allString[KEYBOARD.indexOf(key)].pluck();
      }    
   }

   // post: get all of the samples from the strings of the guitar
   public double sample() {
      double sum = 0.0;
      for (int i = 0; i < KEYBOARD.length(); i++) {
         sum += allString[i].sample();
      }
      return sum;
   }
    
   // post: make all strings of the guitar tic
   public void tic() {
      for (int i = 0; i < KEYBOARD.length(); i++) {
         allString[i].tic();
      }
      time++;
   }
    
   // post: return the number of times the user make all strings of the guitar tic
   public int time() {
      return time;
   }

}