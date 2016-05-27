package command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by EgorVeremeychik on 24.04.2016.
 */
public interface ICommand {
    String execute(HttpServletRequest request);
}
