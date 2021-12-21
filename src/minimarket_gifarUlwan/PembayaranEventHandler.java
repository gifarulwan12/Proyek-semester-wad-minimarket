package minimarket_gifarUlwan;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class PembayaranEventHandler implements EventHandler<ActionEvent>
{
    private int bayar, total;
    private Label totalLabel;
    public PembayaranEventHandler(int bayar, Label totalLabel)
    {
        this.bayar = bayar;
        this.totalLabel = totalLabel;
    }
    
    @Override
    public void handle(ActionEvent event) 
    {
        String text = totalLabel.getText();
        String textout = text.substring(2,text.length());
        
        total = Integer.parseInt(textout) + bayar;
        totalLabel.setText("Rp" + total);
    }
}
