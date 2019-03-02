package embeded_sqlitedb_test;

import sun.misc.IOUtils;

import java.io.*;
import java.sql.*;

public class SQLiteDb {
    private String database;
    private Connection conn = null;
    private DatabaseMetaData meta;

    public Connection connection(String database) {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:"+database+".db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void createTable() {
        String query = "CREATE TABLE image " +"(ID INT PRIMARY KEY NOT NULL, photo BLOB)";
        Connection conn = connection("test");
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert() {
        FileInputStream fis;
        int numOfrows = 0;
        File imagePath = new File("/home/shine/Desktop/ui.png");
        try {
            fis = new FileInputStream(imagePath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] imageBuf = new byte[1024];
            for (int readNum; (readNum = fis.read(imageBuf)) != -1;) {
                bos.write(imageBuf, 0, readNum);
            }
            fis.close();
            Connection conn = connection("test");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO image (id, photo) VALUES (?, ?);");
            ps.setInt(1, 2);
            ps.setBytes(2, bos.toByteArray());

            numOfrows = ps.executeUpdate();
            if (numOfrows > 0) {
                System.out.println("Data inserted");
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, String imagePath) throws IOException {
        File file = new File(imagePath);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }
        fis.close();

        Connection conn = connection("test");
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE image set photo=? WHERE id=?;");
            ps.setBytes(1, bos.toByteArray());
            ps.setInt(2, id);
            int numOfRows = ps.executeUpdate();
            if (numOfRows > 0) {
                System.out.println("data is updated at " +id);
            } else {
                System.out.println("data is not updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
