
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/imc")
public class IMCServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("nombre");
        double weight = Double.parseDouble(request.getParameter("peso"));
        double height = Double.parseDouble(request.getParameter("estatura"));

        double imc = weight / (height * height);
        String category;

        if (imc < 18.5) {
            category = "Peso bajo";
        } else if (imc < 25) {
            category = "Peso Normal";
        } else if (imc < 30) {
            category = "Peso alto";
        } else {
            category = "Obesidad";
        }

        out.println("<html><body>");
        out.println("<h2>Resultado</h2>");
        out.println("Nombre: " + name + "<br>");
        out.println("Peso: " + weight + "<br>");
        out.println("Estatura: " + height + "<br>");
        out.printf("Su indice de masa corporal es %.2f. Usted se encuentra en %s.<br>", imc, category);
        out.println("</body></html>");
    }
}
