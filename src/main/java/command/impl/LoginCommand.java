package command.impl;

import command.ICommand;
import entity.impl.User;
import manager.PagesEnum;
import manager.PagesManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by EgorVeremeychik on 01.05.2016.
 */
public class LoginCommand implements ICommand {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);
    private static final String LOGIN = "login";
    private static final String USER = "user";

    @Override
    public String execute(HttpServletRequest request) {
        String result = null;
        User user = (User)request.getSession().getAttribute(USER);
        if(user == null){
            String login = request.getParameter(LOGIN);
            user = new User(login);
            request.getSession().setAttribute(USER, user);
            LOG.info("LOGIN SUCCESS!");
        }
        result = PagesManager.getPage(PagesEnum.START_BOOK_CATALOG);
        return result;
    }
}
