import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Admin {

    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();
    JLabel label4 = new JLabel();

    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton button5 = new JButton();
    JButton button6 = new JButton();
    JButton button7 = new JButton();
    JButton button8 = new JButton();
    JButton button9 = new JButton();
    JButton button = new JButton();
    JButton button0 = new JButton();





    JTextField textField1 = new JTextField();
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    JTextField textField4 = new JTextField();
    String name ;
    String surname ;
    String info;
    String info1;
    boolean isPanelOpen;
    boolean isPanelOpen1;

    int password;
    double gpa;
    String studentInsterest;
    int secilenOgrenciId;
    int secilenOgretmenId;
    JPanel panelStudentList;
    JPanel panelTeacherList;
    JPanel panelStudentInformationList;
    JPanel panelTeacherInformationList;
    JPanel panelTeacherInterestList;
    JList<String> studentInformationJList;
    JList<String> teacherInformationJList;
    JList<Integer> studentJList;
    DefaultListModel<Integer> listModel;
    DefaultListModel<String> listModel1;
    DefaultListModel<String> listModel3;
    DefaultListModel derssler;
    JTable studentTalep;
    JScrollPane talep;
    JTable teacherTalep;
    JScrollPane talep1;


    int index;
    int index1;
    int i;
    //   int secenek;

    String[] letterGrade = {"AA", "BA", "BB", "CB", "CC", "DC", "DD", "FF"};
    String[] course = {"AIT109 Atatürk İlkeleri ve İnkilap Tarihi I (UE)","BLM101 Bilgisayar Laboratuvarı I","BLM103 Bilgisayar Mühendisliğine Giriş","BLM105 Programlama I","FEF103 Lineer Cebir","FEF105 Matematik I","FEF111 Fizik I","TDB107 Türk Dili I (UE)", "AIT110 Atatürk İlkeleri ve İnkilap Tarihi II (UE)","BLM102 Bilgisayar Laboratuvarı II","BLM104 Elektrik Devre Temelleri ve Uygulamaları",
            "BLM106 Programlama II","FEF112 Fizik II","FEF114 Matematik II","TDB108 Türk Dili II (UE)","BLM207 Veri Yapıları ve Algoritmaları","BLM209 Programlama Laboratuvarı - I","BLM211 Mantıksal Tasarım ve Uygulamaları","FEF203 Diferansiyel Denklemler","*BLM213 Staj I","FEF203 Diferansiyel Denklemler",
            "MAT205 Kesikli Matematik","MUH201 Nesneye Yönelik Programlama","BLM206 Bilgisayar Organizasyonu ve Mimarisi","BLM208 Veritabanı Yönetimi","BLM210 Programlama Laboratuvarı – II","BLM212 Sistem Programlama","BLM216 Programlama Dilleri Prensipleri","MUH202 Olasılık ve Raslantı Değişkenleri","MUH204 Elektronik ve Uygulamaları","BLM302 Otomata Teorisi",
            "BLM304 Sayısal Veri İletişimi","BLM310 Web Programlama","BLM306 Yazılım Laboratuvarı - II","BLM308 Yazılım Mühendisliği","BLM320 Algoritma Çözümleme","BLM326 Bilgisayar Mühendisliğinde Matematik Uygulamalar","BLM403 Araştırma Problemleri","BLM401 Bilgisayar Ağları","BLM402 Bitirme Çalışması","BLM404 Yapay Zeka","BLM420 Akıllı Sistemler",
            "BLM431 Web Uygulama Güvenliği","BLM435 Biyoinformatiğe Giriş","BLM411 Bilgisayar Semineri"};


    String[] interest = {"Mobil","Yapay Zeka","Robotik","İşaret, Görüntü ve Ses İşleme","Makine Öğrenmesi","Bulanık Mantık","Kablosuz Ağlar"};
    ArrayList<String> courses = new ArrayList<>();
    ArrayList<String> letters = new ArrayList<>();
    private static final String[] isimler = {"Ahmet", "Mehmet", "Ayşe", "Fatma", "Mustafa", "Emine", "Ali", "Zeynep", "Hasan", "Hüseyin", "Hatice", "Hakan", "Özlem", "Ebru", "Cem", "Serkan", "Berk", "Burcu", "Deniz", "Gizem", "Gökhan", "Feride", "Kemal", "Cihan", "Ece", "Ceren", "İpek", "Can", "Selin", "Barış", "Nur", "Gül", "Yusuf", "Ömer", "Aylin", "Bilge", "Oğuz", "Eren", "İlknur", "Murat", "Özgür", "Pınar", "Tolga", "Uğur", "Seda", "İrem", "Türker", "Zafer", "Berkay"};
    private static final String[] soyisimler = {"Yılmaz", "Kaya", "Demir", "Çelik", "Arslan", "Öztürk", "Şahin", "Özdemir", "Korkmaz", "Kara", "Bulut", "Kılıç", "Yıldırım", "Çetin", "Aslan", "Coşkun", "Keskin", "Taş", "Bakır", "Ateş", "Çetinkaya", "Erdoğan", "Türk", "Polat", "Kaplan", "Erdoğan", "Koç", "Özkan", "Karadağ", "Karahan", "Uçar", "Aydın", "Türkoğlu", "Bozkurt", "Güler", "Güner", "Acar", "Ertürk", "Karagöz", "Gündoğdu", "Köse", "Güneş", "Koçak", "Şimşek", "Doğan", "Demirci", "Özcan", "Şen", "Keser"};



    public Admin(String usernameAdmin, String passwordAdmin, JFrame frame) {


        //frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        frame.setTitle("Yönetici");

        derssler = new DefaultListModel();
        for (int i = 0; i < course.length; i++) {
            derssler.addElement(course[i]);
        }

        isPanelOpen1= false;

        label1.setBounds(50,100,250,30);
        label1.setText("öğrencinin seçebileceği hoca sayısı");
        textField1.setBounds(50,150,150,30);
        button1.setBounds(50,200,150,30);
        button1.setText("ayarla");

        label2.setBounds(50,300,250,30);
        label2.setText("mesaj karakter sayısı belirle");
        textField2.setBounds(50,350,150,30);
        button2.setBounds(50,400,150,30);
        button2.setText("ayarla");

        label3.setBounds(50, 500, 250, 30);
        label3.setText("hocalar için kontenjan belirle");
        textField3.setBounds(50, 550, 150, 30);
        button3.setBounds(50, 600, 150, 30);
        button3.setText("ayarla");

        button4.setBounds(400,410,200,30);
        button4.setText("2. Aşamaya Geç");

        button5.setBounds(400, 100, 150, 50);
        button5.setText("Öğrenci üret");

        button6.setBounds(1000, 20, 190, 40);
        button6.setText("Öğrenci Listesi");

        button7.setBounds(1210,20,190,40);
        button7.setText("Öğretmen Listesi");

        button8.setBounds(1000,350,190,30);
        button8.setText("Listeyi Kapat");

        button9.setBounds(1210,350,190,30);
        button9.setText("Listeyi Kapat");

        button.setBounds(1000,410,400,30);
        button.setText("Öğrenci-Öğretmen Taleplerini Görüntüle");

        button0.setBounds(1000,730,400,30);
        button0.setText("Talep Ekle");


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PgsqlConnection db = new PgsqlConnection();
                if(isPanelOpen1){

                    studentTalep.setModel(db.tableModelTalepStudent());
                    teacherTalep.setModel(db.tableModelTalepTeacher());
                    frame.revalidate();

                }else {

                    isPanelOpen1 = true;

                    JPanel panel = new JPanel(new BorderLayout());
                    panel.setBounds(1000, 440, 400, 30);

                    JLabel label = new JLabel("Öğrenci Talepleri");

                    panel.add(label, BorderLayout.CENTER);

                    studentTalep = new JTable(db.tableModelTalepStudent());
                    talep = new JScrollPane(studentTalep);
                    talep.setBounds(1000, 470, 400, 100);


                    JPanel panel1 = new JPanel(new BorderLayout());
                    panel1.setBounds(1000, 580, 400, 30);

                    JLabel label1 = new JLabel("Öğretmen-Yönetici Talepleri");

                    panel1.add(label1, BorderLayout.CENTER);

                    teacherTalep = new JTable(db.tableModelTalepTeacher());
                    talep1 = new JScrollPane(teacherTalep);
                    talep1.setBounds(1000, 610, 400, 100);


                    frame.add(panel);
                    frame.add(talep);
                    frame.add(panel1);
                    frame.add(talep1);

                    frame.revalidate();


                }

            }
        });

        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PgsqlConnection db = new PgsqlConnection("YazLab1_1","postgres","12345");
                db.studentList();
                db.teacherList();

                JFrame selectionFrame = new JFrame("Öğrenciye Ders Ekleme");
                JComboBox<Integer> studentComboBox = new JComboBox<>();
                for(Integer studentId : db.studentIdList) {
                    studentComboBox.addItem(studentId);
                }

                JComboBox<Integer> teacherComboBox = new JComboBox<>();
                for(Integer teacherId : db.teacherIdList) {
                    teacherComboBox.addItem(teacherId);
                }

                JComboBox<String> courseComboBox = new JComboBox<>(new String[]{"Proje Ders1", "Proje Ders2", "Proje Ders3"});
                JButton ekleButton = new JButton("Talep Ekle");

                ekleButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        selectionFrame.setVisible(false);
                        Integer selectedStudent = (Integer) studentComboBox.getSelectedItem();
                        Integer selectedTeacher = (Integer) teacherComboBox.getSelectedItem();
                        String selectedCourse = (String) courseComboBox.getSelectedItem();
                        //PgsqlConnection db = new PgsqlConnection("YazLab1_1","postgres","12345");

                        db.teacherInformation(selectedTeacher);
                        if(db.teacherQuota>db.studentCounter)
                        {
                            db.insertStudentCourse(selectedStudent,selectedCourse,"Süren çalışma");
                            db.studentAnlasmaSay++;
                            db.setStudentAnlasmaSay(db.studentAnlasmaSay,selectedStudent);
                            db.talepOgretmen(selectedTeacher,selectedStudent,"kabul",selectedCourse);

                            db.updateStudentCounter(selectedTeacher);
                        } else {

                            JOptionPane.showConfirmDialog(null,"Kontenjan Dolu","Kontenjan",JOptionPane.ERROR_MESSAGE);

                        }


                        if(isPanelOpen1){

                            studentTalep.setModel(db.tableModelTalepStudent());
                            teacherTalep.setModel(db.tableModelTalepTeacher());
                            frame.revalidate();

                        }
                    }
                });

                selectionFrame.setLayout(new GridLayout(4, 2));
                selectionFrame.add(new JLabel("Öğrenci Seç: "));
                selectionFrame.add(studentComboBox);
                selectionFrame.add(new JLabel("Öğretmen Seç: "));
                selectionFrame.add(teacherComboBox);
                selectionFrame.add(new JLabel("Ders Seç: "));
                selectionFrame.add(courseComboBox);
                selectionFrame.add(ekleButton);
                selectionFrame.setSize(400, 200);
                selectionFrame.setVisible(true);
                selectionFrame.setLocationRelativeTo(null);

            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int talepMiktar = Integer.parseInt(textField1.getText());

                PgsqlConnection db = new PgsqlConnection();
                db.updateAdminTalepMiktar(talepMiktar);


            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String character = textField2.getText();
                PgsqlConnection pg = new PgsqlConnection("YazLab1_1","postgres","12345");
                pg.characterSet(Integer.parseInt(character));
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String quota = textField3.getText();
                PgsqlConnection pg = new PgsqlConnection("YazLab1_1","postgres","12345");
                pg.quotaSet(Integer.parseInt(quota));

            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button4.setVisible(false);


                JLabel jLabel = new JLabel("2. Aşamaya Geçildi!");
                JButton button1 = new JButton("Random Atama Yap");
                JButton button2 = new JButton("Ortalamaya Göre Atama Yap");
                JButton button3 = new JButton("Belirli Derslere Göre Atama Yap");
                jLabel.setBounds(400,410,250,30);
                button1.setBounds(400,450,250,30);
                button2.setBounds(400,500,250,30);
                button3.setBounds(400,550,250,30);

                PgsqlConnection db = new PgsqlConnection();
                ArrayList<Integer> studentId = new ArrayList<>();
                ArrayList<String> studentName = new ArrayList<>();
                ArrayList<Integer> teacherId = new ArrayList<>();
                ArrayList<Double> gpa = new ArrayList<>();
                teacherId.add(11111);
                teacherId.add(22222);
                teacherId.add(33333);
                teacherId.add(44444);

                ArrayList<String> course = new ArrayList<>();
                course.add("Proje Ders1");
                course.add("Proje Ders2");
                course.add("Proje Ders3");

                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        db.studentInformation(studentName,studentId);


                        for (int i = 0; i < studentId.size(); i++) {

                            db.studentInformation(studentId.get(i));
                            for (int j = 0; j < db.studentLetterGrade.size(); j++) {

                                if(db.studentLetterGrade.get(j).equals("Süren çalışma"))
                                {
                                    System.out.println(studentId.get(i) + " " + studentName.get(i));
                                    studentId.remove(i);
                                    studentName.remove(i);
                                    break;

                                }

                            }
                        }


                        Random random = new Random();
                        int rnd;
                        for (int i = 0; i < studentId.size(); i++) {



                            if(teacherId.size() == 0)
                            {
                                JOptionPane.showConfirmDialog(null,"Tüm hocaların kontenjanı doldu","kontenjan doldu",JOptionPane.PLAIN_MESSAGE);
                            }else{


                            int randomNumberInt = random.nextInt(teacherId.size());
                            db.teacherInformation(teacherId.get(randomNumberInt));

                            if(db.studentCounter>=db.teacherQuota)
                            {
                                System.out.println(db.studentCounter + " " + db.teacherQuota);
                                teacherId.remove(randomNumberInt);
                                i--;
                            }
                            else
                            {
                                rnd = random.nextInt(3);
                                db.updateStudentCounter(teacherId.get(randomNumberInt));
                                db.insertStudentCourse(studentId.get(i),course.get(rnd),"süren çalışma");
                                db.talep(studentId.get(i),teacherId.get(randomNumberInt),"otomatik atama",course.get(rnd));
                            }
                            }
                        }


                        if(isPanelOpen1){

                           // PgsqlConnection db = new PgsqlConnection();

                            studentTalep.setModel(db.tableModelTalepStudent());
                            teacherTalep.setModel(db.tableModelTalepTeacher());
                            frame.revalidate();

                        }
                    }
                });

                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button1.setVisible(false);
                        button2.setVisible(false);
                        button3.setVisible(false);
                        db.studentInformation(studentName,studentId);

                        for (int i = 0; i < studentId.size(); i++) {

                            db.studentInformation(studentId.get(i));
                            for (int j = 0; j < db.studentLetterGrade.size(); j++) {

                                if(db.studentLetterGrade.get(j).equals("Süren çalışma"))
                                {
                                    System.out.println(studentId.get(i) + " " + studentName.get(i));
                                    studentId.remove(i);
                                    studentName.remove(i);
                                    break;

                                }

                            }
                        }


                        for (int i = 0; i < studentId.size(); i++) {

                            db.studentInformation(studentId.get(i));
                            gpa.add(db.studentGpa);


                        }

                        for (int j = 0; j < gpa.size(); j++) {

                            for (int i = 0; i < gpa.size()-1-j; i++) {

                                if(gpa.get(i)<gpa.get(i+1))
                                {
                                    Collections.swap(gpa,i,i+1);
                                    Collections.swap(studentId,i,i+1);

                                }

                            }

                        }


                        JPanel panel = new JPanel(new GridLayout(5,3,5,5));

                        // Label ve TextField'ları ekle
                        JLabel label1 = new JLabel("İbrahim Şahan:");
                        JTextField textField1 = new JTextField();
                        JLabel label2 = new JLabel("Abdurrahman Gün:");
                        JTextField textField2 = new JTextField();
                        JLabel label3 = new JLabel("Furkan Göz:");
                        JTextField textField3 = new JTextField();
                        JLabel label4 = new JLabel("Suhap Şahin:");
                        JTextField textField4 = new JTextField();
                        JLabel label5 = new JLabel("Sırayı Belirle ve Atamayı Başlat");

                        panel.setBounds(400,450,350,150);
                        panel.add(label1);
                        panel.add(textField1);
                        panel.add(label2);
                        panel.add(textField2);
                        panel.add(label3);
                        panel.add(textField3);
                        panel.add(label4);
                        panel.add(textField4);
                        panel.add(label5);
                        //panel.setBackground(Color.pink);

                        // Kaydet butonu ekle
                        JButton button = new JButton("Başlat");
                        panel.add(button);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                panel.setVisible(false);
                                ArrayList<Integer> newTeacherId = new ArrayList<>();
                                newTeacherId.add(0);
                                newTeacherId.add(0);
                                newTeacherId.add(0);
                                newTeacherId.add(0);

                                newTeacherId.set(Integer.parseInt(textField1.getText())-1,teacherId.get(0));
                                newTeacherId.set(Integer.parseInt(textField2.getText())-1,teacherId.get(1));
                                newTeacherId.set(Integer.parseInt(textField3.getText())-1,teacherId.get(2));
                                newTeacherId.set(Integer.parseInt(textField4.getText())-1,teacherId.get(3));


                                System.out.println(newTeacherId.get(0) + "  " +newTeacherId.get(1) + "  " +newTeacherId.get(2) + "  " +newTeacherId.get(3));
                                int sayac = 0;
                                for (int i = 0; i < studentId.size(); i++) {

                                    Random random = new Random();
                                    int   rnd = random.nextInt(3);


                                    if( newTeacherId.size() != 0 &&i%newTeacherId.size()==0)
                                    {
                                        db.teacherInformation(newTeacherId.get(0));
                                        if(db.studentCounter<db.teacherQuota)
                                        {
                                            db.insertStudentCourse(studentId.get(i),course.get(rnd),"süren çalışma");
                                            db.talep(studentId.get(i),newTeacherId.get(0),"otomatik atama",course.get(rnd));
                                            db.updateStudentCounter(newTeacherId.get(0));
                                        }
                                        else
                                        {
                                            newTeacherId.remove(0);
                                            sayac++;
                                            i--;
                                        }

                                    }

                                    else if(newTeacherId.size() != 0 && i%newTeacherId.size()==1)
                                    {
                                        db.teacherInformation(newTeacherId.get(1));
                                        if(db.studentCounter<db.teacherQuota)
                                        {
                                            db.insertStudentCourse(studentId.get(i),course.get(rnd),"süren çalışma");
                                            db.talep(studentId.get(i),newTeacherId.get(1),"otomatik atama",course.get(rnd));
                                            db.updateStudentCounter(newTeacherId.get(1));
                                        }
                                        else
                                        {
                                            newTeacherId.remove(1);
                                            sayac++;
                                            i--;
                                        }

                                    }
                                    else if(newTeacherId.size() != 0 &&i%newTeacherId.size()==2)
                                    {
                                        db.teacherInformation(newTeacherId.get(2));
                                        if(db.studentCounter<db.teacherQuota)
                                        {
                                            db.insertStudentCourse(studentId.get(i),course.get(rnd),"süren çalışma");
                                            db.talep(studentId.get(i),newTeacherId.get(2),"otomatik atama",course.get(rnd));
                                            db.updateStudentCounter(newTeacherId.get(2));
                                        }
                                        else
                                        {
                                            newTeacherId.remove(2);
                                            sayac++;
                                            i--;
                                        }

                                    }
                                    else if(newTeacherId.size() != 0 &&i%newTeacherId.size()==3)
                                    {
                                        db.teacherInformation(newTeacherId.get(3));
                                        if(db.studentCounter<db.teacherQuota)
                                        {
                                            db.insertStudentCourse(studentId.get(i),course.get(rnd),"süren çalışma");
                                            db.talep(studentId.get(i),newTeacherId.get(3),"otomatik atama",course.get(rnd));
                                            db.updateStudentCounter(newTeacherId.get(3));
                                        }
                                        else
                                        {
                                            newTeacherId.remove(3);
                                            sayac++;
                                            i--;
                                        }

                                    }
                                    else
                                    {
                                        JOptionPane.showConfirmDialog(null,"Tüm hocaların Kontenjanı doldu","Kontenjan doldu",JOptionPane.PLAIN_MESSAGE);
                                    }


                                }

                                if(isPanelOpen1){


                                    System.out.println("sfdjghgkjhkh");
                                    studentTalep.setModel(db.tableModelTalepStudent());
                                    teacherTalep.setModel(db.tableModelTalepTeacher());
                                    frame.revalidate();

                                }

                            }
                        });

                        frame.add(panel);
                        frame.setVisible(true);


                    }
                });

                final int[] sayac = {0};
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<Double> carpan = new ArrayList<>();
                        ArrayList<String> formulDers = new ArrayList<>();
                        button3.setVisible(false);
                        button2.setVisible(false);
                        button1.setVisible(false);
                        JList<String> courseList = new JList<>(derssler);
                        JScrollPane scrollPane = new JScrollPane(courseList);
                        scrollPane.setBounds(400, 450, 300, 200);


                        JButton button = new JButton("seç");
                        JButton button2 = new JButton("bitir");
                        button.setBounds(460, 670, 80, 30);
                        button2.setBounds(550, 670, 80, 30);
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                if(sayac[0] < 5)
                                {


                                    String s =  JOptionPane.showInputDialog(null,"lütfen ders için katsayı giriniz","formül",JOptionPane.PLAIN_MESSAGE);
                                    if (s != null && !s.trim().isEmpty()) {
                                        try {
                                            formulDers.add((String) derssler.getElementAt(courseList.getSelectedIndex()));
                                            carpan.add(Double.parseDouble(s));
                                            sayac[0]++;
                                        } catch (NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(null, "Lütfen geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else if (s == null) {
                                        // Kullanıcı iptal etti
                                        JOptionPane.showMessageDialog(null, "İptal edildi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        // Kullanıcı boş giriş yaptı
                                        JOptionPane.showMessageDialog(null, "Boş giriş yapıldı, lütfen bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                else
                                {
                                    JOptionPane.showConfirmDialog(null,"en fazla 5 ders seçebilirsiniz","ders seçimi bitti",JOptionPane.PLAIN_MESSAGE);
                                }

                            }
                        });


                        button2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                button2.setVisible(false);
                                button.setVisible(false);
                                scrollPane.setVisible(false);
                                courseList.setVisible(false);

                                JPanel panel = new JPanel(new GridLayout(5,3,0,5));

                                // Label ve TextField'ları ekle
                                JLabel label1 = new JLabel("İbrahim Şahan:");
                                JTextField textField1 = new JTextField();
                                JLabel label2 = new JLabel("Abdurrahman Gün:");
                                JTextField textField2 = new JTextField();
                                JLabel label3 = new JLabel("Furkan Göz:");
                                JTextField textField3 = new JTextField();
                                JLabel label4 = new JLabel("Suhap Şahin:");
                                JTextField textField4 = new JTextField();
                                JLabel label5 = new JLabel("Sırayı Belirle ve Atamayı Başlat");

                                panel.setBounds(400,450,350,150);
                                panel.add(label1);
                                panel.add(textField1);
                                panel.add(label2);
                                panel.add(textField2);
                                panel.add(label3);
                                panel.add(textField3);
                                panel.add(label4);
                                panel.add(textField4);
                                panel.add(label5);

                                // Kaydet butonu ekle
                                JButton button = new JButton("Başlat");
                                panel.add(button);


                                button.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        panel.setVisible(false);
                                        ArrayList<Double> gpa = new ArrayList<>();
                                        ArrayList<Integer> newTeacherId = new ArrayList<>();
                                        newTeacherId.add(0);
                                        newTeacherId.add(0);
                                        newTeacherId.add(0);
                                        newTeacherId.add(0);

                                        PgsqlConnection db = new PgsqlConnection();
                                        db.studentInformation(studentName,studentId);


                                        for (int i = 0; i < studentId.size(); i++) {

                                            db.studentInformation(studentId.get(i));
                                            for (int j = 0; j < db.studentLetterGrade.size(); j++) {

                                                if(db.studentLetterGrade.get(j).equals("Süren çalışma"))
                                                {
                                                    System.out.println(studentId.get(i) + " " + studentName.get(i));
                                                    studentId.remove(i);
                                                    studentName.remove(i);
                                                    break;

                                                }

                                            }
                                        }


                                        for (int i = 0; i < studentId.size(); i++) {
                                            PgsqlConnection db1 = new PgsqlConnection();
                                            double top = 0;
                                            db1.studentInformation(studentId.get(i));

                                            for (int j = 0; j < formulDers.size(); j++) {

                                                if(db1.studentCourseName.contains(formulDers.get(j)))
                                                {

                                                    int index = db1.studentCourseName.indexOf(formulDers.get(j));

                                                    if(db1.studentLetterGrade.get(index).equals("AA"))
                                                    {
                                                        System.out.println("aa");
                                                        top = top + 4*carpan.get(j);
                                                    }

                                                    else  if(db1.studentLetterGrade.get(index).equals("BA"))
                                                    {
                                                        System.out.println("ba");
                                                        top = top + 3.5*carpan.get(j);
                                                    }

                                                    else if(db1.studentLetterGrade.get(index).equals("BB"))
                                                    {
                                                        System.out.println("bb");
                                                        top = top + 3*carpan.get(j);
                                                    }

                                                    else  if(db1.studentLetterGrade.get(index).equals("CB"))
                                                    {
                                                        System.out.println("cb");
                                                        top = top + 2.5*carpan.get(j);
                                                    }

                                                    else  if(db1.studentLetterGrade.get(index).equals("CC"))
                                                    {
                                                        System.out.println("cc");
                                                        top = top + 2*carpan.get(j);
                                                    }

                                                    else if(db1.studentLetterGrade.get(index).equals("DC"))
                                                    {
                                                        System.out.println("dc");
                                                        top = top + 1.5*carpan.get(j);
                                                    }

                                                    else  if(db1.studentLetterGrade.get(index).equals("DD"))
                                                    {
                                                        System.out.println("dd");
                                                        top = top + 1*carpan.get(j);
                                                    }


                                                }
                                                else
                                                {
                                                    top = top + 0*carpan.get(j);
                                                }
                                                double toplamcarpan = 0;
                                                for (int k = 0; k < carpan.size(); k++) {
                                                    toplamcarpan =  (toplamcarpan + carpan.get(k));
                                                }

                                                top = top/toplamcarpan;

                                            }

                                            gpa.add(top);
                                        }

                                        for (int j = 0; j < gpa.size(); j++) {

                                            for (int i = 0; i < gpa.size()-1-j; i++) {

                                                if(gpa.get(i)<gpa.get(i+1))
                                                {
                                                    Collections.swap(gpa,i,i+1);
                                                    Collections.swap(studentId,i,i+1);

                                                }

                                            }

                                        }

                                        newTeacherId.set(Integer.parseInt(textField1.getText())-1,teacherId.get(0));
                                        newTeacherId.set(Integer.parseInt(textField2.getText())-1,teacherId.get(1));
                                        newTeacherId.set(Integer.parseInt(textField3.getText())-1,teacherId.get(2));
                                        newTeacherId.set(Integer.parseInt(textField4.getText())-1,teacherId.get(3));


                                        int sayac = 0;
                                        for (int i = 0; i < studentId.size(); i++) {

                                            Random random = new Random();
                                            int   rnd = random.nextInt(3);


                                            if( newTeacherId.size() != 0 &&i%newTeacherId.size()==0)
                                            {
                                                db.teacherInformation(newTeacherId.get(0));
                                                if(db.studentCounter<db.teacherQuota)
                                                {
                                                    db.insertStudentCourse(studentId.get(i),course.get(rnd),"süren çalışma");
                                                    db.talep(studentId.get(i),newTeacherId.get(0),"otomatik atama",course.get(rnd));
                                                    db.updateStudentCounter(newTeacherId.get(0));
                                                }
                                                else
                                                {
                                                    newTeacherId.remove(0);
                                                    sayac++;
                                                    i--;
                                                }

                                            }

                                            else if(newTeacherId.size() != 0 && i%newTeacherId.size()==1)
                                            {
                                                db.teacherInformation(newTeacherId.get(1));
                                                if(db.studentCounter<db.teacherQuota)
                                                {
                                                    db.insertStudentCourse(studentId.get(i),course.get(rnd),"süren çalışma");
                                                    db.talep(studentId.get(i),newTeacherId.get(1),"otomatik atama",course.get(rnd));
                                                    db.updateStudentCounter(newTeacherId.get(1));
                                                }
                                                else
                                                {
                                                    newTeacherId.remove(1);
                                                    sayac++;
                                                    i--;
                                                }

                                            }
                                            else if(newTeacherId.size() != 0 &&i%newTeacherId.size()==2)
                                            {
                                                db.teacherInformation(newTeacherId.get(2));
                                                if(db.studentCounter<db.teacherQuota)
                                                {
                                                    db.insertStudentCourse(studentId.get(i),course.get(rnd),"süren çalışma");
                                                    db.talep(studentId.get(i),newTeacherId.get(2),"otomatik atama",course.get(rnd));
                                                    db.updateStudentCounter(newTeacherId.get(2));
                                                }
                                                else
                                                {
                                                    newTeacherId.remove(2);
                                                    sayac++;
                                                    i--;
                                                }

                                            }
                                            else if(newTeacherId.size() != 0 &&i%newTeacherId.size()==3)
                                            {
                                                db.teacherInformation(newTeacherId.get(3));
                                                if(db.studentCounter<db.teacherQuota)
                                                {
                                                    db.insertStudentCourse(studentId.get(i),course.get(rnd),"süren çalışma");
                                                    db.talep(studentId.get(i),newTeacherId.get(3),"otomatik atama",course.get(rnd));
                                                    db.updateStudentCounter(newTeacherId.get(3));
                                                }
                                                else
                                                {
                                                    newTeacherId.remove(3);
                                                    sayac++;
                                                    i--;
                                                }

                                            }
                                            else
                                            {
                                                JOptionPane.showConfirmDialog(null,"Tüm hocaların Kontenjanı doldu","Kontenjan doldu",JOptionPane.PLAIN_MESSAGE);
                                            }


                                        }


                                        if(isPanelOpen1){

                                            studentTalep.setModel(db.tableModelTalepStudent());
                                            teacherTalep.setModel(db.tableModelTalepTeacher());
                                            frame.revalidate();

                                        }


                                    }
                                });

                                frame.add(panel);

                            }
                        });

                        frame.add(button2);
                        frame.add(button);
                        frame.add(scrollPane);
                        frame.revalidate();
                        frame.repaint();
                    }
                });


                frame.add(jLabel);
                frame.add(button1);
                frame.add(button2);
                frame.add(button3);
                frame.revalidate();
                frame.repaint();
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PgsqlConnection pg = new PgsqlConnection("YazLab1_1","postgres","12345");



                generateRandomStudent();
                System.out.println(name + " " + surname + " " + password + " " + gpa + " " + studentInsterest);
                pg.insertStudent(name,surname,password,gpa,studentInsterest,courses,letters);

                if(isPanelOpen){

                    pg.studentList();

                    listModel.clear();
                    for (Integer list : pg.getStudentIdList()) {
                        listModel.addElement(list);
                    }

                    frame.remove(panelStudentList);

                    studentJList.setModel(listModel);

                    frame.add(panelStudentList);
                    frame.revalidate();
                    frame.repaint();


                }


            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                isPanelOpen = true;

                PgsqlConnection db = new PgsqlConnection("YazLab1_1","postgres","12345");
                db.studentList();

                JLabel studentList = new JLabel("Öğrenci Listesi");

                listModel = new DefaultListModel<>();
                for (Integer list : db.getStudentIdList()) {
                    listModel.addElement(list);
                }

                studentJList = new JList<>(listModel);
                studentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                studentJList.setLayoutOrientation(JList.VERTICAL);


                studentJList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent a) {
                        isPanelOpen = false;

                        secilenOgrenciId = studentJList.getSelectedValue();
                        System.out.println(secilenOgrenciId);

                        PgsqlConnection db = new PgsqlConnection("YazLab1_1", "postgres", "12345");
                        db.studentInformation(secilenOgrenciId);

                        listModel1 = new DefaultListModel<>();
                        listModel1.add(0, "Öğrenci Numarası: " + secilenOgrenciId);
                        listModel1.add(1, "Ad: " + db.getStudentName());
                        listModel1.add(2, "Soyad: " + db.getStudentSurname());
                        listModel1.add(3, "GPA: " + db.getStudentGpa());
                        listModel1.add(4, "Şifre: " + db.getStudentPassword());
                        listModel1.add(5, "İlgi Alanı: " + db.getStudentInterest());
                        listModel1.add(6,"Ders Bilgileri");
                        listModel1.add(7,"ÖĞRENCİYİ SİL");

                        studentInformationJList = new JList<>(listModel1);
                        studentInformationJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        studentInformationJList.setLayoutOrientation(JList.VERTICAL);

                        studentInformationJList.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {

                                index = studentInformationJList.getSelectedIndex();
                                info = studentInformationJList.getSelectedValue();

                                if(index == 7){

                                    int result = JOptionPane.showConfirmDialog(null, "Öğrenciyi silmek istediğinizden emin misiniz?", "Öğrenci Silme Onayı", JOptionPane.YES_NO_OPTION);
                                    if (result == JOptionPane.YES_OPTION) {
                                        db.deleteStudent(secilenOgrenciId);
                                    }
                                }else if(index == 6){


                                    PgsqlConnection db = new PgsqlConnection("YazLab1_1","postgres","12345");
                                    db.studentInformation(secilenOgrenciId);

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
                                    JOptionPane.showMessageDialog(frame, scrollPane, "Ders Bilgileri", JOptionPane.INFORMATION_MESSAGE);

                                }else if(index != 0) {

                                    int secenek = JOptionPane.showOptionDialog(null, info, "Seçenekler",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Güncelle"},
                                            "default");

                                    if (secenek == 0) {
                                        // "Güncelle" seçeneği seçildiğinde yapılacak işlemler
                                        System.out.println("Güncelle seçildi.");
                                        String guncelleVeri = JOptionPane.showInputDialog("Güncel veriyi girin:");
                                        System.out.println(guncelleVeri);

                                        switch (index) {
                                            case 0:
                                                // db.studentInformationSet(secilenOgrenciId,"studentid","string",guncelleVeri);
                                                break;
                                            case 1:
                                                db.studentInformationSet(secilenOgrenciId, "name", "string", guncelleVeri);
                                                break;
                                            case 2:
                                                db.studentInformationSet(secilenOgrenciId, "surname", "string", guncelleVeri);
                                                break;
                                            case 3:
                                                db.studentInformationSet(secilenOgrenciId, "gpa", "double", guncelleVeri);
                                                break;
                                            case 4:
                                                db.studentInformationSet(secilenOgrenciId, "studentpassword", "integer", guncelleVeri);
                                                break;
                                            case 5:
                                                db.studentInformationSet(secilenOgrenciId, "interest", "string", guncelleVeri);
                                                break;

                                        }

                                        studentInformationJList.setModel(listModel1);
                                    }

                                }
                                frame.remove(panelStudentInformationList);
                                //panelStudentList.remove(studentJList);


                                db.studentList();

                                listModel.clear();
                                for (Integer list : db.getStudentIdList()) {
                                    listModel.addElement(list);
                                }

                                studentJList.setModel(listModel);

                                // panelStudentList.add(new JScrollPane(studentJList), BorderLayout.CENTER);


                                frame.add(panelStudentList);
                                frame.revalidate();
                                frame.repaint();

                                isPanelOpen = true;
                                studentInformationJList.removeMouseListener(this);

                            }

                        });


                        studentList.setText(db.getStudentName() + " " + db.getStudentSurname());

                        panelStudentInformationList = new JPanel(new BorderLayout());
                        panelStudentInformationList.setBounds(1000,70,190,280);

                        panelStudentInformationList.add(studentList, BorderLayout.NORTH);
                        panelStudentInformationList.add(new JScrollPane(studentInformationJList), BorderLayout.CENTER);
                        //frame.add(panelStudentInformationList);
                        frame.remove(panelStudentList);
                        frame.add(panelStudentInformationList);
                        frame.revalidate();
                        frame.repaint();


                    }

                });

                panelStudentList = new JPanel(new BorderLayout());
                panelStudentList.setBounds(1000,70,190,280);

                panelStudentList.add(studentList,BorderLayout.NORTH);
                panelStudentList.add(new JScrollPane(studentJList), BorderLayout.CENTER);

                frame.add(panelStudentList);
                frame.revalidate();
                frame.repaint();


            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PgsqlConnection db = new PgsqlConnection("YazLab1_1","postgres","12345");
                db.teacherList();

                JLabel teacherList = new JLabel("Öğretmen Listesi");

                DefaultListModel<Integer> listModel2 = new DefaultListModel<>();

                for (Integer list : db.getTeacherIdList()) {
                    listModel2.addElement(list);
                }

                JList<Integer> teacherJList = new JList<>(listModel2);
                teacherJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                teacherJList.setLayoutOrientation(JList.VERTICAL);

                teacherJList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent a) {

                        //if (a.getClickCount()==1) {
                        secilenOgretmenId = teacherJList.getSelectedValue();
                        System.out.println(secilenOgretmenId);
                        ArrayList<String> courseName = new ArrayList<>();
                        ArrayList<String> interest = new ArrayList<>();

                        PgsqlConnection db = new PgsqlConnection("YazLab1_1", "postgres", "12345");
                        db.teacherInformation(secilenOgretmenId);
                        db.teacherCourseInformation1(secilenOgretmenId, courseName);

                        listModel3 = new DefaultListModel<>();
                        listModel3.add(0, "Öğretmen Numarası: " + secilenOgretmenId);
                        listModel3.add(1, "Ad: " + db.getTeacherName());
                        listModel3.add(2, "Soyad: " + db.getTeacherSurname());
                        listModel3.add(3, "Öğrenci Kotası: " + db.getTeacherQuota());
                        listModel3.add(4, "Şifre: " + db.getTeacherPassword());
                        listModel3.add(5, "Öğrenci Sayısı: " + db.getStudentCounter());
                        listModel3.add(6, "İlgi Alanları");
                        listModel3.add(7,"---Verilen Dersler---");
                        i = 1;
                        for (String list : courseName) {
                            listModel3.addElement( i + "-  " + list);
                            i++;
                        }
                        listModel3.add(7+i, "ÖĞRETMENİ SİL");

                        teacherInformationJList = new JList<>(listModel3);
                        teacherInformationJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        teacherInformationJList.setLayoutOrientation(JList.VERTICAL);

                        teacherInformationJList.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {

                                index1 = teacherInformationJList.getSelectedIndex();
                                info1 = teacherInformationJList.getSelectedValue();

                                if(index1==6){

                                    ArrayList<String> interest = new ArrayList<>();

                                    PgsqlConnection db0 = new PgsqlConnection("YazLab1_1", "postgres", "12345");
                                    db0.teacherInterestInformation(secilenOgretmenId, interest);
                                    DefaultListModel<String> listModel4 = new DefaultListModel<>();

                                    for (String list : interest) {
                                        listModel4.addElement(list);
                                    }

                                    JList<String> teacherInterestJList = new JList<>(listModel4);
                                    teacherInterestJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                    teacherInterestJList.setLayoutOrientation(JList.VERTICAL);

                                    teacherInterestJList.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent a) {

                                            String info2 = teacherInterestJList.getSelectedValue();
                                            int index2 = teacherInterestJList.getSelectedIndex();

                                            int secenek1 = JOptionPane.showOptionDialog(null, info2, "Seçenekler",
                                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Güncelle", "Sil"},
                                                    "default");

                                            if (secenek1 == 0) {
                                                // "Güncelle" seçeneği seçildiğinde yapılacak işlemler
                                                System.out.println("Güncelle seçildi.");
                                                String guncelleVeri = JOptionPane.showInputDialog("Güncel veriyi girin:");
                                                System.out.println(guncelleVeri);

                                                db0.teacherInterestSet(secilenOgretmenId,info2,guncelleVeri);

                                                teacherInterestJList.setModel(listModel3);
                                            } else if (secenek1 == 1) {
                                                int result = JOptionPane.showConfirmDialog(null, "Silmek istediğinizden emin misiniz?", "Silme Onayı", JOptionPane.YES_NO_OPTION);
                                                if (result == JOptionPane.YES_OPTION) {
                                                    db0.deleteTeacherInterest(secilenOgretmenId, info2);
                                                }

                                            }


                                            frame.remove(panelTeacherInterestList);
                                            frame.add(panelTeacherInformationList);
                                            frame.revalidate();
                                            frame.repaint();

                                            teacherInterestJList.removeMouseListener(this);


                                        }

                                    });
                                    teacherList.setText(db.getTeacherName() + " " + db.getTeacherSurname() + " İlgi Alanları");

                                    panelTeacherInterestList = new JPanel(new BorderLayout());

                                    panelTeacherInterestList.setBounds(1210,70,190,280);

                                    panelTeacherInterestList.add(teacherList, BorderLayout.NORTH);
                                    panelTeacherInterestList.add(new JScrollPane(teacherInterestJList), BorderLayout.CENTER);

                                    frame.remove(panelTeacherInformationList);
                                    frame.add(panelTeacherInterestList);
                                    frame.revalidate();
                                    frame.repaint();

                                }else if(index1 == (i+7)){

                                    int result = JOptionPane.showConfirmDialog(null, "Öğretmeni silmek istediğinizden emin misiniz?", "Öğretmen Silme Onayı", JOptionPane.YES_NO_OPTION);
                                    if (result == JOptionPane.YES_OPTION) {
                                        db.deleteTeacher(secilenOgretmenId);
                                    }


                                    db.teacherList();
                                    listModel2.clear();


                                    for (Integer list : db.getTeacherIdList()) {
                                        listModel2.addElement(list);
                                    }

                                    teacherJList.setModel(listModel2);

                                    frame.remove(panelTeacherInformationList);
                                    frame.add(panelTeacherList);
                                    frame.revalidate();
                                    frame.repaint();

                                    teacherInformationJList.removeMouseListener(this);

                                }else if(index1>=1 && index1<=5 ){

                                    int secenek1 = JOptionPane.showOptionDialog(null, info1, "Seçenekler",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Güncelle"},
                                            "default");

                                    if (secenek1 == 0) {
                                        // "Güncelle" seçeneği seçildiğinde yapılacak işlemler
                                        System.out.println("Güncelle seçildi.");
                                        String guncelleVeri = JOptionPane.showInputDialog("Güncel veriyi girin:");
                                        System.out.println(guncelleVeri);

                                        switch (index1) {
                                            case 0:
                                                // db.studentInformationSet(secilenOgretmenId ,"studentid","string",guncelleVeri);
                                                break;
                                            case 1:
                                                db.teacherInformationSet(secilenOgretmenId, "name", "string", guncelleVeri);
                                                break;
                                            case 2:
                                                db.teacherInformationSet(secilenOgretmenId, "surname", "string", guncelleVeri);
                                                break;
                                            case 3:
                                                db.teacherInformationSet(secilenOgretmenId, "quota", "integer", guncelleVeri);
                                                break;
                                            case 4:
                                                db.teacherInformationSet(secilenOgretmenId, "password", "integer", guncelleVeri);
                                                break;
                                            case 5:
                                                //db.teacherInformationSet(secilenOgretmenId, "studentcounter", "integer", guncelleVeri);
                                                break;
                                            case 6:

                                                break;
                                        }


                                        teacherInformationJList.setModel(listModel3);
                                    }



                                    db.teacherList();
                                    listModel2.clear();


                                    for (Integer list : db.getTeacherIdList()) {
                                        listModel2.addElement(list);
                                    }

                                    teacherJList.setModel(listModel2);

                                    frame.remove(panelTeacherInformationList);
                                    frame.add(panelTeacherList);
                                    frame.revalidate();
                                    frame.repaint();

                                    teacherInformationJList.removeMouseListener(this);

                                }


                            }

                        });


                        teacherList.setText(db.getTeacherName() + " " + db.getTeacherSurname());

                        panelTeacherInformationList = new JPanel(new BorderLayout());
                        panelTeacherInformationList.setBounds(1210,70,190,280);

                        panelTeacherInformationList.add(teacherList, BorderLayout.NORTH);
                        panelTeacherInformationList.add(new JScrollPane(teacherInformationJList), BorderLayout.CENTER);
                        //frame.add(panelStudentInformationList);
                        frame.remove(panelTeacherList);
                        frame.add(panelTeacherInformationList);
                        frame.revalidate();
                        frame.repaint();



                    }

                });

                panelTeacherList = new JPanel(new BorderLayout());
                panelTeacherList.setBounds(1210,70,190,280);

                panelTeacherList.add(teacherList,BorderLayout.NORTH);
                panelTeacherList.add(new JScrollPane(teacherJList), BorderLayout.CENTER);

                frame.add(panelTeacherList);
                frame.revalidate();
                frame.repaint();




            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(panelStudentList != null){
                    panelStudentList.setVisible(false);
                }
                if(panelStudentInformationList != null){
                    panelStudentInformationList.setVisible(false);
                }

            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(panelTeacherList != null){
                    panelTeacherList.setVisible(false);
                }
                if(panelTeacherInformationList != null){
                    panelTeacherInformationList.setVisible(false);
                }
                if(panelTeacherInterestList != null){
                    panelTeacherInterestList.setVisible(false);
                }

            }
        });


        /*
        frame.add(label1);


        frame.add(label4);
        frame.add(button1);
*/

        frame.add(button0);
        frame.add(button);
        frame.add(label1);
        frame.add(textField1);
        frame.add(button1);

        frame.add(label2);
        frame.add(textField2);
        frame.add(button2);

        frame.add(label3);
        frame.add(textField3);
        frame.add(button3);

        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);
        frame.add(button9);

    }

    public  void generateRandomStudent() {
        Random random = new Random();
        name= isimler[random.nextInt(isimler.length)];
        surname = soyisimler[random.nextInt(soyisimler.length)];
        password = random.nextInt(1000) + 1000;
        gpa = 0;



        courses.clear();
        letters.clear();


        for (int i = 0; i < 10; i++) {

            String Course = course[random.nextInt(course.length)];
            int bayrak = 0;
            for (int j = 0; j < courses.size(); j++) {

                if(courses.get(j).equals(Course))
                {
                    bayrak = 1;
                    i--;
                    break;
                }

            }

            if(bayrak == 0)
            {

                courses.add(Course);
                int sayi = random.nextInt(20);
                if(sayi<8)
                {
                    letters.add(letterGrade[random.nextInt(letterGrade.length)]);
                }

                else if(sayi<11)
                {
                    letters.add("AA");
                }

                else if(sayi<13)
                {
                    letters.add("BA");
                }

                else if(sayi<16)
                {
                    letters.add("BB");
                }

                else if(sayi<18)
                {
                    letters.add("CB");
                }

                else if(sayi<20)
                {
                    letters.add("CC");
                }


                //   System.out.println(Course + " " + letters.get(i));
                if(letters.get(i) == "AA")
                {
                    gpa = gpa +4;
                }
                if(letters.get(i) == "BA")
                {
                    gpa =  gpa +3.5;
                }
                if(letters.get(i) == "BB")
                {
                    gpa = gpa +3;
                }
                if(letters.get(i) == "CB")
                {
                    gpa = gpa +2.5;
                }
                if(letters.get(i) == "CC")
                {

                    gpa = gpa +2;
                }
                if(letters.get(i) == "DC")
                {
                    gpa = gpa +1.5;
                }
                if(letters.get(i) == "DD")
                {
                    gpa = gpa +1;
                }
            }

        }

        gpa = gpa / 10;

        studentInsterest = interest[random.nextInt(interest.length)];

        //  System.out.println(name + " " + surname + " " + password + " " + gpa + " " + studentInsterest);

    }

}