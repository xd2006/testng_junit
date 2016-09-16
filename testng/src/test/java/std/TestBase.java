package std;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by Alex on 27.08.2016.
 */

public class TestBase {


    protected static String directoryPathStr = "";
    protected static Path direcoryPath;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method m) {
        String tmp_dir_prefix = "test_";
        try {
            Path tmp = Files.createTempDirectory(tmp_dir_prefix);
            directoryPathStr = tmp.toString()+"\\";
            direcoryPath = tmp;
            TempDir ignore = m.getAnnotation(TempDir.class);
            if (ignore!=null){
                File f = new File(String.valueOf(tmp));
                //f.setReadable(ignore.read(), false);
//                f.setWritable(ignore.write(), false);
                setFilePermissions(tmp, ignore.read(),ignore.write());
            }


        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp(Method m) throws IOException {
        TempDir ignore = m.getAnnotation(TempDir.class);
        if (ignore!=null){
            setFilePermissions(direcoryPath, true,true);
        }
        deleteAllFilesFolder(direcoryPath.toString());
        Files.delete(direcoryPath);

    }

    private void deleteAllFilesFolder(String path) {
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();
    }

    protected boolean isFileExistsInFolder(String path, String fileName)
    {
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile() && myFile.getName().equals(fileName))
                return true;
                return false;
    }

    //this method works for Windows
    private void setFilePermissions(Path path, boolean read, boolean write) throws IOException {
        {
            AclFileAttributeView aclAttr = Files.getFileAttributeView(path, AclFileAttributeView.class);
//            System.out.println(aclAttr.getOwner());
//            for(AclEntry aclEntry : aclAttr.getAcl()){
//                System.out.println(aclEntry);
//            }
//            System.out.println();

            UserPrincipalLookupService upls = path.getFileSystem().getUserPrincipalLookupService();
            UserPrincipal user = upls.lookupPrincipalByName(System.getProperty("user.name"));

            AclEntry.Builder builderR = AclEntry.newBuilder();
            builderR.setPermissions( EnumSet.of(
                    AclEntryPermission.READ_DATA,
                    AclEntryPermission.EXECUTE,
                    AclEntryPermission.READ_ACL,
                    AclEntryPermission.READ_ATTRIBUTES,
                    AclEntryPermission.READ_NAMED_ATTRS,
                    AclEntryPermission.LIST_DIRECTORY,
                    AclEntryPermission.SYNCHRONIZE
            ));
            builderR.setPrincipal(user);
            if (read)
            builderR.setType(AclEntryType.ALLOW);
            else builderR.setType(AclEntryType.DENY);

            AclEntry.Builder builderW = AclEntry.newBuilder();
            builderW.setPermissions( EnumSet.of(
                    AclEntryPermission.WRITE_ACL,
                    AclEntryPermission.DELETE,
                    AclEntryPermission.ADD_FILE,
                    AclEntryPermission.ADD_SUBDIRECTORY,
                    AclEntryPermission.LIST_DIRECTORY,
                    AclEntryPermission.WRITE_DATA,
                    AclEntryPermission.APPEND_DATA,
                    AclEntryPermission.WRITE_NAMED_ATTRS,
                    AclEntryPermission.DELETE_CHILD,
                    AclEntryPermission.WRITE_ATTRIBUTES,
                    AclEntryPermission.WRITE_OWNER
            ));
            builderW.setPrincipal(user);
            if(write)
            builderW.setType(AclEntryType.ALLOW);
            else builderW.setType(AclEntryType.DENY);

            final AclEntry readPermissions = builderR.build();
            final AclEntry writePermissions = builderW.build();

            List permissionsList = new ArrayList();
            permissionsList.add(readPermissions);
            permissionsList.add(writePermissions);


            aclAttr.setAcl(permissionsList);

        }
    }

   }
