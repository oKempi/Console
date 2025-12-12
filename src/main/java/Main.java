import Utilities.Util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
                    if(input.length == 2){
                        cd(input[1]);
                    }
                    else if(input.length > 1){
                        if(input[1].contains("-r")){
                            cd(true, input[1]);
                            break;
                        }
                        else{ //TODO lowkey fucked it up over here...
                            String path = "";
                            for (int i = 1; i < input.length; i++){
                                path = path + " " + input[i];
                            }
                            cd(path);
                            break;
                        }
                    }
                    else {System.out.println("Too few arguments!");}
                    break;
//                    if (input.length >= 2) {
//                        if(input[1].contains("-r")){
//                            cd(true, input[1]);
//                        }
//                        else{ //lowkey fucked it up over here...
//                            String path = "";
//                            for (int i = 1; i < input.length; i++){
//                                path = path + " " + input[i];
//                            }
//                            cd(path);
//                        }
//                    }
//                    else {System.out.println("Too few arguments!");}
//                    break;
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
                case "find": //TODO fix this mess...
                    if(input.length == 2){
                        find(input[1]);
                    }
                    else if (input.length == 3){
                        find(input[1], input[2]);
                    }else{System.out.println("! Too few/many arguments");}
                    break;
                case "status":
                    if(input.length == 2){
                        ping(input[1]);
                    }else{System.out.println("! You have entered too many/few arguments");}
                    break;
                case "run": //TODO try to implement?
                    break;
                case "grep":
                    if(input.length == 3){
                        grep(input[1], input[2]);
                    }
                    else{System.out.println("! You have entered too many/few arguments");}
                    break;
                default:
                    System.out.println("! Unknown command");
                    break;
            }
        }
    }
}
