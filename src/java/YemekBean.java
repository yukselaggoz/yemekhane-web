import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author yukselaggoz
 */
@ManagedBean(name="yemekBean")
@SessionScoped
public class YemekBean {

    String url = "jdbc:mysql://localhost:3306/Yemekhane";
    String username = "root";
    String password = "root";
    
    Connection connect =null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    List<Yemek> sorguSonucu;
    
    public List<Yemek> getSorguSonucu() {
        return sorguSonucu;
    }

    public void setSorguSonucu(List<Yemek> sorguSonucu) {
        this.sorguSonucu = sorguSonucu;
    }
    
    public List<Yemek> getMusteriListesi() throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection(url, username, password);
        
        preparedStatement = connect.prepareStatement("select * from yemekgunluk");
        resultSet = preparedStatement.executeQuery();
        sorguSonucu = new ArrayList<>();
        
        while(resultSet.next()){
            Yemek yemek = new Yemek();
            yemek.setId(resultSet.getInt("y_id"));
            yemek.setGun(resultSet.getDate("y_gun"));
            yemek.setSabah(resultSet.getString("y_sabah"));
            yemek.setOgle(resultSet.getString("y_ogle"));
            yemek.setOgle(resultSet.getString("y_aksam"));
            sorguSonucu.add(yemek);
        }
        
        preparedStatement.close();
        connect.close();
        
        return sorguSonucu;
    }
    
    public void ekleYemek(Date gun, String sabah, String ogle, String aksam) throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection(url, username, password);
        
        preparedStatement = connect.prepareStatement("insert into yemekgunluk(y_gun, y_sabah, y_ogle, y_aksam) values(?, ?, ?, ?)");
        preparedStatement.setDate(1, (java.sql.Date) gun);
        preparedStatement.setString(2, sabah);
        preparedStatement.setString(3, ogle);
        preparedStatement.setString(4, aksam);
        
        preparedStatement.close();
        connect.close();
    }
    
    public void guncelleYemek(int id) {
        
    }
    
    public void silYemek(int id) {
        
    }
    
}
