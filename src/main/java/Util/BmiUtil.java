package Util;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class BmiUtil {
    public static Double bmi(Double weight, Double heigth) {
        return weight / ((heigth / 100) * (heigth / 100));
    }
}