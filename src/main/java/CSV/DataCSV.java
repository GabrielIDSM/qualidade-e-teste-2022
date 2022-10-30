package CSV;

import java.util.List;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class DataCSV {
    private String Id;
    
    private ParticipantCSV Participant;
    
    private List<BpmCSV> Bpm;
    
    private List<RespiratoryRateCSV> RespiratoryRate;
    
    private List<WristwatchCSV> Wristwatch;
    
    private List<WristwatchCSV> WristwatchBpm;
    
    private String Link;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public ParticipantCSV getParticipant() {
        return Participant;
    }

    public void setParticipant(ParticipantCSV Participant) {
        this.Participant = Participant;
    }

    public List<BpmCSV> getBpm() {
        return Bpm;
    }

    public void setBpm(List<BpmCSV> Bpm) {
        this.Bpm = Bpm;
    }

    public List<RespiratoryRateCSV> getRespiratoryRate() {
        return RespiratoryRate;
    }

    public void setRespiratoryRate(List<RespiratoryRateCSV> RespiratoryRate) {
        this.RespiratoryRate = RespiratoryRate;
    }

    public List<WristwatchCSV> getWristwatch() {
        return Wristwatch;
    }

    public void setWristwatch(List<WristwatchCSV> Wristwatch) {
        this.Wristwatch = Wristwatch;
    }

    public List<WristwatchCSV> getWristwatchBpm() {
        return WristwatchBpm;
    }

    public void setWristwatchBpm(List<WristwatchCSV> WristwatchBpm) {
        this.WristwatchBpm = WristwatchBpm;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }

    @Override
    public String toString() {
        return "DataCSV{" + "Id=" + Id + ", Participant=" + 
            Participant + ", Bpm=" + Bpm + ", RespiratoryRate=" + 
            RespiratoryRate + '}';
    }
    
}