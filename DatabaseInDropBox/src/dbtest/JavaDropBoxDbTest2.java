package dbtest;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import com.dropbox.core.v2.users.FullAccount;

import java.io.*;


public class JavaDropBoxDbTest2 {

    private static final String ACCESS_TOKEN = "mYTkP8aqqkAAAAAAAAAALL8EY8krQgO_TuKh3mUkgaYl19VC4C66OKTahXs6MM1D";
    private DbxRequestConfig config = null;
    DbxClientV2 client = null;
    FullAccount account = null;

    public JavaDropBoxDbTest2() {
        /** create dropbox client **/
        config = new DbxRequestConfig("dropbox/test2"); //  error may happend heer
        client = new DbxClientV2(config, ACCESS_TOKEN);

        try {
            FullAccount account = client.users().getCurrentAccount();
            System.out.println("Account name: "+account.getName().getDisplayName());

        } catch (DbxException dbxe) {
            dbxe.printStackTrace();
        }
    }

    /** creating folder **/
    public void createFolder(String folderName) throws DbxException {
        try {
            FolderMetadata folder = client.files().createFolder(folderName);
            System.out.println("Folder name: " +folder.getName());
        } catch (CreateFolderErrorException err) {
            if (err.errorValue.isPath() && err.errorValue.getPathValue().isConflict()) {
                System.out.println("Something already exists at the path");
            } else {
                System.out.println("Some other CreateFolderExcepton occured here..");
                System.out.println(err.toString());
            }
        }
    }

    /** list of folder **/
    public void listOfFolder() {
        try {
            //  get files and folder metadata from Dropbox root directory
            ListFolderResult result = client.files().listFolder("");
            while (true) {
                for (Metadata metadata: result.getEntries()) {
                    System.out.println(metadata.getPathLower());
                }
                if (!result.getHasMore()) {
                    break;
                }
                result = client.files().listFolderContinue(result.getCursor());
            }
        } catch (DbxException dbxe) {
            dbxe.printStackTrace();
        }
    }

    public void uploadFile(String path, String folderName) {
        //  upload test.txt to dropbox
        try {
            InputStream in = new FileInputStream(path);
            FileMetadata metadata = client.files().uploadBuilder(folderName).uploadAndFinish(in);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UploadErrorException e) {
            e.printStackTrace();
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(String folderName, String fileName) {
        try {
            //  ouput file for download ->> storage location on local system to download file
            FileOutputStream downloadFile = new FileOutputStream("/home/shine/Download/" +fileName);
            try {
                FileMetadata metadata = client.files().downloadBuilder(folderName).download(downloadFile);
            } finally {
                downloadFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DbxException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String path) {
        try {
            Metadata metadata = client.files().delete(path);
        } catch (DbxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws DbxException {
        JavaDropBoxDbTest2 javaDropBoxDbTest2 = new JavaDropBoxDbTest2();
        String folderName = "/test_java_create_folder" +System.currentTimeMillis();
        javaDropBoxDbTest2.listOfFolder();
        javaDropBoxDbTest2.createFolder(folderName);
        javaDropBoxDbTest2.uploadFile("/home/shine/Desktop/test.txt", folderName+"/test/txt");
        javaDropBoxDbTest2.readFile(folderName+"/test.txt", "test.txt");

    }

}
