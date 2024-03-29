package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import db.Category;
import db.DBConnection;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/add_news")
public class AddNewsPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Category> categories = DBConnection.getCategory();
        request.setAttribute("categoryler", categories);
        request.getRequestDispatcher("/addNews.jsp").forward(request, response);

    }
}
