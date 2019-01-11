import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
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

@ManagedBean(name = "personel")
@SessionScoped
public class PersonelBean {
    
    String url = "jdbc:mysql://localhost:3306/Yemekhane";
    String username = "root";
    String password = "root";
    
    Connection connect =null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    List<Personel> sorguSonucu;
    
    public List<Personel> getSorguSonucu() {
        return sorguSonucu;
    }

    public void setSorguSonucu(List<Personel> sorguSonucu) {
        this.sorguSonucu = sorguSonucu;
    }
    
    public List<Personel> getPersonelListesi() throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection(url, username, password);
        
        preparedStatement = connect.prepareStatement("select * from personel");
        resultSet = preparedStatement.executeQuery();
        sorguSonucu = new ArrayList<>();
        
        while(resultSet.next()){
            Personel personel = new Personel();
            personel.setId(resultSet.getInt("p_id"));
            personel.setIsim(resultSet.getString("p_isim"));
            personel.setSoyisim(resultSet.getString("p_soyisim"));
            personel.setGorev(resultSet.getString("p_gorev"));
            sorguSonucu.add(personel);
        }
        
        preparedStatement.close();
        connect.close();
        
        return sorguSonucu;
    }
    
    public void eklePersonel(String isim, String soyisim, String gorev) throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection(url, username, password);
        
        preparedStatement = connect.prepareStatement("insert into personel(p_isim, p_soyisim, p_gorev) values(?, ?, ?)");
        preparedStatement.setString(1, isim);
        preparedStatement.setString(2, soyisim);
        preparedStatement.setString(3, gorev);
        
        preparedStatement.close();
        connect.close();
    }
    
    public void guncellePersonel(int id) {
        
    }
    
    public void silPersonel(int id) {
        
    }
}
