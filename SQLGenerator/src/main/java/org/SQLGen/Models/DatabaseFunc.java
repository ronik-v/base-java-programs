package org.SQLGen.Models;

public interface DatabaseFunc {
    // This functions realise i/o in sql file
    void create();
    void delete();
    void rename(String newName);
}

