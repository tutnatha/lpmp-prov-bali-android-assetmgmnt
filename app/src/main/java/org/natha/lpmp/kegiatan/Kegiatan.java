package org.natha.lpmp.kegiatan;

/**
 * Created by myssd on 11/6/17.
 */
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Kegiatan {
    @Element
    private int kode;
    @Element
    private String namaKegiatan;
    @Element
    private String keterangan;
    @Element
    private String nama;

    public Kegiatan() {
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
