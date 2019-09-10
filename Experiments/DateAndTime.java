import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.DatatypeConfigurationException;

class DateAndTime {

    public static void main(String[] args) throws DatatypeConfigurationException {
        Date d1 = Date.valueOf("1805-01-01");
        //System.out.println(d1 + 1);
        System.out.println(String.format("%tY", d1));
        System.out.println(String.format("%tY/%tm/%td", d1, d1, d1));

        //Date currentDate = new Date();
        //String isoDate = currentDate.toISOString();
        //System.out.println(currentDate, isoDate);

        Instant yourInstant = Instant.now();
        String dateTimeString = yourInstant.toString();
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);
        System.out.println(date2);

        //long offsetDateTime = OffsetDateTime.now().toEpochSecond();
        long offsetDateTime = OffsetDateTime.of(2019, 9, 10, 10, 53, 0, 0, ZoneOffset.UTC).toEpochSecond();
        OffsetDateTime startDateTime = OffsetDateTime
                    .now(Clock.fixed(Instant.ofEpochSecond(offsetDateTime), ZoneOffset.UTC));
        System.out.println(offsetDateTime);
        System.out.println(startDateTime);
    }
}