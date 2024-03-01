package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import db.DBConnection;
import db.News;
import db.User;

import java.io.IOException;

@WebServlet(value = "/save-news")
public class UpdateNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User)request.getSession().getAttribute("currentUser");
        if(currentUser!=null){
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            int id = Integer.parseInt(request.getParameter("id"));

            News news = DBConnection.getNewsById(id);
            if(news!=null){
                news.setTitle(title);
                news.setContent(content);
                news.setUser(currentUser);

                DBConnection.updateNews(news);
                response.sendRedirect("/news-details?id=" + id);
            }else {
                response.sendRedirect("/");
            }
        }else {
            response.sendRedirect("/login");
        }
    }
}
