package command;

import util.photo_id.*;

import java.util.*;

import static util.Constants.*;

public class Executor implements Command {
    private final Map<String, List<String>> commands;

    public Executor() {
        commands = initCommandsMap();
    }

    private Map<String, List<String>> initCommandsMap() {
        Map<String, List<String>> commandMap = new HashMap<>();
        commandMap.put(FIRST_COURSE, PhotoUtil.getFirstSchedule());
        commandMap.put(SECOND_COURSE, PhotoUtil.getSecondSchedule());
        commandMap.put(THIRD_COURSE, PhotoUtil.getThirdSchedule());
        commandMap.put(FOURTH_COURSE, PhotoUtil.getFourthSchedule());
        commandMap.put(FIFTH_COURSE, PhotoUtil.getFifthSchedule());
        commandMap.put(SCHEDULE_OF_BELL, Arrays.asList(PhotoId.SCHEDULE_FOR_BELL));
        commandMap.put(SCHEDULE_OF_WEEKS, Arrays.asList(PhotoId.WEEKS_SCHEDULE));

        return commandMap;
    }

    @Override
    public List<String> execute(String command) {
        System.out.println("\"" + command + "\"");
        List<String> list = commands.get(command);
        if (list == null) {
            list = new ArrayList<>();
            list.add(UNKNOWN_CMD);
        }
        return list;
    }
}
