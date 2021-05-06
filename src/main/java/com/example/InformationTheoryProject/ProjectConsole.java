package com.example.InformationTheoryProject;

import Entities.BinarySearchTree;
import Entities.SymbolProbability;
import Entities.SymbolProbability1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ProjectConsole {

    public static ArrayList<SymbolProbability> table = new ArrayList<>();

    public static String part1Input;
    public static String part1Result;

    public static String part2Input;
    public static String part2Input1;
    public static String part2Result;
    public static String part2Result1;

    public static String part3Input;
    public static String part3Input1;
    public static String part3Result;

    public static String part4Input;
    public static String part4Result;

    public static String part5Input;
    public static String part5Result;

    public static String part6Input;
    public static String part6Result;

    public static double getProbability(Character uniqueSymbol,String wholeText){
        double counter = 0;

        for(int i = 0; i<wholeText.length();i++){
            if(uniqueSymbol==wholeText.charAt(i)){
                counter++;
            }
        }

        return counter/wholeText.length();
    }

    public static ArrayList<Character> getUniqueChars(String data){

        Set<Character> mySet = new HashSet<Character>();
        for(int i=0;i<data.length();i++){
            mySet.add(data.charAt(i));
        }
        ArrayList<Character> myArr = new ArrayList<>(mySet);
        Collections.sort(myArr);

        return myArr;
    }

    public static String getText(){
        String data="";
        try {
            File myObj = new File(System.getProperty("user.dir")+"/src/main/java/files/Text.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return data;
    }

    public static ArrayList<SymbolProbability> part1(){

        part1Input=getText();
        ArrayList<Character> uniqueChars = getUniqueChars(getText());

        for(int i = 0; i < uniqueChars.size(); i++){
            double probability = getProbability(uniqueChars.get(i),getText());
            SymbolProbability sp = new SymbolProbability(String.valueOf(uniqueChars.get(i)), probability);

            table.add(sp);
        }

        Collections.sort(table);

        String res = "";
        for(SymbolProbability sp: table){

            res+=sp.getSymbol()+"  "+sp.getProbability()+"\n";
        }

        part1Input=getText();
        part1Result=res;
        return table;
    }

    public static ArrayList<String> part2fordecoding(){
        ArrayList<SymbolProbability> table = part1();
        ArrayList<SymbolProbability1> table1 = new ArrayList<>();
        for(int i=0;i<table.size();i++){
            SymbolProbability sp = table.get(i);
            table1.add(new SymbolProbability1(sp.getSymbol(),sp.getProbability()));
        }

        BinarySearchTree tree = new BinarySearchTree();
        SymbolProbability1 root = new SymbolProbability1();
        root.setSymbol("root");
        root.setProbability(3.0);
        root.setPriority(0.6);
        tree.insert(root);


        for(int i=0; i<table1.size();i++){

            table1.get(i).setPriority(i);
            tree.insert(table1.get(i));
            if(i==table1.size()-2){
                table1.get(i+1).setPriority(i+1.1);
                tree.insert(table1.get(i+1));
                break;
            }
            SymbolProbability1 newSp1 = new SymbolProbability1();
            double probSum = 0;
            String symbSum = "";

            for(int j=i+1;j<table.size();j++){
                symbSum+=table.get(j).getSymbol();
                probSum+=table.get(j).getProbability();
            }
            newSp1.setSymbol(symbSum);
            newSp1.setProbability(probSum);

            newSp1.setPriority(i+1.1);

            tree.insert(newSp1);
        }
        tree.print();
        String dictionary = "";
        for (int i=0;i<table1.size();i++){
            table.get(i).setCode(tree.find(table1.get(i)).getCode());
            dictionary+=table.get(i).getSymbol() + " " + table.get(i).getCode() + "\n";
        }

        String text = getText();

        ArrayList<String> encoded = new ArrayList<>();

        for(int i=0;i<text.length();i++){
            for(int j=0;j<table.size();j++){
                if(table.get(j).getSymbol().equals(String.valueOf(text.charAt(i)))){
                    encoded.add(table.get(j).getCode());

                    break;
                }
            }
        }
        part2Result1=dictionary;
        return encoded;
    }

    public static String part2(){
        ArrayList<String> encodedArr = part2fordecoding();
        String encoded = "";
        for(String e: encodedArr){
            encoded+=e;
        }

        part2Input=part1Result;
        part2Input1=getText();
        part2Result=encoded;
        return encoded;
    }

    public static String part3(){
        String encoded = part2();
        String tester = "";
        String decoded = "";
        for(int i=0;i<encoded.length();i++){
            tester+=String.valueOf(encoded.charAt(i));
            for(int j=0;j<table.size();j++){
                if(tester.equals(table.get(j).getCode())){
                    decoded+=table.get(j).getSymbol();
                    tester="";
                    break;
                }
            }
        }
        part3Input=part2Result;
        part3Input1=part2Result1;
        part3Result=decoded;
        return decoded;
    }

    public static String xor(String a, String b){
        StringBuilder sb = new StringBuilder();
        for(int k=0; k < a.length(); k++)
            sb.append((a.charAt(k) ^ b.charAt(k + (Math.abs(a.length() - b.length()))))) ;
        return sb.toString();
    }

    public static String part4(){
        String fromPart2 = part2Result;
        ArrayList<String> dividedBitsArray = new ArrayList<>();
        String encodedByHamming = "";

        ArrayList<String> dividedBitsArrayWithRedundancy = new ArrayList<>();

        if(fromPart2.length()%4!=0){
            while(!(fromPart2.length()%4==0)){
                fromPart2+="0";
            }
        }

        for(int i = 0; i < fromPart2.length(); i+=4){
            dividedBitsArray.add(fromPart2.substring(i,i+4));
        }


        for(int i=0;i<dividedBitsArray.size();i++){
            String i1 = String.valueOf(dividedBitsArray.get(i).charAt(0));
            String i2 = String.valueOf(dividedBitsArray.get(i).charAt(1));
            String i3 = String.valueOf(dividedBitsArray.get(i).charAt(2));
            String i4 = String.valueOf(dividedBitsArray.get(i).charAt(3));

            String r1 = xor(xor(i1,i2),i3);
            String r2 = xor(xor(i2,i3),i4);
            String r3 = xor(xor(i1,i2),i4);


            dividedBitsArrayWithRedundancy.add(dividedBitsArray.get(i)+r1+r2+r3);

        }

        for(int i=0; i<dividedBitsArrayWithRedundancy.size();i++){
            encodedByHamming+=dividedBitsArrayWithRedundancy.get(i);
        }

        part4Input=part2Result;
        part4Result=encodedByHamming;
         return encodedByHamming;
    }

    public static ArrayList<String> part4ArrayForm(){
        String fromPart2 = part2();
        ArrayList<String> dividedBitsArray = new ArrayList<>();


        ArrayList<String> dividedBitsArrayWithRedundancy = new ArrayList<>();

        if(fromPart2.length()%4!=0){
            while(!(fromPart2.length()%4==0)){
                fromPart2+="0";
            }
        }


        for(int i = 0; i < fromPart2.length(); i+=4){
            dividedBitsArray.add(fromPart2.substring(i,i+4));
        }


        for(int i=0;i<dividedBitsArray.size();i++){
            String i1 = String.valueOf(dividedBitsArray.get(i).charAt(0));
            String i2 = String.valueOf(dividedBitsArray.get(i).charAt(1));
            String i3 = String.valueOf(dividedBitsArray.get(i).charAt(2));
            String i4 = String.valueOf(dividedBitsArray.get(i).charAt(3));

            String r1 = xor(xor(i1,i2),i3);
            String r2 = xor(xor(i2,i3),i4);
            String r3 = xor(xor(i1,i2),i4);


            dividedBitsArrayWithRedundancy.add(dividedBitsArray.get(i)+r1+r2+r3);
        }

        return dividedBitsArrayWithRedundancy;
    }

    public static String part5(){
        String encodedByHamming = part4();

        for(int i = 0; i < encodedByHamming.length(); i+=14){
            int pos = (int)(i + Math.random() * 14);
            if(pos>=encodedByHamming.length()){
                break;
            }
            String changed = "0";

            if(encodedByHamming.charAt(pos)=='0'){
                changed = "1";
            }

            encodedByHamming = encodedByHamming.substring(0,pos) + changed + encodedByHamming.substring(pos+1);
        }

        part5Input=part4Result;
        part5Result = encodedByHamming;
        return encodedByHamming;
    }

    public static int errorDetectingInWhichPosition(String S){
        if(S.equals("001")){
            return 6;
        }
        else if(S.equals("010")){
            return 5;
        }
        else if(S.equals("011")){
            return 3;
        }
        else if(S.equals("100")){
            return 4;
        }
        else if(S.equals("101")){
            return 0;
        }
        else if(S.equals("110")){
            return 2;
        }
        else if(S.equals("111")){
            return 1;
        }

        return 7;
    }

    public static String part6(){
        String encodedByHammingWithError = part5();

        ArrayList<String> encodedByHammingWithErrorArray = new ArrayList<>();
        for(int i = 0; i < encodedByHammingWithError.length(); i+=7){
            encodedByHammingWithErrorArray.add(encodedByHammingWithError.substring(i,i+7));
        }


        for(int i = 0; i < encodedByHammingWithErrorArray.size(); i++){
            String i1 = String.valueOf(encodedByHammingWithErrorArray.get(i).charAt(0));
            String i2 = String.valueOf(encodedByHammingWithErrorArray.get(i).charAt(1));
            String i3 = String.valueOf(encodedByHammingWithErrorArray.get(i).charAt(2));
            String i4 = String.valueOf(encodedByHammingWithErrorArray.get(i).charAt(3));

            String r1 = String.valueOf(encodedByHammingWithErrorArray.get(i).charAt(4));
            String r2 = String.valueOf(encodedByHammingWithErrorArray.get(i).charAt(5));
            String r3 = String.valueOf(encodedByHammingWithErrorArray.get(i).charAt(6));

            String s1 = xor(xor(xor(r1,i1),i2),i3);
            String s2 = xor(xor(xor(r2,i2),i3),i4);
            String s3 = xor(xor(xor(r3,i1),i2),i4);
            String S = s1 + s2 + s3;


            if(errorDetectingInWhichPosition(S) == 7){
                continue;
            }
            String specified7bitString = encodedByHammingWithErrorArray.get(i);
            String changing = String.valueOf(specified7bitString.charAt(errorDetectingInWhichPosition(S))).equals("0")?"1":"0";
            specified7bitString = specified7bitString.substring(0,errorDetectingInWhichPosition(S)) + changing + specified7bitString.substring(errorDetectingInWhichPosition(S)+1);
            encodedByHammingWithErrorArray.set(i,specified7bitString);
        }

        String str = "";
        for(String s: encodedByHammingWithErrorArray){
            str+=s;
        }

        part6Input=part5Result;
        part6Result = str;
        return str;
    }

    public static void main(String[] args){
        part3();
        part6();

        System.out.println("Part 1");
        System.out.println("Input:");
        System.out.println(part1Input);
        System.out.println("Output:");
        System.out.println(part1Result);
        System.out.println();
        System.out.println();
        System.out.println("Part 2");
        System.out.println("Input:");
        System.out.println(part2Input1);
        System.out.println(part2Input);
        System.out.println("Output:");
        System.out.println(part2Result1);
        System.out.println(part2Result);
        System.out.println();
        System.out.println();
        System.out.println("Part 3");
        System.out.println("Input:");
        System.out.println(part2Result1);
        System.out.println(part3Input);
        System.out.println("Output:");
        System.out.println(part3Result);
        System.out.println();
        System.out.println();
        System.out.println("Part 4");
        System.out.println("Input:");
        System.out.println(part4Input);
        System.out.println("Output:");
        System.out.println(part4Result);
        System.out.println();
        System.out.println();
        System.out.println("Part 5");
        System.out.println("Input:");
        System.out.println(part5Input);
        System.out.println("Output:");
        System.out.println(part5Result);
        System.out.println();
        System.out.println();
        System.out.println("Part 6");
        System.out.println("Input:");
        System.out.println(part6Input);
        System.out.println("Output:");
        System.out.println(part6Result);
        System.out.println();
        System.out.println();

    }
}

//111010011111111111111011000110110001111111100111011111111011000010011111111110111010110100111111111111111
//111000011111111110111011000110110001111101100111001111111011000000011111110110111010110100110111111110111
//111010011111111111111011000110110001111111100111011111111011000010011111111110111010110100111111111111111