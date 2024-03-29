package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import db.Category;
import db.DBConnection;
import db.News;
import db.User;

import java.io.IOException;

@WebServlet("/add-page_news")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User)request.getSession().getAttribute("currentUser");
        if(currentUser!=null && currentUser.getRole()==1){
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            int categoty_id = Integer.parseInt(request.getParameter("news_category"));

            Category category = DBConnection.getCategoryId(categoty_id);
            if(category!=null) {
                News news = new News();
                news.setTitle(title);
                news.setContent(content);
                news.setUser(currentUser);
                news.setCategory(category);

                DBConnection.addNews(news);
                response.sendRedirect("/add_news");
            }
        }else {
            response.sendRedirect("/login");
        }
    }

}
