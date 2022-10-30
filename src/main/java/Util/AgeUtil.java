package Util;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class AgeUtil {
    public static int calculateAge(Date birthDate) {
        Date currentDate = new Date();
        if ((birthDate != null)) {
            return Period.between(
                    birthDate.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate(),
                    currentDate.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()).getYears();
        } else {
            return 0;
        }
    }
}