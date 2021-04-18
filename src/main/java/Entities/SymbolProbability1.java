package Entities;

public class SymbolProbability1 implements Comparable<SymbolProbability1>{
    private String symbol;
    private double probability;
    private String code = "";
    private double priority;
    public SymbolProbability1(){}
    public SymbolProbability1(String symbol, double probability, double priority) {
        this.symbol = symbol;
        this.probability = probability;
        this.priority = priority;
    }
    public SymbolProbability1(String symbol, double probability) {
        this.symbol = symbol;
        this.probability = probability;
    }

    public SymbolProbability1(double probability, String code) {
        this.probability = probability;
        this.code = code;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int compareTo(SymbolProbability1 o) {
        if(this.priority > o.getPriority()){
            return 1;
        }
        else if(this.priority < o.getPriority()){
            return -1;
        }
        return 0;
    }
}
