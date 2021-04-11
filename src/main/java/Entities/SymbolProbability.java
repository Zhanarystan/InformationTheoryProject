package Entities;

public class SymbolProbability {
    private Character symbol;
    private double probability;

    public SymbolProbability(){}
    public SymbolProbability(Character symbol, double probability) {
        this.symbol = symbol;
        this.probability = probability;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
