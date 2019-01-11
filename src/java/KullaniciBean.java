import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author yukselaggoz
 */
@ManagedBean(name="kullaniciBean")
@SessionScoped
public class KullaniciBean {
    
    String kullanici_ad;
    String isim;
    String soyisim;
    String parola;
    String email;
    int telefon;
    
    int i=0;
    String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getKullanici_ad() {
        return kullanici_ad;
    }

    public void setKullanici_ad(String kullanici_ad) {
        this.kullanici_ad = kullanici_ad;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }
    
    public String veriTabaninaGonder()//Sayfadan girilen verileri veri tabanına gönderem metot.
    {
        PreparedStatement ps=null;//Veri tabanına gönderilecek bilgileri bu nesne tuttacak ve veri tabanına gönderecek.
        Connection con=null;//Veri tabanına bağlantı yapmamızı sağlayacak nesne.
        try {
            Class.forName("com.mysql.jdbc.Driver");//Hangi türde bir veri tabanını kullanacağını bildiriyoruz.
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Yemekhane","root","root");
            ps=con.prepareStatement("INSERT INTO kullanici(kullanici_ad, k_sifre, k_isim, k_soyisim, k_email, k_telefon) VALUES(?,?,?,?,?,?)");
            ps.setString(1, kullanici_ad);
            ps.setString(2, parola);
            ps.setString(3, isim);
            ps.setString(4, soyisim);
            ps.setString(5, email);
            ps.setInt(6, telefon);
            i=ps.executeUpdate();//executeUpdate verilen sorguyu çalıştırır ve integer değer döndürür.
            //exequteUdate eğer 0'dan büyük değer döndürürse kayıt başarılı olmuş demektir. 
        }
        catch(ClassNotFoundException | SQLException exception)//Hata olduğunda konsola verilecek.
        {
            System.out.println(exception);
            setErrorMessage(exception.toString());
        }
        finally //Ne olursa olsun her koşulda çalışacak kısım 
        {
            try {
                if(con!=null){
                    con.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            catch(SQLException sqlException)
            {
                System.out.println(sqlException);
            }
        }
        if(i>0) //Sorgu başarılı olarak çalışınca i 0'dan büyük oluyor ve bizi başarılı sayfasına yönlediriyor.
        {
            return "login?faces-redirect=true";
        }
        else //Sorgu başarısız ise başarısız sayfasına gidiyoruz.
        {
            return "kaydol?faces-redirect=true";
        }
    }
}
