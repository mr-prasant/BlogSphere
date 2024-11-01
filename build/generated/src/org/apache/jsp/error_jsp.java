package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import in.blogsphere.dao.UserDAO;
import in.blogsphere.util.CookieUtil;

public final class error_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/./elements/header.jsp");
    _jspx_dependants.add("/./elements/aside.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Sign In</title>\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"./css/style.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./css/error.css\">\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css\"\n");
      out.write("              integrity=\"sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==\"\n");
      out.write("              crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");

    String userid = CookieUtil.valueOf("blogsphere-user", request.getCookies());

      out.write("\n");
      out.write("\n");
      out.write("<header>\n");
      out.write("    <div class=\"title\">\n");
      out.write("        <div>BLOG SPHERE</div>\n");
      out.write("        <p>Unleash Your Voice, Share Your World</p>\n");
      out.write("    </div>\n");
      out.write("    <nav>\n");
      out.write("        <div class=\"buttons\">\n");
      out.write("            <a href=\"index.jsp\"><i class=\"fa-solid fa-house\"></i></a>\n");
      out.write("            <a href=\"post-content.jsp\"> <i class=\"fa-regular fa-square-plus\"></i> </a>\n");
      out.write("            <a href=\"#\"><i class=\"fa-solid fa-circle-info\"></i></a>\n");
      out.write("\n");
      out.write("            <a href=\"#\" class=\"signin\"></a>\n");
      out.write("\n");
      out.write("\n");
      out.write("            ");

                if (userid == null) {
                    out.println("<a class='signin' href='./signin.jsp'>SIGN IN?</a>");
                } else {
                    out.println("<a href='profile.jsp?uid=" + userid + "'> <i class='fa-solid fa-user'></i> : Hi, " + UserDAO.getUsername(userid).split(" ")[0] + "! </a>");
                }
            
      out.write("\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <div class=\"icons\">\n");
      out.write("            <a href=\"#\"><i class=\"fa-brands fa-facebook-f\"></i></a>\n");
      out.write("            <a href=\"#\"><i class=\"fa-brands fa-twitter\"></i></a>\n");
      out.write("            <a href=\"#\"><i class=\"fa-brands fa-linkedin-in\"></i></a>\n");
      out.write("        </div>\n");
      out.write("    </nav>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        $(document).ready(() => {\n");
      out.write("            $('.signin').on('click', function () {\n");
      out.write("                document.cookie = \"blogsphere-page=index.jsp; path=/\";\n");
      out.write("                window.location.href = \"signin.html\";\n");
      out.write("            });\n");
      out.write("        });\n");
      out.write("    </script>\n");
      out.write("</header>\n");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<aside>\n");
      out.write("    <div class=\"category\">\n");
      out.write("        <a href=\"index.jsp\">All Blogs</a>\n");
      out.write("        <a href=\"#\">Development</a>\n");
      out.write("        <a href=\"#\">Programming</a>\n");
      out.write("        <a href=\"#\">Machine Learning</a>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    ");
 String search = String.valueOf(request.getParameter("search")); 
      out.write("\n");
      out.write("\n");
      out.write("    <div class=\"search\">\n");
      out.write("        <form action=\"index.jsp\">\n");
      out.write("            <input type=\"text\" name=\"search\" class=\"searchbox\" value=\"");
      out.print( (search.equals("null")? "" : search) );
      out.write("\" required>\n");
      out.write("            <button type=\"submit\"><i class=\"fa-solid fa-magnifying-glass\"></i></button>\n");
      out.write("        </form>\n");
      out.write("    </div>\n");
      out.write("</aside>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"imgcontainer\">\n");
      out.write("            <img class=\"errorimg\" src=\"./img/datanotfound.jpg\" alt=\"Ops, No data found!\">\n");
      out.write("            <h3> No Data Found :( </h3>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
