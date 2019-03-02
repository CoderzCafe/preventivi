package main.model;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.DbxUserUsersRequests;
import com.dropbox.core.v2.users.FullAccount;
import main.Main;

import java.io.*;

public class Dropbox {
    public static final String FILE_PATH = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"main"+File.separator+"data"+File.separator;
    private File file = null;
    //  app access token
    private static final String ACCESS_TOKEN = "VzrCijyj5jAAAAAAAAAALYCVpK6OZIyLMEiOCJWE9cA_L0vey90JPWEm4FPwNABt";
    private DbxClientV2 client;
    private DbxRequestConfig config;

    public Dropbox() throws DbxException {
        config = new DbxRequestConfig("dropbox/ProductsDemo1");
        client = new DbxClientV2(config, ACCESS_TOKEN);
        Main.notification("Congratulations", "Connection established");
    }

    public void getUserData() throws DbxException {
        FullAccount account;
        DbxUserUsersRequests userUsersRequests = client.users();
        account = userUsersRequests.getCurrentAccount();
        System.out.println("Account name: " +account.getName());
    }

    /** get file and folder metadata **/
    public void getFilesAndFolderMetadata() throws DbxException {
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata: result.getEntries()) {
                System.out.println("Path: " +metadata.getPathLower());
            }
            if (!result.getHasMore()) {
                break;
            }
            result = client.files().listFolderContinue(result.getCursor());
        }
    }

    /** upload files to dropbox
     * file location is default to project data directory **/
    public void uploadFile(String fileName) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(FILE_PATH+fileName));
        } catch (FileNotFoundException e) {
            Main.notification("File not found", e.getMessage());
        }
        try {
            FileMetadata uploadMetadata = client.files().uploadBuilder("/pic/"+fileName).uploadAndFinish(fis);
        } catch (DbxException e) {
            Main.notification("Error in uploading file", e.getMessage());
        } catch (IOException e) {
            Main.notification("Error in uploading file", e.getMessage());
        }
        Main.notification("Congratulations", "File is uploaded successfully");
    }


    /** download the file from dropbox **/
    public void downloadFile(String fileName) {
        try {
            DbxDownloader downloader = client.files().download("/pic/"+fileName);
//            FileOutputStream fout = new FileOutputStream(FILE_PATH+fileName);
            FileOutputStream fout = new FileOutputStream(new File("/home/shine/Desktop/1"+fileName));
            downloader.download(fout);
        } catch (DbxException e) {
            Main.notification("Error in downloading", e.getMessage());
        } catch (FileNotFoundException e) {
            Main.notification("Error in file location", e.getMessage());
        } catch (IOException e) {
            Main.notification("Error in downloading", e.getMessage());
        }
        Main.notification("Congratulations", "File is downloading");
    }

}
