package minimarket_gifarUlwan;

import java.io.*;
import javafx.collections.*;
import java.sql.*;
import java.util.*;
public class SaveDataTransaksi
{
    public SaveDataTransaksi()
    {
        
    }
    public void simpanData(ObservableList<BarangDibeli> listBarang)
    {
        
        try
        {
            Class.forName( "com.mysql.jdbc.Driver" ).newInstance();
            // Get a connection to the database
            Connection connection; 
            connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/DataPembelian","adminKasir","adminKasirPass");  
            Statement statement = connection.createStatement();
            
            String sqlQuery;
            Random randomizer = new Random();
            
            for(BarangDibeli item : listBarang)
            {
                sqlQuery = "INSERT INTO pembelian(idPembelian,Nama_Barang,Jumlah,Total_Harga) VALUE('%d','%s', '%d', '%d')";
                sqlQuery = String.format(sqlQuery, item.getIDPembelian(), item.getNamaBarang(), item.getJumlahBarang(), item.getHargaBarang());
                statement.execute(sqlQuery);
            }
            
            
        }
        catch(SQLException se)
        {
            System.out.println( "SQL Exception:" ) ;
            while( se != null )
            {
                System.out.println( "State  : " + se.getSQLState()  ) ;
                System.out.println( "Message: " + se.getMessage()   ) ;
                System.out.println( "Error  : " + se.getErrorCode() ) ;
                
                se = se.getNextException() ;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
