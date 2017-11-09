package org.natha.lpmp.pegawai;

import java.util.Date;

/**
 * Created by myssd on 11/5/17.
 */
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Pegawai {
    @Element
    private String nip;     //nip
    @Element
    private String name;    //nama
    @Element
    private String tempatLahir; //tempat_lahir
    @Element
    private Date tglLahir;   //tgl_lahir
    @Element
    private String pangkat;     //pangkat
    @Element
    private String golonganRuang;  //golongan_ruang
    @Element
    private String jabatan;         //jabatan
    @Element
    private Date pangkatTmt;     //pangkat_tmt
    @Element
    private int thnMkg;         //thn_mkg
    @Element
    private int blnMkg;         //bln_mkg
    @Element
    private int thnGolGaji;    //thn_gol_gaji
    @Element
    private int thnSeluruh;     //thn_seluruh
    @Element
    private int blnSeluruh;     //bln_seluruh
    @Element
    private Date tmtPangkatPertama; //tmt_pangkat_pertama
    @Element
    private String golPangkatPertama; //gol_pangkat_pertama
    @Element
    private int thnPangkatPertama; //thn_pangkat_pertama
    @Element
    private int blnPangkatPertama; //bln_pangkat_pertama
    @Element
    private int thnPensiun;         //thn_pensiun
    @Element
    private String jenjangPendidikan;  //jenjang_pendidikan
    @Element
    private String jurusanPendidikan;  //jurusan_pendidikan
    @Element
    private int blnGolGaji;        //bln_gol_gaji

    public Pegawai() {
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }

    public String getGolonganRuang() {
        return golonganRuang;
    }

    public void setGolonganRuang(String golonganRuang) {
        this.golonganRuang = golonganRuang;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public Date getPangkatTmt() {
        return pangkatTmt;
    }

    public void setPangkatTmt(Date pangkatTmt) {
        this.pangkatTmt = pangkatTmt;
    }

    public int getThnMkg() {
        return thnMkg;
    }

    public void setThnMkg(int thnMkg) {
        this.thnMkg = thnMkg;
    }

    public int getBlnMkg() {
        return blnMkg;
    }

    public void setBlnMkg(int blnMkg) {
        this.blnMkg = blnMkg;
    }

    public int getThnGolGaji() {
        return thnGolGaji;
    }

    public void setThnGolGaji(int thnGolGaji) {
        this.thnGolGaji = thnGolGaji;
    }

    public int getThnSeluruh() {
        return thnSeluruh;
    }

    public void setThnSeluruh(int thnSeluruh) {
        this.thnSeluruh = thnSeluruh;
    }

    public int getBlnSeluruh() {
        return blnSeluruh;
    }

    public void setBlnSeluruh(int blnSeluruh) {
        this.blnSeluruh = blnSeluruh;
    }

    public Date getTmtPangkatPertama() {
        return tmtPangkatPertama;
    }

    public void setTmtPangkatPertama(Date tmtPangkatPertama) {
        this.tmtPangkatPertama = tmtPangkatPertama;
    }

    public String getGolPangkatPertama() {
        return golPangkatPertama;
    }

    public void setGolPangkatPertama(String golPangkatPertama) {
        this.golPangkatPertama = golPangkatPertama;
    }

    public int getThnPangkatPertama() {
        return thnPangkatPertama;
    }

    public void setThnPangkatPertama(int thnPangkatPertama) {
        this.thnPangkatPertama = thnPangkatPertama;
    }

    public int getBlnPangkatPertama() {
        return blnPangkatPertama;
    }

    public void setBlnPangkatPertama(int blnPangkatPertama) {
        this.blnPangkatPertama = blnPangkatPertama;
    }

    public int getThnPensiun() {
        return thnPensiun;
    }

    public void setThnPensiun(int thnPensiun) {
        this.thnPensiun = thnPensiun;
    }

    public String getJenjangPendidikan() {
        return jenjangPendidikan;
    }

    public void setJenjangPendidikan(String jenjangPendidikan) {
        this.jenjangPendidikan = jenjangPendidikan;
    }

    public String getJurusanPendidikan() {
        return jurusanPendidikan;
    }

    public void setJurusanPendidikan(String jurusanPendidikan) {
        this.jurusanPendidikan = jurusanPendidikan;
    }

    public int getBlnGolGaji() {
        return blnGolGaji;
    }

    public void setBlnGolGaji(int blnGolGaji) {
        this.blnGolGaji = blnGolGaji;
    }
}
