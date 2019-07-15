package ru.eltex;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner; 
import java.util.Comparator; 

public class Main{
        
    public static void deldir(Path dir) {// throws IOException{
        try {
            Files.walk(dir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
        }
        catch (IOException e) {
            System.out.println("Deldir error");
            System.err.print(e.getMessage()); 
        }
    }
        
    public static void main(String args[]) {//throws IOException{
        String path = "/proc/";
        //File proc = new File(path);
        Path proc = Paths.get(path);
        //Path res = Paths.get("/proc/746/");
        String pid = "";
        while(!pid.equals("q")) {
            try {
                DirectoryStream<Path> dirs = Files.newDirectoryStream(proc);
                for (Path file: dirs) {
                    String name = file.getFileName().toString();
                    //System.out.println(name);
                    try {
                        Integer.parseInt(name);
                        try {
                            Path logFile = Paths.get(path + name + "/stat");
                            BufferedReader reader = Files.newBufferedReader(logFile);
                            String line = reader.readLine();
                            Integer delimiter_count = 0;
                            Integer delimiter_begin = 0;
                            Integer delimiter_end = 0;
                            for(int i = 0; i < line.length(); i++) {
                                if (line.charAt(i) == '(') {
                                    if (delimiter_count == 0)
                                        delimiter_begin = i + 1;
                                    delimiter_count++;
                                }else if(line.charAt(i) == ')') {
                                    delimiter_count--;
                                    if (delimiter_count == 0) {
                                        delimiter_end = i;
                                        break;
                                    }
                                }
                            }
                            line = line.substring(delimiter_begin, delimiter_end);
                            
                            System.out.printf("%-7s|%s\n", name, line);
                            
                        }
                        catch (IOException error) {
                            System.out.println("Failed open file " + path +  name + "/stat");
                            System.err.print(error.getMessage()); 
                        }
                    }
                    catch (NumberFormatException e) {
                        continue;
                    }
                }
                    //System.out.println(file.getFileName());
            }
            catch (IOException error) {
                System.err.print(error.getMessage()); 
            }
            
            Scanner in = new Scanner(System.in);
            System.out.print("Enter PID to kill or q to exit: ");
            pid = in.nextLine();
            //System.out.println("/proc/" + pid); //+ "/");
            System.out.println(pid);
            if(!pid.equals("q")) {
                //Path kill = Paths.get("/proc/" + pid); //+ "/");
                Path kill = Paths.get(pid); //+ "/");
                deldir(kill);
            }
        }
        
        
        /*String[] dirs = proc.list();
        System.out.println();
        for(int i = 0; i < dirs.length; i++)
            System.out.println(dirs[i]);
        System.out.println();
        System.out.printf("PID    |NAME\n");
        for(int i = 0; i < dirs.length; i++) {
            try {
                Integer.parseInt(dirs[i]);
                try {
                    FileReader fr = new FileReader (path + dirs[i] + "/stat");
                    Scanner scan = new Scanner(fr);
                    scan.useDelimiter("\\(");
                    String name = scan.next();
                    scan.useDelimiter("\\) ");
                    name = scan.next();
                    name = name.substring(1);
                    System.out.printf("%-7s|%s\n",dirs[i], name);
                }
                catch (IOException error) {
                    System.out.println("Failed open file " + path+  dirs[i] + "/stat");
                    System.err.print(error.getMessage()); 
                }
            }    
            catch (NumberFormatException e) {
                continue;
            }
        }*/
    }
}
