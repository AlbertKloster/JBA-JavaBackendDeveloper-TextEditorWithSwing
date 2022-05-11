package editor.view;

import editor.controller.listener.OpenListener;
import editor.controller.listener.SaveListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FileMenu extends JMenuBar implements ActionListener {

    private SaveListener saveListener;
    private OpenListener openListener;

    private final JMenuItem saveMenuItem;
    private final JMenuItem openMenuItem;

    public FileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.setName("MenuFile");

        saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
        saveMenuItem.setName("MenuSave");
        openMenuItem = new JMenuItem("Open");
        openMenuItem.setMnemonic(KeyEvent.VK_O);
        openMenuItem.setName("MenuOpen");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.setName("MenuExit");

        saveMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(e -> System.exit(0));

        fileMenu.add(saveMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        this.add(fileMenu);
    }

    public void setSaveListener(SaveListener saveListener) {
        this.saveListener = saveListener;
    }

    public void setOpenListener(OpenListener openListener) {
        this.openListener = openListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(openMenuItem)) {
            openListener.open();
        }
        if (e.getSource().equals(saveMenuItem)) {
            saveListener.save();
        }
    }
}
