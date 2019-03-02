package dbtest;

import com.dropbox.core.*;

import java.io.*;
import java.util.Locale;

public class JavaDropBoxDbTest1 {

    private static final String DROP_BOX_APP_KEY = "4mov8fzwdwvluin";
    private static final String DROP_BOX_APP_SECRECT = "t8ilpyl4fvm5g9f";

    private DbxClient dbxClient;

    public DbxClient authDropbox(String dropBoxAppKey, String dropboxAppSecrect) throws IOException, DbxException {
        DbxAppInfo dbxAppInfo = new DbxAppInfo(dropBoxAppKey, dropboxAppSecrect);
        DbxRequestConfig dbxRequestConfig = new DbxRequestConfig("SimpleDbTest1", Locale.getDefault().toString());

        DbxWebAuthNoRedirect dbxWebAuthNoRedirect = new DbxWebAuthNoRedirect(dbxRequestConfig, dbxAppInfo);

        String authorizeUrl = dbxWebAuthNoRedirect.start();
        System.out.println("1. Authorize: Go to url and click allow: " +authorizeUrl);
        System.out.println("2. Auth code: Copy authorization code and input here ");
        String dropboxAuthCode = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();

        DbxAuthFinish authFinish = dbxWebAuthNoRedirect.finish(dropboxAuthCode);
        String authAccessToken = authFinish.getAccessToken();
        dbxClient = new DbxClient(dbxRequestConfig, authAccessToken, DbxHost.DEFAULT);
        System.out.println("Dropbox Account Name: " +dbxClient.getAccountInfo().displayName);

        return dbxClient;
    }

    /** returns Dropbox size in GB **/
    public long getDropboxSize() throws DbxException {
        long dropboxSize = 0;
        DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo();
        dropboxSize = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
        return dropboxSize;
    }

    public void uploadToDropbox(String fileName) throws DbxException, IOException {
        File inputFile = new File(fileName);
        FileInputStream fis = new FileInputStream(inputFile);
        try {
            DbxEntry.File uploadedFile = dbxClient.uploadFile("/" +fileName, DbxWriteMode.add(), inputFile.length(), fis);
            String sharedUrl = dbxClient.createShareableUrl("/" +fileName);
            System.out.println("Uploaded: " +uploadedFile.toString()+ " URL " +sharedUrl);
        } finally {
            fis.close();
        }
    }

    public void createFolder(String folderName) throws DbxException{
        dbxClient.createFolder("/" +folderName);
    }

    public void listDropboxFolders(String folderPath) throws DbxException {
        DbxEntry.WithChildren listing = dbxClient.getMetadataWithChildren(folderPath);
        System.out.println("Files list: ");
        for (DbxEntry child: listing.children) {
            System.out.println("   " +child.name+ ": " +child.toString());
        }
    }

    public void downloadFromDropbox(String fileName) throws DbxException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        try {
            DbxEntry.File downloadedFile = dbxClient.getFile("/" +fileName, null, fileOutputStream);
            System.out.println("Metadata: " +downloadedFile.toString());
        } finally {
            fileOutputStream.close();
        }
    }

    public static void main(String[] args) throws IOException, DbxException{
        JavaDropBoxDbTest1 javaDropBoxDbTest1 = new JavaDropBoxDbTest1();
        javaDropBoxDbTest1.authDropbox(DROP_BOX_APP_KEY, DROP_BOX_APP_SECRECT);

        System.out.println("Dropbox size: " +javaDropBoxDbTest1.getDropboxSize() + " GB");
        javaDropBoxDbTest1.uploadToDropbox("/home/shine/Desktop/java.jpg");
        javaDropBoxDbTest1.createFolder("test1");
        javaDropBoxDbTest1.listDropboxFolders("/");
        javaDropBoxDbTest1.downloadFromDropbox("java.jpg");
    }


}
