package listener;

import dao.pool.ConnectionsPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by EgorVeremeychik on 01.05.2016.
 */

@WebListener()
public class SessionListener implements ServletContextListener {
    public static final Logger LOG = Logger.getLogger(SessionListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ConnectionsPool.init();
        StringBuilder path = new StringBuilder(servletContextEvent.getServletContext().getRealPath("/"));
        path.append("WEB-INF\\classes\\config\\log4j.xml");
        new DOMConfigurator().doConfigure(path.toString(), LogManager.getLoggerRepository());
        LOG.info("Logging initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
