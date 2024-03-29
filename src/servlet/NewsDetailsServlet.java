package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import db.Category;
import db.Comment;
import db.DBConnection;
import db.News;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/news-details")
public class NewsDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        News news = DBConnection.getNewsById(id);
        request.setAttribute("news", news);

        if(news!=null) {
            ArrayList<Comment> comments = DBConnection.getComment(news.getId());
            request.setAttribute("comments", comments);
            ArrayList<Category> categories = DBConnection.getCategory();
            request.setAttribute("categoryler", categories);
        }
        request.getRequestDispatcher("/newsdetails.jsp").forward(request, response);
    }
}
