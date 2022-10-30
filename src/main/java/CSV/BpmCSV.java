package CSV;

import com.opencsv.bean.CsvBindByPosition;
import java.util.Date;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class BpmCSV {
    @CsvBindByPosition(position = 0)
    private String Id;

    @CsvBindByPosition(position = 1)
    private String Pcap;

    @CsvBindByPosition(position = 2)
    private Integer Bpm;

    private java.util.Date Timestamp;
    
    private String Way;
    
    private String WayNicename;
    
    private Integer Code;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getPcap() {
        return Pcap;
    }

    public void setPcap(String Pcap) {
        this.Pcap = Pcap;
    }

    public Integer getBpm() {
        return Bpm;
    }

    public void setBpm(Integer Bpm) {
        this.Bpm = Bpm;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date Timestamp) {
        this.Timestamp = Timestamp;
    }

    public String getWay() {
        return Way;
    }

    public void setWay(String Way) {
        this.Way = Way;
    }

    public String getWayNicename() {
        return WayNicename;
    }

    public void setWayNicename(String WayNicename) {
        this.WayNicename = WayNicename;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer Code) {
        this.Code = Code;
    }

    @Override
    public String toString() {
        return "BpmCSV{" + "Id=" + Id + ", Pcap=" + Pcap + ", Bpm=" + Bpm + ", Timestamp=" + Timestamp + ", Way=" + Way + ", WayNicename=" + WayNicename + ", Code=" + Code + '}';
    }
    
}