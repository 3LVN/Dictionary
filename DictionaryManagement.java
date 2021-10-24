import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class DictionaryManagement {
   public Dictionary dict = new Dictionary();


   public void insertFromCommandline() {
       String en, vn;
       int n;
       System.out.println("Nhap so luong tu");
       Scanner in = new Scanner(System.in);
       n = in.nextInt();
       in.nextLine();
       for(int i = 1; i<= n; i++ ){
           System.out.println("Nhap tu thu " + i +":");
           en = in.nextLine();
           vn = in.nextLine();
           dict.Word.add(new Word(en, vn));
       }
       in.close();
   }
   public void createFile() {
       try {
           File blank = new File("dictionary.txt");
           if (blank.createNewFile()) {
            System.out.println("File created.");
           }
           else System.out.println("File already exists.");
       }
       catch (IOException e) {
           System.out.println("ERROR");
            e.printStackTrace();
       }
   }
   public void insertFromFile() {
        try {
            File dic = new File("dictionary.txt");
            Scanner read = new Scanner(dic).useDelimiter("\t");
            while (read.hasNext()) {
                String en = read.next();
                String vn = read.nextLine();
                en = en.replaceAll("\t", "");
                vn = vn.replaceAll("\t", "");
                dict.Word.add(new Word(en, vn));
            }
            read.close();
        }
        catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        
   }

   public void exportToFile() {
       try {
           File fout = new File("dictionary.txt");
           FileOutputStream fos = new FileOutputStream(fout);

           BufferedWriter wre = new BufferedWriter(new OutputStreamWriter(fos));
           for(int i = 0; i < dict.Word.size() ; i++) {
            Word word = dict.Word.get(i);
            wre.write(word.getTarget() + "\t" + word.getExplain());
            wre.newLine();
           }
           wre.close();
           System.out.println("Wrote to file.");
       }
       catch (IOException e) {
           System.out.println("ERROR");
           e.printStackTrace();
       }
   }

   public void dictionaryLookup() {
       System.out.println("Nhap tu can tim");
        Scanner toLook = new Scanner(System.in);
        String key = toLook.nextLine();
        toLook.close();
        for(int i = 0; i< dict.Word.size(); i++) {
            Word index = dict.Word.get(i);
            if(index.getTarget().contains(key) || index.getExplain().contains(key) ) {
                String s = i+1 + "\t| " + index.getTarget() + "\t\t| " + index.getExplain();
                System.out.println(s);
                break;
            }
        }
   }
   public int wordSearcher(String key) {
    int i = 0;
    for(; i< dict.Word.size(); i++) {
        Word index = dict.Word.get(i);
        if(index.getTarget().contains(key) || index.getExplain().contains(key) ) {
            break;
        }
    }
    return i;
   }

   public void addWord() {
       System.out.println("Them tu moi:");
       Scanner add = new Scanner(System.in);
        String en = add.nextLine();
        String vn = add.nextLine();
        add.close();
        dict.Word.add(new Word(en, vn));
   }

   public void editWord() {
       System.out.println("Tim tu can sua:");
        Scanner edit = new Scanner(System.in);
        String find = edit.nextLine();
        int i = wordSearcher(find);
        Word index = dict.Word.get(i);
        String s = i+1 + "\t| " + index.getTarget() + "\t\t| " + index.getExplain();
        System.out.println(s);
        System.out.println("Sua tu duoc chon:");
        String en = edit.nextLine();
        String vn = edit.nextLine();
        edit.close();
        dict.Word.get(i).setTarget(en);
        dict.Word.get(i).setExplain(vn);

   }

   public void delWord() {
        System.out.println("Tim tu can xoa:");
        Scanner edit = new Scanner(System.in);
        String find = edit.nextLine();
        edit.close();
        int i = wordSearcher(find);
        Word index = dict.Word.get(i);
        String s = i+1 + "\t| " + index.getTarget() + "\t\t| " + index.getExplain();
        System.out.println(s);
        dict.Word.remove(i);
        System.out.println("Tu da duoc xoa.");
    }

    public void dictionarySeacher() {
        System.out.println("Nhap cac tu can tim:");
        Scanner toLook = new Scanner(System.in);
        String key = toLook.nextLine();
        toLook.close();
        for(int i = 0; i< dict.Word.size(); i++) {
            Word index = dict.Word.get(i);
            if(index.getTarget().contains(key)) {
                String s = i+1 + "\t| " + index.getTarget() + "\t\t| " + index.getExplain();
                System.out.println(s);
            }
        }
    }


}
