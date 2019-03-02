package sample;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.users.DbxUserUsersRequests;
import com.dropbox.core.v2.users.FullAccount;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DropboxAsDb {

    private static final String ACCESS_TOKEN = "VzrCijyj5jAAAAAAAAAAK_AZHhx7uS-_o_3NUvbA305wHcTBNSDKMxX3gXkxlQly";
    private DbxClientV2 client;
    private DbxRequestConfig config;

    public DbxClientV2 connection() {
        config = new DbxRequestConfig("dropbox/ProductsDemo1");
        client = new DbxClientV2(config, ACCESS_TOKEN);
        System.out.println("Connection is established with dropbox....");
        return client;
    }

    public void getUserData() throws DbxException {
        FullAccount account;
        DbxUserUsersRequests userUsersRequests = client.users();
        account = userUsersRequests.getCurrentAccount();
        System.out.println("Account name: " +account.getName());
    }

    public void uploadToDropbox(String fileName) throws IOException, DbxException {
        /** get the project absolute raw path **/
        String projectFilePath = System.getProperty("user.dir")+File.separator+"src"+File.separator+"data"+File.separator+fileName;

        File inputFile = new File(projectFilePath);

        if (!inputFile.exists()) {
            inputFile.createNewFile();
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(inputFile);
            FileMetadata uploadMetadata = client.files().uploadBuilder("/pic/"+fileName).uploadAndFinish(fis);
        } finally {
            fis.close();
        }
        System.out.println("File is uploaded..");
    }

    public static void main(String[] args) throws DbxException, IOException {
        System.out.println(DropboxAsDb.class.getResource("/data/test.txt").toString());
        DropboxAsDb dropboxAsDb = new DropboxAsDb();
        dropboxAsDb.connection();

        dropboxAsDb.getUserData();

        dropboxAsDb.uploadToDropbox("products.xlsx");
    }
}
