package CSV;

import Util.AgeUtil;
import Util.I18nUtil;
import com.opencsv.bean.CsvBindByPosition;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class ParticipantCSV {
    @CsvBindByPosition(position = 0)
    private String Timestamp;

    @CsvBindByPosition(position = 1)
    private String Id;
    
    @CsvBindByPosition(position = 2)
    private String Link;
    
    @CsvBindByPosition(position = 3)
    private String BirthDate;

    @CsvBindByPosition(position = 4)
    private String Height;

    @CsvBindByPosition(position = 5)
    private String Weight;
    
    @CsvBindByPosition(position = 6)
    private String AssignedSex;
    
    /*
     * Foi diagnosticado com alguma doença cardiovascular como pressão alta, 
     * angina, infarto, arritmia ou outra?
     */
    @CsvBindByPosition(position = 7)
    private String Characteristic1;
    
    /*
     * Caso tenha marcado “Sim” na pergunta anterior, especifique qual ou 
     * quais doenças. (Em caso de mais de uma doença, pode utilizar a vírgula 
     * como separador)
     */
    @CsvBindByPosition(position = 8)
    private String Characteristic2;
    
    /*
     * Tem histórico de doenças cardiovasculares na família?
     */
    @CsvBindByPosition(position = 9)
    private String Characteristic3;
    
    /*
     * Foi diagnosticado com alguma doença pulmonar como asma, bronquite, 
     * enfisema, atelectasia ou outra?
     */
    @CsvBindByPosition(position = 10)
    private String Characteristic4;
    
    /*
     * É diabético?
     */
    @CsvBindByPosition(position = 11)
    private String Characteristic5;
    
    /*
     * É fumante?
     */
    @CsvBindByPosition(position = 12)
    private String Characteristic6;
    
    /*
     * Já foi fumante?
     */
    @CsvBindByPosition(position = 13)
    private String Characteristic7;
    
    /*
     * Possui alguma prótese ou órtese (placa, parafuso, stent, válvula, 
     * marca-passo, prótese mamária ou outra)?
     */
    @CsvBindByPosition(position = 14)
    private String Characteristic8;
    
    /*
     * Confirma que os dados inseridos estão corretos?
     */
    @CsvBindByPosition(position = 15)
    private String Characteristic9;
    
    /*
     * Caso tenha marcado “Sim” na pergunta anterior, especifique qual ou 
     * quais doenças. (Em caso de mais de uma doença, pode utilizar a vírgula 
     * como separador)
     */
    @CsvBindByPosition(position = 16)
    private String Characteristic10;
    
    /*
     * Caso tenha marcado “Sim” na pergunta anterior responda: há quanto tempo?
     */
    @CsvBindByPosition(position = 17)
    private String Characteristic11;
    
    /*
     * Caso tenha marcado “Sim” na pergunta anterior responda: por quanto tempo?
     */
    @CsvBindByPosition(position = 18)
    private String Characteristic12;

    private java.util.Date TimestampJD;
    
    private java.util.Date BirthDateJD;
    
    private Double Bmi;

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String Timestamp) {
        this.Timestamp = Timestamp;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String BirthDate) {
        this.BirthDate = BirthDate;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String Height) {
        this.Height = Height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    public String getAssignedSex() {
        return AssignedSex;
    }

    public void setAssignedSex(String AssignedSex) {
        this.AssignedSex = AssignedSex;
    }

    public String getCharacteristic1() {
        return Characteristic1;
    }

    public void setCharacteristic1(String Characteristic1) {
        this.Characteristic1 = Characteristic1;
    }

    public String getCharacteristic2() {
        return Characteristic2;
    }

    public void setCharacteristic2(String Characteristic2) {
        this.Characteristic2 = Characteristic2;
    }

    public String getCharacteristic3() {
        return Characteristic3;
    }

    public void setCharacteristic3(String Characteristic3) {
        this.Characteristic3 = Characteristic3;
    }

    public String getCharacteristic4() {
        return Characteristic4;
    }

    public void setCharacteristic4(String Characteristic4) {
        this.Characteristic4 = Characteristic4;
    }

    public String getCharacteristic5() {
        return Characteristic5;
    }

    public void setCharacteristic5(String Characteristic5) {
        this.Characteristic5 = Characteristic5;
    }

    public String getCharacteristic6() {
        return Characteristic6;
    }

    public void setCharacteristic6(String Characteristic6) {
        this.Characteristic6 = Characteristic6;
    }

    public String getCharacteristic7() {
        return Characteristic7;
    }

    public void setCharacteristic7(String Characteristic7) {
        this.Characteristic7 = Characteristic7;
    }

    public String getCharacteristic8() {
        return Characteristic8;
    }

    public void setCharacteristic8(String Characteristic8) {
        this.Characteristic8 = Characteristic8;
    }

    public String getCharacteristic9() {
        return Characteristic9;
    }

    public void setCharacteristic9(String Characteristic9) {
        this.Characteristic9 = Characteristic9;
    }

    public String getCharacteristic10() {
        return Characteristic10;
    }

    public void setCharacteristic10(String Characteristic10) {
        this.Characteristic10 = Characteristic10;
    }

    public String getCharacteristic11() {
        return Characteristic11;
    }

    public void setCharacteristic11(String Characteristic11) {
        this.Characteristic11 = Characteristic11;
    }

    public String getCharacteristic12() {
        return Characteristic12;
    }

    public void setCharacteristic12(String Characteristic12) {
        this.Characteristic12 = Characteristic12;
    }

    public java.util.Date getTimestampJD() {
        return TimestampJD;
    }

    public void setTimestampJD(java.util.Date TimestampJD) {
        this.TimestampJD = TimestampJD;
    }

    public java.util.Date getBirthDateJD() {
        return BirthDateJD;
    }

    public void setBirthDateJD(java.util.Date BirthDateJD) {
        this.BirthDateJD = BirthDateJD;
    }

    public Double getBmi() {
        return Bmi;
    }

    public void setBmi(Double Bmi) {
        this.Bmi = Bmi;
    }

    public String i18n(Integer language) {
        String assignedSex;
        Integer age = AgeUtil.calculateAge(this.getBirthDateJD());
        switch (this.getAssignedSex()) {
            case "Masculino":
                assignedSex = I18nUtil.i18n("MALE", language);
                break;
            case "Feminino":
                assignedSex = I18nUtil.i18n("FEMALE", language);
                break;
            default:
                assignedSex = I18nUtil.i18n("UNDEFINED_GENDER", language);
        }
        
        if (language.equals(I18nCSV.PT_BR)) {
            if (age > 0) {
                return assignedSex + " de " + age + " anos, " + this.Height + " cm e " + this.Weight +" kg";
            } else {
                return assignedSex + " de idade indefinida, " + this.Height + " cm e " + this.Weight +" kg";
            }
        } else if (language.equals(I18nCSV.EN_US)) {
            if (age > 0) {
                return age + " years old " + assignedSex + ", " + this.Height + " cm tall and " + this.Weight +" kg weight ";
            } else {
                return "Ageless " + assignedSex + ", " + this.Height + " cm tall and " + this.Weight +" kg weight ";
            }
        } else {
            return "";
        }
    }
    
    @Override
    public String toString() {
        return "ParticipantCSV{" + "Timestamp=" + Timestamp + ", Id=" + Id + 
                ", BirthDate=" + BirthDate + ", Height=" + Height + 
                ", Weight=" + Weight + ", AssignedSex=" + AssignedSex + 
                ", Characteristic1=" + Characteristic1 + 
                ", Characteristic2=" + Characteristic2 + 
                ", Characteristic3=" + Characteristic3 + 
                ", Characteristic4=" + Characteristic4 + 
                ", Characteristic5=" + Characteristic5 + 
                ", Characteristic6=" + Characteristic6 + 
                ", Characteristic7=" + Characteristic7 + 
                ", Characteristic8=" + Characteristic8 + 
                ", Characteristic9=" + Characteristic9 + 
                ", Characteristic10=" + Characteristic10 + 
                ", Characteristic11=" + Characteristic11 + 
                ", Characteristic12=" + Characteristic12 + '}';
    }
    
}