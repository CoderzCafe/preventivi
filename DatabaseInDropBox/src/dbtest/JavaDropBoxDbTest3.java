package dbtest;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.DbxUserUsersRequests;
import com.dropbox.core.v2.users.FullAccount;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class JavaDropBoxDbTest3 {
    private static final String ACCESS_TOKEN = "VzrCijyj5jAAAAAAAAAAI57a4PVUR4BEDvluwTVptJxwRzdGeoldRekglR_BwlxH";

    public static void main(String args[]) throws DbxException, IOException {
        System.out.println("Hello");

        try {
            DbxRequestConfig config;
	    // app name "dropbox/SimpleDbTest1"
            config = new DbxRequestConfig("dropbox/SimpleDbTest1");
            DbxClientV2 client;
            client = new DbxClientV2(config, ACCESS_TOKEN);
            FullAccount account;
            DbxUserUsersRequests r1 = client.users();
            account = r1.getCurrentAccount();
            System.out.println(account.getName().getDisplayName());

            //  get files and folder metadata from dropbox root directory
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

            try (InputStream in = new FileInputStream("/home/shine/Desktop/test.txt")) {
                FileMetadata metadata = client.files().uploadBuilder("/test.txt")
                        .uploadAndFinish(in);
                System.out.println("File is uploaded");
            }

            DbxDownloader downloader = client.files().download("/test.txt");
            try {
                FileOutputStream out = new FileOutputStream("/home/shine/Desktop/test2.txt");
                downloader.download(out);
                out.close();

                System.out.println("file is downloaded: ");
            } catch (DbxException ex) {
                System.out.println(ex.getMessage());
            }

        } catch (DbxException e) {
            e.printStackTrace();
        }
    }
}
