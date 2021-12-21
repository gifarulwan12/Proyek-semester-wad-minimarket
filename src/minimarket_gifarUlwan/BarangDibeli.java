package minimarket_gifarUlwan;

public class BarangDibeli
{
    private String namaBarang;
    private int hargaBarang, jumlahBarang, hargaSatuan,IDPembelian;
    
    public BarangDibeli(String newNama, int newHarga, int newJumlah, int hargaSatuan, int idPembelian)
    {
        namaBarang = newNama;
        hargaBarang = newHarga;
        jumlahBarang = newJumlah;
        this.hargaSatuan = hargaSatuan;
        this.IDPembelian = idPembelian;
    }
    
    public String getNamaBarang()
    {
        return namaBarang;
    }
    public void setNamaBarang(String namaBarang)
    {
        this.namaBarang = namaBarang;
    }
    public int getHargaBarang()
    {
        return hargaBarang;
    }
    public void setHargaBarang(int hargaBarang)
    {
        this.hargaBarang = hargaBarang;
    }
    public int getJumlahBarang()
    {
        return jumlahBarang;
    }
    public void setJumlahBarang(int jumlahBarang)
    {
        this.jumlahBarang = jumlahBarang;
    }
    public int getHargaSatuan()
    {
        return hargaSatuan;
    }
    public void setHargaSatuan(int hargaSatuan)
    {
        this.hargaSatuan = hargaSatuan;
    }
    public int getIDPembelian()
    {
        return IDPembelian;
    }
    public void setIDPembelian(int IDPembelian)
    {
        this.IDPembelian = IDPembelian;
    }
}
