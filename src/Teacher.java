import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Teacher {

    String[] courses = {"AIT109 Atatürk İlkeleri ve İnkilap Tarihi I (UE)","BLM101 Bilgisayar Laboratuvarı I","BLM103 Bilgisayar Mühendisliğine Giriş","BLM105 Programlama I","FEF103 Lineer Cebir","FEF105 Matematik I","FEF111 Fizik I","TDB107 Türk Dili I (UE)", "AIT110 Atatürk İlkeleri ve İnkilap Tarihi II (UE)","BLM102 Bilgisayar Laboratuvarı II","BLM104 Elektrik Devre Temelleri ve Uygulamaları",
            "BLM106 Programlama II","FEF112 Fizik II","FEF114 Matematik II","TDB108 Türk Dili II (UE)","BLM207 Veri Yapıları ve Algoritmaları","BLM209 Programlama Laboratuvarı - I","BLM211 Mantıksal Tasarım ve Uygulamaları","FEF203 Diferansiyel Denklemler","*BLM213 Staj I","FEF203 Diferansiyel Denklemler",
            "MAT205 Kesikli Matematik","MUH201 Nesneye Yönelik Programlama","BLM206 Bilgisayar Organizasyonu ve Mimarisi","BLM208 Veritabanı Yönetimi","BLM210 Programlama Laboratuvarı – II","BLM212 Sistem Programlama","BLM216 Programlama Dilleri Prensipleri","MUH202 Olasılık ve Raslantı Değişkenleri","MUH204 Elektronik ve Uygulamaları","BLM302 Otomata Teorisi",
            "BLM304 Sayısal Veri İletişimi","BLM310 Web Programlama","BLM306 Yazılım Laboratuvarı - II","BLM308 Yazılım Mühendisliği","BLM320 Algoritma Çözümleme","BLM326 Bilgisayar Mühendisliğinde Matematik Uygulamalar","BLM403 Araştırma Problemleri","BLM401 Bilgisayar Ağları","BLM402 Bitirme Çalışması","BLM404 Yapay Zeka","BLM420 Akıllı Sistemler",
            "BLM431 Web Uygulama Güvenliği","BLM435 Biyoinformatiğe Giriş","BLM411 Bilgisayar Semineri"};

    String[] interest = {"Mobil","Yapay Zeka","Robotik","İşaret, Görüntü ve Ses İşleme","Makine Öğrenmesi","Bulanık Mantık","Kablosuz Ağlar"};
    JButton button1 = new JButton("İlgi Alanı Seç");
    JButton button2 = new JButton("Gelen Talepler");
    JButton button3 = new JButton("Öğrenci Görüntüle");
    JButton button4 = new JButton("Öğrenci Puanlama Formülü Oluştur");

    int userTeacherId;
    int gelenIndex;
    public Teacher(int usernameTeacher, int passwordTeacher, JFrame frame) {

        button1.setBounds(100,100,250,30);
        button2.setBounds(100,150,250,30);
        button3.setBounds(100,200,250,30);
        button4.setBounds(100,250,250,30);
        userTeacherId = usernameTeacher;
        frame.setTitle(String.valueOf(userTeacherId)+ " Numaralı Öğretmen");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jListFrame = new JFrame("İlgi Alan Tablosu");
                jListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jListFrame.setSize(300, 300);



                JList<String> list = new JList<>(interest);
                JScrollPane scrollPane = new JScrollPane(list);
                jListFrame.add(scrollPane, BorderLayout.CENTER);

                JButton getSelectedItemButton = new JButton("Seçili Öğeyi Al");
                getSelectedItemButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if (!list.isSelectionEmpty()) {
                            String selectedValue = list.getSelectedValue();
                            JOptionPane.showMessageDialog(jListFrame, "Seçilen Öğe: " + selectedValue);

                            PgsqlConnection conn = new PgsqlConnection("YazLab1_1", "postgres", "12345");
                            conn.insertTeacherInterest(usernameTeacher,selectedValue);

                        } else {
                            JOptionPane.showMessageDialog(jListFrame, "Herhangi bir öğe seçilmedi!");
                        }
                    }
                });

                JPanel panel = new JPanel();
                panel.add(getSelectedItemButton);
                jListFrame.add(panel, BorderLayout.SOUTH);
                jListFrame.setVisible(true);
                jListFrame.setLocationRelativeTo(null);


            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Integer> ogrenciNo = new ArrayList<>();
                ArrayList<String> dersler = new ArrayList<>();
                ArrayList<String> talepAnlasmaDurum = new ArrayList<>();


                PgsqlConnection conn = new PgsqlConnection();
                conn.talepInformation2(ogrenciNo,dersler,userTeacherId,talepAnlasmaDurum);

                ArrayList<Double> puan= new ArrayList<>();
                JFrame newFrame = new JFrame("Gelen Talepler");
                JPanel newPanel = new JPanel();
                DefaultListModel<String> listModel = new DefaultListModel<>();
                JList<String> jList = new JList<>(listModel);
                JScrollPane scrollPane = new JScrollPane(jList);
                for (int i = 0; i < ogrenciNo.size(); i++) {
                    conn.studentInformation(ogrenciNo.get(i));
                    if(talepAnlasmaDurum.get(i).equals("beklemede"))
                    {

                        double deger =    conn.puanHesapla(userTeacherId,ogrenciNo.get(i));
                        puan.add(deger);
                        listModel.addElement("Öğrenci Ad: " + conn.studentName + " " + conn.studentSurname + "   Ders: " + dersler.get(i) + "  formül puanı: " + String.format("%.2f", deger));
                        System.out.println(deger);
                    }
                }
                newPanel.add(scrollPane);


                JButton acceptButton = new JButton("Kabul Et");
                acceptButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        PgsqlConnection db = new PgsqlConnection();
                        db.teacherInformation(userTeacherId);
                        if(db.teacherQuota>db.studentCounter)
                        {
                            int index = jList.getSelectedIndex();
                            conn.insertStudentCourse(ogrenciNo.get(index),dersler.get(index),"Süren çalışma");
                            conn.studentAnlasmaSay++;
                            conn.setStudentAnlasmaSay(conn.studentAnlasmaSay,ogrenciNo.get(index));
                            conn.talepDurumSet("kabul",dersler.get(index),ogrenciNo.get(index),userTeacherId);


                            listModel.remove(index);
                            ogrenciNo.remove(index);
                            dersler.remove(index);
                            db.updateStudentCounter(userTeacherId);

                        }

                        else
                        {
                            JOptionPane.showConfirmDialog(null,"Kontenjan Doldu","kontenjan",JOptionPane.ERROR_MESSAGE);
                        }



                    }
                });

                JButton rejectButton = new JButton("Reddet");
                rejectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index = jList.getSelectedIndex();
                        conn.talepDurumSet("ret",dersler.get(index),ogrenciNo.get(index),userTeacherId);


                        listModel.remove(index);
                        ogrenciNo.remove(index);
                        dersler.remove(index);


                    }
                });

                JButton courseButton = new JButton("Ders Bilgisi");
                courseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index = jList.getSelectedIndex();



                        PgsqlConnection db2 = new PgsqlConnection();

                        db2.studentInformation(ogrenciNo.get(index));


                        JFrame studentInfoFrame = new JFrame();
                        String[] items  =new String[db2.studentCourseName.size()];

                        for (int i = 0; i < db2.studentCourseName.size(); i++) {
                            items[i] = db2.studentCourseName.get(i) + " " + db2.studentLetterGrade.get(i);
                        }



                        JList<String> jList = new JList<>(items);

                        // Panel oluştur
                        JPanel panel = new JPanel();
                        panel.setLayout(new BorderLayout());
                        panel.add(new JScrollPane(jList), BorderLayout.CENTER);

                        // Pencereye paneli ekle
                        studentInfoFrame.getContentPane().add(panel);

                        // JFrame ayarları
                        //  studentInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        studentInfoFrame.setSize(300, 200);
                        studentInfoFrame.setLocationRelativeTo(null);
                        studentInfoFrame.setVisible(true);


                    }
                });

                newPanel.add(acceptButton);
                newPanel.add(rejectButton);
                newPanel.add(courseButton);
                newFrame.add(newPanel);
                newFrame.setSize(300, 250);
                newFrame.setVisible(true);
                newFrame.setLocationRelativeTo(null);



            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> courseName = new ArrayList<>();
                PgsqlConnection db = new PgsqlConnection();
                JFrame frame = new JFrame("Öğrenci Görüntüle");
                frame.setLayout(new FlowLayout());
                db.teacherCourseInformation(userTeacherId,courseName);
                ArrayList<JButton> buttonList = new ArrayList<>(); // Dinamik boyutlu bir JButton listesi oluşturuyoruz.

                for (int i = 0; i < courseName.size(); i++) {
                    JButton button = new JButton("Proje Ders" + (i + 1));
                    buttonList.add(button);
                    frame.add(button);

                    // Her buton için ayrı bir ActionListener ekleme
                    int finalI = i;
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("Button " + (finalI + 1) + " clicked.");
                            JButton clickedButton = (JButton) e.getSource();
                            System.out.println("Clicked Button Text: " + clickedButton.getText());
                            findStudent(clickedButton.getText());


                        }
                    });
                }
                frame.setSize(400, 100); // Frame boyutu
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);// Frame'i görünür yapma

            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame;
                JPanel panel;
                JScrollPane scrollPane;
                frame = new JFrame("Ders Listesi");
                frame.setSize(400,400);
                // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                for (String course : courses) {
                    JCheckBox checkBox = new JCheckBox(course);
                    panel.add(checkBox);
                }

                scrollPane = new JScrollPane(panel);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                JButton button = new JButton("Seçili Dersleri Al");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (Component comp : panel.getComponents()) {
                            if (comp instanceof JCheckBox) {
                                JCheckBox checkBox = (JCheckBox) comp;
                                if (checkBox.isSelected()) {
                                    System.out.println(checkBox.getText());

                                    String input = JOptionPane.showInputDialog(checkBox.getText() + " dersi için katsayı yazınız");

                                    // Girişin double türüne dönüştürülüp dönüşümün doğruluğu kontrol ediliyor
                                    try {
                                        double number = Double.parseDouble(input);
                                        System.out.println(number);

                                        PgsqlConnection db = new PgsqlConnection();
                                        db.insertKriterDers(checkBox.getText(),number,userTeacherId);


                                        JOptionPane.showMessageDialog(null, "Girilen sayı: " + number);
                                    } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(null, "Geçersiz giriş. Lütfen bir sayı girin.");
                                    }

                                    // işaretlenen dersler burada alıncak



                                }
                            }
                        }
                    }
                });

                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BorderLayout());
                mainPanel.add(scrollPane, BorderLayout.CENTER);
                mainPanel.add(button, BorderLayout.SOUTH);

                frame.add(mainPanel);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setSize(500,500);
        messagePanel.setBounds(1000,50,200,400);

        JLabel label = new JLabel("Gelen Kutusu");
        messagePanel.add(label,BorderLayout.NORTH);

        PgsqlConnection db = new PgsqlConnection("YazLab1_1","postgres","12345");
        db.messageBox(usernameTeacher);

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
        JButton button = new JButton("Mesaj Gönder");


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int senderid = usernameTeacher; ;//öğretmen id
                int receiverid = db.getGelenId().get(gelenIndex);// seçilen öğrenci id

                String senderName ;
                String receiverName;

                PgsqlConnection db = new PgsqlConnection("YazLab1_1","postgres","12345");

                db.teacherInformation(senderid);
                senderName = db.getTeacherName();

                PgsqlConnection db1 = new PgsqlConnection("YazLab1_1","postgres","12345");

                db1.studentInformation(receiverid);
                receiverName = db1.getStudentName() + " " + db1.getStudentSurname();

                JFrame frame = new JFrame("Mesaj Gönder");

                frame.setSize(400, 300);

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                JLabel label = new JLabel("Alıcı : "+ receiverName);
                JTextArea mesajTextArea = new JTextArea();
                JButton gonderButton = new JButton("Gönder");
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
                            super.replace(fb, offset, length, text,  attrs);
                        }else{
                            JOptionPane.showMessageDialog(null,"karakter sayısı aşıldı","Uyarı", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

                gonderButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String message = mesajTextArea.getText();
                        db1.message(senderid,receiverid,message);
                        frame.setVisible(false);
                        JOptionPane.showMessageDialog(null,"Mesaj Gönderildi","Bilgilendirme", JOptionPane.INFORMATION_MESSAGE);


                    }
                });

                panel.add(label, BorderLayout.NORTH);
                panel.add(new JScrollPane(mesajTextArea), BorderLayout.CENTER);
                panel.add(gonderButton, BorderLayout.SOUTH);

                frame.add(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);


            }
        });

        buttonPanel.add(button);
        messagePanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(messagePanel);

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);

    }

    public void findStudent(String courseName)
    {

        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<Integer> talepEdenOgrenciId = new ArrayList<>();
        PgsqlConnection db = new PgsqlConnection();
        ArrayList<String> talepAnlasmaDurumu = new ArrayList<>();
        db.studentInformation(name,id);
        db.talepInformation(courseName,talepEdenOgrenciId,talepAnlasmaDurumu);
        ArrayList<Double> degerler = new ArrayList<>();

        ArrayList<Integer>talepEdilenOgrenci = new ArrayList<>();
        ArrayList<String>talepAnlasma = new ArrayList<>();


        db.talepOgretmenInformation(courseName,talepEdilenOgrenci,talepAnlasma);

        for (int i = 0; i < id.size(); i++) {

            if(talepEdenOgrenciId.contains(id.get(i)))
            {
                int index = talepEdenOgrenciId.indexOf(id.get(i));
                if(talepAnlasmaDurumu.get(index).equals("kabul"))
                {

                    id.remove(i);
                    name.remove(i);
                    i--;
                }
            }

            if(talepEdilenOgrenci.contains(id.get(i)))
            {
                int index = talepEdilenOgrenci.indexOf(id.get(i));
                if(talepAnlasma.get(index).equals("kabul"))
                {

                    id.remove(i);
                    name.remove(i);
                    i--;
                }
            }
        }

        JFrame frame = new JFrame("Öğrenci Listesi");


        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        String[] students= new String[id.size()];
        PgsqlConnection conn = new PgsqlConnection();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> studentList = new JList<>(listModel);
        for (int i = 0; i < id.size(); i++) {

            double deger =    conn.puanHesapla(userTeacherId,id.get(i));
            degerler.add(deger);

            listModel.addElement("Ad: " + name.get(i) + "  No: " + id.get(i) + "  formül puanı: " + String.format("%.2f", deger));

        }




        JScrollPane scrollPane = new JScrollPane(studentList);

        // Frame'e listeyi ekleme
        frame.add(scrollPane, BorderLayout.CENTER);
        DefaultListModel<String> listModel1 = new DefaultListModel<>();

        JList<String> jList = new JList<>(listModel1);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Buton oluşturma
        JButton button = new JButton("Öğrenciyi Görüntüle");
        button.addActionListener(e -> {

            frame.setVisible(false);
            PgsqlConnection db2 = new PgsqlConnection();
            int index = studentList.getSelectedIndex();

            db2.studentInformation(id.get(index));

            JFrame studentInfoFrame = new JFrame();
            String[] items  =new String[db2.studentCourseName.size()];


            for (int i = 0; i < db2.studentCourseName.size(); i++) {

                listModel1.addElement(db2.studentCourseName.get(i) + " " + db2.studentLetterGrade.get(i));
            }



            // Panel oluştur
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(new JScrollPane(jList), BorderLayout.CENTER);

            // Pencereye paneli ekle
            studentInfoFrame.getContentPane().add(panel);

            // JFrame ayarları
            //  studentInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            studentInfoFrame.setSize(350, 300);
            studentInfoFrame.setLocationRelativeTo(null);
            studentInfoFrame.setVisible(true);



        });


        JButton button2 = new JButton("Filtrele");
        JButton but = new JButton("Sırala");
        JButton talepButon = new JButton("Talep Oluştur");

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String puan = JOptionPane.showInputDialog("Lütfen alt sınır formül puanı giriniz: ");
                listModel.clear();
                degerler.clear();
                for (int i = 0; i < id.size(); i++) {

                    double deger =    conn.puanHesapla(userTeacherId,id.get(i));
                    if(Double.parseDouble(puan)<=deger)
                    {
                        listModel.addElement("Ad: " + name.get(i) + "  No: " + id.get(i) + "  formül puanı: " + String.format("%.2f", deger));
                        degerler.add(deger);
                    }

                }


            }
        });

        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int n = degerler.size();
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (degerler.get(j) < degerler.get(j + 1)) {
                            // Swap işlemi

                            Double temp = degerler.get(j+1);
                            degerler.set(j+1, degerler.get(j));
                            degerler.set(j, temp);

                            String temp1 = listModel.get(j+1);
                            listModel.set(j+1,listModel.get(j));
                            listModel.set(j,temp1);
                        }
                    }
                }


            }
        });

        talepButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                PgsqlConnection db = new PgsqlConnection();
                db.teacherInformation(userTeacherId);

                if(db.teacherQuota>db.studentCounter)
                {
                    int index =   studentList.getSelectedIndex();
                    System.out.println(index);
                    String item = listModel.getElementAt(index);
                    String[] parts = item.split("  ");
                    String noPart = parts[1].substring(4); // 'No: ' ifadesinden sonrasını alır
                    int extractedId = Integer.parseInt(noPart);
                    conn.talepOgretmen(userTeacherId,extractedId,"beklemede",courseName);

                }
                else{
                    JOptionPane.showConfirmDialog(null,"Kontenjan Doldu","kontenjan",JOptionPane.ERROR_MESSAGE);

                }




            }
        });




        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(button);
        buttonPanel.add(button2);
        buttonPanel.add(but);
        buttonPanel.add(talepButon);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }


}