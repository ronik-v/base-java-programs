package org.SQLGen;

import org.SQLGen.Field;
import org.SQLGen.Table;
import org.SQLGen.Database;

public class Main {
    public static void main(String[] args) {
        Database database = new Database("base");
        database.create();
        Table table = new Table("base", "some");
        table.addField(new Field("a1", "VARCHAR", 50));
        table.addField(new Field("a2", "TEXT", 100));
        table.create();
        Table otherTable = new Table("base", "some1");
        otherTable.addField(new Field("b1", "VARCHAR", 50));
        otherTable.addField(new Field("b2", "TEXT", 100));
        otherTable.create();
        otherTable.rename("aaaaaaaaaaaaa");
        /*
        Table table = new Table("Base", "All");
        table.addField(new Field("VARCHAR", 50));
        */
    }
}