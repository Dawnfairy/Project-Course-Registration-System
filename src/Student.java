import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Student {

    JLabel label1 = new JLabel("Yüklemek istediğiniz pdf'in dosya yolunu giriniz.");
    JButton button1 = new JButton("Yükle");
    JTextField textField1 = new JTextField();
    JButton button2 = new JButton("Ders Bilgileri");

    JButton button3 = new JButton("Ders Seçimi");

    JButton button4 = new JButton("Talep İptal");

    JButton button5 = new JButton("Gelen Talepler");

    static JDialog dialog;

    static int index;
    String pdfPath;
    String text;

    boolean flag;

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {  // kullanmaya gerek yok ancak yinede ekledim
        this.pdfPath = pdfPath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    int gelenIndex;

    int userIdStudent;
    public Student(int usernameStudent, int passwordStudent, JFrame frame) {


        userIdStudent = usernameStudent;

        frame.setTitle(String.valueOf(userIdStudent)+ " Numaralı Öğrenci");

        label1.setBounds(50,50,300,30);
        textField1.setBounds(50,80,300,30);
        button1.setBounds(360,80,100,30);
        button2.setBounds(50,200,200,30);
        button3.setBounds(50,250,200,30);
        button4.setBounds(50,350,200,30);
        button5.setBounds(50,300,200,30);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pdfPath = textField1.getText();
                String text = null;
                text =   pdfToText(pdfPath,text);

                if(text != null)
                {
                    PgsqlConnection db = new PgsqlConnection("YazLab1_1","postgres","12345");
                    db.pdfSet(text,userIdStudent);
                }

                else {
                    System.out.println("nullll");
                }


            }
        });


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PgsqlConnection db = new PgsqlConnection("YazLab1_1","postgres","12345");
                db.studentInformation(userIdStudent);

                JTextArea textArea = new JTextArea(10, 40);

                ArrayList<String> studentCourseName = new ArrayList<>();
                ArrayList<String> studentLetterGrade = new ArrayList<>();

                studentCourseName.addAll(db.getStudentCourseName());
                studentLetterGrade.addAll(db.getStudentLetterGrade()) ;

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < studentCourseName.size(); i++) {
                    sb.append("Ders adı: ").append(studentCourseName.get(i)).append("   Harf Notu: ").append(studentLetterGrade.get(i)).append("\n");                }


                textArea.setText(sb.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                JOptionPane.showMessageDialog(frame, scrollPane, "Uzun Bilgiler", JOptionPane.INFORMATION_MESSAGE);


            }
        });


        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog = new JDialog(frame, " Ders Listesi ", true);

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                ArrayList<String> name= new ArrayList<>();
                ArrayList<Integer> id= new ArrayList<>();
                PgsqlConnection conn = new PgsqlConnection("YazLab1_1", "postgres", "12345");
                JButton lessonButton1 = new JButton("Proje Ders1 Detay");
                lessonButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        name.clear();
                        id.clear();
                        dialog.setVisible(false);
                        conn.teacherCourseInformation("Proje Ders1", name, id);
                        addLessonAndButton(name,id,"Proje Ders1");
                    }
                });


                panel.add(lessonButton1);

                JButton lessonButton2 = new JButton("Proje Ders2 Detay");
                lessonButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        name.clear();
                        id.clear();
                        dialog.setVisible(false);
                        conn.teacherCourseInformation("Proje Ders2", name, id);
                        addLessonAndButton(name,id,"Proje Ders2");


                    }
                });

                panel.add(lessonButton2);

                JButton lessonButton3 = new JButton("Proje Ders3 Detay");
                lessonButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        name.clear();
                        id.clear();
                        dialog.setVisible(false);
                        conn.teacherCourseInformation("Proje Ders3", name, id);
                        addLessonAndButton(name,id,"Proje Ders3");
                    }
                });

                panel.add(lessonButton3);
                dialog.add(panel);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

            }
        });


        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                PgsqlConnection db = new PgsqlConnection();
                ArrayList<Integer> hocaNo = new ArrayList<>();
                ArrayList<String> dersler = new ArrayList<>();
                ArrayList<String> talepAnlasmaDurum = new ArrayList<>();
                db.talepInformation(hocaNo, dersler, userIdStudent,talepAnlasmaDurum);

                DefaultListModel<String> model = new DefaultListModel<>();
                for (int i = 0; i < hocaNo.size(); i++) {
                    db.teacherInformation(hocaNo.get(i));
                    if(talepAnlasmaDurum.get(i).equals("beklemede"))
                    {
                        model.addElement("Öğretmen ismi: " + db.getTeacherName() + " "  + db.teacherSurname + ", Ders: " + dersler.get(i));
                    }
                }

                JList<String> list = new JList<>(model);

                JScrollPane scrollPane = new JScrollPane(list);

                JPanel panel = new JPanel();
                panel.add(scrollPane);

                JButton iptalButton = new JButton("İptal Et");
                iptalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        db.talepDurumSet("iptal",dersler.get(list.getSelectedIndex()),userIdStudent,hocaNo.get(list.getSelectedIndex()));
                        //   db.talepSil(userIdStudent,hocaNo.get(list.getSelectedIndex()),dersler.get(list.getSelectedIndex()));
                        hocaNo.remove(list.getSelectedIndex());
                        dersler.remove(list.getSelectedIndex());
                        model.remove(list.getSelectedIndex());


                    }
                });
                panel.add(iptalButton);

                JFrame frame = new JFrame();
                frame.add(panel);
                frame.setSize(300,210);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);


            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<Integer> teacherId = new ArrayList<>();
                ArrayList<String> course = new ArrayList<>();
                ArrayList<String> talepDurum = new ArrayList<>();

                PgsqlConnection conn = new PgsqlConnection();
                JFrame newFrame = new JFrame("Gelen Talepler");
                JPanel newPanel = new JPanel();

                conn.talepOgretmenInformation(userIdStudent,teacherId,talepDurum,course);
                DefaultListModel<String> listModel = new DefaultListModel<>();
                JList<String> jList = new JList<>(listModel);
                JScrollPane scrollPane = new JScrollPane(jList);
                newPanel.add(scrollPane);
                for (int i = 0; i < teacherId.size(); i++) {

                    if(talepDurum.get(i).equals("beklemede"))
                    {
                        conn.teacherInformation(teacherId.get(i));
                        listModel.addElement("Öğretmen isim: " + conn.teacherName + " " + conn.teacherSurname + " Ders: " + course.get(i));
                    }

                }

                JButton acceptButton = new JButton("Kabul Et");
                acceptButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        PgsqlConnection db = new PgsqlConnection();
                        int index = jList.getSelectedIndex();
                        db.teacherInformation(teacherId.get(index));

                        if(db.teacherQuota>db.studentCounter)
                        {
                            db.studentInformation(userIdStudent);
                            db.insertStudentCourse(userIdStudent,course.get(index),"Süren çalışma");
                            db.studentAnlasmaSay++;
                            db.setStudentAnlasmaSay(db.studentAnlasmaSay,usernameStudent);
                            db.ogretmentalepDurumSet("kabul",course.get(index),teacherId.get(index),userIdStudent);
                            db.updateStudentCounter(teacherId.get(index));

                            listModel.remove(index);
                            course.remove(index);
                            teacherId.remove(index);

                        }
                        else{
                            JOptionPane.showConfirmDialog(null,"Kontenjan Doldu","Kontenjan",JOptionPane.ERROR_MESSAGE);

                        }


                    }
                });

                newPanel.add(acceptButton);
                newFrame.add(newPanel);
                newFrame.setSize(300, 210);
                newFrame.setVisible(true);
                newFrame.setLocationRelativeTo(null);



            }
        });




        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setSize(500,500);
        messagePanel.setBounds(1000,50,200,400);

        JLabel label = new JLabel("Gelen Kutusu");
        messagePanel.add(label,BorderLayout.NORTH);

        PgsqlConnection db = new PgsqlConnection("YazLab1_1","postgres","12345");
        db.messageBox(userIdStudent);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String gelenKutusu : db.getGelenIsim()) {
            listModel.addElement(gelenKutusu);
        }

        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    gelenIndex = list.getSelectedIndex();
                    JOptionPane.showMessageDialog(null, db.getGelenMesaj().get(gelenIndex), db.getGelenIsim().get(gelenIndex), JOptionPane.INFORMATION_MESSAGE);

                }

            }
        });

        JScrollPane scrollPane = new JScrollPane(list);
        messagePanel.add(scrollPane, BorderLayout.CENTER);



        JPanel buttonPanel = new JPanel(new FlowLayout()); // Yeni bir panel oluşturuldu
        JButton button = new JButton("Seç");

        //buttonPanel.add(button);
        messagePanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(messagePanel);



        frame.add(button3);

        frame.add(label1);
        frame.add(button1);
        frame.add(textField1);
        frame.add(button2);
        frame.add(button4);
        frame.add(button5);


    }

    public String pdfToText(String pdfPath,String text)
    {

        pdfPath = pdfPath.replace("\\", "\\\\");


        File file = new File(pdfPath);

        if (file.exists()) {


            PDDocument document = null;
            try {
                document = PDDocument.load(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            PDFTextStripper pdfTextStripper = null;
            try {
                pdfTextStripper = new PDFTextStripper();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                text = pdfTextStripper.getText(document);
                // System.out.println(text);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } else {
            JOptionPane.showMessageDialog(null, "Pdf dosyası bulunamadı.", "Uyarı", JOptionPane.WARNING_MESSAGE);

        }
        return text;

    }

    private  void addLessonAndButton(ArrayList<String> name,ArrayList<Integer> id,String dersAd) {
        JFrame frame = new JFrame("Dersi Veren Öğretmenler");
        JPanel mainPanel = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String teacherName : name) {
            listModel.addElement(teacherName);
        }

        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    index = list.getSelectedIndex();



                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout()); // Yeni bir panel oluşturuldu
        JButton button = new JButton("Talep Gönder");


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean bayrak = false;
                frame.setVisible(false);

                System.out.println(index);
                System.out.println(id.get(index) + " " + userIdStudent);
                PgsqlConnection db = new PgsqlConnection();
                db.adminInformation();
                int talepEdilebilecekMiktar =  db.getAdminTalepMiktar();
                int talepMiktar = db.talepSay(userIdStudent,dersAd);

                if(talepEdilebilecekMiktar>talepMiktar)
                {
                    db.talep(userIdStudent,id.get(index),"beklemede",dersAd);

                } else {
                    JOptionPane.showConfirmDialog(null,"Bu dersi en fazla " + talepEdilebilecekMiktar + " farklı hocadan alabilirsin.","Talep Geçersiz",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton button2 = new JButton("Öğrencinin İlgi Alanına Göre Filtrele");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> teacherInterest = new ArrayList<>();
                ArrayList<Integer> teacherId = new ArrayList<>();
                PgsqlConnection  conn = new PgsqlConnection("YazLab1_1", "postgres", "12345");
                conn.studentInformation(userIdStudent);
                conn.findTeacherInterest(teacherInterest,teacherId);
                listModel.clear();


                for (int i = 0; i < teacherInterest.size(); i++) {

                    if(teacherInterest.get(i).equals(conn.studentInterest))
                    {
                        int index = id.indexOf(teacherId.get(i));

                        if(index!=-1)
                        {
                            listModel.addElement(name.get(index));
                        }

                    }

                }

                JOptionPane.showConfirmDialog(null,conn.studentInterest + "'e göre filtrelendi.","Bilgi",JOptionPane.PLAIN_MESSAGE);



            }
        });

        JButton button3 = new JButton("Öğretmenin İlgi Alanlarını Gör");

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = list.getSelectedIndex();
                PgsqlConnection db = new PgsqlConnection();
                ArrayList<String> interests = new ArrayList<>();
                db.findTeacherInterest2(interests,id.get(index));
                String bilgi = "";
                for (int i = 0; i < interests.size(); i++) {
                    bilgi = bilgi + interests.get(i) + " ";
                }

                JOptionPane.showConfirmDialog(null,"Öğretmenin İlgi Alanları: "+ bilgi + ", ","Bilgi",JOptionPane.PLAIN_MESSAGE);


            }
        });

        JButton button1 = new JButton("Talep ve Mesaj Gönder");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                boolean bayrak = false;

                PgsqlConnection db = new PgsqlConnection();
                db.adminInformation();
                int talepEdilebilecekMiktar =  db.getAdminTalepMiktar();
                int talepMiktar = db.talepSay(userIdStudent,dersAd);

                if(talepEdilebilecekMiktar>talepMiktar)
                {
                    db.talep(userIdStudent,id.get(index),"beklemede",dersAd);


                int senderid = userIdStudent; ;//öğrenci id
                int receiverid = id.get(index);// seçilen öğretmen id

                String senderName ;
                String receiverName;


                PgsqlConnection db0 = new PgsqlConnection("YazLab1_1","postgres","12345");

                db0.studentInformation(senderid);
                senderName = db0.getStudentName();

                PgsqlConnection db1 = new PgsqlConnection("YazLab1_1","postgres","12345");

                db1.teacherInformation(receiverid);
                receiverName = db1.getTeacherName() + " " + db1.getTeacherSurname();

                JFrame frame11 = new JFrame("Ders Talebi Ve Mesaj Gönder");

                frame11.setSize(400, 200);

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                JLabel label = new JLabel("Alıcı : "+ receiverName);
                JTextArea mesajTextArea = new JTextArea();
                JButton gonderButton = new JButton("Ders Talebi ve Mesaj Gönder");
                mesajTextArea.setLineWrap(true);
                mesajTextArea.setWrapStyleWord(true);

                PgsqlConnection db2 = new PgsqlConnection("YazLab1_1","postgres","12345");
                db2.adminInformation();
                int character = db2.getAdminNumberOfCharacter();

                // Karakter sınırlamasını eklemek için bir DocumentFilter oluşturuyoruz.
                ((AbstractDocument) mesajTextArea.getDocument()).setDocumentFilter(new DocumentFilter() {
                    @Override
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String content = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                        if (content.length() <= character) {
                            super.replace(fb, offset, length, text, attrs);
                        }else{
                            JOptionPane.showMessageDialog(null,"Karakter sayısı aşıldı.","Uyarı", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

                gonderButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        frame.setVisible(false);

                        String message = mesajTextArea.getText();
                        System.out.println(message);
                        db2.message(senderid,receiverid,message);
                        frame11.setVisible(false);
                        JOptionPane.showMessageDialog(null,"Ders Talebi ve Mesaj Gönderildi.","Bilgilendirme", JOptionPane.INFORMATION_MESSAGE);


                    }
                });
                    panel.add(label, BorderLayout.NORTH);
                    panel.add(new JScrollPane(mesajTextArea), BorderLayout.CENTER);
                    panel.add(gonderButton, BorderLayout.SOUTH);

                    frame11.add(panel);
                    frame11.setLocationRelativeTo(null);
                    frame11.setVisible(true);
                }
                else {
                    JOptionPane.showConfirmDialog(null,"Bu dersi en fazla " + talepEdilebilecekMiktar + " farklı öğretmenden alabilirsin.","Talep Geçersiz",JOptionPane.ERROR_MESSAGE);
                }

            }
        });


        buttonPanel.add(button);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setSize(750, 200);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}