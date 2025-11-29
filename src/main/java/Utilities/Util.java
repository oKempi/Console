package Utilities;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

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
        System.out.println("cat - show contents of a file");
        System.out.println("clear/cls - clear the whole console");
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
            pwd = new File(System.getProperty("user.home").toString());
            return;
        }
    }

    //ls
    public static void listDirectories(BufferedReader reader) throws IOException{ //TODO make directories be \name and files +name (list both)
        File[] files = pwd.listFiles();
        int count = 0;

        if(files !=null && files.length > 25){
            System.out.println("Do you want to list all files? (y/n)");
            if(reader.readLine().equals("y")){
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.println("/" + file.getName());
                    }
                    else {System.out.println("+" + file.getName());}
                }
            }
        }
        else if(files != null && files.length > 0){
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("/" + file.getName());
                }
                else {System.out.println("+" + file.getName());}
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
        for (File dirs : files) {
            System.out.println(dirs.getName());
            if (dirs.getName().equals(filename)) {
                File file = new File(pwd + "\\" + filename);
                try (Scanner sc = new Scanner(file)){
                    while(sc.hasNextLine()){
                        System.out.println(sc.nextLine());
                    }
                    file = null;
                    files = null;
                    return;
                }catch(IOException e){return;}
            }
        }
        System.out.println("No such file was found!");
    }

    public static void newLine(){
        String name = System.getProperty("user.name") + "@" + System.getenv("COMPUTERNAME");
        if(pwd.toString().equals(System.getProperty("user.home").toString())){
            System.out.print(name + " ~" + "\n" + "> ");
        }
        else{System.out.print(name + " ~\\" + pwd.getAbsolutePath() + "\n" + "> ");}

    }
}
