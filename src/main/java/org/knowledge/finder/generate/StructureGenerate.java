package org.knowledge.finder.generate;

import java.io.BufferedWriter; // Import BufferedWriter
import java.io.FileWriter;     // Import FileWriter
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
// import java.nio.charset.StandardCharsets; // Not needed when using FileWriter directly if you rely on the platform's default encoding

import org.knowledge.finder.data.LessonData;

public class StructureGenerate {

    private LessonData lessonData;
    private Path directoryProject;

    public StructureGenerate(LessonData lessonData) {
        this.lessonData = lessonData;
    }

    public void process() {
        createProjectDir();
        createContext();
        createSubfoldersAndMarkdownFiles();
    }

    public void createProjectDir() {
        directoryProject = Paths.get(lessonData.getProjectName());

        try {
            Files.createDirectories(directoryProject);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Project Directory created: " + directoryProject.toAbsolutePath());
    }

    public void createContext() {
        Path contextFile = directoryProject.resolve("context.txt");
        String content = "This is the context for the project.";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(contextFile.toFile()))) {
            writer.write(content);
            System.out.println("Context File created and content written using BufferedWriter: " + contextFile.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createSubfoldersAndMarkdownFiles() {
        String[] folderNames = {"folder1", "folder2", "folder3"};

        for (String folderName : folderNames) {
            Path subfolderPath = directoryProject.resolve(folderName);

            try {
                Files.createDirectories(subfolderPath);
                System.out.println("Subfolder created: " + subfolderPath.toAbsolutePath());

                for (int i = 1; i <= 2; i++) {
                    String fileName = "file" + i + ".md";
                    Path markdownFilePath = subfolderPath.resolve(fileName);
                    String fileContent = "# " + folderName + " - Markdown File " + i + "\n\nThis is content for " + fileName + " in " + folderName + ".";

                    // Use try-with-resources for BufferedWriter and FileWriter
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(markdownFilePath.toFile()))) { 
                        writer.write(fileContent);
                        System.out.println("Markdown file created and content written using BufferedWriter: " + markdownFilePath.toAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
