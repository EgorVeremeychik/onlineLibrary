package controller;

import entity.impl.Book;
import org.apache.log4j.Logger;
import service.BooksService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by EgorVeremeychik on 07.06.2016.
 */

@WebServlet(name = "DownloadBook", urlPatterns = "/downloadBook")
public class DownloadBook extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(DownloadBook.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        Integer bookID = Integer.parseInt(request.getParameter("bookID"));
        Book book = BooksService.getBookByID(bookID);
        response.reset();
        try (OutputStream out = response.getOutputStream()) {
            response.setContentType("application/download");
            String fileName = book.getName() + ".pdf";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLDecoder.decode(fileName, "ISO8859_1"));
            response.setContentLength(book.getContent().length);
            out.write(book.getContent());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
