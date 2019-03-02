package main;

import java.sql.*;
import java.util.Scanner;

public class SqLiteDbTest1 {

    public static void main(String[] args) {
//        insertData(5, "D", "24", "Dhaka");
//        update(2, "Sudipto", "22", "Dhaka");
//        delete(3);
//        getAllData();
        int update = updateTable("user", "image", "BLOB", "100000");
        if (update > 0) {
            System.out.println("Table is updated");
        }
    }

    private static Connection connection() {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static void createTable(String tableName){
        Connection conn = connection();
        String query = "CREATE TABLE "+tableName+"(id INTEGER, name VARCHAR(200), age VARCHAR(200), city VARCHAR(200));";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertData(int id, String name, String age, String city) {
        int num_row  = 0;
        Connection conn = connection();
        String query = "INSERT INTO user(id, name, age, city) VALUES(?, ?, ?, ?);";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, city);
            num_row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (num_row > 0) {
            System.out.println("Data is inserted..");
        } else {
            System.out.println("Data is not inserted..");
        }
    }

    private static void getAllData() {
        Connection conn = connection();
        String query = "SELECT id, name, age, city FROM user;";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Id : " +rs.getInt(1)+"\n"+
                                    "Name : " +rs.getString(2)+"\n"+
                                    "Age : " +rs.getString(3)+"\n"+
                                    "City : " +rs.getString(4)+"\n\n");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static void update(int id, String name, String age, String city) {
        System.out.println("Which row you want to update.?\n please enter everything :");
        String query = "UPDATE user SET name=?, age=?, city=? WHERE id=?;";
        Connection conn = connection();
        PreparedStatement prst = null;
        int row = 0;

        try {
            prst = conn.prepareStatement(query);
            prst.setString(1, name);
            prst.setString(2, age);
            prst.setString(3, city);
            prst.setInt(4, id);
            row = prst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (row > 0) {
            System.out.println(id+" row is updated successfully");
        } else {
            System.out.println("Data is not updated");
        }
    }

    private static void delete(int id) {
        String query = "DELETE FROM user WHERE id=?;";
        Connection conn = conn = connection();
        PreparedStatement prst = null;
        int row = 0;

        try {
            prst = conn.prepareStatement(query);
            prst.setInt(1, id);
            row = prst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (row > 0) {
            System.out.println("Data is deleted successfully...");
        } else {
            System.out.println("Data is not deleted");
        }
    }

    private static int updateTable(String tableName, String newColumnName, String newColumnDataTypes, String dataTypeSize) {
        String query = "ALTER "+tableName+ " ADD " +newColumnName+ " "+newColumnDataTypes+"("+dataTypeSize+");";
        Connection conn = connection();
        PreparedStatement prst = null;
        int row = 0;
        try {
            prst = conn.prepareStatement(query);
            row = prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
