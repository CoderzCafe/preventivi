package sample;

import java.io.*;
import java.sql.*;

public class Database {

    public Connection connection() {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:products.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /** creating table **/
    public void createTable(String tableName) {
        Connection conn = connection();
        String query = "CREATE TABLE "+tableName+"(id INTEGER, productName VARCHAR(200), productPrice VARCHAR(200), productType VARCHAR(200), productDetails VARCHAR(200), productImage BLOB);";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Table created successfully");
    }

    /** insert data into the table **/
    public int insertData(int id, String name, String price, String type, String details, File file) {
        int row = 0;

        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String query = "INSERT INTO products(id, productName, productPrice, productType, productDetails, productImage) VALUES(?, ?, ?, ?, ?, ?);";
        Connection conn = connection();
        PreparedStatement prst = null;

        try {
            prst = conn.prepareStatement(query);
            prst.setInt(1, id);
            prst.setString(2, name);
            prst.setString(3, price);
            prst.setString(4, type);
            prst.setString(5, details);
            prst.setBytes(6, bos.toByteArray());
            row = prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (row > 0) {
            System.out.println("Data is inserted..");
        } else System.out.println("Data is not inserted..");

        return row;
    }

}
