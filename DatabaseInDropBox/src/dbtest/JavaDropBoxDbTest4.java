package dbtest;

import com.dropbox.core.*;
import com.dropbox.core.v1.DbxAccountInfo;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.Account;
import com.dropbox.core.v2.users.DbxUserUsersRequests;
import com.dropbox.core.v2.users.FullAccount;
import embeded_sqlitedb_test.EmbededSQLiteDbTest1;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.*;

public class JavaDropBoxDbTest4 {
    private File file = null;
    private static final String ACCESS_TOKEN = "VzrCijyj5jAAAAAAAAAAI57a4PVUR4BEDvluwTVptJxwRzdGeoldRekglR_BwlxH";
    private DbxClientV2 client;
    private DbxRequestConfig config;

    public DbxClientV2 connection() {
        config = new DbxRequestConfig("dropbox/SimpleDbTest1");
        client = new DbxClientV2(config, ACCESS_TOKEN);
        return client;
    }

    private void getUserData() throws DbxException {
        FullAccount account;
        DbxUserUsersRequests userUsersRequests = client.users();
        account = userUsersRequests.getCurrentAccount();
        System.out.println("Account name: " +account.getName().getDisplayName());
    }

    /** get the files and folder metadata **/
    private void getFilesAndFolderMetadata() throws DbxException {
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

    /** get the dropbox size in gb **/
//    private long getDropboxSize() throws DbxException {
//        long dropBoxSize = 0;
//        DbxUserUsersRequests userUsersRequests = client.users();
//        FullAccount account = userUsersRequests.getCurrentAccount();
//    }


    /** method is modified for EmbededSQLiteDbTest1 _> fileName or location **/
    public void UploadToDropbox(String fileName) throws DbxException, IOException {

//        File inputFile = new File(EmbededSQLiteDbTest1.path+fileName);
        File inputFile = new File("/home/shine/Desktop/"+fileName);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(inputFile);
            FileMetadata uploadMetadata = client.files().uploadBuilder("/pic/"+fileName).uploadAndFinish(fis);
        } finally {
            fis.close();
        }
        System.out.println("file is uploaded successfully");
    }

    private void createFolder(String folderName) throws DbxException{
        client.files().createFolder("/"+folderName);
        System.out.println(folderName+ " folder is created..");
    }

    public void downloadFromDropbox(String fileName) throws DbxException {
        DbxDownloader downloader = client.files().download("/pic/"+fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(EmbededSQLiteDbTest1.path+"data"+File.separator+fileName);
            downloader.download(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File is downloaded... in ~:" +EmbededSQLiteDbTest1.path+fileName);
    }


    public static void main(String[] args) throws DbxException, IOException {
        System.out.println();
        JavaDropBoxDbTest4 javaDropBoxDbTest4 = new JavaDropBoxDbTest4();
        javaDropBoxDbTest4.connection();

        System.out.println("Get the user data");
        javaDropBoxDbTest4.getUserData();

        System.out.println("folder information");
        javaDropBoxDbTest4.getFilesAndFolderMetadata();

//        System.out.println("Creating folder");
//        javaDropBoxDbTest4.createFolder("pic");

//        System.out.println("Upload a file");
//        javaDropBoxDbTest4.UploadToDropbox("jsonTest.json");

//        System.out.println("Download form dropbox");
//        javaDropBoxDbTest4.downloadFromDropbox("jsonTest.json");

        /** open file and read **/
        File file = new File(EmbededSQLiteDbTest1.path+"data"+File.separator+"jsonTest.json");
        FileInputStream fis = new FileInputStream(file);



    }
}
