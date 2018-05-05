package command;

import com.sun.istack.internal.Nullable;

import java.util.List;

public interface Command {

    @Nullable
    List<String> execute(String command);
}
