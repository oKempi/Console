package Utilities;
import java.io.File;
import java.util.Objects;

public class Util {
    public static String userDir = System.getProperty("user.home").toString();
    public static File pwd = new File(userDir);

    //man
    public static void printMan() {
        System.out.println("Manual:");
        System.out.println("cd [path] - change directory to [path]");
        System.out.println("cd [-flag] - change directory using flag");
        System.out.println("    [-r] - return to root directory");
        System.out.println("ls - list directories in current directory");
        System.out.println("pwd - print working directory");
        System.out.println("exit - exit the program");
    }

    //CD
    public static void cd(String path) {
        if (path.equals("..")) {
            try {
                pwd = pwd.getParentFile();
                return;
            }catch (Exception e){
                System.out.println("There is no directory above this one!"); //TODO wth...
            }
        }

        String dummyPWD = pwd.toString() + "/" + path;
        try{
            File testPWD = new File(dummyPWD);
            if(testPWD.isDirectory()){
                pwd = testPWD;
            }
        }
        catch(Exception e){System.out.println("No such directory");}
    }
    public static void cd(Boolean yes, String flag) {
        if(Objects.equals(flag, "-r")){
            pwd = new File("C:\\Users\\Jakub");
            return;
        }
    }

    //ls
    public static void listDirectories(File directory) {
        File[] files = directory.listFiles();
        int count = 0;

//        if(files.length > 25){
//            System.out.println("Do you want to continue? (y/n)");
//        }

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(file.getName());
                }
            }
        }
        else{
            System.out.println("No files found!");
        }
    }

    //cat
    public static void cat(String filename) {
        //TODO implement cat
        File[] files = pwd.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.getName().equals(filename)) {
                System.out.println("File found");
            }
        }
    }

    public static void newLine(){
        String name = System.getProperty("user.name") + "@" + System.getenv("COMPUTERNAME");
        //TODO write something like user@pc:~/current/directory$
        if(pwd.toString().equals(System.getProperty("user.home").toString())){
            System.out.print(name + " ~" + "\n" + "> ");
        }
        else{System.out.print(name + " ~\\" + pwd.getAbsolutePath() + "\n" + "> ");}

    }
}
