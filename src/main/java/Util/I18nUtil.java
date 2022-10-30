package Util;

import CSV.I18nCSV;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class I18nUtil {
    private static List<I18nCSV> I18n = null;

    private static final Integer TO_LOWER_CASE = 1;
    private static final Integer TO_UPPER_CASE = 2;
    
    public static List<I18nCSV> getI18n(String path) {
        if (I18n != null) 
            return I18n;
       
        try {
            I18n = new CsvToBeanBuilder(new FileReader(path + "i18n.csv"))
                .withSkipLines(1)
                .withType(I18nCSV.class)
                .build()
                .parse();
        } catch (IOException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }
        
        return I18n;
    }
    
    public static Boolean updateI18n(String path) {
        if (getI18n(path) != null)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }
    
    public static String i18n(String id, Integer language) {
        String exp = String.copyValueOf(id.toCharArray());

        if (I18n == null) 
            return I18nCSV.UNDEFINED;
        
        for (int i = 0; i < I18n.size(); i++) {
            if (Objects.equals(I18n.get(i).getId(), id)) {
                if (Objects.equals(language, I18nCSV.EN_US)) {
                    exp = I18n.get(i).getEn_US();
                    break;
                } else if (Objects.equals(language, I18nCSV.PT_BR)) {
                    exp = I18n.get(i).getPt_BR();
                    break;
                } else {
                    exp = I18nCSV.UNDEFINED;
                    break;
                }
            }
        }
        
        return exp;
    }
    
    public static String i18n(String id, Integer language, Integer flag) {
        String exp = i18n(id, language);
        
        if (Objects.equals(flag, TO_LOWER_CASE))
            return exp.toLowerCase();
        else if (Objects.equals(flag, TO_UPPER_CASE))
            return exp.toUpperCase();
        else
            return exp;
    }
}