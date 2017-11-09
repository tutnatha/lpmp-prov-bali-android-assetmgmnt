package org.natha.lpmp.kamar;

/**
 * Created by myssd on 10/28/17.
 */
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Kamar {
    @Element
    private String no;
    @Element
    private int lantai;
    @Element
    private int jmlTt;
    @Element
    private String urlPicture;

    public Kamar() {

    }

    public Kamar(String no, int lantai, int jmlTt, String urlPicture) {
        super();
        this.no = no;
        this.lantai = lantai;
        this.jmlTt = jmlTt;
        this.urlPicture = urlPicture;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getLantai() {
        return lantai;
    }

    public void setLantai(int lantai) {
        this.lantai = lantai;
    }

    public int getJmlTt() {
        return jmlTt;
    }

    public void setJmlTt(int jmlTt) {
        this.jmlTt = jmlTt;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

}
