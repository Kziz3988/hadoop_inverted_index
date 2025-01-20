import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextSplit {
    public static void SplitByLines(String filePath, String outputPath, int maxLine) {
        try {
            //Get file name without extension
            File file = new File(filePath);
            String fileName = file.getName();
            int dot = fileName.lastIndexOf(".");
            fileName = dot > 0 ? fileName.substring(0, dot) : fileName;

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            File outputDir = new File(outputPath);
            if(!outputDir.exists()) {
                outputDir.mkdirs();
            }
            int fileNo = 0, lineCount = maxLine;
            String line;
            StringBuilder newFileName = new StringBuilder();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputPath, "temp.txt")));
            while((line = reader.readLine()) != null) {
                if(lineCount % maxLine == 0) {
                    writer.close();
                    fileNo++;
                    newFileName.setLength(0);//Clear
                    newFileName.append(fileName);
                    newFileName.append("_");
                    newFileName.append(fileNo);
                    newFileName.append(".txt");
                    File newFile = new File(outputPath, newFileName.toString());
                    newFile.createNewFile();
                    writer = new BufferedWriter(new FileWriter(newFile));
                }
                writer.write(line);
                writer.newLine();
                lineCount++;
            }
            writer.close();
            reader.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SplitByLines(args[0], args[1], Integer.parseInt(args[2]));
    }
}