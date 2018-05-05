package util.photo_id;

import java.util.ArrayList;
import java.util.List;

public class PhotoUtil implements PhotoId {
    private PhotoUtil(){}

    public static List<String> getFirstSchedule() {
        return getStrings(FIRST_MONDAY, FIRST_TUESDAY, FIRST_WEDNESDAY, FIRST_THURSDAY, FIRST_FRIDAY);
    }
    public static List<String> getSecondSchedule() {
        return getStrings(SECOND_MONDAY, SECOND_TUESDAY, SECOND_WEDNESDAY, SECOND_THURSDAY, SECOND_FRIDAY);
    }

    public static List<String> getThirdSchedule() {
        return getStrings(THIRD_MONDAY, THIRD_TUESDAY, THIRD_WEDNESDAY, THIRD_THURSDAY, THIRD_FRIDAY);
    }

    public static List<String> getFourthSchedule() {
        return getStrings(FOURTH_MONDAY, FOURTH_TUESDAY, FOURTH_WEDNESDAY, FOURTH_THURSDAY, FOURTH_FRIDAY);
    }

    public static List<String> getFifthSchedule(){
        return getStrings(FIFTH_MONDAY, FIFTH_TUESDAY, FIFTH_WEDNESDAY, FIFTH_THURSDAY, FIFTH_FRIDAY);
    }

    private static List<String> getStrings(String secondMonday, String secondTuesday, String secondWednesday, String secondThursday, String secondFriday) {
        List<String> list = new ArrayList<>();
        list.add(secondMonday);
        list.add(secondTuesday);
        list.add(secondWednesday);
        list.add(secondThursday);
        list.add(secondFriday);
        return list;
    }
}
