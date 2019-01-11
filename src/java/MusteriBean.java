import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

/**
 *
 * @author yukselaggoz
 */
@ManagedBean(name="musteriBean")
@SessionScoped
public class MusteriBean {

    String url = "jdbc:mysql://localhost:3306/Yemekhane";
    String username = "root";
    String password = "root";
    
    Connection connect =null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    List<Musteri> sorguSonucu;
    
    public List<Musteri> getSorguSonucu() {
        return sorguSonucu;
    }

    public void setSorguSonucu(List<Musteri> sorguSonucu) {
        this.sorguSonucu = sorguSonucu;
    }
    
    public List<Musteri> getMusteriListesi() throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection(url, username, password);
        
        preparedStatement = connect.prepareStatement("select * from musteri");
        resultSet = preparedStatement.executeQuery();
        sorguSonucu = new ArrayList<>();
        
        while(resultSet.next()){
            Musteri musteri = new Musteri();
            musteri.setId(resultSet.getInt("m_id"));
            musteri.setIsim(resultSet.getString("m_isim"));
            musteri.setSoyisim(resultSet.getString("m_soyisim"));
            musteri.setBakiye(resultSet.getDouble("m_bakiye"));
            sorguSonucu.add(musteri);
        }
        
        preparedStatement.close();
        connect.close();
        
        return sorguSonucu;
    }
    
    public void ekleMusteri(String isim, String soyisim, String bakiye) throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection(url, username, password);
        
        preparedStatement = connect.prepareStatement("insert into musteri(m_isim, m_soyisim, m_bakiye) values(?, ?, ?)");
        preparedStatement.setString(1, isim);
        preparedStatement.setString(2, soyisim);
        preparedStatement.setString(3, bakiye);
        
        preparedStatement.close();
        connect.close();
    }
    
    public void guncelleMusteri(int id) {
        
    }
    
    public void silMusteri(int id) {
        
    }
}

