package org.SQLGen;

import org.SQLGen.Models.TableFunc;
import org.SQLGen.BaseIO.ReadText;
import org.SQLGen.BaseIO.WriteText;

import java.util.ArrayList;

public class Table implements TableFunc {
    String DatabaseName;
    String tableName;
    final ArrayList<String> tableFields = new ArrayList<>();

    public Table(String DatabaseName, String tableName) {
        this.DatabaseName = DatabaseName;
        this.tableName = tableName;
    }

    public void addField(Field field) {
        String toAllFields = " NOT NULL";
        String prefix = "   ";
        String stringField = "";
        if (field.size != -1) {
            stringField = prefix + field.name + " " + field.type + "(" + field.size + ")" + toAllFields + "," + "\n";
        } else {
            stringField = prefix + field.name + " " + field.type + toAllFields + "," + "\n";
        }
        this.tableFields.add(stringField);
    }

    @Override
    public void create() {
        String table = "\n\nCREATE TABLE " + this.tableName + "(" + "\n";
        String index = "   " + this.tableName + "_id" + " BIGSERIAL NOT NULL PRIMARY KEY,\n";
        WriteText.toFile(this.DatabaseName + ".sql", table, true);
        WriteText.toFile(this.DatabaseName + ".sql", index, true);
        for (int position = 0; position < this.tableFields.size(); ++position) {
            if (position != this.tableFields.size() - 1) {
                WriteText.toFile(this.DatabaseName + ".sql", this.tableFields.get(position), true);
            } else {
                String last = this.tableFields.get(position);
                WriteText.toFile(this.DatabaseName + ".sql", last.substring(0, last.length() - 2) + "\n", true);
            }
        }
        WriteText.toFile(this.DatabaseName + ".sql", ");", true);
    }

    @Override
    public void rename(String newName) {
        String allText = ReadText.toFile(this.DatabaseName + ".sql");
        String newAllText = allText.replace(this.tableName, newName);
        WriteText.toFile(this.DatabaseName + ".sql", newAllText, false);
    }
}
