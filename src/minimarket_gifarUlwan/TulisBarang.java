package minimarket_gifarUlwan;

import java.io.*;
import java.util.*;

public class TulisBarang
{
    private File tempTextFile = new File("temp.txt");
    private File BarangJual = new File("BarangJual.txt");
    public List<String> namaBarang = new ArrayList<String>(), harga = new ArrayList<String>(), desc = new ArrayList<String>(), kodeBarang= new ArrayList<String>();
    public TulisBarang()
    {
        //TulisTextFile("PS001", "Pensil", "1000", "Pensil 2b cocok buat ujian");
        BacaTextFile();
        //editBarang("PL001", "PS001", "Pensil", "1000", "Pensil 2b cocok buat ujian");
        //hapusBarang("PL001");
        //BacaTextFile();
    }
    public void TulisTextFile(String KodeBarang, String NamaBarang, 
                                String HargaBarang, String DeskripsiBarang)
    {
        try
        {
            FileWriter Fwriter = new FileWriter(BarangJual,true);
            BufferedWriter BufWriter = new BufferedWriter(Fwriter);
            BufWriter.write(KodeBarang);
            BufWriter.newLine();
            BufWriter.write(NamaBarang);
            BufWriter.newLine();
            BufWriter.write(HargaBarang);
            BufWriter.newLine();
            BufWriter.write(DeskripsiBarang);
            BufWriter.newLine();
            BufWriter.newLine();
            
            BufWriter.close();
            Fwriter.close();
        }
        catch(IOException exc)
        {
            exc.printStackTrace();
        }
    }
    public void BacaTextFile()
    {
        try
        {
            FileReader Freader = new FileReader(BarangJual);
            BufferedReader BufReader = new BufferedReader(Freader);
            
            namaBarang = new ArrayList<String>();
            harga = new ArrayList<String>();
            desc = new ArrayList<String>();
            kodeBarang = new ArrayList<String>();
            
            String read = BufReader.readLine();
            while(read != null)
            {
                System.out.println(read);
                
                kodeBarang.add(read);
                read = BufReader.readLine();
                namaBarang.add(read);
                read = BufReader.readLine();
                harga.add(read);
                read = BufReader.readLine();
                desc.add(read);
                
                read = BufReader.readLine();
                read = BufReader.readLine();
            }
            Freader.close();
            BufReader.close();
        }
        catch(IOException exc)
        {
            exc.printStackTrace();
            TulisTextFile("PS001", "Pensil", "1000", "Pensil 2b cocok buat ujian");
            BacaTextFile();
        }
        
    }
    public void hapusBarang(String barangDihapus)
    {
        try
        {
            //FileWriter FwriterTemp = new FileWriter(tempTextFile); // tulis ke temp file
            //FileReader FreaderMain = new FileReader(BarangJual); //baca file barangjual
            
            BufferedWriter BufWriterTemp = new BufferedWriter(new FileWriter(tempTextFile));
            BufferedReader BufReaderMain = new BufferedReader(new FileReader(BarangJual));
            
            String word;
            //Pindah text ke temp file
            while((word = BufReaderMain.readLine()) != null)
            {
                if(barangDihapus.equals(word.trim()))
                {
                    for(int i=0; i< 4; i++)
                    {
                        word = BufReaderMain.readLine();
                    }
                    //skip text
                    continue;
                }
                BufWriterTemp.write(word); //kode
                BufWriterTemp.newLine();
                word = BufReaderMain.readLine();
                BufWriterTemp.write(word); //nama
                BufWriterTemp.newLine();
                word = BufReaderMain.readLine();
                BufWriterTemp.write(word); //harga
                BufWriterTemp.newLine();
                word = BufReaderMain.readLine();
                BufWriterTemp.write(word); //desc
                BufWriterTemp.newLine();
                BufWriterTemp.newLine();
                word = BufReaderMain.readLine(); //baca newline
            }
            //tutup writer & reader
            BufWriterTemp.close();
            BufReaderMain.close();
            
            //delete file asli, rename file temp jadi file asli
            BarangJual.delete();
            tempTextFile.renameTo(BarangJual);
            
        }
        catch(IOException exc)
        {
            exc.printStackTrace();
        }
    }
    public void editBarang(String barangDiedit, String gantiBarang, String namaBarangGanti,
                            String hargaGanti, String descGanti)
    {
        try
        {
            //FileWriter FwriterTemp = new FileWriter(tempTextFile); // tulis ke temp file
            //FileReader FreaderMain = new FileReader(BarangJual); //baca file barangjual
            
            BufferedWriter BufWriterTemp = new BufferedWriter(new FileWriter(tempTextFile));
            BufferedReader BufReaderMain = new BufferedReader(new FileReader(BarangJual));
            
            String word;
            //Pindah text ke temp file
            while((word = BufReaderMain.readLine()) != null)
            {
                if(barangDiedit.equals(word.trim()))
                {
                    //edit text
                    BufWriterTemp.write(gantiBarang);
                    BufWriterTemp.newLine();
                    BufWriterTemp.write(namaBarangGanti);
                    BufWriterTemp.newLine();
                    BufWriterTemp.write(hargaGanti);
                    BufWriterTemp.newLine();
                    BufWriterTemp.write(descGanti);
                    BufWriterTemp.newLine();
                    BufWriterTemp.newLine();
                    for(int i=0; i< 4; i++)
                    {
                        word = BufReaderMain.readLine();
                    }
                        
                    continue;
                }
                BufWriterTemp.write(word); //kode
                BufWriterTemp.newLine();
                word = BufReaderMain.readLine();
                BufWriterTemp.write(word); //nama
                BufWriterTemp.newLine();
                word = BufReaderMain.readLine();
                BufWriterTemp.write(word); //harga
                BufWriterTemp.newLine();
                word = BufReaderMain.readLine();
                BufWriterTemp.write(word); //desc
                BufWriterTemp.newLine();
                BufWriterTemp.newLine();
                word = BufReaderMain.readLine(); //baca newline
                
            }
            //tutup writer & reader
            BufWriterTemp.close();
            BufReaderMain.close();
            
            //delete file asli, rename file temp jadi file asli
            if(!BarangJual.delete()) System.out.println("fail to delete");
            tempTextFile.renameTo(BarangJual);
            
        }
        catch(IOException exc)
        {
            exc.printStackTrace();
        }
    }
}
