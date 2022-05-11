package editor.controller;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class FileHandler {

    private JFileChooser fileChooser;

    public FileHandler() {
        this.fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    }

    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    public String getTextFromFile() {
        String text = "";
        try {
            text = readFile(getFileWithOpenDialog());
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return text;
    }

    private String readFile(File file) throws IOException {
        String text = "";
        if (file != null)
            text = Files.readString(file.toPath());
        return text;
    }

    private File getFileWithOpenDialog() {
        File file = null;
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        return file;
    }

    public void save(String text) {
        try {
            writeTextInFile(text, getFileWithSaveDialog());
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private void writeTextInFile(String text, File file) throws IOException {
        if (file != null) {
            PrintWriter printWriter = new PrintWriter(new FileWriter(file));
            printWriter.printf(text);
            printWriter.close();
        }
    }

    private File getFileWithSaveDialog() {
        File file = null;
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        return file;
    }

}
