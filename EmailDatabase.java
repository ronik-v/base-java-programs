package com.company;
import java.util.Random;
import java.util.Scanner;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class Create_Email_Password {
    private String FirstName;
    private String SecondName;
    private String Department;
    private String Company;

    Create_Email_Password(String FirstName, String SecondName, String Department, String Company) {
        this.FirstName = FirstName;
        this.SecondName = SecondName;
        this.Department = Department;
        this.Company = Company;
    }
    public String createEmail() {
        return SecondName + "." + FirstName + "@" + Department + "." + Company + "@gmail.com";
    }
    public String createPassword() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890~`@#¹#;:%^&*()-_+=|";
        String passResult = "";
        for (int i = 0; i < 12; ++i) {
            Random r = new Random();
            char c = alphabet.charAt(r.nextInt(alphabet.length()));
            passResult += String.valueOf(c);
        }
        return passResult;
    }

}


class AddDataToSql extends com.company.Create_Email_Password {
    private static String url = "jdbc:mysql://localhost:3306/peoplescom";
    private static String user = "root";
    private static String password = "password";

    private static Connection con;
    private static Statement stmt;

    AddDataToSql(String FirstName, String SecondName, String Department, String Company) {
        super(FirstName, SecondName, Department, Company);
    }

    public void Add(String FirstName, String SecondName, String Department, String Company) {
        com.company.Create_Email_Password create_email_password = new com.company.Create_Email_Password(FirstName, SecondName, Department, Company);
        String Email = create_email_password.createEmail();
        String EmailPassword = create_email_password.createPassword();

        String query = "INSERT INTO peoplescom.PeoplesData (FirstName, SecondName, " +
                "Department, Email, EmailPassword, Company) \n" +
                " VALUES ('"+FirstName+"', '"+SecondName+"', '"+Department+"', '"+Email+"'" +
                ", '"+EmailPassword+"', '"+Company+"');";
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("==================================================================================================");
            System.out.println("{OK} Add to PeoplesData - " + FirstName + " - " + SecondName + " - " + Company + " - " + Department);
            System.out.println(Email);
            System.out.println(EmailPassword);
            System.out.println("==================================================================================================");

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Enter First Name: ");
            String FirstName = scan.nextLine();
            System.out.print("Enter Second Name: ");
            String SecondName = scan.nextLine();
            System.out.print("Enter Department: ");
            String Department = scan.nextLine();
            String Company = "Google";
            System.out.println("==================================================================================================");
            com.company.AddDataToSql addDataToSql = new com.company.AddDataToSql(FirstName, SecondName, Department, Company);
            addDataToSql.Add(FirstName, SecondName, Department, Company);
        }
    }
}
