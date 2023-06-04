package org.SQLGen;

import org.SQLGen.Models.DatabaseFunc;
import org.SQLGen.BaseIO.ReadText;
import org.SQLGen.BaseIO.WriteText;

import java.io.File;
import java.io.IOException;


public class Database implements DatabaseFunc {
    String name;
    File Db;

    public Database(String name) {
        this.name = name + ".sql";
        this.Db = new File(this.name);
    }

    @Override
    public void create() {
        try {
            boolean newFile = this.Db.createNewFile();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
        String message = "CREATE DATABASE " + this.name.substring(0,this.name.length() - 4) + ";";
        WriteText.toFile(this.name, message, false);
    }

    @Override
    public void rename(String newName) {
        String allText = ReadText.toFile(this.name);
        String newAllText = allText.replace(this.name.substring(0,this.name.length() - 4), newName);
        WriteText.toFile(this.name, newAllText, false);
        this.Db.renameTo(new File(newName + ".sql"));
    }

    @Override
    public void delete() {
        this.Db.delete();
    }
}
