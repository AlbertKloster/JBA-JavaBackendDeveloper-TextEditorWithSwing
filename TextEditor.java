package editor;

import editor.controller.FileHandler;
import editor.view.SearchMenu;
import editor.view.TextPanel;
import editor.view.ToolBar;
import editor.model.Searcher;
import editor.view.FileMenu;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JFrame {

    private final TextPanel textPanel;
    private final ToolBar toolBar;
    private final FileHandler fileHandler;
    private final JFileChooser fileChooser;
    private final Searcher searcher;

    public TextEditor() {
        super("Text Editor");
        textPanel = new TextPanel();
        toolBar = new ToolBar();
        fileHandler = new FileHandler();
        fileChooser = new JFileChooser();
        searcher = new Searcher(textPanel.getTextArea(), toolBar.getSearchField());
        init();
    }

    private void init() {
        BorderLayout layout = new BorderLayout();
        JMenuBar menuBar = new JMenuBar();
        FileMenu fileMenu = new FileMenu();
        SearchMenu searchMenu = new SearchMenu();
        fileChooser.setName("FileChooser");
        add(fileChooser);

        setLayout(layout);

        menuBar.add(fileMenu);
        menuBar.add(searchMenu);
        setJMenuBar(menuBar);

        toolBar.setSaveListener(this::save);
        toolBar.setOpenListener(this::open);
        toolBar.setSearchListener(this::search);
        toolBar.setNextMatchListener(this::searchNext);
        toolBar.setPreviousMatchListener(this::searchPrevious);
        toolBar.setUseRegexListener(this::setRegex);

        fileMenu.setSaveListener(this::save);
        fileMenu.setOpenListener(this::open);
        searchMenu.setSearchListener(this::search);
        searchMenu.setNextMatchListener(this::searchNext);
        searchMenu.setPreviousMatchListener(this::searchPrevious);
        searchMenu.setUseRegexListener(this::setRegexMenu);

        add(toolBar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 500));
        setVisible(true);
    }

    private void save() {
        setFileChooser();
        fileHandler.save(textPanel.getTextArea().getText());
    }

    private void open() {
        setFileChooser();
        textPanel.getTextArea().setText(fileHandler.getTextFromFile());
    }

    private void setFileChooser() {
        fileHandler.setFileChooser(fileChooser);
    }

    private void search() {
        searcher.selectFirstMatch();
    }

    private void searchNext() {
        searcher.setNextIndex();
        searcher.select();
    }

    private void searchPrevious() {
        searcher.setPreviousIndex();
        searcher.select();
    }

    private void setRegexMenu() {
        boolean currentCheckboxState = toolBar.getUseRegExCheckbox().isSelected();
        toolBar.getUseRegExCheckbox().setSelected(!currentCheckboxState);
        setRegex();
    }

    private void setRegex() {
        searcher.setRegex(toolBar.getUseRegExCheckbox().isSelected());
    }

}
