package Test;

import CSV.BpmCSV;
import CSV.RespiratoryRateCSV;
import CSV.DataCSV;
import CSV.ParticipantCSV;
import Util.CsvUtil;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class Csv {
    public static void main(String[] args) {
        String path = Paths.get("src\\main\\webapp\\CSV\\")
                .toAbsolutePath()
                .toString();
        List<BpmCSV> bpmList = CsvUtil
                .getBpmFromCsv(path);
        List<RespiratoryRateCSV> brList = CsvUtil
                .getRespiratoryRateFromCsv(path);
        List<ParticipantCSV> pList = CsvUtil
                .getParticipantsFromCsv(path);
        List<DataCSV> dList = CsvUtil
                .getData(path);
        for (BpmCSV b : bpmList)
            System.out.println(b);
        for (RespiratoryRateCSV vs : brList)
            System.out.println(vs);
        for (ParticipantCSV p : pList)
            System.out.println(p);
        for (DataCSV d : dList)
            System.out.println(d);
        CsvUtil.clear();
    }
}