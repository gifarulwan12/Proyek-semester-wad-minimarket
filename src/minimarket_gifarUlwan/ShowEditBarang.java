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
import java.util.*;

public class ShowEditBarang
{
    private Stage stage = new Stage();
    private Stage sg = new Stage();
    private Button buttonTambah = new Button("Tambah Barang");
    private Button buttonHapus = new Button("Hapus Barang");
    private Button buttonEdit = new Button("Edit Barang");
    private Button buttonDone;
    private Scene sceneUtama;
    private TextField textKode = new TextField();
    private TextField textNama = new TextField();
    private TextField textHarga = new TextField();
    private TextField textDeskripsi = new TextField();
    private TextField textKodeEdit = new TextField();
    private TulisBarang tulisBarang;
    private ComboBox cb;
    ObservableList<String> option;
    
    public ShowEditBarang (TulisBarang tulisBarang, ComboBox comboBoxBarang)
    {
        cb = comboBoxBarang;
        this.tulisBarang = tulisBarang;
    }
    
    public void showScene()
    {
        BorderPane bpane = new BorderPane();
        
        VBox vbox = new VBox(8); 
        vbox.setPrefWidth(10);
        vbox.setAlignment(Pos.CENTER);
        Label labelPilihan = new Label("Pilih Fungsi Mengedit Barang");
        
        vbox.getChildren().add(labelPilihan);
        
        vbox.getChildren().add(buttonTambah);
        vbox.getChildren().add(buttonHapus);
        vbox.getChildren().add(buttonEdit);
        
        buttonTambah.setOnAction(this::clickButtonTambah);
        buttonHapus.setOnAction(this::clickButtonHapus);
        buttonEdit.setOnAction(this::clickButtonEdit);
        
        bpane.setCenter(vbox);
        
        Scene scene = new Scene(bpane, 300,300);
        sceneUtama = scene;
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void clickButtonTambah(ActionEvent e)
    {
        GridPane gPane = new GridPane();
        createGridPane(gPane);
        
        gPane.add(new Label("Kode Barang"),0,0);
        gPane.add(new Label("Nama Barang"), 0,1);
        gPane.add(new Label("Harga Barang"), 0,2);
        gPane.add(new Label("Deskripsi Barang"), 0,3);
        
        gPane.add(textKode, 1,0);
        gPane.add(textNama, 1,1);
        gPane.add(textHarga, 1,2);
        gPane.add(textDeskripsi, 1,3);
        
        buttonDone = new Button("Tambahkan");
        gPane.add(buttonDone, 1,4);
        buttonDone.setOnAction(new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                tulisBarang.TulisTextFile(textKode.getText(), textNama.getText(), 
                                            textHarga.getText(), textDeskripsi.getText());
                clickButtonDone();
            } 
        });
        
        Scene scene = new Scene(gPane, 300,300);
        stage.setScene(scene);
        stage.show();
    }
    public void clickButtonHapus(ActionEvent e)
    {
        GridPane gPane = new GridPane();
        createGridPane(gPane);
        
        gPane.add(new Label("Kode Barang"),0,0);
        gPane.add(textKode, 1,0);
        
        buttonDone = new Button("Hapus");
        gPane.add(buttonDone, 1,2);
        buttonDone.setOnAction(new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                tulisBarang.hapusBarang(textKode.getText());
                clickButtonDone();
            } 
        });
        
        Scene scene = new Scene(gPane, 300,300);
        stage.setScene(scene);
        stage.show();
    }
    public void clickButtonEdit(ActionEvent e)
    {
        GridPane gPane = new GridPane();
        createGridPane(gPane);
        
        gPane.add(new Label("Kode Barang yang ingin di edit"),0,0);
        gPane.add(new Label("Kode Barang setelah di edit"),0,1);
        gPane.add(new Label("Nama Barang setelah di edit"), 0,2);
        gPane.add(new Label("Harga Barang setelah di edit"), 0,3);
        gPane.add(new Label("Deskripsi Barang setelah di edit"), 0,4);
        
        gPane.add(textKodeEdit, 1,0);
        gPane.add(textKode, 1,1);
        gPane.add(textNama, 1,2);
        gPane.add(textHarga, 1,3);
        gPane.add(textDeskripsi, 1,4);
        
        buttonDone = new Button("Edit");
        gPane.add(buttonDone, 1,5);
        buttonDone.setOnAction(new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                tulisBarang.editBarang(textKodeEdit.getText(), textKode.getText(), textNama.getText(), 
                                            textHarga.getText(), textDeskripsi.getText());
                clickButtonDone();
            } 
        });
        
        Scene scene = new Scene(gPane, 400,300);
        stage.setScene(scene);
        stage.show();
    }
    
    private void createGridPane(GridPane gPane)
    {
        gPane.setPadding(new Insets(10, 10, 10, 10));
        gPane.setMinSize(300, 300);
        gPane.setVgap(10);
        gPane.setHgap(10);
    }
    private void clickButtonDone()
    {
        BorderPane bpane = new BorderPane();
        VBox vb = new VBox(8);
        
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().add(new Label("Berhasil memodifikasi barang!"));
        
        HBox hb = new HBox(15);
        hb.setAlignment(Pos.CENTER);
        
        Button okButton = new Button("Ok");
        hb.getChildren().addAll(okButton);
        
        okButton.setOnAction(this::clickOK);
        
        bpane.setTop(vb);
        bpane.setCenter(hb);
        
        Scene sc = new Scene(bpane, 300,75);
        sg.setScene(sc);
        sg.show();
        
        //update combobox & kosongin text field
        UpdateBarang();
        resetTextField();
    }
    private void clickOK(ActionEvent e)
    {
        sg.close();
    }
    private void UpdateBarang()
    {
        tulisBarang.BacaTextFile();
        option = FXCollections.observableArrayList(tulisBarang.kodeBarang);
        cb.setItems(option);
    }
    private void resetTextField()
    {
        textKode.setText("");
        textNama.setText("");
        textHarga.setText("");
        textDeskripsi.setText("");
        textKodeEdit.setText("");
    }
}
