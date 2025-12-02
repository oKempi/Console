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
                assert pwd.getParentFile() != null;
                pwd = pwd.getParentFile();
                return;
            }catch (NullPointerException e){
                System.out.println("There is no directory above this one!"); //TODO how the hell am I supposed to do this...
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
    public static void listDirectories(BufferedReader reader) throws IOException{
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
            else{return;}
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
    public static void cat(String filename) { //cat file.smth -p "keyword" <= [-p] = print && "keyword" returns true/false if found in file
        File[] files = pwd.listFiles();
        assert files != null;
        for (File dirs : files) {
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

    public static void newLine(){ //TODO still somehow have to make this not crash after trying to get above "C:\" ...
        String name = System.getProperty("user.name") + "@" + System.getenv("COMPUTERNAME");
        assert pwd != null;
        if(pwd.toString().equals(System.getProperty("user.home").toString())){
            System.out.print(name + " ~" + "\n" + "> ");
        }
        else{System.out.print(name + " ~\\" + pwd.getAbsolutePath() + "\n" + "> ");}

    }

    public static void clearConsole(){ //TODO implement cls
         return;
    }

    public static void createFile(String filename) throws IOException { //TODO complete this (make sure the file gets created at the right place)
        File file = new File(pwd, filename);
        if(file.createNewFile()){
            System.out.println("File created!");
        }else{System.out.println("! Failed to create file");}
    }

    public static void removeFile(String filename) throws IOException {
        File file = new File(pwd, filename);
        if(file.delete()){
            System.out.println("File deleted!");
        }else {System.out.println("! Could not delete file");}
    }

    public static void find(String name){
        File[] files = pwd.listFiles();

        if(files != null && files.length > 0){
            for(File file : files){
                if(file.getName().equals(name)) {
                    System.out.println("File found!");
                    return;
                }
            }
            System.out.println("! File not found");
        }
    }
}
