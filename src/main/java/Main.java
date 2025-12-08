import Utilities.Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import static Utilities.Util.*;

public class Main {
    private static final BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
    private static Boolean running = true;
    private static File pwd;

    public static void main(String[] args) throws IOException {

        while (running) {
            pwd = Util.pwd;
            newLine();

            String[] input = reader.readLine().split(" ");
            switch (input[0]) {
                case "man", "help":
                    printMan();
                    break;
                case "exit":
                    running = false;
                    break;
                case "return":
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 1; i < input.length; i++) {
                        stringBuilder.append(input[i] + " ");
                    }
                    System.out.println(stringBuilder.toString());
                case "cd":
                    if (!(input.length > 2)) {
                        if(input[1].contains("-r")){
                            cd(true, input[1]);
                        }
                        else{cd(input[1]);}
                    }
                    else {System.out.println("Too many arguments!");}
                    break;
                case "ls":
                    listDirectories(reader);
                    break;
                case "cat":
                    cat(input[1]);
                    break;
                case "pwd":
                    System.out.println(pwd.getAbsolutePath());
                    break;
                case "create", "mk":
                    createFile(input[1]);
                    break;
                case "remove", "rm":
                    removeFile(input[1]);
                    break;
                case "mkdir":
                    createDir(input[1]);
                    break;
                case "rmdir":
                    removeDir(input[1]);
                    break;
                case "find":
                    if(input.length < 3){
                        find(input[1]);
                    }else{
                        find(input[1], input[2]);
                    }
                    break;
                case "status":
                    if(input.length == 2){
                        ping(input[1]);
                    }else{System.out.println("! You have entered too many/few arguments");}
                    break;
                case "run": //TODO try to implement?
                    break;
                default:
                    System.out.println("! Unknown command");
                    break;
            }
        }
    }
}
