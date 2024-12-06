//package mk.ukim.finki.wp.lab.web.servlet;
//
//import jakarta.servlet.ServletContext;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.model.Event;
//import mk.ukim.finki.wp.lab.service.EventService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "event-list-servlet", urlPatterns = "")
//public class EventListServlet extends HttpServlet {
//    private final SpringTemplateEngine springTemplateEngine;
//    private final EventService eventService;
//
//    public EventListServlet(SpringTemplateEngine springTemplateEngine, EventService eventService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.eventService = eventService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getSession(true);
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//
//        ServletContext servletContext = getServletContext();
//        int activeSessions = (int) servletContext.getAttribute("active-sessions");
//
//        List<Event> events = eventService.listAll();
//
//        context.setVariable("events", events);
//        context.setVariable("activeSessions", activeSessions);
//        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//
//        ServletContext servletContext = getServletContext();
//        int activeSessions = (int) servletContext.getAttribute("active-sessions");
//
//        String name = req.getParameter("searchText");
//        String minRating = req.getParameter("minRating");
//
//        List<Event> events = eventService.searchEvents(name, Double.parseDouble(minRating));
//
//        context.setVariable("events", events);
//        context.setVariable("activeSessions", activeSessions);
//        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
//    }
//}