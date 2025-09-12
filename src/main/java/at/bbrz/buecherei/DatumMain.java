package at.bbrz.buecherei;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatumMain {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2024, 12, 31);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy/MM/dd");

        String formattedDate = date.format(formatter);
        System.out.println(formattedDate); // Ausgabe: 2024-12-31
    }
}
