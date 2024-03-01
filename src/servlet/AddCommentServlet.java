package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import db.Comment;
import db.DBConnection;
import db.News;
import db.User;

import java.io.IOException;

@WebServlet(value = "/add-comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User)request.getSession().getAttribute("currentUser");
        if(currentUser!=null){
            String commentText = request.getParameter("comment");
            int newsId = Integer.parseInt(request.getParameter("news_id"));

            News news = DBConnection.getNewsById(newsId);
            if(news != null){
                Comment comment = new Comment();
                comment.setComment(commentText);
                comment.setNews(news);
                comment.setUser(currentUser);

                DBConnection.addComment(comment);
                response.sendRedirect("/news-details?id="+newsId);
            }else {
                response.sendRedirect("/");
            }


        }else {
            response.sendRedirect("/login");
        }
    }
}
