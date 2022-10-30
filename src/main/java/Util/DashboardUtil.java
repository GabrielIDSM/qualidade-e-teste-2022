package Util;

import CSV.BpmCSV;
import CSV.DataCSV;
import CSV.ParticipantCSV;
import CSV.RespiratoryRateCSV;
import CSV.WristwatchCSV;
import DTO.FilterDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class DashboardUtil {
    private static final Integer HEIGHT = 1;
    private static final Integer WEIGHT = 2;
    private static final Integer AGE = 3;
    private static final Integer BMI = 4;
    
    private static final Integer BPM = 1;
    private static final Integer RESPIRATORY_RATE = 2;

    public static final String[] TAGS = {
        "rgba(0, 127, 127",
        "rgba(127, 0, 127",
        "rgba(127, 127, 0",
        "rgba(0, 127, 255",
        "rgba(127, 0, 255",
        "rgba(127, 255, 0",
        "rgba(0, 255, 127",
        "rgba(255, 0, 127",
        "rgba(255, 127, 0",
        "rgba(127, 127, 255",
        "rgba(127, 127, 255",
        "rgba(127, 255, 127",
        "rgba(127, 255, 127",
        "rgba(255, 127, 127",
        "rgba(255, 127, 127",
        "rgba(255, 127, 255",
        "rgba(127, 255, 255",
        "rgba(127, 255, 255",
        "rgba(255, 255, 127",
        "rgba(255, 255, 127",
        "rgba(255, 127, 255"
    };
    
    public static Integer getNextId(List<FilterDTO> filterList) {
        Integer nextId = 1;
        if (filterList != null) if (!filterList.isEmpty())
            for (FilterDTO f : filterList) {
                nextId = f.getId() + 1 > nextId ? f.getId() + 1 : nextId;
            }
        return nextId;
    }
    
    public static String getNextTag(List<FilterDTO> filterList) {
        String nextTag = "rgba(127, 127, 127";
        Integer nextId = getNextId(filterList);
        if (nextId <= TAGS.length)
            nextTag = TAGS[nextId - 1];
        return nextTag;
    }
    
    private static Boolean compare(DataCSV data, Integer field,
            Integer operator, Integer type, Integer value) {
        Boolean rValue = Boolean.FALSE;
        Boolean foundOne = Boolean.FALSE;
        Boolean all = Boolean.TRUE;
        Double dValue = Double.valueOf(value);
        Double size = 0.0;
        Double pValue = 0.0;
        
        if (Objects.equals(field, BPM)) {
            for (BpmCSV o : data.getBpm()) {
                Double v = Double.valueOf(o.getBpm());
                if (type.equals(FilterDTO.getAT_LEAST_ONE())
                        || type.equals(FilterDTO.getNONE())) {
                    if (operator.equals(FilterDTO.getEQUAL())) {
                        if (Objects.equals(v, dValue)) {
                            foundOne = Boolean.TRUE;
                        }
                    } else if (operator.equals(FilterDTO.getGREATER())) {
                        if (v > dValue) {
                            foundOne = Boolean.TRUE;
                        }
                    } else if (operator.equals(FilterDTO.getLESS())) {
                        if (v < dValue) {
                            foundOne = Boolean.TRUE;
                        }
                    } else if (operator.equals(FilterDTO.getGREATER_EQUAL())) {
                        if (v >= dValue) {
                            foundOne = Boolean.TRUE;
                        }
                    } else if (operator.equals(FilterDTO.getLESS_EQUAL())) {
                        if (v <= dValue) {
                            foundOne = Boolean.TRUE;
                        }
                    }
                } else if (type.equals(FilterDTO.getAVG())) {
                    pValue += v;
                    size += 1.0;
                } else if (type.equals(FilterDTO.getALL())) {
                    if (operator.equals(FilterDTO.getEQUAL())) {
                        if (!(Objects.equals(v, dValue))) {
                            all = Boolean.FALSE;
                        }
                    } else if (operator.equals(FilterDTO.getGREATER())) {
                        if (!(v > dValue)) {
                            all = Boolean.FALSE;
                        }
                    } else if (operator.equals(FilterDTO.getLESS())) {
                        if (!(v < dValue)) {
                            all = Boolean.FALSE;
                        }
                    } else if (operator.equals(FilterDTO.getGREATER_EQUAL())) {
                        if (!(v >= dValue)) {
                            all = Boolean.FALSE;
                        }
                    } else if (operator.equals(FilterDTO.getLESS_EQUAL())) {
                        if (!(v <= dValue)) {
                            all = Boolean.FALSE;
                        }
                    }
                }
            }
        } else if (Objects.equals(field, RESPIRATORY_RATE)) {
            for (RespiratoryRateCSV o : data.getRespiratoryRate()) {
                Double v = o.getRespiratoryRate();
                if (type.equals(FilterDTO.getAT_LEAST_ONE())
                        || type.equals(FilterDTO.getNONE())) {
                    if (operator.equals(FilterDTO.getEQUAL())) {
                        if (Objects.equals(v, dValue)) {
                            foundOne = Boolean.TRUE;
                        }
                    } else if (operator.equals(FilterDTO.getGREATER())) {
                        if (v > dValue) {
                            foundOne = Boolean.TRUE;
                        }
                    } else if (operator.equals(FilterDTO.getLESS())) {
                        if (v < dValue) {
                            foundOne = Boolean.TRUE;
                        }
                    } else if (operator.equals(FilterDTO.getGREATER_EQUAL())) {
                        if (v >= dValue) {
                            foundOne = Boolean.TRUE;
                        }
                    } else if (operator.equals(FilterDTO.getLESS_EQUAL())) {
                        if (v <= dValue) {
                            foundOne = Boolean.TRUE;
                        }
                    }
                } else if (type.equals(FilterDTO.getAVG())) {
                    pValue += v;
                    size += 1.0;
                } else if (type.equals(FilterDTO.getALL())) {
                    if (operator.equals(FilterDTO.getEQUAL())) {
                        if (!(Objects.equals(v, dValue))) {
                            all = Boolean.FALSE;
                        }
                    } else if (operator.equals(FilterDTO.getGREATER())) {
                        if (!(v > dValue)) {
                            all = Boolean.FALSE;
                        }
                    } else if (operator.equals(FilterDTO.getLESS())) {
                        if (!(v < dValue)) {
                            all = Boolean.FALSE;
                        }
                    } else if (operator.equals(FilterDTO.getGREATER_EQUAL())) {
                        if (!(v >= dValue)) {
                            all = Boolean.FALSE;
                        }
                    } else if (operator.equals(FilterDTO.getLESS_EQUAL())) {
                        if (!(v <= dValue)) {
                            all = Boolean.FALSE;
                        }
                    }
                }
            }
        }
            
        if (type.equals(FilterDTO.getAT_LEAST_ONE())) {
            return foundOne;
        } else if (type.equals(FilterDTO.getAVG())) {
            pValue /= size;
            if (operator.equals(FilterDTO.getEQUAL()))
                return (Objects.equals(pValue, dValue));
            else if (operator.equals(FilterDTO.getGREATER()))
                return (Double.compare(pValue, dValue) > 0);
            else if (operator.equals(FilterDTO.getLESS()))
                return (Double.compare(pValue, dValue) < 0);
            else if (operator.equals(FilterDTO.getGREATER_EQUAL()))
                return ((Double.compare(pValue, dValue) > 0) ||
                        Objects.equals(pValue, dValue));
            else if (operator.equals(FilterDTO.getLESS_EQUAL()))
                return ((Double.compare(pValue, dValue) < 0) ||
                        Objects.equals(pValue, dValue));
            else return rValue;
        } else if (type.equals(FilterDTO.getALL())) {
            return all;
        } else if (type.equals(FilterDTO.getNONE())) {
            return !foundOne;
        } else {
            return rValue;
        }
    }
    
    private static Boolean compare(ParticipantCSV participant, Integer field,
            Integer operator, Integer value) {
        Double pValue;
        Double dValue = Double.valueOf(value);
        Boolean rValue = Boolean.FALSE;
                
        if (field.equals(HEIGHT))
            pValue = Double.parseDouble(participant.getHeight());
        else if (field.equals(WEIGHT))
            pValue = Double.parseDouble(participant.getWeight());
        else if (field.equals(BMI))
            pValue = participant.getBmi();
        else if (field.equals(AGE))
            pValue = Double.valueOf(AgeUtil.
                    calculateAge(participant.getBirthDateJD()));
        else return Boolean.FALSE;
        
        if (operator.equals(FilterDTO.getEQUAL()))
            rValue = (Objects.equals(pValue, dValue));
        else if (operator.equals(FilterDTO.getGREATER()))
            rValue = (Double.compare(pValue, dValue) > 0);
        else if (operator.equals(FilterDTO.getLESS()))
            rValue = (Double.compare(pValue, dValue) < 0);
        else if (operator.equals(FilterDTO.getGREATER_EQUAL()))
            rValue = ((Double.compare(pValue, dValue) > 0) ||
                    Objects.equals(pValue, dValue));
        else if (operator.equals(FilterDTO.getLESS_EQUAL()))
            rValue = ((Double.compare(pValue, dValue) < 0) ||
                    Objects.equals(pValue, dValue));
        
        return rValue;
    }
    
    public static List<DataCSV> filterData(List<DataCSV> dataCsvList, FilterDTO filter) {
        List<DataCSV> filteredCsvList = new ArrayList<>(dataCsvList);
        if (filter.getWays() != null || filter.getPositionType() != null) {
            List<Integer> codeToRemove = new ArrayList<>();
            for (Integer i = 0; i < 18; i++) {
                if (filter.getWays() != null) {
                    if (!filter.getWays().contains(i)) {
                        codeToRemove.add(i);
                        continue;
                    }
                }
                
                if (filter.getPositionType() != null) {
                    if (Objects.equals(filter.getPositionType(),
                            FilterDTO.getIN_MOTION()) && (i == 3 || i >= 14))
                        codeToRemove.add(i);
                    else if (Objects.equals(filter.getPositionType(),
                            FilterDTO.getSTATIC()) && (i == 0 || i == 1 ||
                            i == 2 || (i >= 4 && i <=13) ))
                        codeToRemove.add(i);
                }
            }
            
            for (DataCSV data : filteredCsvList) {
                for (Integer code : codeToRemove) {
                    if (data.getBpm() != null) {
                        List<BpmCSV> bpmToRemove = new ArrayList<>();
                        for (BpmCSV bpm : data.getBpm()) {
                            if (bpm.getCode().equals(code)) bpmToRemove.add(bpm);
                        }
                        for (BpmCSV bpm : bpmToRemove)
                            if (data.getBpm().contains(bpm))
                                data.getBpm().remove(bpm);
                    }
                    if (data.getRespiratoryRate() != null) {
                        List<RespiratoryRateCSV> rrToRemove = new ArrayList<>();
                        for (RespiratoryRateCSV rr : data.getRespiratoryRate()) {
                            if (rr.getCode().equals(code)) rrToRemove.add(rr);
                        }
                        for (RespiratoryRateCSV rr : rrToRemove)
                            if (data.getRespiratoryRate().contains(rr))
                                data.getRespiratoryRate().remove(rr);
                    }
                    if (data.getWristwatchBpm() != null) {
                        List<WristwatchCSV> wwToRemove = new ArrayList<>();
                        for (WristwatchCSV ww : data.getWristwatchBpm()) {
                            if (ww.getCode().equals(code)) wwToRemove.add(ww);
                        }
                        for (WristwatchCSV ww : wwToRemove)
                            if (data.getWristwatchBpm().contains(ww))
                                data.getWristwatchBpm().remove(ww);
                    }
                }
            }
        }
        for (DataCSV data : dataCsvList) {
            if (filter.getParticipants() != null)
                if (!filter.getParticipants().isEmpty()) {
                    Boolean in = Boolean.FALSE;
                    for (Integer pId : filter.getParticipants())
                        if (pId.equals(Integer.parseInt(data.getId())))
                            in = Boolean.TRUE;
                    if (!in)
                        filteredCsvList.remove(data);
                }
            
            if (filter.getAssignedSex() != null) {
                if (filter.getAssignedSex().equals(FilterDTO.getMALE()) 
                        && !data.getParticipant().getAssignedSex().contains("Masculino"))
                    if (filteredCsvList.contains(data)) filteredCsvList.remove(data);
                if (filter.getAssignedSex().equals(FilterDTO.getFEMALE()) 
                        && !data.getParticipant().getAssignedSex().contains("Feminino"))
                    if (filteredCsvList.contains(data)) filteredCsvList.remove(data);
            }
            
            if (filter.getWeight() != null)
                if (!compare(data.getParticipant(), WEIGHT,
                        filter.getWeightOperator(), filter.getWeight()))
                    if (filteredCsvList.contains(data)) filteredCsvList.remove(data);
            
            if (filter.getWeight_2() != null)
                if (!compare(data.getParticipant(), WEIGHT,
                        filter.getWeightOperator_2(), filter.getWeight_2()))
                    if (filteredCsvList.contains(data)) filteredCsvList.remove(data);
            
            if (filter.getHeight() != null)
                if (!compare(data.getParticipant(), HEIGHT,
                        filter.getHeightOperator(), filter.getHeight()))
                    if (filteredCsvList.contains(data)) filteredCsvList.remove(data);
            
            if (filter.getHeight_2() != null)
                if (!compare(data.getParticipant(), HEIGHT,
                        filter.getHeightOperator_2(), filter.getHeight_2()))
                    if (filteredCsvList.contains(data)) filteredCsvList.remove(data);
            
            if (filter.getAge() != null)
                if (!compare(data.getParticipant(), AGE,
                        filter.getAgeOperator(), filter.getAge()))
                    if (filteredCsvList.contains(data)) filteredCsvList.remove(data);
            
            if (filter.getAge_2() != null)
                if (!compare(data.getParticipant(), AGE,
                        filter.getAgeOperator_2(), filter.getAge_2()))
                    if (filteredCsvList.contains(data)) filteredCsvList.remove(data);
            
            if (filter.getBpm() != null)
                if (!compare(data, BPM, filter.getBpmOperator(),
                        filter.getBpmType(), filter.getBpm()))
                    if (filteredCsvList.contains(data)) filteredCsvList.remove(data);
            
            if (filter.getRespiratoryRate() != null)
                if (!compare(data, RESPIRATORY_RATE, filter.getRespiratoryRateOperator(),
                        filter.getRespiratoryRateType(), filter.getRespiratoryRate()))
                    if (filteredCsvList.contains(data)) filteredCsvList.remove(data);
            
            if (filter.getBmi() != null)
                if (!compare(data.getParticipant(), BMI,
                        filter.getBmiOperator(), filter.getBmi()))
                    if (filteredCsvList.contains(data)) filteredCsvList.remove(data);
            
            if (filter.getBmi_2() != null)
                if (!compare(data.getParticipant(), BMI,
                        filter.getBmiOperator_2(), filter.getBmi_2()))
                    if (filteredCsvList.contains(data)) filteredCsvList.remove(data);
        
        }
        return filteredCsvList;
    }
}