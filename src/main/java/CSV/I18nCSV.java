package CSV;

import com.opencsv.bean.CsvBindByPosition;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class I18nCSV {
    public static final Integer PT_BR = 1;
    public static final Integer EN_US = 2;

    public static final String UNDEFINED = "UNDEFINED SYMBOL";

    @CsvBindByPosition(position = 0)
    private String Id;

    @CsvBindByPosition(position = 1)
    private String pt_BR;

    @CsvBindByPosition(position = 2)
    private String en_US;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getPt_BR() {
        return pt_BR;
    }

    public void setPt_BR(String pt_BR) {
        this.pt_BR = pt_BR;
    }

    public String getEn_US() {
        return en_US;
    }

    public void setEn_US(String en_US) {
        this.en_US = en_US;
    }
}