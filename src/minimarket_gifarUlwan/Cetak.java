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
import java.io.*;

public class Cetak
{
    private int GrandTotal, kembalian,cashBayar;
    private File notaFile = new File("Nota.txt");
    private TableView table;
    
    private Label labelGrandTotal, labelReceipt;
    private TableViewCreator TableCreator;
    private BorderPane bpane;
    
    public Cetak(TableViewCreator tableCreator, int GrandTotal, int kembalian, int cashBayar)
    {
        this.TableCreator = tableCreator;
        this.GrandTotal = GrandTotal;
        this.kembalian = kembalian;
        this.cashBayar = cashBayar;
    }
    public void showCetak()
    {
       Stage stage = new Stage();
       
       stage.setTitle("Receipt");
       
       bpane = new BorderPane();
       GridPane pane = new GridPane();
       pane.setVgap(10);
       pane.setHgap(15);
       pane.setPadding(new Insets(10, 10, 10, 10));
       
       //pane.add(new Label("Pembelian"),0,0);
       table = TableCreator.createTable();
       pane.add(table,0,0);
       
       createComponentBawah(pane);
       
       HBox hbox = createBoxAtas();
                                    
       bpane.setCenter(pane);
       bpane.setTop(hbox);
       Scene scene = new Scene(bpane, 450, 700);
       stage.setScene(scene);
       stage.show();
    }
    
    public void cetakFileNota()
    {
        try
        {
            FileWriter Fwriter = new FileWriter(notaFile);
            BufferedWriter BufWriter = new BufferedWriter(Fwriter);
            
            
            
            BufWriter.close();
            Fwriter.close();
        }
        catch(IOException exc)
        {
            exc.printStackTrace();
        }
    }
    public void createComponentBawah(GridPane pane)
    {
       labelGrandTotal = new Label("Total Pembelian: Rp" + GrandTotal);
       labelGrandTotal.setFont(new Font("Cambria", 15));
       Label labelKembalian = new Label("Kembalian: Rp" + kembalian);
       labelKembalian.setFont(new Font("Cambria", 15));
       Label labelBayar = new Label("Cash: Rp" + cashBayar);
       labelBayar.setFont(new Font("Cambiria", 15));
       pane.add(labelGrandTotal,0,1);
       pane.add(labelBayar,0,2);
       pane.add(labelKembalian,0,3);
       pane.setHalignment(labelGrandTotal, HPos.RIGHT);
       pane.setHalignment(labelBayar, HPos.RIGHT);
       pane.setHalignment(labelKembalian, HPos.RIGHT);
       
       Button printButton = new Button("Print");
       printButton.setOnAction(this::printReceipt);
       pane.add(printButton,0,4);
       pane.setHalignment(printButton, HPos.CENTER);
    }
    public HBox createBoxAtas()
    {
       HBox hbox = new HBox(8);
       hbox.setPadding(new Insets(10,10,10,10));
       
       labelReceipt = new Label("RECEIPT");
       labelReceipt.setFont(new Font("Cambria", 30));
       hbox.getChildren().addAll(labelReceipt);
       return hbox;
    }
    private void printReceipt(ActionEvent e)
    {
        PrintReceipt printer = new PrintReceipt();
        printer.printBuktiTransaksi(bpane);
    }
}
