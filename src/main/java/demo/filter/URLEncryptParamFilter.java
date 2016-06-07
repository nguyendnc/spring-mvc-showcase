package demo.filter;

import demo.encrypters.DesEncrypter;
import demo.servlet.URLEncryptReponse;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class URLEncryptParamFilter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        // http://localhost:8080/spring-mvc-showcase/views/detail?enryptedParams=78688ad222d20c40f5839fe51771b493c390e99e3f182c693d299ef2819e81eee1f0d193f4f7df7264e6ae0be815b39d4a10f7f7c4e89cc0ad6ab3b5926d882c
        String url = httpRequest.getRequestURI().substring(
                httpRequest.getContextPath().length());

        final Object ep = httpRequest.getParameter("enryptedParams");
        if (ep != null) {
            final String encryptedParams = String.valueOf(ep);
            String decryptedParams = DesEncrypter.decrypt(encryptedParams,
                    httpRequest.getSession().getId());

            if (decryptedParams == null) {
                decryptedParams = "";
            }
            url = url + "?" + decryptedParams;
            // http://localhost:8080/spring-mvc-showcase/views/detail?id=1&username=nguyen&fewf=fasdf
        }

        final URLEncryptReponse er = new URLEncryptReponse(httpRequest, httpResponse);
        final RequestDispatcher rd = request.getRequestDispatcher(url);

        rd.forward(httpRequest, er);
    }

    @Override
    public void destroy() {
        // do something if any
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // do something if any
    }

}
