import javax.swing.*;

import java.awt.event.MouseEvent;

import java.awt.event.*;
import java.awt.*;

public class DictionaryApplication{
    JTextField search, edit, addW;
    JTextArea explain;
    JList<String> leftList;
    Checkbox checkEn, checkVn;
    DefaultListModel<String> list;
    JLabel searchText, editText, addText, reEdit, reAdd, reDel;
    DictionaryCommandline com = new DictionaryCommandline();
    public void runApplication() {
        com.man.insertFromFile();
        JFrame f= new JFrame("Dictionary");  
        f.getContentPane();
        f.getContentPane().setBackground(new Color(125, 150,200));
        
        list= new DefaultListModel<>();
        for (int i = 0; i< com.man.dict.Word.size(); i++){
            String s = com.man.dict.Word.get(i).getTarget().split("\\s")[0];
            list.addElement(s);
        }
        leftList = new JList<>(list);
        JScrollPane listScroll = new JScrollPane(leftList);
        listScroll.setBounds(10,200,200,500);
        listScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        f.getContentPane().add(listScroll);
        search = new JTextField();
        search.setBounds(10, 100, 150, 30);
        f.getContentPane().add(search);
        
        searchText = new JLabel("Tìm từ:");
        searchText.setBounds(10, 70, 150, 25);
        searchText.setForeground(Color.black);
        f.getContentPane().add(searchText);
        JButton sea = new JButton("Search");
        sea.setBounds(45, 135, 80, 30);
        sea.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String key = search.getText();
            int[] sList =  com.man.searchList(key);
            int i =0;
            list.removeAllElements();
            String a = com.man.dict.Word.get(sList[i]).getTarget().split("\\s")[0];
            list.addElement(a);
            i++;
            while(sList[i] != 0){
                String s = com.man.dict.Word.get(sList[i]).getTarget().split("\\s")[0];
                list.addElement(s);
                i++;
            }
            }
        });
        
        f.getContentPane().add(sea);
        
        explain = new JTextArea();
        explain.setMargin( new Insets(8,8,8,8));
        JScrollPane explainScroll = new JScrollPane(explain);
        explainScroll.setBounds(300, 200, 400, 500);
        f.getContentPane().add(explainScroll);
        
        
        leftList.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                String text;
                if (e.getClickCount() == 2) {
                    String selectedEn = (String) leftList.getSelectedValue();
                    int found = com.man.wordSearcher(selectedEn);
                    Word ex = com.man.dict.Word.get(found);
                    if(ex.getTarget().split("\\s")[1] != null) text = ex.getTarget().split("\\s")[1] + "\n" + ex.getExplain();
                    else text =  ex.getExplain();
                    explain.setText(text);
                }
            }
        });
        
        editText = new JLabel("Thay đổi từ:");
        editText.setBounds(300, 70, 150, 25);
        editText.setForeground(Color.black);
        edit = new JTextField();
        edit.setBounds(300, 100, 180, 30);
        JButton editButtonEn = new JButton("Edit EN");
        editButtonEn.setBounds(300, 135,80,30);
        JButton editButtonVn = new JButton("Edit VN");
        editButtonVn.setBounds(400, 135,80,30);
        
        reEdit = new JLabel();
        reEdit.setBounds(300, 170, 180, 25);
        reEdit.setForeground(Color.green);

        f.getContentPane().add(editText);
        f.getContentPane().add(edit);
        f.getContentPane().add(editButtonEn);
        f.getContentPane().add(editButtonVn);

        editButtonEn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String ed = edit.getText();
                String key = leftList.getSelectedValue();
                int found = com.man.wordSearcher(key);
                com.man.dict.Word.get(found).setTarget(ed);
                reEdit.setText("Đã chỉnh sửa từ.");
                }
            });
        editButtonVn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String ed = edit.getText();
                String key = leftList.getSelectedValue();
                int found = com.man.wordSearcher(key);
                com.man.dict.Word.get(found).setExplain(ed);
                reEdit.setText("Đã chỉnh sửa từ.");
                }
            });
        addText = new JLabel("Thêm từ mới:");
        addText.setBounds(500, 70, 150, 25);
        addText.setForeground(Color.black);
        addW = new JTextField();
        addW.setBounds(500, 100, 200, 30);
        JButton addButton = new JButton("Add");
        addButton.setBounds(560, 135, 80,30);
        
        reAdd = new JLabel();
        reAdd.setBounds(500, 170, 200, 25);
        reAdd.setForeground(Color.green);
        
        f.getContentPane().add(addText);
        f.getContentPane().add(addW);
        f.getContentPane().add(reAdd);
        f.getContentPane().add(addButton);
        
        addButton.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String ad = addW.getText();
                String en ="", vn = "";
                if(ad.split("\\s")[1] == null) vn = " ";
                if(ad.split("\\s")[2] == null) en = ad.split("\\s")[0];
                else {
                    en = ad.split("\\s")[0] +" "+ ad.split("\\s")[2];
                    vn = ad.split("\\s")[1];
                }
                com.man.dict.Word.add(new Word(en, vn));
                reAdd.setText("Từ "+ ad.split("\\s")[0] + " đã được thêm");
                }
            });
        
        JButton del = new JButton("Delete");
        del.setBounds(40, 710, 100, 30);
        reDel = new JLabel();
        reDel.setBounds(150, 710, 200 ,30);
        reDel.setForeground(Color.red);
        f.getContentPane().add(del);
        f.getContentPane().add(reDel);

        del.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String key = leftList.getSelectedValue();
                int found = com.man.wordSearcher(key);
                com.man.dict.Word.remove(found);
                reDel.setText("Đã xóa thành công từ: " + key);
                }
            });
        
        
        
        f.setSize(800,800);  
        f.setLayout(null);  
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        SwingUtilities.updateComponentTreeUI(f);
    }
    

    public static void main(String[] args) {
       //DictionaryCommandline com = new DictionaryCommandline();
       //com.dictionaryAdvance();
       DictionaryApplication app = new DictionaryApplication();
       app.runApplication();
    }


    
    
}
