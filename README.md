# Öğrenci Bilgi Sistemi Projesi

**Hazırlayanlar:** Fatma Nur Kurt, Tayyib Okur

---

## Özet

Bu proje, 2023-2024 Güz döneminde yazılım laboratuvarı için hazırlanan bir öğrenci bilgi sistemi uygulamasıdır. Amacımız, veritabanı becerilerimizi geliştirirken öğrenci, öğretmen ve yönetici bilgilerini tutacak, bunlar üzerinden çeşitli işlem ve atama işlemlerini gerçekleştirebilen bir masaüstü uygulaması geliştirmektir.  
Uygulamada PostgreSQL veritabanı kullanılmış, Java ve Java Swing ile masaüstü arayüzü oluşturulmuştur.

---

## Özellikler

- **Kullanıcı Girişleri:**  
  Öğrenci, öğretmen ve yönetici ayrı giriş panelleri üzerinden sisteme erişim sağlar.

- **Öğrenci İşlemleri:**  
  - Transkript PDF dosyasını yükleyip, ders notlarını veritabanına kaydetme.  
  - Açılmış proje derslerinden ders talebi oluşturma ve talep durumlarını görüntüleme.

- **Öğretmen İşlemleri:**  
  - İlgi alanı seçimi ve ders taleplerini görüntüleme, kabul/ret işlemleri.  
  - Formül puanı oluşturma ve öğrencileri filtreleme.

- **Yönetici İşlemleri:**  
  - Öğrenci ve öğretmen bilgilerini ekleme, güncelleme, silme.  
  - Ders atama işlemlerini (rastgele, ortalamaya göre veya formül puanına göre) yönetme.

---

## Gereksinimler

- **Programlama Dili:** Java  
- **Veritabanı:** PostgreSQL  
- **Arayüz:** Java Swing  
- **Bağlantı Kütüphanesi:** JDBC (PostgreSQL JDBC Driver)  
- **PDF İşlemleri:** PDFBox

---

## Kurulum ve Çalıştırma

1. **Depoyu Klonlayın:**

   ```bash
   git clone https://github.com/Dawnfairy/Project-Course-Registration-System.git


1. **Veritabanı Kurulumu:**
  
   PostgreSQL’i kurun ve proje için gerekli veritabanı ve tabloları oluşturun.
   SQL sorguları ve tablo yapıları, proje dokümanınızda yer almalıdır.

1. **IDE Seçimi:**
 
   Projeyi Android Studio, IntelliJ IDEA, Eclipse veya Visual Studio Code gibi bir Java IDE’sinde açın.
   Gerekli JDBC ve PDFBox kütüphanelerinin projeye eklenmiş olduğundan emin olun.
   
1. **Projeyi Açma:**

   IDE’de projeyi içe aktarın veya “File > Open” seçeneği ile proje klasörünü açın.
   
1. **Projeyi Çalıştırın:**

  IDE üzerinden “Run” veya “Debug” seçeneğini kullanarak projeyi derleyip çalıştırın.
  Uygulama açıldığında, kullanıcı giriş ekranından sisteme erişim sağlanacaktır.


## Not: Veritabanı Bağlantı Ayarları

Aşağıdaki kod parçası, PostgreSQL veritabanına bağlantı sağlamak için kullanılmaktadır:

    ```java
    {
        try {
            connection1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "YazLab1_1", "postgres", "12345");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

**Dikkat Edilmesi Gerekenler:**

- **Veritabanı URL'si:**  
  Yukarıdaki URL, yerel makinede çalışan PostgreSQL sunucusuna ve "YazLab1_1" adlı veritabanına bağlanmayı denemektedir. Kendi ortamınızda, sunucunuzun adresi, portu ve veritabanı adınızı kullanacak şekilde bu URL'yi güncellemeniz gerekmektedir.

- **Kullanıcı Adı ve Şifre:**  
  `"postgres"` ve `"12345"` örnek değerlerdir. Gerçek veritabanı ortamınızda tanımlı olan kullanıcı adı ve şifrenizi kullanacak şekilde değiştirilmelidir.

---

## Kullanım


- **Giriş:**
   - Her kullanıcının (öğrenci, öğretmen, yönetici) kendine ait ID ve şifre ile sisteme giriş yapması sağlanır.

- **Öğrenci Paneli:**
   - Transkript dosyası yüklenip ders notları kaydedilir, ders talepleri oluşturulur ve mevcut dersler görüntülenir.

- **Öğretmen Paneli:**
   - Gelen ders talepleri onaylanır veya reddedilir, formül puanı oluşturularak öğrenci filtreleme işlemleri yapılır.

- **Yönetici Paneli:**
   - Öğrenci ve öğretmen bilgileri yönetilir, toplu öğrenci üretimi ve ders atama işlemleri gerçekleştirilir.
 

## İletişim

Proje ile ilgili sorular, geri bildirimler veya katkı talepleri için aşağıdaki kişilerle iletişime geçebilirsiniz:

  Fatma Nur Kurt - kurtfatmanur8@gmail.com
  Tayyib Okur - ultratayyib@gmail.com
