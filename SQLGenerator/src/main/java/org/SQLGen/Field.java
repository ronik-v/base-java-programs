package org.SQLGen;

public class Field {
    String name;
    String type;
    int size = -1;

    public Field(String name, String type, int size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }
}
