package top.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import top.service.MainService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/top10/*", loadOnStartup = 1)
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    private MainService mainService;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf8");
        PrintWriter out = response.getWriter();
        List<Date> dates = mainService.getAllDatesOfTop10();
        String dateOptionsStart = "<form action=\"/top10\">\n" +
                "  <label for=\"date\">Choose a date:</label>\n" +
                "  <select id=\"date\" name=\"date\">\n";
        String dateOptionsEnd =
                "  </select>\n" +
                "  <input type=\"submit\">\n" +
                "</form>";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dateOptionsStart);
        dates.stream()
                .map(simpleDateFormat::format)
                .forEach(date -> stringBuilder.append("<option value=\"").append(date).append("\">").append(date).append("</option>\n"));
        stringBuilder.append(dateOptionsEnd);
        out.println(
                "<head>\n" +
                "<style>\n" +
                "table {\n" +
                "  font-family: arial, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "  border: 1px solid #dddddd;\n" +
                "  text-align: left;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "  background-color: #dddddd;\n" +
                "}\n" +
                "</style>\n" +
                "</head>");
        out.println("<h3>Choose day for Top10 films!</h3>");
        out.println(stringBuilder.toString());
        String queryString = request.getQueryString();
        if ((queryString!=null) && (queryString.contains("date"))){
            stringBuilder.setLength(0);
            stringBuilder.append("<table>  \n <tr>\n" +
                    "    <th>Position</th>\n" +
                    "    <th>Rating</th>\n" +
                    "    <th>Name</th>\n" +
                    "    <th>Original name</th>\n" +
                    "    <th>Year</th>\n" +
                    "    <th>Voters</th>\n" +
                    "  </tr>");
            try {
                mainService.getTop10FilmsForDate(simpleDateFormat.parse(queryString.substring(queryString.indexOf("date=")+5, queryString.indexOf("date=")+15)))
                .forEach(top10->{
                    stringBuilder.append("<tr>")
                            .append("<th>").append(top10.getPosition()).append("</th>\n")
                            .append("<th>").append(top10.getRating()).append("</th>\n")
                            .append("<th>").append("<a href=\"https://www.kinopoisk.ru/film/").append(top10.getFilm().getId()).append("\">").append(top10.getFilm().getName()).append("</a>").append("</th>\n")
                            .append("<th>").append(top10.getFilm().getOriginalName()).append("</th>\n")
                            .append("<th>").append(top10.getFilm().getYear()).append("</th>\n")
                            .append("<th>").append(top10.getVoters()).append("</th>\n")
                    .append("  </tr> \n")
                    ;
                });
            } catch (ParseException e) {
                e.printStackTrace();
            }
            stringBuilder.append("</table> \n");
            out.println(stringBuilder.toString());
        }

    }
}