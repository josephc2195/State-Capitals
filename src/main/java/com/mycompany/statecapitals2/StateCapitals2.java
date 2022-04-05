/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.statecapitals2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * This program reads from a text file, adds text to a hashmap.
 * User is prompted to play a game of guessing the capitals of a state using random numbers.
 * 
 * @author chica
 */
public class StateCapitals2 {

    public static void main(String[] args) throws IOException {
        Map<String, String> capitals = new HashMap<>();//creating hashmap for the capitals
        
        //scanner is used to read from a file
        Scanner sc = new Scanner(new BufferedReader(new FileReader("Capitals.txt")));
        
        //grab the next line from file, split the state and capital into an array, then add the 2 elements from array to the hashmap
        while(sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            String[] both = currentLine.split("::");
            //index 0 should always hold the state, index 1 should hold the capital
            capitals.put(both[0], both[1]);
        }
        
        System.out.println("The size of my map: " + capitals.size());
        
        //create a set based off the Key's in the Capitals hashmap
        Set<String> states = capitals.keySet();
        
        
        //loop thru the set created in the previous line of code
        System.out.println("List of states:");
        for(String s : states) {
            System.out.println(s);
        }   
        
        //scanner used to read input from user
        Scanner read = new Scanner(System.in);
        int games = 0; //will be used to save the input from user
        boolean valid = false; //for data validation
        do { 
            try {
                System.out.print("How many states would you like to guess? ");
                games = read.nextInt();
                if(games >= 0 && games <= 50) { //as long as the number is not negative the loop will end
                    valid = true;
                }
            } catch(InputMismatchException e) {
                System.out.println("Please try again and type a whole integer");
                read.next();
            }
        } while(!valid);
        
        //list of state index's that have been used in playing the game below
        List<Integer> removedStates = new ArrayList<>();
        for(int i = 0; i < games; i++) { 
            //startGame returns the index of the state in the states Set
            removedStates.add(startGame(states, capitals, removedStates));
        }
        sc.close();
    }
    
    
    /**
     * Randomly generates a number, checks if the number has been used before
     * Runs a loop until a new number is selected. 
     * 
     * @param states    a set of the 50 states
     * @param capitals  a map of the 50 states along with their capitals
     * @param removed   a list of indices of states that have been used
     * @return          the index of the state randomly chosen
     */
    public static int startGame(Set<String> states, Map<String, String> capitals, List<Integer> removed) {
        //randomly selecting a state
        Random rand = new Random();
        int stateIndex = rand.nextInt(states.size()); 
        while(removed.contains(stateIndex)) {//if the removed list already has that index it will not pass this while loop
            stateIndex = rand.nextInt(states.size());
        }
        gamePrompts(states, capitals, stateIndex);//the next method for the game
        return stateIndex;   
    }
    
    /**
     * Uses a random number to prompt user to input capital of selected state
     * Compares user input to the correct capital taken from capitals hashMap
     * User then gets prompted if they were correct or not
     * 
     * @param states    A set of 50 states
     * @param capitals  A map of 50 states along with their capitals
     * @param stateIndex index of the randomly chosen state from the previous method
     */
    public static void gamePrompts(Set<String> states, Map<String, String> capitals, int stateIndex) {
        int count = 0; //count is used as an index counter
        String userAns = "";
        Scanner in = new Scanner(System.in);
            for(String s : states) {//advanced looping thru the states set
                if(count == stateIndex) {//once the index is equal to the random number it prints this out: 
                    //used to get the answer from user
                    System.out.println("\n********************\n");
                    System.out.println("Do you know the capital of " + s + "?");//s is the randomly selected state here
                    userAns = in.nextLine();
                    
                    String ans = capitals.get(s); //this is the actual correct answer
                    if(userAns.equalsIgnoreCase(ans)) {//compares the answer given by user to hashmap capital value
                        System.out.println("Correct!");
                    }
                    else {
                        System.out.println("Incorrect! The correct answer is: " + ans);
                    }
                }
                count++;
            }
    }
}