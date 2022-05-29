package ch.gobothegeek.hsqlsrv;

import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.Server;
import javax.servlet.*;
import java.io.IOException;

public class HSqlServerApp implements Servlet {
    private static final Logger logger = LogManager.getLogger(HSqlServerApp.class);

    private Server hServer;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        String[] arDbsCfg;
        String[] arDbInfo;

        logger.info("Starting HSqlServer");
        this.hServer = new Server();
        this.hServer.setPort(Integer.parseInt(ConfigResolver.getPropertyValue("application.port")));
        arDbsCfg = ConfigResolver.getPropertyValue("application.databases").split(";");
        for (int posDb = 0; posDb < arDbsCfg.length; posDb++) {
            if (0 < arDbsCfg[posDb].indexOf(":")) {
                arDbInfo = arDbsCfg[posDb].split(":");
                this.hServer.setDatabaseName(posDb, arDbInfo[0]);
                this.hServer.setDatabasePath(posDb, arDbInfo[1]);
            }
        }
        this.hServer.start();
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        logger.info("Destroying HSqlServer");
        this.hServer.stop();
    }
}