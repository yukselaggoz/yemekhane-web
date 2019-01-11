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
@ManagedBean(name="stokBean")
@SessionScoped
public class StokBean {

    String url = "jdbc:mysql://localhost:3306/Yemekhane";
    String username = "root";
    String password = "root";
    
    Connection connect =null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    List<Stok> sorguSonucu;
    
    public List<Stok> getSorguSonucu() {
        return sorguSonucu;
    }

    public void setSorguSonucu(List<Stok> sorguSonucu) {
        this.sorguSonucu = sorguSonucu;
    }
    
    public List<Stok> getStokListesi() throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection(url, username, password);
        
        preparedStatement = connect.prepareStatement("select * from stok");
        resultSet = preparedStatement.executeQuery();
        sorguSonucu = new ArrayList<>();
        
        while(resultSet.next()){
            Stok stok = new Stok();
            stok.setId(resultSet.getInt("p_id"));
            stok.setUrun(resultSet.getString("s_ad"));
            stok.setMiktar(resultSet.getInt("s_miktar"));
            sorguSonucu.add(stok);
        }
        
        preparedStatement.close();
        connect.close();
        
        return sorguSonucu;
    }
    
    public void ekleStok(String ad, String miktar, String gorev) throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection(url, username, password);
        
        preparedStatement = connect.prepareStatement("insert into stok(s_ad, s_miktar) values(?, ?)");
        preparedStatement.setString(1, ad);
        preparedStatement.setString(2, miktar);
        
        preparedStatement.close();
        connect.close();
    }
    
    public void guncelleStok(int id) {
        
    }
    
    public void silStok(int id) {
        
    }
    
}
