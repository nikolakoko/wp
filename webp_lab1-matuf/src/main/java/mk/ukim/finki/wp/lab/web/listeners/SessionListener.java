package mk.ukim.finki.wp.lab.web.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
        ServletContext context = se.getSession().getServletContext();
        int activeSessions = (int) context.getAttribute("active-sessions");
        context.setAttribute("active-sessions", ++activeSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
        ServletContext context = se.getSession().getServletContext();
        int activeSessions = (int) context.getAttribute("active-sessions");
        context.setAttribute("active-sessions", --activeSessions);
    }
}
