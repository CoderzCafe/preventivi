package embeded_sqlitedb_test;

import com.dropbox.core.DbxException;
import dbtest.JavaDropBoxDbTest4;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class EmbededSQLiteDbTest1 {

    public static String path = System.getProperty("user.dir")+ File.separator;
    public static void main(String[] args) throws IOException, DbxException, SQLException {
        SQLiteDb sqLiteDb = new SQLiteDb();
//        sqLiteDb.connection("test");
//        sqLiteDb.createTable();
//        sqLiteDb.insert();
//        sqLiteDb.update(1, "/home/shine/Desktop/images.png");
        JavaDropBoxDbTest4 dp = new JavaDropBoxDbTest4();
        dp.connection();
//        dp.UploadToDropbox("test1.db");
        dp.downloadFromDropbox("test.db");
    }

}
