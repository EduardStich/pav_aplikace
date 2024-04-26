import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PPanel extends JPanel {

    // Nav
    JLabel label1, label2, label3;
    Font navFont = new Font("New Times Roman",Font.PLAIN,25);

    // Next Button
    JButton nextBtn;
    Font btnFont = new Font("New Times Roman",Font.BOLD,20);
    int phase = 0;

    // Select
    ButtonGroup select;
    JRadioButton btn1, btn2;
    Font btnFont2 = new Font("New Times Roman",Font.PLAIN,15);

    // Source
    ScrollPane scroll;
    JTextArea input1;
    JTextField input2;

    String[] raw;

    // Export
    JLabel label4;
    JButton btn3, btn4, btn5, btn6;

    public PPanel() {

        setPreferredSize(new Dimension(600,600));
        setBackground(Color.WHITE);

        setLayout(null);

        setNav();
        setNext();
        setSelect();
        setSource();
        setExport();

        nextPhase();
    }

    void setExport() {

        label4 = new JLabel("Vyberte možnost výpisu (soubory se ukládají do skožky ve které je aplikace uložena)");
        label4.setBounds(25,125,550,30);
        label4.setFont(btnFont2);
        label4.setVisible(false);
        add(label4);

        btn3 = new JButton("Vypsat do Terminálu (pokud byla aplikace zaptnuta z něj)");
        btn3.setBounds(25,165,550,30);
        btn3.setFont(btnFont2);
        btn3.setFocusPainted(false);
        btn3.setContentAreaFilled(false);
        btn3.setBorder(new LineBorder(Color.BLACK));
        btn3.setVisible(false);
        btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn3.addActionListener((e) -> {

            for (int i = 0;i < sNames.length;i++) System.out.println((i+1) + ". " + sNames[i] + "[" + sNumbers[i] + "]");
        });
        add(btn3);

        btn4 = new JButton("Uložit do textového souboru");
        btn4.setBounds(25,200,550,30);
        btn4.setFont(btnFont2);
        btn4.setFocusPainted(false);
        btn4.setContentAreaFilled(false);
        btn4.setBorder(new LineBorder(Color.BLACK));
        btn4.setVisible(false);
        btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn4.addActionListener((e) -> {

            File export = new File("./export.txt");

            try {
                FileWriter fw = new FileWriter(export);

                for (int i = 0;i < sNumbers.length;i++) {
                    fw.write(sNames[i]+"["+ sNumbers[i] + "]\n");
                }

                fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        add(btn4);

        btn5 = new JButton("Uložit do HTML souboru");
        btn5.setBounds(25,235,550,30);
        btn5.setFont(btnFont2);
        btn5.setFocusPainted(false);
        btn5.setContentAreaFilled(false);
        btn5.setBorder(new LineBorder(Color.BLACK));
        btn5.setVisible(false);
        btn5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn5.addActionListener((e) -> {

            File export = new File("./export.html");

            try {
                FileWriter fw = new FileWriter(export);

                fw.write("<!DOCTYPE html><html>" +
                            "<head>" +
                                "<meta charset=\"UTF-8\"><title>EXPORT</title>" +
                            "<head>" +
                            "<style>table{border-collapse: collapse;width: 50vw;min-width: 20vh;} td{border: 1px solid black;padding: 1vh;text-align: center;}</style>" +
                            "<body>" +
                                "<table>");
                for (int i = 0;i < sNumbers.length;i++) {
                    fw.write("<tr><td>" + sNames[i]+"</td><td>"+ sNumbers[i] + "</td></tr>");
                }
                fw.write("   </table>" +
                             "</body>" +
                        "</html>");
                fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        add(btn5);

        btn6 = new JButton("Uložit do .CSV souboru");
        btn6.setBounds(25,270,550,30);
        btn6.setFont(btnFont2);
        btn6.setFocusPainted(false);
        btn6.setContentAreaFilled(false);
        btn6.setBorder(new LineBorder(Color.BLACK));
        btn6.setVisible(false);
        btn6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn6.addActionListener((e) -> {

            File export = new File("./export.csv");

            try {
                FileWriter fw = new FileWriter(export);

                fw.write("slovo,pocet\n");

                for (int i = 0;i < sNumbers.length;i++) {
                    fw.write(sNames[i]+","+ sNumbers[i] + "\n");
                }

                fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        add(btn6);
    }

    void setSelect() {

        select = new ButtonGroup();

        btn1 = new JRadioButton("Vložit samotný text");
        btn1.setSelected(true);
        btn1.setBounds(25,125,550,30);
        btn1.setFont(btnFont2);
        btn1.setFocusPainted(false);
        select.add(btn1);
        btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btn1);

        btn2 = new JRadioButton("Vložit cestu k textovému souboru");
        btn2.setSelected(false);
        btn2.setBounds(25,155,550,30);
        btn2.setFont(btnFont2);
        btn2.setFocusPainted(false);
        select.add(btn2);
        btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btn2);
    }

    // Source
    void setSource() {

        scroll = new ScrollPane();
        scroll.setBounds(25,155,550,325);

        input1 = new JTextArea();
        input1.setSize(new Dimension(550,2147480000));
        input1.setBorder(new LineBorder(Color.BLACK,1));
        input1.setFont(btnFont2);
        scroll.add(input1);
        scroll.setVisible(false);

        input2 = new JTextField("C:\\\\path\\\\path\\\\file.txt");
        input2.setBounds(25,155,550,30);
        input2.setFont(btnFont2);
        input2.setVisible(false);
        input2.setBorder(new LineBorder(Color.BLACK,1));
        add(input2);

        add(scroll);
    }

    // Next Button
    void setNext() {

        nextBtn = new JButton("Potvrdit");
        nextBtn.setBounds(375,525,200,50);
        nextBtn.setFont(btnFont);
        nextBtn.setFocusPainted(false);
        nextBtn.setBorder(new LineBorder(Color.BLACK,1));
        nextBtn.setContentAreaFilled(false);
        nextBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        nextBtn.addActionListener(e -> nextPhase());

        add(nextBtn);
    }

    // Nav
    void setNav() {

        label1 = new JLabel("Nastavení");
        label1.setBounds(0,0,200,100);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setFont(navFont);
        add(label1);

        label2 = new JLabel("Zdroj");
        label2.setBounds(200,0,200,100);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setFont(navFont);
        add(label2);

        label3 = new JLabel("Výseledek");
        label3.setBounds(400,0,200,100);
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setFont(navFont);
        add(label3);
    }

    public final Color BLUE = new Color(0x056277);

    // Posunutí fáze
    void nextPhase() {

        phase++;

        // Nav
        if (phase == 1) {
            label1.setForeground(BLUE);
        } else if (phase == 2) {
            label1.setForeground(Color.BLACK);
            label2.setForeground(BLUE);
        } else if (phase == 3) {
            label2.setForeground(Color.BLACK);
            label3.setForeground(BLUE);

            nextBtn.setVisible(false);
        }

        // Select
        if (phase == 1) {
            btn1.setVisible(true);
            btn2.setVisible(true);
        } else if (phase == 2) {
            btn1.setVisible(false);
            btn2.setVisible(false);
        }

        // Source
        if (phase == 2) {
            if (btn1.isSelected()) {
                scroll.setVisible(true);
            } else if (btn2.isSelected()) {
                input2.setVisible(true);
            }
        } else if (phase == 3) {
            if (btn1.isSelected()) {
                scroll.setVisible(false);

                // GET RAW DATA
                raw = input1.getText().toLowerCase().split("\\W+");

                formatData(raw);

                sortData();
            } else if (btn2.isSelected()) {

                input2.setVisible(false);

                // GET RAW DATA

                Scanner sc = null;

                try {

                    sc = new Scanner(new File(input2.getText()));

                } catch (Exception e) {

                    System.out.println("Chyba");
                    System.exit(0);
                }

                String beforeRAW = "";

                while (sc.hasNextLine()) {

                    beforeRAW += sc.nextLine() + " ";
                }

                raw = beforeRAW.toLowerCase().split("\\W+");

                formatData(raw);

                sortData();
            }
        }

        // Export

        if (phase == 3) {
            label4.setVisible(true);
            btn3.setVisible(true);
            btn4.setVisible(true);
            btn5.setVisible(true);
            btn6.setVisible(true);
        }

        repaint();
    }

    // Formatting Data
    String[] names;
    int[] numbers;

    void formatData(String[] s) {

        String[] na = new String[s.length];
        int[] nu = new int[s.length];
        int originals = 0;

        na[0] = s[0];
        nu[0]++;
        originals++;

        for (int i = 1; i < s.length; i++) {

            boolean before = false;

            for (int j = 0;j < originals;j++) {

                // not original
                if (s[i].equals(na[j])) {

                    nu[j]++;
                    before = true;
                }
            }

            // its original
            if (!before) {

                na[originals] = s[i];
                nu[originals]++;
                originals++;
            }
        }

        names = new String[originals];
        numbers = new int[originals];

        for (int i = 0;i < originals;i++) {

            names[i] = na[i];
            numbers[i] = nu[i];
        }
    }

    String[] sNames;
    int[] sNumbers;

    // Sort Data
    void sortData() {

        final int length = names.length;

        boolean[] ignored = new boolean[length];
        int[] ignoredNum = new int[length];
        int max;

        int end = 0;

        for (boolean x:
             ignored) {
            x = false;
        }

        // 1. projde se celé pole
        for(int i = 0;i < length;i++) {

            // 2. nové maximum
            max = 0;

            for (int j = 0;j < numbers.length;j++) {

                if (numbers[j] > max && !ignored[j]) {

                    max = numbers[j];
                    ignored[j] = true;
                    ignoredNum[i] = j;
                }
            }




            if (i > 0) {
                if (names[ignoredNum[i]].equals(names[ignoredNum[i-1]])) {

                    break;
                }
            }

            end++;
        }

        // 3. Výpis

        sNames = new String[end];
        sNumbers = new int[end];

        for (int i = 0;i < end;i++) {

            sNames[i] = names[ignoredNum[i]];
            sNumbers[i] = numbers[ignoredNum[i]];
        }
    }
}
