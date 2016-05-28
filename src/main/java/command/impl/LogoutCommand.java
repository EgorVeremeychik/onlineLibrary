package command.impl;

import command.ICommand;
import manager.PagesEnum;
import manager.PagesManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by EgorVeremeychik on 01.05.2016.
 */

public class LogoutCommand implements ICommand {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);
    private static final String USER = "user";

    @Override
    public String execute(HttpServletRequest request) {
        String result;
        request.getSession().setAttribute(USER, null);
        result = PagesManager.getPage(PagesEnum.INDEX);
        LOG.info("LOGOUT SUCCESS!");
        return result;
    }
}
