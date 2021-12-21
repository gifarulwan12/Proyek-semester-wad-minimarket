package minimarket_gifarUlwan;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*; 
import javafx.scene.paint.*; 
import javafx.scene.text.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*;

public class MainClass extends Application
{
    public static void main(String[] args) {
        launch(args);
    }
    
    Text NamaBarang = new Text("");
    Text HargaBarang = new Text("");
    Text TotalHargaText = new Text("");
    TextField textFieldJumlah = new TextField ();
    private Label myLabel = new Label("0");
    private TextArea textListBarang = new TextArea("");
    
    private int indexBarang = -1;
    private Stage sg;
    Label totalBayarLabel, kembalianLabel = new Label("Rp.");
    
    TulisBarang tb = new TulisBarang();
    Text totalText = new Text("");

    ObservableList<String> optionsKasir = FXCollections.observableArrayList(
        "Gifar Ulwan"
    );
    final ComboBox comboBoxKasir = new ComboBox(optionsKasir);
    ObservableList<String> optionsBarang = FXCollections.observableArrayList(tb.kodeBarang);
    ComboBox comboBoxBarang = new ComboBox(optionsBarang);
    
    private Random randomizer = new Random();
    private int idPembelian = randomizer.nextInt(1000000);
    
    private ShowEditBarang showEdit = new ShowEditBarang(tb,comboBoxBarang);
    private HitungHarga hitungHarga = new HitungHarga();
    private int TotalHarga, totalBayar=0;
    private int harga, jumlahBarang, kembalian;
    private TableView table;
    private TableViewCreator TableCreator = new TableViewCreator();
    
    @Override
    public void start(Stage stage)
    {
        BorderPane borderPane = new BorderPane();
        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);
        
        GridPane pane2 = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);
        pane.getColumnConstraints().add(new ColumnConstraints(90));//set kolom 1 75 width
        pane.getColumnConstraints().add(new ColumnConstraints(90));//set kolom 2 75 width
        pane.getColumnConstraints().add(new ColumnConstraints(90));//set kolom 3 75 width

        pane.add(new Label("Kasir: "), 0, 0);
        pane.add(comboBoxKasir, 1,0);
        pane.add(new Label("Kode Barang: "), 0, 1);
        pane.add(comboBoxBarang, 1,1);
        
        EventHandler<ActionEvent> event = 
                  new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                if(comboBoxBarang.getValue() == null) return;
                TotalHargaText.setText("");
                int i = 0;
                for(String a: tb.kodeBarang)
                {
                    if(comboBoxBarang.getValue().equals(a))
                    {
                        indexBarang = i;
                        break;
                    }
                    i++;
                }
                NamaBarang.setText(tb.namaBarang.get(indexBarang));
                HargaBarang.setText(tb.harga.get(indexBarang));
            } 
        }; 
        
        comboBoxBarang.setOnAction(event); 
        
        pane.add(new Label("Nama Barang: "), 0, 2);
        pane.add(NamaBarang, 1,2);
        pane.add(new Label("Harga: "), 0, 3);
        pane.add(HargaBarang, 1,3);
        pane.add(new Label("Jumlah: "), 0, 4); 
        
        pane.add(textFieldJumlah, 1,4);
        
        EventHandler<ActionEvent> eventJumlah = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                jumlahBarang = Integer.parseInt(textFieldJumlah.getText());
                TotalHarga =  Integer.parseInt(tb.harga.get(indexBarang)) * jumlahBarang;
                TotalHargaText.setText("Rp"+TotalHarga);
            } 
        }; 
        
        textFieldJumlah.setOnAction(eventJumlah); 
        
        pane.add(new Label("Total Bayar: "), 0, 5);
        pane.add(TotalHargaText, 1,5);
       
        Button buttonCetak = new Button("Cetak");
        buttonCetak.setStyle("-fx-background-color: #47AB6C; -fx-text-fill: white");
        pane.add(buttonCetak, 1,6);
        
        Button buttonAddItem = new Button("Tambah");
        buttonAddItem.setStyle("-fx-background-color: #FF733F; -fx-text-fill: white");
        pane.add(buttonAddItem, 0,6);
        
        createPembayaran(pane);
        
        table = TableCreator.createTable();
        addPane2Component(pane2); //tambahkan list pembayaran yang di tengah
       
        buttonCetak.setOnAction(this::buttonClickCetak);
        buttonAddItem.setOnAction(this::buttonClickAdd);
        
        addHBoxAtas(borderPane);
        borderPane.setLeft(pane);
        borderPane.setCenter(pane2);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        borderPane.setStyle("-fx-background-color: #FFFFFF;");
        Scene scene = new Scene(borderPane, 1000, 700, Color.WHITE);
        stage.setTitle("MINIMARKET");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
        sg = stage;
        
    }

    
    private void buttonClickCetak(ActionEvent event)
    {
       Cetak c = new Cetak(TableCreator ,hitungHarga.getGrandTotal(), kembalian, totalBayar);
       c.showCetak();
       //sg.setScene(showEdit.showScene());
       //showEdit.showScene();
    }
    private void buttonClickAdd(ActionEvent event)
    {
       String nama = tb.namaBarang.get(indexBarang);
       int hargaSatuan = Integer.parseInt(tb.harga.get(indexBarang));
       //int jumlah = jumlahBarang, total = TotalHarga;
       
       TableCreator.addBarangDibeli(nama,jumlahBarang,TotalHarga, hargaSatuan, idPembelian);
       
       hitungHarga.addItem(TotalHarga);
       
       updateGrandTotal();
       resetText();
    }
    private void addHBoxAtas(BorderPane bpane)
    {
        HBox hbox = new HBox(20);
        hbox.setPadding(new Insets(20, 20, 20, 20));
        hbox.setAlignment(Pos.CENTER_LEFT);
        Label labelJudul = new Label("PEMBAYARAN");
        labelJudul.setTextFill(Color.web("#03a9fc"));
        labelJudul.setFont(new Font("Cambria", 20));
        hbox.getChildren().add(labelJudul);
        
        Button buttonTambah = new Button("Tambah Barang");
        Button buttonHapus = new Button("Hapus Barang");
        Button buttonEdit = new Button("Edit Barang");
        buttonTambah.setStyle("-fx-background-color: #ffffff; -fx-font-size:12");
        buttonHapus.setStyle("-fx-background-color: #ffffff; -fx-font-size:12 ");
        buttonEdit.setStyle("-fx-background-color: #ffffff; -fx-font-size:12 ");
        
        buttonTambah.setOnAction(showEdit::clickButtonTambah);
        buttonHapus.setOnAction(showEdit::clickButtonHapus);
        buttonEdit.setOnAction(showEdit::clickButtonEdit);
        
        hbox.getChildren().addAll(buttonTambah,buttonHapus,buttonEdit);
        
        bpane.setTop(hbox);
    }
    private void addPane2Component(GridPane pane2)
    {
        
        pane2.add(new Label("List pembelian barang"),0,0);
        pane2.add(table,0,1);
        totalText.setText("Total Pembayaran Rp0");
        totalText.setTextAlignment(TextAlignment.RIGHT);
        totalText.setFont(new Font("Cambria", 15));
        pane2.setHalignment(totalText, HPos.RIGHT);
        pane2.add(totalText, 0,2);
    }
    private void updateGrandTotal()
    {
        totalText.setText("Total Pembayaran Rp" + hitungHarga.getGrandTotal());
    }
    private void resetText()
    {
        NamaBarang.setText("");
        HargaBarang.setText("");
        TotalHargaText.setText("");
        textFieldJumlah.setText("");
        comboBoxBarang.setValue(null);
        jumlahBarang = 0;
        indexBarang = -1;
        TotalHarga = 0;
    }
    
    
    
    private void createPembayaran(GridPane pane)
    {
        pane.add(new Label("Bayar"), 0,8);
        totalBayarLabel = new Label("Rp." );
        totalBayarLabel.setFont(new Font("Cambria", 20));
        pane.add(totalBayarLabel, 0,9);
        
        List<Button> buttons = new ArrayList<Button>( Arrays.asList(
            new Button("Rp500"), new Button("Rp1000"), new Button("Rp2000"), new Button("Rp5000"),
            new Button("Rp10.000"), new Button("Rp20.000"), new Button("Rp50.000"), 
            new Button("Rp100.000"), new Button("Confirm"))
        );
        
        int j = 0,k=10;
        for(int i=0 ; i < buttons.size() ; i++)
        {
            buttons.get(i).setMinWidth(90);
            buttons.get(i).setStyle("-fx-background-color: #02547D; -fx-text-fill: white");
            if(j==3)
            {
                k++;
                j=0;
            }
            pane.add(buttons.get(i), j, k);
            j++;
        }
        
        buttons.get(0).setOnAction(new PembayaranEventHandler(500,totalBayarLabel));
        buttons.get(1).setOnAction(new PembayaranEventHandler(1000,totalBayarLabel));
        buttons.get(2).setOnAction(new PembayaranEventHandler(2000,totalBayarLabel));
        buttons.get(3).setOnAction(new PembayaranEventHandler(5000,totalBayarLabel));
        buttons.get(4).setOnAction(new PembayaranEventHandler(10000,totalBayarLabel));
        buttons.get(5).setOnAction(new PembayaranEventHandler(20000,totalBayarLabel));
        buttons.get(6).setOnAction(new PembayaranEventHandler(50000,totalBayarLabel));
        buttons.get(7).setOnAction(new PembayaranEventHandler(100000,totalBayarLabel));
        buttons.get(8).setOnAction(this::confirmNonimalPembayaran);
        
        pane.add(new Label("Kembalian"), 0,k+1);
        kembalianLabel.setFont(new Font("Cambria", 20));
        pane.add(kembalianLabel, 0,k+2);
        
        Button buttonPembelianBaru = new Button("Beli Baru");
        buttonPembelianBaru.setStyle("-fx-background-color: #16B1E7; -fx-text-fill: white");
        pane.add(buttonPembelianBaru,1, k+3);
        buttonPembelianBaru.setOnAction(this::pembelianBaru);
    }
    private void confirmNonimalPembayaran(ActionEvent e)
    {
        String text = totalBayarLabel.getText();
        String textout = text.substring(2,text.length());
        
        totalBayar = Integer.parseInt(textout);
        if ( (kembalian = hitungHarga.hitungKembalian(totalBayar)) < 0)
        {
            //show error
            showError();
            return;
        }
        kembalianLabel.setText("Rp" + kembalian);
    }
    private void pembelianBaru(ActionEvent e)
    {
        //kosongin list yang di beli
        TableCreator.resetPembelian();
        table.setItems(TableCreator.listBarangDibeli);
        //reset pembayaran, kembalian, total bayar, idpembelian
        totalBayar = 0;
        kembalian = 0;
        idPembelian = randomizer.nextInt(1000000);
        resetText();
        
        kembalianLabel.setText("Rp" + kembalian);
        totalBayarLabel.setText("Rp0");
        
        hitungHarga.addItem(hitungHarga.getGrandTotal() * -1);
        updateGrandTotal();
    }
    private void showError()
    {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        
        Button ok = new Button("OK");
        ok.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent e)
            {
                stage.close();
            }
        });
        vbox.getChildren().addAll(new Label("Nominal yang anda masukkan kurang"), ok);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 300,100);
        stage.setScene(scene);
        stage.show();
    }
}
