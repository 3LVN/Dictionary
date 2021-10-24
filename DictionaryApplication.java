import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class DictionaryApplication{
    JTextField search;
    DictionaryCommandline com = new DictionaryCommandline();
    public void runApplication() {
        DefaultListModel<String> target = new DefaultListModel<>();
        com.man.insertFromFile();
        for(int  i = 0; i < com.man.dict.Word.size(); i++){
            Word list = com.man.dict.Word.get(i);
            String s = list.getTarget();
            s= s.split("\\s")[0];
            target.addElement(s);
        }
        JList<String> list = new JList<>(target);
        final JFrame f = new JFrame();
        f.setSize(800, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.getContentPane().setLayout(new FlowLayout());
        list.setSize(200, 500);
        list.setLocation(0,100);
        JScrollPane scr = new JScrollPane(list);

        scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        f.getContentPane().add(scr);

    }
    public static void main(String[] args) {
        DictionaryApplication app = new DictionaryApplication();
        app.runApplication();
    }

}
