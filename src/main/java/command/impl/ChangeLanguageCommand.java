package command.impl;

import command.ICommand;
import manager.PagesEnum;
import manager.PagesManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by EgorVeremeychik on 27.05.2016.
 */
public class ChangeLanguageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        String result = null;
        request.getSession(true).setAttribute("lang", request.getParameter("loc"));
        result = PagesManager.getPage(PagesEnum.START_BOOK_CATALOG);
        return result;
    }
}
