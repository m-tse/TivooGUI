package event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.dom4j.Element;

public class DukeBasketballEvent extends Event {
    private static final String dateFormat = "MM/DD/yyyyhh:mm:ss aa";
    private static final String title = "Subject";
    private static final String description = "Description";
    private static final String startDate = "StartDate";
    private static final String startTime = "StartTime";
    private static final String endDate = "EndDate";
    private static final String endTime = "EndTime";
    private static final String link = "Location";
    private static final String author = "Location";
	public DukeBasketballEvent(Element event) throws ParseException {
		// sets title, time, description etc. of event

		setTitle(event.elementText(title));
		setDescription(event.elementText(description));
		setStart(parseTime(event, startDate, startTime));
		setEnd(parseTime(event, endDate, endTime));
		setLink(event.elementText(link));
		setAuthor(event.elementText(author));
	}

	private Calendar parseTime(Element event, String dateCategory,
			String timeCategory) throws ParseException {
		String startDate = event.elementText(dateCategory);
		String startTime = event.elementText(timeCategory);
		String combinedDateTime = startDate + startTime;
		Calendar result = new GregorianCalendar();
		result.setTime(new SimpleDateFormat(dateFormat)
				.parse(combinedDateTime));
		return result;
	}
}
