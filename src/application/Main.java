package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import database.DB;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            conn = DB.getConnection();
            pst = conn.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ? , ?)");
            pst.setString(1, "Wesley Prado");
            pst.setString(2, "wesleyprado.dev@gmail.com");
            pst.setDate(3, java.sql.Date.valueOf("1995-07-01"));
            pst.setDouble(4, 10230.00);
            pst.setInt(5, 1);

            int rowsAffected = pst.executeUpdate();
            System.out.println("Done! Rows affected: " + rowsAffected);

            pst.close();

            pst = conn.prepareStatement(
                    "UPDATE seller "
                            + "SET BaseSalary = BaseSalary * ? "
                            + "WHERE Name = \"Wesley Prado\"");

            pst.setDouble(1, 1.1);
            rowsAffected = pst.executeUpdate();
            System.out.println("Done! Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closePreparedStatement(pst);
            DB.closeConnection();
        }
    }
}
