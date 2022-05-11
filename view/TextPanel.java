package editor.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextPanel extends JPanel {

    private final JTextArea textArea;

    public TextPanel() {
        textArea = new JTextArea();
        textArea.setName("TextArea");
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");

        setLayout(new BorderLayout(4, 4));
        setBorder(new EmptyBorder(0, 10, 10, 10));

        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

}
