package com.example.InformationTheoryProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Test {
    public static double symbolCounter(Character uniqueSymbol, String wholeText){
        int counter = 0;

        for(int i = 0; i<wholeText.length();i++){
            if(uniqueSymbol==wholeText.charAt(i)){
                counter++;
            }
        }

        return counter;
    }

    public static void main(String[] args){
        String data="";
        System.out.println(System.getProperty("user.dir"));
        try {
            File myObj = new File(System.getProperty("user.dir")+"/src/main/java/files/Text.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(data);
        Set<Character> mySet = new HashSet<Character>();
        for(int i=0;i<data.length();i++){
            mySet.add(data.charAt(i));
        }
        ArrayList<Character> myArr = new ArrayList<>(mySet);
        System.out.println(myArr);

        Collections.sort(myArr);
        System.out.println(myArr);
        ArrayList<Double> probabilities = new ArrayList<>();
        for(int i=0;i<myArr.size();i++){
            double counter = symbolCounter(myArr.get(i),data);
            probabilities.add(counter/data.length());
        }

        System.out.println(probabilities);

    }
}
