package editor.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher {

    private final JTextArea textArea;
    private String text;
    private final JTextField searchField;
    private String searchText;
    private final List<Integer> startIndices;
    private final List<Integer> endIndices;
    private int indexPointer;
    private boolean regex;

    public Searcher(JTextArea textArea, JTextField searchField) {
        this.textArea = textArea;
        this.text = textArea.getText().trim();
        this.searchField = searchField;
        this.searchText = searchField.getText().trim();
        this.startIndices = new ArrayList<>();
        this.endIndices = new ArrayList<>();
        this.indexPointer = 0;
        this.regex = false;
    }

    public void setRegex(boolean regex) {
        this.regex = regex;
        init();
    }

    private void init() {
        text = textArea.getText();
        searchText = searchField.getText();
        indexPointer = 0;
        if (regex)
            getNewIndicesRegex();
        else
            getNewIndicesPlain();
    }

    private void getNewIndicesRegex() {
        startIndices.clear();
        endIndices.clear();
        if (!text.isBlank() && !searchText.isBlank()) {
            new Thread(() -> {
                Matcher matcher = Pattern.compile(searchText).matcher(text);
                while (matcher.find()) {
                    startIndices.add(matcher.start());
                    endIndices.add(matcher.end());
                }
            }).start();
        }
    }

    private void getNewIndicesPlain() {
        startIndices.clear();
        endIndices.clear();
        if (text.isBlank() || searchText.isBlank())
            return;
        new Thread(() -> {
            int startIndex = text.indexOf(searchText);
            int endIndex = startIndex + searchText.length();
            while (startIndex != -1) {
                startIndices.add(startIndex);
                endIndices.add(endIndex);
                startIndex = text.indexOf(searchText, endIndex);
                endIndex = startIndex + searchText.length();
            }
        }).start();
    }

    public void selectFirstMatch() {
        if (isNew())
            init();
        if (regex)
            selectRegex();
        else
            selectPlain();
    }

    private void selectRegex() {
        Matcher matcher = Pattern.compile(searchText).matcher(text);
        if (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            markText(startIndex, endIndex);
        }
    }

    private void selectPlain() {
        int startIndex = text.indexOf(searchText);
        if (startIndex == -1)
            return;
        int endIndex = startIndex + searchText.length();
        markText(startIndex, endIndex);
    }

    private void markText(int startIndex, int endIndex) {
        textArea.setCaretPosition(endIndex);
        textArea.select(startIndex, endIndex);
        textArea.grabFocus();
    }

    private boolean isNew() {
        return !textArea.getText().trim().equals(text) ||
                !searchField.getText().trim().equals(searchText);
    }

    public void setNextIndex() {
        int newIndexPointer = indexPointer + 1;
        setIndexPointer(newIndexPointer);
    }

    public void setPreviousIndex() {
        int newIndexPointer = indexPointer - 1;
        setIndexPointer(newIndexPointer);

    }

    private void setIndexPointer(int newIndexPointer) {
        int listSize = endIndices.size();
        indexPointer =  (listSize + newIndexPointer) % listSize;
    }

    public void select() {
        if (startIndices.isEmpty())
            return;
        int startIndex = startIndices.get(indexPointer);
        int endIndex = endIndices.get(indexPointer);
        textArea.setCaretPosition(endIndex);
        textArea.select(startIndex, endIndex);
        textArea.grabFocus();
    }

}
