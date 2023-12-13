import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class PgsqlConnection {



    String studentName;
    // int studentid;
    String studentSurname;
    int studentNumberOfRequests;
    String studentPdfDocument;
    double studentGpa;
    String studentInterest;
    int studentCounter;
    String teacherName;
    String teacherSurname;
    int teacherQuota;

    ArrayList <String> studentCourseName;
    ArrayList <String> studentLetterGrade;

    ArrayList<String> gelenIsim ;
    ArrayList<String> gelenMesaj ;
    ArrayList<Integer> gelenId;
    ArrayList<String> gidenIsim ;
    ArrayList<String> gidenMesaj ;

    ArrayList<String> studentNameList ;
    ArrayList<Integer> studentIdList ;

    ArrayList<String> teacherNameList ;
    ArrayList<Integer> teacherIdList ;
    int senderId;
    String teacherCourseName;
    String teacherInterest;
    int studentAnlasmaSay;
    String teacherCriterionCourse;

    int teacherid;

    int teacherPassword;
    int studentPassword;

    int adminPassword;
    int adminTalepMiktar;
    String adminId;


    int adminNumberOfCharacter;

    public ArrayList<String> getStudentNameList() {
        return studentNameList;
    }

    public ArrayList<Integer> getStudentIdList() {
        return studentIdList;
    }

    public ArrayList<String> getTeacherNameList() {
        return teacherNameList;
    }

    public ArrayList<Integer> getTeacherIdList() {
        return teacherIdList;
    }

    public int getAdminNumberOfCharacter() {
        return adminNumberOfCharacter;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public int getStudentNumberOfRequests() {
        return studentNumberOfRequests;
    }

    public String getStudentPdfDocument() {
        return studentPdfDocument;
    }

    public double getStudentGpa() {
        return studentGpa;
    }

    public String getStudentInterest() {
        return studentInterest;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public int getTeacherQuota() {
        return teacherQuota;
    }

    public int getStudentPassword() {
        return studentPassword;
    }

    public int getTeacherPassword() {
        return teacherPassword;
    }

    // public int getStudentId() {  return studentid;  }



    public ArrayList<String> getStudentCourseName() {

        return studentCourseName;
    }

    public ArrayList<String> getStudentLetterGrade() {
        return studentLetterGrade;
    }

    public String getTeacherCourseName() {
        return teacherCourseName;
    }

    public String getTeacherCriterionCourse() {
        return teacherCriterionCourse;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public int getStudentCounter() {
        return studentCounter;
    }

    public String getTeacherInterest() {
        return teacherInterest;
    }

    public int getAdminPassword() {
        return adminPassword;
    }

    public int getAdminTalepMiktar() {
        return adminTalepMiktar;
    }

    public String getAdminId() {
        return adminId;
    }

    public ArrayList<String> getGelenIsim() {
        return gelenIsim;
    }

    public ArrayList<String> getGelenMesaj() {
        return gelenMesaj;
    }

    public ArrayList<Integer> getGelenId() {
        return gelenId;
    }

    public ArrayList<String> getGidenIsim() {
        return gidenIsim;
    }

    public ArrayList<String> getGidenMesaj() {
        return gidenMesaj;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getStudentAnlasmaSay() {
        return studentAnlasmaSay;
    }
    Connection connection1;

    {
        try {
            connection1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+"YazLab1_1","postgres","12345");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public PgsqlConnection(String dbname, String user, String pass) {

        studentCourseName = new ArrayList<>();
        studentLetterGrade = new ArrayList<>();

    }
    public PgsqlConnection() {
        studentCourseName = new ArrayList<>();
        studentLetterGrade = new ArrayList<>();
    }

    public void studentInformation(int studentId)
    {

        try {
            Statement statement = connection1.createStatement();

            String query = "SELECT * FROM Student WHERE studentid = " + studentId;
            ResultSet resultSet = statement.executeQuery(query);



            while (resultSet.next()) {

                studentName = resultSet.getString("name");
                studentSurname  = resultSet.getString("surname");
                studentNumberOfRequests = resultSet.getInt("numberofrequests");
                studentPdfDocument= resultSet.getString("pdfdocument");
                studentGpa = resultSet.getDouble("gpa");
                studentInterest = resultSet.getString("interest");
                studentPassword = resultSet.getInt("studentPassword");
                studentAnlasmaSay = resultSet.getInt("anlasmasay");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {

            Statement statement = connection1.createStatement();


            String query = "SELECT * FROM studentcourse WHERE studentid = " + studentId;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                studentCourseName.add(resultSet.getString("coursename"));
                studentLetterGrade.add(resultSet.getString("lettergrade"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void studentInformation(ArrayList<String> name,ArrayList<Integer>id)
    {
        try {

            Statement statement = connection1.createStatement();

            // SQL sorgusu oluşturma
            String query = "SELECT * FROM student";
            ResultSet resultSet = statement.executeQuery(query);

            // Veritabanından veri çekme
            while (resultSet.next()) {
                String studentName = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int studentId = resultSet.getInt("studentid");
                name.add(studentName + " " + surname);
                id.add(studentId);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void studentCourseInformation(int studentId,ArrayList<String>courses,ArrayList<String> letterGrade)
    {
        try {


            Statement statement = connection1.createStatement();


            String query = "SELECT * FROM studentcourse WHERE studentid = " + studentId;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                courses.add(String.valueOf(resultSet.getString("coursename")));
                letterGrade.add(resultSet.getString("lettergrade"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void teacherInformation(int teacherId)
    {

        try {

            Statement statement = connection1.createStatement();

            String query = "SELECT * FROM teacher WHERE id = " + teacherId;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                teacherName   = resultSet.getString("name");
                teacherSurname  = resultSet.getString("surname");
                teacherQuota  = resultSet.getInt("quota");
                teacherPassword = resultSet.getInt("password");
                studentCounter = resultSet.getInt("studentcounter");


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Statement statement = connection1.createStatement();

            String query = "SELECT * FROM teachercourse WHERE teacherid = " + teacherId;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                teacherCourseName   = resultSet.getString("coursename");
                teacherid  = resultSet.getInt("teacherid");


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void teacherCourseInformation(String courseName,ArrayList<String> name,ArrayList<Integer> id)
    {

        try {


            String query = "SELECT teacherid FROM teachercourse WHERE coursename = ?";
            PreparedStatement preparedStatement = connection1.prepareStatement(query);
            preparedStatement.setString(1, courseName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int teacherId = resultSet.getInt("teacherid");
                teacherInformation(teacherId);
                name.add(teacherName + " " + teacherSurname);
                id.add(teacherId);
                // System.out.println("Teacher ID for the course " + courseName + " is: " + teacherId);
            }

        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantısı başarısız. Hata: " + e.getMessage());
        }

    }

    public void teacherCourseInformation(int teacherid,ArrayList<String>teacherCourseName)
    {

        try {


            String query = "SELECT coursename FROM teachercourse WHERE teacherid = ?";
            PreparedStatement preparedStatement = connection1.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String courseName = resultSet.getString("coursename");
                teacherCourseName.add(courseName);

            }

        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantısı başarısız. Hata: " + e.getMessage());
        }

    }


    public void findTeacherInterest(ArrayList<String> interests,ArrayList<Integer> id)
    {
        String query = "SELECT interest, teacherid FROM teacherinterest";

        try {
            Statement statement = connection1.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String interest = rs.getString("interest");
                int teacherId = rs.getInt("teacherid");
                interests.add(interest);
                id.add(teacherId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void findTeacherInterest2(ArrayList<String> interests,int id)
    {
        try  {

            String query = "SELECT interest FROM teacherInterest WHERE teacherId = ?";
            PreparedStatement preparedStatement = connection1.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String interest = resultSet.getString("interest");
                interests.add(interest);
            }

            for (String interest : interests) {
                System.out.println("Interest: " + interest);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    public void pdfSet(String pdfText,int id)
    {

        String[] satirlar = pdfText.split("\n");
        try{

            String sql = "UPDATE student SET pdfdocument=? WHERE studentid=?";
            PreparedStatement p1 = connection1.prepareStatement(sql);

            p1.setString(1,pdfText);
            p1.setInt(2,id);

            if(p1.executeUpdate() == 1)
            {
                //  System.out.println("kayıt güncellendi");

                for (int i = 0; i < satirlar.length; i++) {

                    char[] karakterDizisi = satirlar[i].toCharArray();

                    if(karakterDizisi[0] == 'Z' && karakterDizisi[1] == ' ' && karakterDizisi[2] == 'T' && karakterDizisi[3] == 'r')
                    {
                        //  System.out.println(satirlar[i-2]);

                        String characters = satirlar[i].substring(satirlar[i].length() - 6, satirlar[i].length() - 4);
                        if(characters.charAt(1) == 'G')
                        {
                            characters = "G";
                        }

                        //  System.out.println(characters);
                        String sql1 = "INSERT INTO studentcourse (studentId,coursename,lettergrade) VALUES (?,?,?)";
                        PreparedStatement  stmt1 = connection1.prepareStatement(sql1);

                        stmt1.setInt(1, id);
                        stmt1.setString(2, satirlar[i-2]);
                        stmt1.setString(3,characters);
                        stmt1.executeUpdate();


                    }
                }
            }

            else
            {
                System.out.println("kayıt güncellenemedi");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void insertTeacherInterest(int teacherid,String interest)
    {
        try {

            String sql = "INSERT INTO teacherinterest (interest, teacherid) VALUES (?, ?)";

            PreparedStatement statement = connection1.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, interest); // Örnek olarak "interest" değeri eklendi
            statement.setInt(2, teacherid); // Örnek olarak "teacherid" değeri eklendi

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int teacherInterestId = generatedKeys.getInt(1);
                        System.out.println("Yeni teacherinterest ID: " + teacherInterestId);
                    } else {
                        throw new SQLException("Oluşturulan anahtar alınamadı, veri eklenemedi.");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Veritabanı hatası: ");
            e.printStackTrace();
        }

    }
    public void insertStudent(String name,String surname,int password,double gpa,String studentInterest,ArrayList<String> courses,ArrayList<String> letters)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Sorgu oluştur
            String sql = "INSERT INTO student (name,surname,gpa,interest,studentpassword) VALUES (?,?,?,?,?)";
            stmt = connection1.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setDouble(3, gpa);
            stmt.setString(4, studentInterest);
            stmt.setInt(5, password);

            // Sorguyu çalıştır
            int affectedRows = stmt.executeUpdate();
            int studentId = 0;
            if (affectedRows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    studentId = rs.getInt(1); // Assuming the student ID is of type integer

                }
            }


            for (int i = 0; i < courses.size(); i++) {

                String sql1 = "INSERT INTO studentcourse (studentId,coursename,lettergrade) VALUES (?,?,?)";
                PreparedStatement  stmt1 = connection1.prepareStatement(sql1);

                stmt1.setInt(1, studentId);
                stmt1.setString(2, courses.get(i));
                stmt1.setString(3, letters.get(i));
                stmt1.executeUpdate();

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void insertStudentCourse(int studentId,String course,String letter)
    {
        try{
            String sql1 = "INSERT INTO studentcourse (studentId,coursename,lettergrade) VALUES (?,?,?)";
            PreparedStatement  stmt1 = connection1.prepareStatement(sql1);

            stmt1.setInt(1, studentId);
            stmt1.setString(2, course);
            stmt1.setString(3, letter);
            stmt1.executeUpdate();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

    }

    public void insertTeacher(String name, String surname,int id, int password,int quota)
    {
        try {

            String sql = "INSERT INTO Teacher (id, name, surname, password,quota,studentcounter) VALUES (?, ?, ?,?,?,?)";
            PreparedStatement stmt = connection1.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, surname);
            stmt.setInt(4, password);
            stmt.setInt(5, quota);
            stmt.setInt(6,0);

            stmt.executeUpdate();

            // Sorguyu çalıştır


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertTeacherCourse(String courseName, int id)
    {
        try {



            String sql1 = "INSERT INTO teachercourse (coursename, teacherid) VALUES (?, ?)";
            PreparedStatement stmt1 = connection1.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            stmt1.setString(1, courseName);
            stmt1.setInt(2, id);

            int rowsAffected = stmt1.executeUpdate(); // Veritabanına ekleme işlemi gerçekleştiriliyor
            if (rowsAffected > 0) {
                ResultSet rs = stmt1.getGeneratedKeys();
                if (rs.next()) {
                    int generatedKey = rs.getInt(1);
                    //  System.out.println("Generated Teachercourse ID: " + generatedKey);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteTable(String tableName)
    {



        try {

            String deleteSql = "DELETE FROM  " + tableName ;
            PreparedStatement deleteStmt = connection1.prepareStatement(deleteSql);
            deleteStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isStudent(int studentId)
    {

        boolean bayrak = false;
        try {

            Statement statement = connection1.createStatement();

            String query = "SELECT * FROM Student WHERE studentid = " + studentId;
            ResultSet resultSet = statement.executeQuery(query);



            if (resultSet.next()) {
                bayrak = true;

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bayrak;
    }

    public void quotaSet(int quota)
    {
        try {
            System.out.println("Veritabanına bağlandı.");

            // SQL sorgusu
            String sql = "UPDATE teacher SET quota = ?";

            // Prepared statement oluşturma
            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            preparedStatement.setInt(1, quota); // yourDesiredValue değiştirilecek değerdir.

            // Sorguyu çalıştırma
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Güncellenen satır sayısı: " + rowsAffected);

        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantısı başarısız. Hata: " + e.getMessage());
        }

    }

    public boolean isTeacher(int teacherid)
    {

        boolean bayrak = false;
        try {

            Statement statement = connection1.createStatement();

            String query = "SELECT * FROM teacher WHERE id = " + teacherid;
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                bayrak = true;

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bayrak;
    }


    public void updateAdminTalepMiktar(int talepMiktar)
    {
        try {

            String sql = "UPDATE admin SET talepmiktar = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            preparedStatement.setInt(1, talepMiktar); // Yeni talep miktarını buraya girin
            preparedStatement.setString(2, "admin123");

            int etkilenenSatir = preparedStatement.executeUpdate();

            if (etkilenenSatir > 0) {
                System.out.println("Veri güncelleme başarılı!");
            } else {
                System.out.println("Veri güncelleme başarısız!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void talep(int talepEdenOgrenci,int talepEdilenHoca,String talepDurumu,String dersAd)
    {

        try {

            String sql = "INSERT INTO talep (talepedenogrenci, talepedilenhoca, anlasmadurumu,talepedilenders) VALUES (?, ?, ?,?)";

            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            preparedStatement.setInt(1, talepEdenOgrenci); // Öğrenci ID'sini buraya girin
            preparedStatement.setInt(2, talepEdilenHoca); // Hoca ID'sini buraya girin
            preparedStatement.setString(3, talepDurumu); // Anlaşma durumunu buraya girin
            preparedStatement.setString(4,dersAd);
            int etkilenenSatir = preparedStatement.executeUpdate();

            if (etkilenenSatir > 0) {
                //   System.out.println("Veri ekleme başarılı!");
            } else {
                //  System.out.println("Veri ekleme başarısız!");
            }

            //JOptionPane.showConfirmDialog(null,"talep başarılı bir şekilde gönderildi","talep",JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void talepOgretmen(int talepEdenHoca,int talepEdilenOgrenci,String talepDurumu,String dersAd)
    {

        try {


            String sql = "INSERT INTO talep2 (talepedenhoca, talepedilenogrenci, anlasmadurumu,talepedilenders) VALUES (?, ?, ?,?)";

            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            preparedStatement.setInt(1, talepEdenHoca); // Öğrenci ID'sini buraya girin
            preparedStatement.setInt(2, talepEdilenOgrenci); // Hoca ID'sini buraya girin
            preparedStatement.setString(3, talepDurumu); // Anlaşma durumunu buraya girin
            preparedStatement.setString(4,dersAd);
            int etkilenenSatir = preparedStatement.executeUpdate();

            if (etkilenenSatir > 0) {
                System.out.println("Veri ekleme başarılı!");
            } else {
                System.out.println("Veri ekleme başarısız!");
            }

            JOptionPane.showConfirmDialog(null,"talep başarılı bir şekilde gönderildi","talep",JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void talepOgretmenInformation (int studentId,ArrayList<Integer>teacherId,ArrayList<String>talepAnlasma,ArrayList<String> dersler)
    {
        try  {

            String sql = "SELECT talepedenhoca, talepedilenogrenci, anlasmadurumu, talepedilenders FROM talep2 WHERE talepedilenogrenci = ?";

            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int talepEdenHoca = resultSet.getInt("talepedenhoca");
                String anlasmaDurumu = resultSet.getString("anlasmadurumu");
                String talepEdilenDers = resultSet.getString("talepedilenders");
                teacherId.add(talepEdenHoca);
                dersler.add(talepEdilenDers);
                talepAnlasma.add(anlasmaDurumu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void talepOgretmenInformation(String talepEdilenDers,ArrayList<Integer>talepEdilenOgrenci,ArrayList<String> talepAnlasma)
    {
        try  {

            String sql = "SELECT talepedenogrenci, talepedilenhoca, anlasmadurumu, talepedilenders FROM talep WHERE talepedilenders = ?";

            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            preparedStatement.setString(1,talepEdilenDers);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int talepedenogrenci = resultSet.getInt("talepedenogrenci");
                talepEdilenOgrenci.add(talepedenogrenci);
                String anlasmaDurumu = resultSet.getString("anlasmadurumu");
                talepAnlasma.add(anlasmaDurumu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void ogretmentalepDurumSet(String durum,String talepedilenders,int talepEdenHoca,int talepEdilenOgrenci)
    {

        PreparedStatement stmt = null;
        try {


            // SQL sorgusu oluşturma
            String sql = "UPDATE talep2 SET anlasmadurumu = ? WHERE talepedenhoca = ? AND talepedilenogrenci = ? AND talepedilenders = ?";

            // Sorguyu çalıştırma
            stmt = connection1.prepareStatement(sql);
            stmt.setString(1, durum);
            stmt.setInt(2, talepEdenHoca);
            stmt.setInt(3, talepEdilenOgrenci);
            stmt.setString(4, talepedilenders);
            stmt.executeUpdate();

            System.out.println("Veri güncellendi.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int talepSay(int ogrenciID,String talepEdilenDers)
    {

        int count = 0;
        try  {

            String sql = "SELECT COUNT(*) FROM talep WHERE talepedenogrenci = ? AND talepedilenders = ?";

            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            preparedStatement.setInt(1, ogrenciID);
            preparedStatement.setString(2, talepEdilenDers);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    public void talepInformation(ArrayList<Integer> hocaNo,ArrayList<String> dersler,int talepEdenOgrenci,ArrayList<String> talepAnlasma)
    {

        try  {

            String sql = "SELECT talepedenogrenci, talepedilenhoca, anlasmadurumu, talepedilenders FROM talep WHERE talepedenogrenci = ?";

            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            preparedStatement.setInt(1, talepEdenOgrenci);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int talepEdilenHoca = resultSet.getInt("talepedilenhoca");
                String anlasmaDurumu = resultSet.getString("anlasmadurumu");
                String talepEdilenDers = resultSet.getString("talepedilenders");
                hocaNo.add(talepEdilenHoca);
                dersler.add(talepEdilenDers);
                talepAnlasma.add(anlasmaDurumu);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void talepInformation2(ArrayList<Integer> ogrenciNo,ArrayList<String> dersler,int talepedilenhoca,ArrayList<String> talepAnlasma)
    {

        try  {
            String sql = "SELECT talepedenogrenci, talepedilenhoca, anlasmadurumu, talepedilenders FROM talep WHERE talepedilenhoca = ?";

            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            preparedStatement.setInt(1, talepedilenhoca);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int talepedenogrenci = resultSet.getInt("talepedenogrenci");
                String anlasmaDurumu = resultSet.getString("anlasmadurumu");
                String talepEdilenDers = resultSet.getString("talepedilenders");
                ogrenciNo.add(talepedenogrenci);
                dersler.add(talepEdilenDers);
                talepAnlasma.add(anlasmaDurumu);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void talepInformation(String talepEdilenDers,ArrayList<Integer>talepEdenOgrenci,ArrayList<String> talepAnlasma)
    {
        try  {

            String sql = "SELECT talepedenogrenci, talepedilenhoca, anlasmadurumu, talepedilenders FROM talep WHERE talepedilenders = ?";

            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            preparedStatement.setString(1,talepEdilenDers);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int talepedenogrenci = resultSet.getInt("talepedenogrenci");
                talepEdenOgrenci.add(talepedenogrenci);
                String anlasmaDurumu = resultSet.getString("anlasmadurumu");
                talepAnlasma.add(anlasmaDurumu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void talepDurumSet(String durum,String talepedilenders,int talepedenOgrenci,int talepEdilenHoca)
    {


        PreparedStatement stmt = null;
        try {


            // SQL sorgusu oluşturma
            String sql = "UPDATE talep SET anlasmadurumu = ? WHERE talepedenogrenci = ? AND talepedilenhoca = ? AND talepedilenders = ?";

            // Sorguyu çalıştırma
            stmt = connection1.prepareStatement(sql);
            stmt.setString(1, durum);
            stmt.setInt(2, talepedenOgrenci);
            stmt.setInt(3, talepEdilenHoca);
            stmt.setString(4, talepedilenders);
            stmt.executeUpdate();

            System.out.println("Veri güncellendi.");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void setStudentAnlasmaSay(int anlasmaSay,int studentId)
    {
        PreparedStatement stmt = null;
        try {

            // SQL sorgusu oluşturma
            String sql = "UPDATE student SET anlasmasay = ? WHERE studentid = ?";

            // Sorguyu çalıştırma
            stmt = connection1.prepareStatement(sql);
            stmt.setInt(1, anlasmaSay);
            stmt.setInt(2, studentId);
            stmt.executeUpdate();

            System.out.println("Veri güncellendi.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateStudentCounter(int teacherId)
    {
        try {

            String sql = "UPDATE teacher SET studentCounter = studentCounter + 1 WHERE id = ?";
            PreparedStatement preparedStatement = connection1.prepareStatement(sql);

            preparedStatement.setInt(1, teacherId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                //   System.out.println("Student Counter başarıyla artırıldı!");
            } else {
                System.out.println("Öğretmen bulunamadı!");
            }

        } catch (SQLException e) {
            System.out.println("Hata oluştu!");
            e.printStackTrace();
        }
    }
    public void insertKriterDers(String course,double coefficient,int teacherid) {

        try {

            String sql = "INSERT INTO kriterdersler (course, coefficient,teacherid) VALUES (?, ?,?)";
            PreparedStatement statement = connection1.prepareStatement(sql);
            statement.setString(1, course); // Örnek bir ders adı
            statement.setDouble(2, coefficient); // Örnek bir katsayı
            statement.setInt(3,teacherid);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Veri eklendi!");
            }

        } catch (SQLException e) {
            System.out.println("Veritabanına bağlanırken hata oluştu.");
            e.printStackTrace();
        }


    }

    public double puanHesapla(int teacherid,int studentId)
    {
        ArrayList<String> courses = new ArrayList<>();
        ArrayList<Double>coefficients = new ArrayList<>();
        double hesap = 0;
        double toplamKatsay = 0;

        try {

            String query = "SELECT * FROM kriterdersler WHERE teacherid = ?";
            PreparedStatement statement = connection1.prepareStatement(query);
            statement.setInt(1, teacherid); // Değerinizi buraya ekleyin

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String course = resultSet.getString("course");
                double coefficient = resultSet.getDouble("coefficient");
                int teacherId = resultSet.getInt("teacherid");
                courses.add(course);
                coefficients.add(coefficient);

                // Verileri kullanma veya işleme örneği
                System.out.println("Course: " + course + ", Coefficient: " + coefficient + ", Teacher ID: " + teacherId);
            }
        } catch (SQLException e) {
            System.out.println("Veritabanına bağlanırken hata oluştu.");
            e.printStackTrace();
        }


        ArrayList<String> ders = new ArrayList<>();
        ArrayList<String> harfNot = new ArrayList<>();

        studentCourseInformation(studentId,ders,harfNot);


        for (int i = 0; i < courses.size(); i++) {
            toplamKatsay = toplamKatsay + coefficients.get(i);
            if(ders.contains(courses.get(i)))
            {

                int index = ders.indexOf(courses.get(i));


                if(harfNot.get(index).equals("AA"))
                {
                    hesap = coefficients.get(i)*4 + hesap;
                }

                else if(harfNot.get(index).equals("BA"))
                {
                    hesap = coefficients.get(i)*3.5 + hesap;
                }
                else if(harfNot.get(index).equals("BB"))
                {
                    hesap = coefficients.get(i)*3 + hesap;
                }
                else if(harfNot.get(index).equals("CB"))
                {
                    hesap = coefficients.get(i)*2.5 + hesap;
                }
                else if(harfNot.get(index).equals("CC"))
                {
                    hesap = coefficients.get(i)*2 + hesap;
                }
                else if(harfNot.get(index).equals("DC"))
                {
                    hesap = coefficients.get(i)*1.5 + hesap;
                }
                else if(harfNot.get(index).equals("DD"))
                {
                    hesap = coefficients.get(i)*1 + hesap;
                }

                else
                {
                    hesap = coefficients.get(i)*0 + hesap;
                }

            }



        }
        System.out.println(hesap);
        System.out.println(toplamKatsay);
        hesap = hesap/toplamKatsay;
        return hesap;

    }

    public void characterSet(int character)
    {
        try {

            // SQL sorgusu
            String sql = "UPDATE admin SET numberofcharacters = ?";

            // Prepared statement oluşturma
            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            preparedStatement.setInt(1, character); // yourDesiredValue değiştirilecek değerdir.

            // Sorguyu çalıştırma
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Güncellenen satır sayısı: " + rowsAffected);

        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantısı başarısız. Hata: " + e.getMessage());
        }

    }

    public void message(int senderid, int receiverid, String message) {


        try  {



            String sql = "INSERT INTO message (senderid, receiverid,message) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection1.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, senderid);
            stmt.setInt(2, receiverid);
            stmt.setString(3,message);


            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                    } else {
                        throw new SQLException("Oluşturulan anahtar alınamadı, veri eklenemedi.");
                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void messageBox(int id){


        gelenIsim = new ArrayList<>();
        gelenMesaj = new ArrayList<>();
        gelenId = new ArrayList<>();

        gidenIsim = new ArrayList<>();
        gidenMesaj = new ArrayList<>();

        String senderName = null;
        String receiverName = null;
        int senderId;


        try {
//gelenkutusu
            Statement statement = connection1.createStatement();

            String query = "SELECT * FROM message WHERE receiverid = " + id;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                senderId   = resultSet.getInt("senderid");
                String message = resultSet.getString("message");

                if(isStudent(senderId)){
                    studentInformation(senderId);
                    senderName = studentName+" "+studentSurname;
                } else if (isTeacher(senderId)) {
                    teacherInformation(senderId);
                    senderName = teacherName+" "+teacherSurname;
                }

                gelenIsim.add(senderName);
                gelenMesaj.add(message);
                gelenId.add(senderId);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
//gidenkutusu
            Statement statement = connection1.createStatement();

            String query = "SELECT * FROM message WHERE senderid = " + id;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int receiverId   = resultSet.getInt("receiverid");
                String message = resultSet.getString("message");
                if(isStudent(receiverId)){
                    studentInformation(receiverId);
                    receiverName = studentName+" "+studentSurname;
                } else if (isTeacher(receiverId)) {
                    teacherInformation(receiverId);
                    receiverName = teacherName+" "+teacherSurname;
                }

                gidenMesaj.add(message);
                gidenIsim.add(receiverName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void studentList()
    {
        studentNameList = new ArrayList<>();
        studentIdList = new ArrayList<>();


        try {
            Statement statement = connection1.createStatement();

            String query = "SELECT studentid, name FROM student";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                studentIdList.add(rs.getInt("studentid"));
                studentNameList.add(rs.getString("name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void teacherList()
    {
        teacherNameList = new ArrayList<>();
        teacherIdList = new ArrayList<>();


        try {

            Statement statement = connection1.createStatement();

            String query = "SELECT id, name FROM teacher";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                teacherIdList.add(rs.getInt("id"));
                teacherNameList.add(rs.getString("name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void studentInformationSet(int id, String attribute,String dataType, String setData){


        int newIntData;
        double newDoubleData;

        try  {

            // SQL sorgusu
            String sql = "UPDATE student SET "+ attribute +" = ? WHERE studentid = ?";

            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            if(dataType.equals("integer")) {
                newIntData = Integer.parseInt(setData);
                preparedStatement.setInt(1, newIntData);
            } else if (dataType.equals("double")) {
                newDoubleData = Double.parseDouble(setData);
                preparedStatement.setDouble(1,newDoubleData);
            }else{
                preparedStatement.setString(1, setData);
            }

            preparedStatement.setInt(2,id);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Güncellenen satır sayısı: " + rowsAffected);

        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantısı başarısız. Hata: " + e.getMessage());
        }

    }
    public void adminInformation()
    {

        try  {

            String sql = "SELECT numberofcharacters, talepmiktar, id, password FROM admin";

            PreparedStatement preparedStatement = connection1.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                adminNumberOfCharacter = resultSet.getInt("numberofcharacters");
                adminTalepMiktar = resultSet.getInt("talepmiktar");
                adminId = resultSet.getString("id");
                adminPassword = resultSet.getInt("password");


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void teacherCourseInformation1(int teacherid, ArrayList<String> teacherCourseName)
    {

        try  {


            String query = "SELECT coursename FROM teachercourse WHERE teacherid = ?";
            PreparedStatement preparedStatement = connection1.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                teacherCourseName.add(resultSet.getString("coursename"));

            }

        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantısı başarısız. Hata: " + e.getMessage());
        }

    }
    public void teacherInterestInformation(int teacherid, ArrayList<String> teacherInterest)
    {

        try  {


            String query = "SELECT interest FROM teacherinterest WHERE teacherid = ?";
            PreparedStatement preparedStatement = connection1.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                teacherInterest.add(resultSet.getString("interest"));

            }

        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantısı başarısız. Hata: " + e.getMessage());
        }

    }
    public void teacherInterestSet(int id,String data, String setData){

        try {

            // SQL sorgusu
            String sql = "UPDATE teacherinterest SET interest = ? WHERE teacherid = ? AND interest = ?";

            PreparedStatement preparedStatement = connection1.prepareStatement(sql);

            preparedStatement.setString(1,setData);
            preparedStatement.setInt(2, id);
            preparedStatement.setString(3,data);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Güncellenen satır sayısı: " + rowsAffected);

        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantısı başarısız. Hata: " + e.getMessage());
        }

    }

    public void teacherInformationSet(int id, String attribute,String dataType, String setData){


        int newIntData;

        try {

            // SQL sorgusu
            String sql = "UPDATE teacher SET "+ attribute +" = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection1.prepareStatement(sql);
            if(dataType.equals("integer")) {
                newIntData = Integer.parseInt(setData);
                preparedStatement.setInt(1, newIntData);
            }else{
                preparedStatement.setString(1, setData);
            }

            preparedStatement.setInt(2,id);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Güncellenen satır sayısı: " + rowsAffected);

        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantısı başarısız. Hata: " + e.getMessage());
        }

    }

    public void deleteStudent(int id)
    {

        try {

            String deleteSql = "DELETE FROM student WHERE studentid = ?"  ;
            PreparedStatement deleteStmt = connection1.prepareStatement(deleteSql);
            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTeacher(int id)
    {

        try {

            String deleteSql = "DELETE FROM teacher WHERE id = ?" ;
            PreparedStatement deleteStmt = connection1.prepareStatement(deleteSql);
            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTeacherInterest(int id,String interest)
    {

        try {

            String deleteSql = "DELETE FROM teacherinterest WHERE teacherid = ? AND interest = ?" ;
            PreparedStatement deleteStmt = connection1.prepareStatement(deleteSql);
            deleteStmt.setInt(1, id);
            deleteStmt.setString(2, interest);
            deleteStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public DefaultTableModel tableModelTalepStudent( ){


        String[] columnName = {"Öğrenci No", "Öğretmen No", "Talep Durumu", "Ders"};
        DefaultTableModel tableModel = new DefaultTableModel();


        try {
            Statement statement = connection1.createStatement();

            String query = "SELECT*FROM talep";
            ResultSet rs = statement.executeQuery(query);


            for (String name : columnName) {
                tableModel.addColumn(name);
            }

            while (rs.next()) {

                int talepEden = rs.getInt("talepedenogrenci");
                int talepEdilen = rs.getInt("talepedilenhoca");
                String anlasma = rs.getString("anlasmadurumu");
                String ders = rs.getString("talepedilenders");


                tableModel.addRow(new Object[]{talepEden, talepEdilen, anlasma, ders});

            }

            return tableModel;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    public DefaultTableModel tableModelTalepTeacher( ){


        String[] columnName = {"Öğretmen No", "Öğrenci No", "Talep Durumu", "Ders"};
        DefaultTableModel tableModel = new DefaultTableModel();


        try {
            Statement statement = connection1.createStatement();

            String query = "SELECT*FROM talep2";
            ResultSet rs = statement.executeQuery(query);


            for (String name : columnName) {
                tableModel.addColumn(name);
            }

            while (rs.next()) {

                int talepEden = rs.getInt("talepedenhoca");
                int talepEdilen = rs.getInt("talepedilenogrenci");
                String anlasma = rs.getString("anlasmadurumu");
                String ders = rs.getString("talepedilenders");

                tableModel.addRow(new Object[]{talepEden, talepEdilen, anlasma, ders});

            }

            return tableModel;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }





}
