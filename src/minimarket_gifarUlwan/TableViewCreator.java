package minimarket_gifarUlwan;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*; 
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewCreator
{
    ObservableList<BarangDibeli> listBarangDibeli = FXCollections.observableArrayList();
    TableView table;
    
    public TableView createTable()
    {
        TableColumn<BarangDibeli, String> kolomJumlah = new TableColumn<>("Jumlah");
        TableColumn<BarangDibeli, String> kolomNama = new TableColumn<>("Nama Barang");
        TableColumn<BarangDibeli, String> kolomHarga = new TableColumn<>("Subtotal(Rp)");
        TableColumn<BarangDibeli, String> kolomHargaSatuan = new TableColumn<>("Harga(Rp)");
        
        kolomJumlah.setMinWidth(30);
        kolomJumlah.setStyle("-fx-background-color:white");
        kolomJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlahBarang"));
        kolomNama.setMinWidth(170);
        kolomNama.setStyle("-fx-background-color:white");
        kolomNama.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        kolomHarga.setMinWidth(100);
        kolomHarga.setStyle("-fx-background-color:white");
        kolomHarga.setCellValueFactory(new PropertyValueFactory<>("hargaBarang"));
        kolomHargaSatuan.setMinWidth(100);
        kolomHargaSatuan.setStyle("-fx-background-color:white");
        kolomHargaSatuan.setCellValueFactory(new PropertyValueFactory<>("hargaSatuan"));
        
        table = new TableView<>();
        table.setMaxWidth(430.0);
        table.setItems(listBarangDibeli);
        table.getColumns().addAll(kolomJumlah, kolomNama,kolomHargaSatuan, kolomHarga);
        
        return table;
    }
    
    public void addBarangDibeli(String nama, int jumlah, int harga, int hargaSatuan, int idPembelian)
    {
        listBarangDibeli.add(new BarangDibeli(nama,harga,jumlah,hargaSatuan,idPembelian));
    }
    
    public void resetPembelian()
    {
        SaveDataTransaksi data = new SaveDataTransaksi();
        data.simpanData(listBarangDibeli);
        
        listBarangDibeli = FXCollections.observableArrayList();
    }
}
