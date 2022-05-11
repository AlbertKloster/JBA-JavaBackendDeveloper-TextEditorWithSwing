package editor.view;

import editor.controller.listener.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel implements ActionListener {

    private final JButton saveButton;
    private final JButton openButton;
    private final JTextField searchField;
    private final JButton searchButton;
    private final JButton previousMatchButton;
    private final JButton nextMatchButton;
    private final JCheckBox useRegExCheckbox;

    private OpenListener openListener;
    private SaveListener saveListener;
    private SearchListener searchListener;
    private NextMatchListener nextMatchListener;
    private PreviousMatchListener previousMatchListener;
    private UseRegexListener useRegexListener;

    public ToolBar() {
        Border buttonBorder = BorderFactory.createEmptyBorder(4,4,4,4);

        Icon iOpen = new ImageIcon("open.png");
        openButton = new JButton(iOpen);
        openButton.setName("OpenButton");
        openButton.setBorder(buttonBorder);
        openButton.addActionListener(this);

        Icon iSave = new ImageIcon("save.png");
        saveButton = new JButton(iSave);
        saveButton.setName("SaveButton");
        saveButton.setBorder(buttonBorder);
        saveButton.addActionListener(this);

        searchField = new JTextField();
        searchField.setName("SearchField");
        searchField.setPreferredSize(new Dimension(229, 24));

        Icon iSearch = new ImageIcon("search.png");
        searchButton = new JButton(iSearch);
        searchButton.setName("StartSearchButton");
        searchButton.setBorder(buttonBorder);
        searchButton.addActionListener(this);

        Icon iPreviousMatch = new ImageIcon("previous.png");
        previousMatchButton = new JButton(iPreviousMatch);
        previousMatchButton.setName("PreviousMatchButton");
        previousMatchButton.setBorder(buttonBorder);
        previousMatchButton.addActionListener(this);

        Icon iNextMatch = new ImageIcon("next.png");
        nextMatchButton = new JButton(iNextMatch);
        nextMatchButton.setName("NextMatchButton");
        nextMatchButton.setBorder(buttonBorder);
        nextMatchButton.addActionListener(this);

        useRegExCheckbox = new JCheckBox("Use regex");
        useRegExCheckbox.setName("UseRegExCheckbox");
        useRegExCheckbox.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEADING));
        setBorder(new EmptyBorder(10, 5, 0, 10));

        add(openButton);
        add(saveButton);
        add(searchField);
        add(searchButton);
        add(previousMatchButton);
        add(nextMatchButton);
        add(useRegExCheckbox);
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JCheckBox getUseRegExCheckbox() {
        return useRegExCheckbox;
    }

    public void setOpenListener(OpenListener openListener) {
        this.openListener = openListener;
    }
    public void setSaveListener(SaveListener saveListener) {
        this.saveListener = saveListener;
    }
    public void setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }
    public void setNextMatchListener(NextMatchListener nextMatchListener) {
        this.nextMatchListener = nextMatchListener;
    }
    public void setPreviousMatchListener(PreviousMatchListener previousMatchListener) {
        this.previousMatchListener = previousMatchListener;
    }

    public void setUseRegexListener(UseRegexListener useRegexListener) {
        this.useRegexListener = useRegexListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(openButton))
            openListener.open();

        if (e.getSource().equals(saveButton))
            saveListener.save();

        if (e.getSource().equals(searchButton))
            searchListener.search();

        if (e.getSource().equals(nextMatchButton))
            nextMatchListener.searchNext();

        if (e.getSource().equals(previousMatchButton))
            previousMatchListener.searchPrevious();

        if (e.getSource().equals(useRegExCheckbox))
            useRegexListener.setRegex();
    }
}
