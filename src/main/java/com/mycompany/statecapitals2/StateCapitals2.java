/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.statecapitals2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author chica
 */
public class StateCapitals2 {

    public static void main(String[] args) throws IOException {
        Map<String, String> capitals = new HashMap<>();//creating hashmap for the capitals
        String userAns;//used to get answer for game question later
        
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
        
        //randomly selecting a state
        Random rand = new Random();
        int stateIndex = rand.nextInt(states.size());
        int count = 0;//count is used as an index counter
        for(String s : states) {//advanced looping thru the states set
            if(count == stateIndex) {//once the index is equal to the random number it prints this out: 
                Scanner in = new Scanner(System.in);//used to get the answer from user
                System.out.println("Do you know the capital of " + s + "?");
                userAns = in.nextLine();
                if(userAns.equalsIgnoreCase(capitals.get(s))) {//compares the answer given by user to hash map capital
                    System.out.println("Correct!");
                }
                else {
                    System.out.println("Incorrect, the correct answer is " + capitals.get(s));
                }
                in.close();
            }
            
            count++;
        }        
        sc.close();
    }
}