import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    static String pdfPath;
    public static void main(String[] args) throws  IOException{

        Color renk = Color.decode("#FADBD8");
        //Color renk = Color.decode("#D6EAF8");
        //Color renk = Color.decode("#E8DAEF");
        //Color renk = Color.decode("#D5F5E3");

        UIManager.put("Button.background", renk);
        //UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 14));
        //UIManager.put("Button.border", BorderFactory.createLineBorder(renk, 30, true));

        PgsqlConnection db = new PgsqlConnection("YazLab1_1", "postgres", "12345");

        db.deleteTable("teacher");
        db.deleteTable("teachercourse");
        db.deleteTable("admin");
        db.deleteTable("talep");
        db.deleteTable("talep2");
        db.deleteTable("student");
         /*
        db.deleteTable("studentcourse");
        db.deleteTable("kriterdersler");
*/

        try {
            String sql = "INSERT INTO admin (talepmiktar, id, password) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = db.connection1.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "admin123");
            preparedStatement.setInt(3, 12345);

            int etkilenenSatir = preparedStatement.executeUpdate();

            if (etkilenenSatir > 0) {
                System.out.println("Veri ekleme başarılı!");
            } else {
                System.out.println("Veri ekleme başarısız!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        db.insertTeacher("İbrahim","Şahan",11111,1122,5);
        db.insertTeacher("Abdurrahman","Gün",22222,3344,5);
        db.insertTeacher("Furkan","Göz",33333,5566,5);
        db.insertTeacher("Suhap","Şahin",44444,7788,5);

        db.insertTeacherCourse("Proje Ders1",11111);
        db.insertTeacherCourse("Proje Ders2",11111);
        db.insertTeacherCourse("Proje Ders3",11111);
        db.insertTeacherCourse("Proje Ders1",22222);
        db.insertTeacherCourse("Proje Ders2",22222);
        db.insertTeacherCourse("Proje Ders3",22222);
        db.insertTeacherCourse("Proje Ders1",33333);
        db.insertTeacherCourse("Proje Ders2",33333);
        db.insertTeacherCourse("Proje Ders3",33333);
        db.insertTeacherCourse("Proje Ders1",44444);
        db.insertTeacherCourse("Proje Ders2",44444);
        db.insertTeacherCourse("Proje Ders3",44444);

        // JFrame oluştur
        JFrame frame = new JFrame("Kocaeli Üniversitesi Proje Dersi Kayıt Sistemi - Giriş");

        // Layout yöneticisini ayarla
        frame.setLayout(new GridLayout(1, 3));

        // öğrenci paneli
        JPanel studentPanel = new JPanel();

        studentPanel.setBorder(new LineBorder(Color.BLACK, 2)); // 2 piksel kalınlığında siyah bir kenarlık
        // student.setBackground(Color.PINK);
        studentPanel.setLayout(new GridBagLayout());

        JPanel loginPanel = new JPanel();
        // loginPanel.setBackground(Color.PINK);
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setPreferredSize(new Dimension(300,200));

        JPanel loginPanel0 = new JPanel();
        //loginPanel0.setBackground(Color.PINK);
        loginPanel0.setPreferredSize(new Dimension(300,40));

        JPanel loginPanel1 = new JPanel();
        //loginPanel1.setBackground(Color.PINK);
        loginPanel1.setLayout(new GridLayout(5, 2));

        JPanel loginPanel2 = new JPanel();
        // loginPanel2.setBackground(Color.PINK);
        loginPanel2.setPreferredSize(new Dimension(300,40));

        JLabel studentLogin = new JLabel("ÖĞRENCİ GİRİŞ");


        JLabel empty = new JLabel("");
        JLabel empty1 = new JLabel("");
        JLabel empty2 = new JLabel("");
        JLabel empty3 = new JLabel("");
        JLabel empty4 = new JLabel("");
        JLabel empty5 = new JLabel("");

        JLabel userLabel = new JLabel("Kullanıcı Adı:");
        JTextField userField = new JTextField();

        JLabel passwordLabel = new JLabel("Şifre:");
        JTextField passwordField = new JTextField();

        JButton StudentloginButton = new JButton("Giriş");
        StudentloginButton.setPreferredSize(new Dimension(300, 30));

        StudentloginButton.addActionListener(e -> {

            if(userField.getText().isEmpty() || passwordField.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "Boş alan bırakılamaz!", "Uyarı", JOptionPane.WARNING_MESSAGE);

            }else {


                int usernameStudent = Integer.parseInt(userField.getText());
                int passwordStudent = Integer.parseInt(passwordField.getText());



                if (Login(usernameStudent, passwordStudent, "student") == 0) {//şifre yanlış

                    JOptionPane.showMessageDialog(null, "Hatalı Şifre!", "Uyarı", JOptionPane.WARNING_MESSAGE);

                } else if (Login(usernameStudent, passwordStudent, "student") == 1) {

                    // frame.setVisible(false);

                    JFrame studentFrame = new JFrame();
                    studentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Frame'i tam ekran yap


                    studentFrame.setVisible(true);
                    studentFrame.setLayout(null);

                    Student student = new Student(usernameStudent, passwordStudent, studentFrame);

                } else if (Login(usernameStudent, passwordStudent, "student") == 2) {

                    JOptionPane.showMessageDialog(null, "Kullanıcı Bulunamadı!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        loginPanel0.add(studentLogin);

        loginPanel1.add(empty);
        loginPanel1.add(empty1);
        loginPanel1.add(userLabel);
        loginPanel1.add(userField);
        loginPanel1.add(empty2);
        loginPanel1.add(empty3);
        loginPanel1.add(passwordLabel);
        loginPanel1.add(passwordField);
        loginPanel1.add(empty4);
        loginPanel1.add(empty5);

        loginPanel2.add(StudentloginButton);

        loginPanel.add(loginPanel0);
        loginPanel.add(loginPanel1);
        loginPanel.add(loginPanel2);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(50, 0, 0, 0); // Yükseklik ayarı için
        studentPanel.add(loginPanel, constraints);
        frame.add(studentPanel);


        // Panel 2
        JPanel teacherPanel = new JPanel();
        teacherPanel.setBorder(new LineBorder(Color.BLACK, 2)); // 2 piksel kalınlığında siyah bir kenarlık
        teacherPanel.setLayout(new GridBagLayout());

        JPanel loginPanelT = new JPanel();
        // loginPanel.setBackground(Color.PINK);
        loginPanelT.setLayout(new BoxLayout(loginPanelT, BoxLayout.Y_AXIS));
        loginPanelT.setPreferredSize(new Dimension(300,200));

        JPanel loginPanel0T = new JPanel();
        //loginPanel0.setBackground(Color.PINK);
        loginPanel0T.setPreferredSize(new Dimension(300,40));

        JPanel loginPanel1T = new JPanel();
        //loginPanel1.setBackground(Color.PINK);
        loginPanel1T.setLayout(new GridLayout(5, 2));

        JPanel loginPanel2T = new JPanel();
        // loginPanel2.setBackground(Color.PINK);
        loginPanel2T.setPreferredSize(new Dimension(300,40));

        JLabel emptyT = new JLabel("");
        JLabel empty1T = new JLabel("");
        JLabel empty2T = new JLabel("");
        JLabel empty3T = new JLabel("");
        JLabel empty4T = new JLabel("");
        JLabel empty5T = new JLabel("");


        JLabel teacherLogin = new JLabel("ÖĞRETMEN GİRİŞ");


        JLabel userLabelTeacher = new JLabel("Kullanıcı Adı:");
        JTextField userFieldTeacher = new JTextField();

        JLabel passwordLabelTeacher = new JLabel("Şifre:");
        JTextField passwordFieldTeacher = new JTextField();

        JButton loginButtonTeacher = new JButton("Giriş");
        loginButtonTeacher.setPreferredSize(new Dimension(300, 30));

        loginButtonTeacher.addActionListener(e -> {

            if(userFieldTeacher.getText().isEmpty() || passwordFieldTeacher.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "Boş alan bırakılamaz!", "Uyarı", JOptionPane.WARNING_MESSAGE);

            }else {

                int usernameTeacher = Integer.parseInt(userFieldTeacher.getText());
                int passwordTeacher = Integer.parseInt(passwordFieldTeacher.getText());
                System.out.println(usernameTeacher + " " + passwordTeacher);
                if (Login(usernameTeacher, passwordTeacher, "teacher") == 0) {//şifre yanlış

                    JOptionPane.showMessageDialog(null, "Hatalı Şifre!", "Uyarı", JOptionPane.WARNING_MESSAGE);

                } else if (Login(usernameTeacher, passwordTeacher, "teacher") == 1) {

                    //  frame.setVisible(false);
                    JFrame teacherFrame = new JFrame();

                    teacherFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Frame'i tam ekran yap
                    //frame.setUndecorated(true); // Başlık çubuğunu gizle

                    teacherFrame.setVisible(true);
                    teacherFrame.setLayout(null);
                    //  teacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    Teacher teacher = new Teacher(usernameTeacher, passwordTeacher,teacherFrame);

                } else if (Login(usernameTeacher, passwordTeacher, "teacher") == 2) {

                    JOptionPane.showMessageDialog(null, "Kullanıcı Bulunamadı!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                }
            }


        });

        loginPanel0T.add(teacherLogin);
        loginPanel1T.add(emptyT);
        loginPanel1T.add(empty1T);
        loginPanel1T.add(userLabelTeacher);
        loginPanel1T.add(userFieldTeacher);
        loginPanel1T.add(empty2T);
        loginPanel1T.add(empty3T);
        loginPanel1T.add(passwordLabelTeacher);
        loginPanel1T.add(passwordFieldTeacher);
        loginPanel1T.add(empty4T);
        loginPanel1T.add(empty5T);

        loginPanel2T.add(loginButtonTeacher);

        loginPanelT.add(loginPanel0T);
        loginPanelT.add(loginPanel1T);
        loginPanelT.add(loginPanel2T);

        GridBagConstraints constraintsT = new GridBagConstraints();
        constraintsT.gridx = 0;
        constraintsT.gridy = 0;
        constraintsT.insets = new Insets(50, 0, 0, 0); // Yükseklik ayarı için
        teacherPanel.add(loginPanelT, constraintsT);
        frame.add(teacherPanel);

        // Panel 3
        JPanel adminPanel = new JPanel();
        adminPanel.setBorder(new LineBorder(Color.BLACK, 2)); // 2 piksel kalınlığında siyah bir kenarlık
        adminPanel.setLayout(new GridBagLayout());

        JPanel loginPanelA = new JPanel();
        // loginPanel.setBackground(Color.PINK);
        loginPanelA.setLayout(new BoxLayout(loginPanelA, BoxLayout.Y_AXIS));
        loginPanelA.setPreferredSize(new Dimension(300,200));

        JPanel loginPanel0A = new JPanel();
        //loginPanel0.setBackground(Color.PINK);
        loginPanel0A.setPreferredSize(new Dimension(300,40));

        JPanel loginPanel1A = new JPanel();
        //loginPanel1.setBackground(Color.PINK);
        loginPanel1A.setLayout(new GridLayout(5, 2));

        JPanel loginPanel2A = new JPanel();
        // loginPanel2.setBackground(Color.PINK);
        loginPanel2A.setPreferredSize(new Dimension(300,40));

        JLabel emptyA = new JLabel("");
        JLabel empty1A = new JLabel("");
        JLabel empty2A = new JLabel("");
        JLabel empty3A = new JLabel("");
        JLabel empty4A = new JLabel("");
        JLabel empty5A = new JLabel("");


        JLabel adminLogin = new JLabel("YÖNETİCİ GİRİŞ");

        JLabel userLabelAdmin = new JLabel("Kullanıcı Adı:");
        JTextField userFieldAdmin = new JTextField();


        JLabel passwordLabelAdmin = new JLabel("Şifre:");
        JPasswordField passwordFieldAdmin = new JPasswordField();

        JButton loginButtonAdmin = new JButton("Giriş");
        loginButtonAdmin.setPreferredSize(new Dimension(300, 30));

        loginButtonAdmin.addActionListener(e -> {
            String usernameAdmin = userFieldAdmin.getText();
            String passwordAdmin = new String(passwordFieldAdmin.getPassword());


            if(usernameAdmin.equals("admin123") && passwordAdmin.equals("12345"))
            {

                JFrame adminFrame = new JFrame();
                Admin admin = new Admin(usernameAdmin,passwordAdmin,adminFrame);
                // Frame'i tam ekran yap
                adminFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                //frame.setUndecorated(true); // Başlık çubuğunu gizle
                adminFrame.setLayout(null);
                // Frame'i görünür yap
                adminFrame.setVisible(true);
                // X tuşuna basıldığında programı kapat
                //  adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }

            else
            {
                JOptionPane.showMessageDialog(frame, "Kullanıcı Adı Veya Şifre Hatalı", "Kimlik Doğrulanamadı", JOptionPane.WARNING_MESSAGE);
            }




        });

        loginPanel0A.add(adminLogin);

        loginPanel1A.add(emptyA);
        loginPanel1A.add(empty1A);
        loginPanel1A.add(userLabelAdmin);
        loginPanel1A.add(userFieldAdmin);
        loginPanel1A.add(empty2A);
        loginPanel1A.add(empty3A);
        loginPanel1A.add(passwordLabelAdmin);
        loginPanel1A.add(passwordFieldAdmin);
        loginPanel1A.add(empty4A);
        loginPanel1A.add(empty5A);

        loginPanel2A.add(loginButtonAdmin);

        loginPanelA.add(loginPanel0A);
        loginPanelA.add(loginPanel1A);
        loginPanelA.add(loginPanel2A);

        GridBagConstraints constraintsA = new GridBagConstraints();
        constraintsA.gridx = 0;
        constraintsA.gridy = 0;
        constraintsA.insets = new Insets(50, 0, 0, 0); // Yükseklik ayarı için
        adminPanel.add(loginPanelA, constraintsA);

        frame.add(adminPanel);


        // Frame'i tam ekran yap
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true); // Başlık çubuğunu gizle

        // Frame'i görünür yap
        frame.setVisible(true);

        // X tuşuna basıldığında programı kapat
        //      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    public static int Login(int userName, int password, String type) {

        PgsqlConnection db = new PgsqlConnection("YazLab1_1", "postgres", "12345");


        if (type.equals("student")) {

            if (db.isStudent(userName)) {
                System.out.println("öğrenci var ");
                db.studentInformation(userName);
                if (password == db.getStudentPassword()) {
                    return 1;//şifre doğru
                } else {
                    return 0;//şifre yanlış
                }
            } else {
                System.out.println("öğrenci yok");
                return 2;//kullanıcı bulunamadı
            }
        } else if (type.equals("teacher")) {

            if (db.isTeacher(userName)) {
                System.out.println("öğretmen var ");
                db.teacherInformation(userName);
                if (password == db.getTeacherPassword()) {
                    return 1;//şifre doğru
                } else {
                    return 0;//şifre yanlış
                }
            } else {
                System.out.println("öğretmen yok");
                return 2;//kullanıcı bulunamadı
            }

        }else return 10;

    }

}