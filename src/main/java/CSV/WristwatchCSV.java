package CSV;

import com.opencsv.bean.CsvBindByPosition;
import java.util.Date;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class WristwatchCSV {
    @CsvBindByPosition(position = 0)
    private String Id;

    @CsvBindByPosition(position = 1)
    private String File;

    @CsvBindByPosition(position = 2)
    private String Timestamp;

    @CsvBindByPosition(position = 3)
    private Double Bpm;
    
    private String Way;
    
    private String WayNicename;
    
    private Integer Code;

    private java.util.Date TimestampJD;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getFile() {
        return File;
    }

    public void setFile(String File) {
        this.File = File;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String Timestamp) {
        this.Timestamp = Timestamp;
    }

    public Double getBpm() {
        return Bpm;
    }

    public void setBpm(Double Bpm) {
        this.Bpm = Bpm;
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

    public Date getTimestampJD() {
        return TimestampJD;
    }

    public void setTimestampJD(Date TimestampJD) {
        this.TimestampJD = TimestampJD;
    }
}