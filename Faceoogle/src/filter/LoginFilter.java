package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
  
public class LoginFilter implements Filter {
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UserBean userBean = (UserBean)((HttpServletRequest)request).getSession().getAttribute("userBean");

        if (userBean == null || !userBean.getLoggedIn()) {
            String contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath + "/login.xhtml");
        } else {
        	chain.doFilter(request, response);
        }
    }
 
    public void init(FilterConfig config) throws ServletException {}
 
    public void destroy() {}    
}