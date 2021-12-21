package minimarket_gifarUlwan;

import javafx.print.PrinterJob;
import javafx.scene.Node;

public class PrintReceipt
{
    public void printBuktiTransaksi(Node node)
    {
        PrinterJob printJob = PrinterJob.createPrinterJob();
        if(printJob == null)
        {
            return;
        }
        else
        {
            boolean printStatus = printJob.printPage(node);
            if (printStatus == true) printJob.endJob();
        }
    }
    
}
