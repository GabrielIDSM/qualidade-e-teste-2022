package DTO;

import Util.I18nUtil;
import java.util.List;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class FilterDTO {
    private static final Integer PARTICIPANTS = 1;
    private static final Integer AGGREGATED = 2;
    private static final Integer GROUPED = 3;
    private static final Integer REPORT = 4;
    
    private static final Integer IN_MOTION = 1;
    private static final Integer STATIC = 2;
    
    private static final Integer MALE = 1;
    private static final Integer FEMALE = 2;
    
    private static final Integer EQUAL = 1;
    private static final Integer GREATER = 2;
    private static final Integer GREATER_EQUAL = 3;
    private static final Integer LESS = 4;
    private static final Integer LESS_EQUAL = 5;
    
    private static final Integer AVG = 1;
    private static final Integer AT_LEAST_ONE = 2;
    private static final Integer ALL = 3;
    private static final Integer NONE = 4;
    
    private Integer Id;
    private Integer Type;
    private String Tag;
    private List<Integer> Participants;
    private List<Integer> Ways;
    private Integer AssignedSex;
    private Integer Height;
    private Integer HeightOperator;
    private Integer Weight;
    private Integer WeightOperator;
    private Integer Age;
    private Integer AgeOperator;
    private Integer Height_2;
    private Integer HeightOperator_2;
    private Integer Weight_2;
    private Integer WeightOperator_2;
    private Integer Age_2;
    private Integer AgeOperator_2;
    private Integer Bpm;
    private Integer BpmOperator;
    private Integer BpmType;
    private Integer RespiratoryRate;
    private Integer RespiratoryRateOperator;
    private Integer RespiratoryRateType;
    private Integer PositionType;
    private Integer Bmi;
    private Integer BmiOperator;
    private Integer Bmi_2;
    private Integer BmiOperator_2;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer Type) {
        this.Type = Type;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String Tag) {
        this.Tag = Tag;
    }

    public List<Integer> getParticipants() {
        return Participants;
    }

    public void setParticipants(List<Integer> Participants) {
        this.Participants = Participants;
    }

    public List<Integer> getWays() {
        return Ways;
    }

    public void setWays(List<Integer> Ways) {
        this.Ways = Ways;
    }

    public Integer getAssignedSex() {
        return AssignedSex;
    }

    public void setAssignedSex(Integer AssignedSex) {
        this.AssignedSex = AssignedSex;
    }

    public Integer getHeight() {
        return Height;
    }

    public void setHeight(Integer Height) {
        this.Height = Height;
    }

    public Integer getHeightOperator() {
        return HeightOperator;
    }

    public void setHeightOperator(Integer HeightOperator) {
        this.HeightOperator = HeightOperator;
    }

    public Integer getWeight() {
        return Weight;
    }

    public void setWeight(Integer Weight) {
        this.Weight = Weight;
    }

    public Integer getWeightOperator() {
        return WeightOperator;
    }

    public void setWeightOperator(Integer WeightOperator) {
        this.WeightOperator = WeightOperator;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer Age) {
        this.Age = Age;
    }

    public Integer getAgeOperator() {
        return AgeOperator;
    }

    public void setAgeOperator(Integer AgeOperator) {
        this.AgeOperator = AgeOperator;
    }

    public Integer getHeight_2() {
        return Height_2;
    }

    public void setHeight_2(Integer Height_2) {
        this.Height_2 = Height_2;
    }

    public Integer getHeightOperator_2() {
        return HeightOperator_2;
    }

    public void setHeightOperator_2(Integer HeightOperator_2) {
        this.HeightOperator_2 = HeightOperator_2;
    }

    public Integer getWeight_2() {
        return Weight_2;
    }

    public void setWeight_2(Integer Weight_2) {
        this.Weight_2 = Weight_2;
    }

    public Integer getWeightOperator_2() {
        return WeightOperator_2;
    }

    public void setWeightOperator_2(Integer WeightOperator_2) {
        this.WeightOperator_2 = WeightOperator_2;
    }

    public Integer getAge_2() {
        return Age_2;
    }

    public void setAge_2(Integer Age_2) {
        this.Age_2 = Age_2;
    }

    public Integer getAgeOperator_2() {
        return AgeOperator_2;
    }

    public void setAgeOperator_2(Integer AgeOperator_2) {
        this.AgeOperator_2 = AgeOperator_2;
    }

    public Integer getBpm() {
        return Bpm;
    }

    public void setBpm(Integer Bpm) {
        this.Bpm = Bpm;
    }

    public Integer getBpmOperator() {
        return BpmOperator;
    }

    public void setBpmOperator(Integer BpmOperator) {
        this.BpmOperator = BpmOperator;
    }

    public Integer getBpmType() {
        return BpmType;
    }

    public void setBpmType(Integer BpmType) {
        this.BpmType = BpmType;
    }

    public Integer getRespiratoryRate() {
        return RespiratoryRate;
    }

    public void setRespiratoryRate(Integer RespiratoryRate) {
        this.RespiratoryRate = RespiratoryRate;
    }

    public Integer getRespiratoryRateOperator() {
        return RespiratoryRateOperator;
    }

    public void setRespiratoryRateOperator(Integer RespiratoryRateOperator) {
        this.RespiratoryRateOperator = RespiratoryRateOperator;
    }

    public Integer getRespiratoryRateType() {
        return RespiratoryRateType;
    }

    public void setRespiratoryRateType(Integer RespiratoryRateType) {
        this.RespiratoryRateType = RespiratoryRateType;
    }

    public Integer getPositionType() {
        return PositionType;
    }

    public void setPositionType(Integer positionType) {
        this.PositionType = positionType;
    }

    public Integer getBmi() {
        return Bmi;
    }

    public void setBmi(Integer Bmi) {
        this.Bmi = Bmi;
    }

    public Integer getBmiOperator() {
        return BmiOperator;
    }

    public void setBmiOperator(Integer BmiOperator) {
        this.BmiOperator = BmiOperator;
    }

    public Integer getBmi_2() {
        return Bmi_2;
    }

    public void setBmi_2(Integer Bmi_2) {
        this.Bmi_2 = Bmi_2;
    }

    public Integer getBmiOperator_2() {
        return BmiOperator_2;
    }

    public void setBmiOperator_2(Integer BmiOperator_2) {
        this.BmiOperator_2 = BmiOperator_2;
    }

    public String i18n(Integer language) {
        String filterDto;
        
        if (this.Type.equals(PARTICIPANTS))
            filterDto = I18nUtil.i18n("FILTER_TYPE_LIST", language);
        else if (this.Type.equals(AGGREGATED))
            filterDto = I18nUtil.i18n("FILTER_TYPE_AVG", language);
        else if (this.Type.equals(GROUPED))
            filterDto = I18nUtil.i18n("FILTER_TYPE_BY_PARTICIPANT", language);
        else if (this.Type.equals(REPORT))
            filterDto = I18nUtil.i18n("FILTER_TYPE_REPORT", language);
        else filterDto = "";
        
        if (this.Participants != null) {
            if (this.Participants.size() > 1)
                filterDto = filterDto + ", " + this.Participants.size() + " " +
                        I18nUtil.i18n("PARTICIPANTS", language);
            else
                filterDto = filterDto + ", " + this.Participants.size() + " " +
                        I18nUtil.i18n("PARTICIPANT", language);
        }
        
        if (this.PositionType != null) {
            if (this.PositionType.equals(IN_MOTION)) filterDto = filterDto + ", " +
                    I18nUtil.i18n("IN_MOTION", language);
            else if (this.PositionType.equals(STATIC)) filterDto = filterDto +
                    ", " + I18nUtil.i18n("STATIC", language);
        }
        
        if (this.Ways != null) {
            if (this.Ways.size() > 1)
                filterDto = filterDto + ", " + this.Ways.size() + " " +
                        I18nUtil.i18n("POSITIONS", language);
            else
                filterDto = filterDto + ", " + this.Ways.size() + " " +
                        I18nUtil.i18n("POSITION", language);
        }
        
        if (this.AssignedSex != null) {
            if (this.AssignedSex.equals(MALE)) filterDto = filterDto + ", " +
                    I18nUtil.i18n("ONLY_MALE", language);
            else if (this.AssignedSex.equals(FEMALE)) filterDto = filterDto +
                    ", " + I18nUtil.i18n("ONLY_FEMALE", language);
        }
        
        if (this.Age != null) {
            String operator = "";
            
            if (this.AgeOperator.equals(FilterDTO.getEQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_EQUAL", language) + " ";
            else if (this.AgeOperator.equals(FilterDTO.getGREATER()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER", language) + " ";
            else if (this.AgeOperator.equals(FilterDTO.getLESS()))
                operator = " " + I18nUtil.i18n("FILTER_LESS", language) + " ";
            else if (this.AgeOperator.equals(FilterDTO.getGREATER_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER_EQUAL", language) + " ";
            else if (this.AgeOperator.equals(FilterDTO.getLESS_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_LESS_EQUAL", language) + " ";
            
            filterDto = filterDto + ", " + I18nUtil.i18n("AGE", language) +
                    operator + this.Age + " " + I18nUtil.i18n("YEARS", language);
        }
        
        if (this.Age_2 != null) {
            String operator = "";
            
            if (this.AgeOperator_2.equals(FilterDTO.getEQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_EQUAL", language) + " ";
            else if (this.AgeOperator_2.equals(FilterDTO.getGREATER()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER", language) + " ";
            else if (this.AgeOperator_2.equals(FilterDTO.getLESS()))
                operator = " " + I18nUtil.i18n("FILTER_LESS", language) + " ";
            else if (this.AgeOperator_2.equals(FilterDTO.getGREATER_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER_EQUAL", language) + " ";
            else if (this.AgeOperator_2.equals(FilterDTO.getLESS_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_LESS_EQUAL", language) + " ";
            
            filterDto = filterDto + ", " + I18nUtil.i18n("AGE", language) +
                    operator + this.Age_2 + " " + I18nUtil.i18n("YEARS", language);
        }
        
        if (this.Weight != null) {
            String operator = "";
            
            if (this.WeightOperator.equals(FilterDTO.getEQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_EQUAL", language) + " ";
            else if (this.WeightOperator.equals(FilterDTO.getGREATER()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER", language) + " ";
            else if (this.WeightOperator.equals(FilterDTO.getLESS()))
                operator = " " + I18nUtil.i18n("FILTER_LESS", language) + " ";
            else if (this.WeightOperator.equals(FilterDTO.getGREATER_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER_EQUAL", language) + " ";
            else if (this.WeightOperator.equals(FilterDTO.getLESS_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_LESS_EQUAL", language) + " ";
            
            filterDto = filterDto + ", " + I18nUtil.i18n("WEIGHT", language) +
                    operator + this.Weight + " kg";
        }
        
        if (this.Weight_2 != null) {
            String operator = "";
            
            if (this.WeightOperator_2.equals(FilterDTO.getEQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_EQUAL", language) + " ";
            else if (this.WeightOperator_2.equals(FilterDTO.getGREATER()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER", language) + " ";
            else if (this.WeightOperator_2.equals(FilterDTO.getLESS()))
                operator = " " + I18nUtil.i18n("FILTER_LESS", language) + " ";
            else if (this.WeightOperator_2.equals(FilterDTO.getGREATER_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER_EQUAL", language) + " ";
            else if (this.WeightOperator_2.equals(FilterDTO.getLESS_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_LESS_EQUAL", language) + " ";
            
            filterDto = filterDto + ", " + I18nUtil.i18n("WEIGHT", language) +
                    operator + this.Weight_2 + " kg";
        }
        
        if (this.Height != null) {
            String operator = "";
            
            if (this.HeightOperator.equals(FilterDTO.getEQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_EQUAL", language) + " ";
            else if (this.HeightOperator.equals(FilterDTO.getGREATER()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER", language) + " ";
            else if (this.HeightOperator.equals(FilterDTO.getLESS()))
                operator = " " + I18nUtil.i18n("FILTER_LESS", language) + " ";
            else if (this.HeightOperator.equals(FilterDTO.getGREATER_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER_EQUAL", language) + " ";
            else if (this.HeightOperator.equals(FilterDTO.getLESS_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_LESS_EQUAL", language) + " ";

            filterDto = filterDto + ", " + I18nUtil.i18n("STATURE", language) +
                    operator + this.Height + " cm";
        }
        
        if (this.Height_2 != null) {
            String operator = "";
            
            if (this.HeightOperator_2.equals(FilterDTO.getEQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_EQUAL", language) + " ";
            else if (this.HeightOperator_2.equals(FilterDTO.getGREATER()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER", language) + " ";
            else if (this.HeightOperator_2.equals(FilterDTO.getLESS()))
                operator = " " + I18nUtil.i18n("FILTER_LESS", language) + " ";
            else if (this.HeightOperator_2.equals(FilterDTO.getGREATER_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER_EQUAL", language) + " ";
            else if (this.HeightOperator_2.equals(FilterDTO.getLESS_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_LESS_EQUAL", language) + " ";

            filterDto = filterDto + ", " + I18nUtil.i18n("STATURE", language) +
                    operator + this.Height_2 + " cm";
        }
        
        if (this.Bmi != null) {
            String operator = "";
            
            if (this.BmiOperator.equals(FilterDTO.getEQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_EQUAL", language) + " ";
            else if (this.BmiOperator.equals(FilterDTO.getGREATER()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER", language) + " ";
            else if (this.BmiOperator.equals(FilterDTO.getLESS()))
                operator = " " + I18nUtil.i18n("FILTER_LESS", language) + " ";
            else if (this.BmiOperator.equals(FilterDTO.getGREATER_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER_EQUAL", language) + " ";
            else if (this.BmiOperator.equals(FilterDTO.getLESS_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_LESS_EQUAL", language) + " ";

            filterDto = filterDto + ", " + I18nUtil.i18n("BODY_MASS_INDEX", language) +
                    operator + this.Bmi;
        }
        
        if (this.Bmi_2 != null) {
            String operator = "";
            
            if (this.BmiOperator_2.equals(FilterDTO.getEQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_EQUAL", language) + " ";
            else if (this.BmiOperator_2.equals(FilterDTO.getGREATER()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER", language) + " ";
            else if (this.BmiOperator_2.equals(FilterDTO.getLESS()))
                operator = " " + I18nUtil.i18n("FILTER_LESS", language) + " ";
            else if (this.BmiOperator_2.equals(FilterDTO.getGREATER_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_GREATER_EQUAL", language) + " ";
            else if (this.BmiOperator_2.equals(FilterDTO.getLESS_EQUAL()))
                operator = " " + I18nUtil.i18n("FILTER_LESS_EQUAL", language) + " ";

            filterDto = filterDto + ", " + I18nUtil.i18n("BODY_MASS_INDEX", language) +
                    operator + this.Bmi_2;
        }
        
        return filterDto;
    }

    public static Integer getPARTICIPANTS() {
        return PARTICIPANTS;
    }

    public static Integer getAGGREGATED() {
        return AGGREGATED;
    }

    public static Integer getGROUPED() {
        return GROUPED;
    }

    public static Integer getREPORT() {
        return REPORT;
    }

    public static Integer getMALE() {
        return MALE;
    }

    public static Integer getFEMALE() {
        return FEMALE;
    }

    public static Integer getIN_MOTION() {
        return IN_MOTION;
    }

    public static Integer getSTATIC() {
        return STATIC;
    }

    public static Integer getEQUAL() {
        return EQUAL;
    }

    public static Integer getGREATER() {
        return GREATER;
    }

    public static Integer getGREATER_EQUAL() {
        return GREATER_EQUAL;
    }

    public static Integer getLESS() {
        return LESS;
    }

    public static Integer getLESS_EQUAL() {
        return LESS_EQUAL;
    }

    public static Integer getAVG() {
        return AVG;
    }

    public static Integer getAT_LEAST_ONE() {
        return AT_LEAST_ONE;
    }

    public static Integer getALL() {
        return ALL;
    }

    public static Integer getNONE() {
        return NONE;
    }
}