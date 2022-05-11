package editor.view;

import editor.controller.listener.NextMatchListener;
import editor.controller.listener.PreviousMatchListener;
import editor.controller.listener.SearchListener;
import editor.controller.listener.UseRegexListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SearchMenu extends JMenuBar implements ActionListener {

    private final JMenuItem startSearchMenuItem;
    private final JMenuItem previousSearchMenuItem;
    private final JMenuItem nextSearchMenuItem;
    private final JMenuItem useRegularExpressionsMenuItem;

    private SearchListener searchListener;
    private NextMatchListener nextMatchListener;
    private PreviousMatchListener previousMatchListener;
    private UseRegexListener useRegexListener;

    public SearchMenu() {
        JMenu searchMenu = new JMenu("Search");
        searchMenu.setMnemonic(KeyEvent.VK_S);
        searchMenu.setName("MenuSearch");

        startSearchMenuItem = new JMenuItem("Start search");
        startSearchMenuItem.setMnemonic(KeyEvent.VK_T);
        startSearchMenuItem.setName("MenuStartSearch");
        startSearchMenuItem.addActionListener(this);

        previousSearchMenuItem = new JMenuItem("Previous search");
        previousSearchMenuItem.setMnemonic(KeyEvent.VK_P);
        previousSearchMenuItem.setName("MenuPreviousMatch");
        previousSearchMenuItem.addActionListener(this);

        nextSearchMenuItem = new JMenuItem("Next match");
        nextSearchMenuItem.setMnemonic(KeyEvent.VK_N);
        nextSearchMenuItem.setName("MenuNextMatch");
        nextSearchMenuItem.addActionListener(this);

        useRegularExpressionsMenuItem = new JMenuItem("Use regular expressions");
        useRegularExpressionsMenuItem.setMnemonic(KeyEvent.VK_U);
        useRegularExpressionsMenuItem.setName("MenuUseRegExp");
        useRegularExpressionsMenuItem.addActionListener(this);

        searchMenu.add(startSearchMenuItem);
        searchMenu.add(previousSearchMenuItem);
        searchMenu.add(nextSearchMenuItem);
        searchMenu.add(useRegularExpressionsMenuItem);

        this.add(searchMenu);
    }

    public void setSearchListener (SearchListener searchListener) {
        this.searchListener = searchListener;
    }

    public void setPreviousMatchListener (PreviousMatchListener previousMatchListener) {
        this.previousMatchListener = previousMatchListener;
    }

    public void setNextMatchListener (NextMatchListener nextMatchListener) {
        this.nextMatchListener = nextMatchListener;
    }

    public void setUseRegexListener (UseRegexListener useRegexListener) {
        this.useRegexListener = useRegexListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(startSearchMenuItem))
            searchListener.search();

        if (e.getSource().equals(previousSearchMenuItem))
            previousMatchListener.searchPrevious();

        if (e.getSource().equals(nextSearchMenuItem))
            nextMatchListener.searchNext();

        if (e.getSource().equals(useRegularExpressionsMenuItem))
            useRegexListener.setRegex();
    }
}
