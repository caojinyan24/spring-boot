package org.example.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class FileProcessor {
    public static final String WRITE_NAME = new File("").getAbsolutePath() + "/";
    private static final String READ_NAME = new File("").getAbsolutePath() + "/";

    public static void makeDir(String dirName, Boolean isClear) {
        File file = new File(WRITE_NAME + dirName);
        if (!file.exists()) {
            file.mkdir();
        } else if (isClear) {
            File[] files = file.listFiles();
            log.info("clear dir: " + dirName);
            for (File f : files) {
                f.delete();
            }
        }
    }

    public static List<String> readFile(String fileName) {
        List<String> output = new ArrayList<>();
        try {
            try (Scanner sc = new Scanner(new FileReader(READ_NAME + fileName))) {
                while (sc.hasNextLine()) {  //按行读取字符串
                    String line = sc.nextLine();
                    output.add(line);
                }
            }
        } catch (Exception e) {
            System.out.println("read file error" + e.getMessage());
            return output;
        }
        return output;
    }



    public static void readFile(String fileName, Executor executor) {
        try {
            try (Scanner sc = new Scanner(new FileReader(READ_NAME + fileName))) {
                while (sc.hasNextLine()) {  // 按行读取字符串
                    String line = sc.nextLine();
                    executor.execute(line);
                }
            }
        } catch (Exception e) {
            System.out.println("read file error" + e.getMessage());
        }
    }



    public static void writeFile(String fileName, String line, Boolean append) {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(WRITE_NAME + fileName, append),
                    StandardCharsets.UTF_8);
            // 写入内容到文件
            writer.write(line);
            // 刷新写入流，确保所有内容都被写入
            writer.flush();
        } catch (Exception e) {
            // 处理可能的IOException
            System.out.println("write file error" + e.getMessage());
//            throw e;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    System.out.println("close writer error" + e.getMessage());
                }
            }
        }
    }
}
