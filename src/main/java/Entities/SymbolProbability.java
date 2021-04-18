package Entities;

public class SymbolProbability implements Comparable<SymbolProbability> {
    private String symbol;
    private double probability;
    private String code = "";

    public SymbolProbability(){}
    public SymbolProbability(String symbol, double probability) {
        this.symbol = symbol;
        this.probability = probability;
    }

    public SymbolProbability(double probability, String code) {
        this.probability = probability;
        this.code = code;
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
    public int compareTo(SymbolProbability o) {
        if(this.probability > o.getProbability()){
            return -1;
        }
        else if(this.probability == o.getProbability()){
            return 0;
        }

        return 1;
    }
}
