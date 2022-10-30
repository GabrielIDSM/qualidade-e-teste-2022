package Util;

import CSV.BpmCSV;
import CSV.RespiratoryRateCSV;
import CSV.DataCSV;
import CSV.ParticipantCSV;
import CSV.WristwatchCSV;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class CsvUtil {
    private static List<BpmCSV> bpmList = null;
    private static List<RespiratoryRateCSV> respiratoryRateList = null;
    private static List<ParticipantCSV> participantsList = null;
    private static List<WristwatchCSV> wristwatchList = null;
    private static List<DataCSV> dataList = null;
    private static final String[] codeList = {
        "", "S_F_R", "S_F_I", "SL_F_R", "S_C_R", "S_C_I", "P_F_R", "P_F_I", 
        "P_C_R", "P_C_I", "D_F_R", "D_F_I", "D_C_R", "D_C_I", "DL_F_R", "A_F_R", 
        "C_F_R", "V_F_R"
    };
    private static final String[] codeNicenameList = {
        "Sala vazia",
        "Sentado, De frente para o Raspberry, Respiração normal",
        "Sentado, De frente para o Raspberry, Respiração intervalada",
        "Sentar e levantar, De frente para o Raspberry, Respiração normal",
        "Sentado, De costas para o Raspberry, Respiração normal",
        "Sentado, De costas para o Raspberry, Respiração intervalada",
        "De pé, De frente para o Raspberry, Respiração normal",
        "De pé, De frente para o Raspberry, Respiração intervalada",
        "De pé, De costas para o Raspberry, Respiração normal",
        "De pé, De costas para o Raspberry, Respiração intervalada",
        "Deitado, De frente para o Raspberry, Respiração normal",
        "Deitado, De frente para o Raspberry, Respiração intervalada",
        "Deitado, De costas para o Raspberry, Respiração normal",
        "Deitado, De costas para o Raspberry, Respiração intervalada",
        "Deitar e levantar, De frente para o Raspberry, Respiração normal",
        "Andar, De frente para o Raspberry, Respiração normal",
        "Correr, De frente para o Raspberry, Respiração normal",
        "Varrer, De frente para o Raspberry, Respiração normal"
    };
    
    public static Boolean clear() {
        if (bpmList != null)
            bpmList = null;
        if (respiratoryRateList != null)
            respiratoryRateList = null;
        if (participantsList != null)
            participantsList = null;
        if (wristwatchList != null)
            wristwatchList = null;
        if (dataList != null)
            dataList = null;
        return Boolean.TRUE;
    }
    
    public static Boolean get(String path) {
        if (bpmList == null)
            bpmList = getBpmFromCsv(path);
        if (respiratoryRateList == null)
            respiratoryRateList = getRespiratoryRateFromCsv(path);
        if (participantsList == null)
            participantsList = getParticipantsFromCsv(path);
        if (wristwatchList == null)
            wristwatchList = getWristwatchFromCsv(path);
        return Boolean.TRUE;
    }
    
    private static String getWay(String str) {
        String way;
        Integer code;
        if (!String.valueOf(str.charAt(1)).equals("_"))
            code = Integer.parseInt(String.valueOf(str.charAt(0) + "" + str.charAt(1)));
        else
            code = Integer.parseInt(String.valueOf(str.charAt(0)));
        if (code > 17)
            return null;
        way = codeList[code];
        return way;
    }
    
    private static Integer getCode(String str) {
        Integer code;
        if (!String.valueOf(str.charAt(1)).equals("_"))
            code = Integer.parseInt(String.valueOf(str.charAt(0) + "" + str.charAt(1)));
        else
            code = Integer.parseInt(String.valueOf(str.charAt(0)));
        if (code > 17)
            return null;
        return code;
    }
    
    private static String getWayNicename(String str) {
        String nway;
        Integer code;
        if (!String.valueOf(str.charAt(1)).equals("_"))
            code = Integer.parseInt(String.valueOf(str.charAt(0) + "" + str.charAt(1)));
        else
            code = Integer.parseInt(String.valueOf(str.charAt(0)));
        if (code > 17)
            return null;
        nway = codeNicenameList[code];
        return nway;
    }
    
    public static List<BpmCSV> getBpmFromCsv(String path) {
        if (bpmList != null)
            return bpmList;
        try {
            bpmList = new CsvToBeanBuilder(new FileReader(path + "bpm.csv"))
                .withSkipLines(1)
                .withType(BpmCSV.class)
                .build()
                .parse();
            if(bpmList != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_-_HH_mm_ss");  
                Pattern pattern = Pattern.compile("\\d{4}_\\d{2}_\\d{2}_-_\\d{2}_\\d{2}_\\d{2}");
                for (BpmCSV b : bpmList) {
                    Matcher matcher = pattern.matcher(b.getPcap());
                    if (matcher.find()) {
                        b.setTimestamp(formatter.parse(matcher.group()));
                        b.setCode(getCode(b.getPcap()));
                        b.setWay(getWay(b.getPcap()));
                        b.setWayNicename(getWayNicename(b.getPcap()));
                    }
                }
            }
        } catch (IOException | IllegalStateException | ParseException e) {
            System.err.println(e.getMessage());
        }
        
        Collections.sort(bpmList, new Comparator<BpmCSV>() {
            @Override
            public int compare(BpmCSV bpm1, BpmCSV bpm2) {
                return bpm1.getCode() > bpm2.getCode() ? 1 : -1;
            }
        });
        
        return bpmList;
    }
    
    public static List<RespiratoryRateCSV> getRespiratoryRateFromCsv(String path) {
        if (respiratoryRateList != null)
            return respiratoryRateList;
        try {
            respiratoryRateList = new CsvToBeanBuilder(new FileReader(Paths
                .get(path + "respiratoryrate.csv")
                .toAbsolutePath()
                .toString()))
                .withSkipLines(1)
                .withType(RespiratoryRateCSV.class)
                .build()
                .parse();
            if(respiratoryRateList != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_-_HH_mm_ss");  
                Pattern pattern = Pattern.compile("\\d{4}_\\d{2}_\\d{2}_-_\\d{2}_\\d{2}_\\d{2}");
                for (RespiratoryRateCSV v : respiratoryRateList) {
                    Matcher matcher = pattern.matcher(v.getPcap());
                    if (matcher.find()) {
                        v.setTimestamp(formatter.parse(matcher.group()));
                        v.setCode(getCode(v.getPcap()));
                        v.setWay(getWay(v.getPcap()));
                        v.setWayNicename(getWayNicename(v.getPcap()));
                    }
                }
            }
        } catch (IOException | IllegalStateException | ParseException e) {
            System.err.println(e.getMessage());
        }
        
        Collections.sort(respiratoryRateList, new Comparator<RespiratoryRateCSV>() {
            @Override
            public int compare(RespiratoryRateCSV br1, RespiratoryRateCSV br2) {
                return br1.getCode() > br2.getCode() ? 1 : -1;
            }
        });
        
        return respiratoryRateList;
    }
    
    public static List<WristwatchCSV> getWristwatchFromCsv(String path) {
        if (wristwatchList != null)
            return wristwatchList;
        try {
            wristwatchList = new CsvToBeanBuilder(new FileReader(path + "wristwatch.csv"))
                .withSkipLines(1)
                .withType(WristwatchCSV.class)
                .build()
                .parse();
            if(wristwatchList != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_-_HH_mm_ss");  
                Pattern pattern = Pattern.compile("\\d{4}_\\d{2}_\\d{2}_-_\\d{2}_\\d{2}_\\d{2}");
                for (WristwatchCSV ww : wristwatchList) {
                    Matcher matcher = pattern.matcher(ww.getFile());
                    if (matcher.find()) {
                        ww.setTimestampJD(formatter.parse(matcher.group()));
                        ww.setCode(getCode(ww.getFile()));
                        ww.setWay(getWay(ww.getFile()));
                        ww.setWayNicename(getWayNicename(ww.getFile()));
                    }
                }
            }
        } catch (IOException | IllegalStateException | ParseException e) {
            System.err.println(e.getMessage());
        }
        
        return wristwatchList;
    }
    
    public static List<ParticipantCSV> getParticipantsFromCsv(String path) {
        if (participantsList != null)
            return participantsList;
        try {
            participantsList = new CsvToBeanBuilder(new FileReader(Paths
                .get(path + "participants.csv")
                .toAbsolutePath()
                .toString()))
                .withSkipLines(1)
                .withType(ParticipantCSV.class)
                .build()
                .parse();
            if(participantsList != null) {
                for (ParticipantCSV p : participantsList) {
                    String[] strDate = p.getBirthDate().split("/");
                    LocalDate date = LocalDate.of(Integer.parseInt(strDate[2]), Integer.parseInt(strDate[1]), Integer.parseInt(strDate[0]));
                    p.setBirthDateJD(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    p.setBmi(BmiUtil.bmi(Double.valueOf(p.getWeight()), Double.valueOf(p.getHeight())));
                }
            }
        } catch (IOException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }
        return participantsList;
    }
    
    public static List<DataCSV> getData(String path) {
        if (get(path)) {
            dataList = new ArrayList<>();
            if (participantsList == null)
                return dataList;
            for (ParticipantCSV p : participantsList) {
                DataCSV dataCsv = new DataCSV();
                List<BpmCSV> pBpm = new ArrayList<>();
                List<RespiratoryRateCSV> pRr = new ArrayList<>();
                List<WristwatchCSV> pWw = new ArrayList<>();
                List<WristwatchCSV> pWwBpm = new ArrayList<>();
                Double[] pWwArray = new Double[18];
                Double[] pWwCountArray = new Double[18];
                
                for (int i = 0; i < 18; i++) {
                    pWwArray[i] = null;
                    pWwCountArray[i] = null;
                }

                if (bpmList != null)
                    for (BpmCSV b : bpmList)
                        if (b.getId().equals(p.getId()))
                            pBpm.add(b);

                if (respiratoryRateList != null)
                    for (RespiratoryRateCSV rr : respiratoryRateList)
                        if (rr.getId().equals(p.getId()))
                            pRr.add(rr);

                if (wristwatchList != null)
                    for (WristwatchCSV ww : wristwatchList)
                        if (ww.getId().equals(p.getId()))
                            pWw.add(ww);
                
                for (WristwatchCSV ww : pWw) {
                    if (ww.getCode() == null)
                        continue;
                    
                    if (pWwArray[ww.getCode()] == null)
                        pWwArray[ww.getCode()] = ww.getBpm();
                    else
                        pWwArray[ww.getCode()] = pWwArray[ww.getCode()] + ww.getBpm();
                    
                    if (pWwCountArray[ww.getCode()] == null)
                        pWwCountArray[ww.getCode()] = 1.0;
                    else
                        pWwCountArray[ww.getCode()] += 1.0;
                }
                
                for (int i = 0; i < 18; i++) {
                    if (pWwArray[i] != null) 
                        pWwArray[i] /= pWwCountArray[i];
                }
                
                for (int i = 0; i < 18; i++) {
                    WristwatchCSV wristwatchCSV = new WristwatchCSV();
                    wristwatchCSV.setId(p.getId());
                    wristwatchCSV.setCode(i);
                    wristwatchCSV.setBpm(pWwArray[i]);
                    pWwBpm.add(wristwatchCSV);
                }

                dataCsv.setId(p.getId());
                dataCsv.setParticipant(p);
                dataCsv.setLink(p.getLink());
                dataCsv.setBpm(pBpm);
                dataCsv.setRespiratoryRate(pRr);
                dataCsv.setWristwatch(pWw);
                dataCsv.setWristwatchBpm(pWwBpm);
                dataList.add(dataCsv);
            }
        }
        return dataList;
    }
}