package fr.cned.emdsgil.suividevosfrais;

public class fraisEntry {

    private String name;

    private int value;

    public fraisEntry(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

}