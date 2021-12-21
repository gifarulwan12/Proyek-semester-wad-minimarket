package minimarket_gifarUlwan;

public class HitungHarga
{
    private int GrandTotal = 0;
    public int getGrandTotal()
    {
        return GrandTotal;
    }
    public void setGrandTotal(int a)
    {
        GrandTotal = a;
    }
    public void addItem(int harga)
    {
        GrandTotal += harga;
    }
    
    public int hitungKembalian(int totalBayar)
    {
        return totalBayar - GrandTotal;
    }
}
