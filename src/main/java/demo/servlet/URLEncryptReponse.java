package demo.servlet;

import demo.encrypters.DesEncrypter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Created by nguyen.dang on 6/7/16.
 */
public class URLEncryptReponse extends HttpServletResponseWrapper {

    private HttpServletRequest request;

    public URLEncryptReponse(HttpServletRequest request,
                             HttpServletResponse response) {
        super(response);
        this.request = request;
    }

    @Override
    public String encodeRedirectURL(String url) {
        return encode(url);
    }

    @Override
    public String encodeURL(String url) {
        return encode(url);
    }

    private String encode(String url) {
        final int index = url.indexOf("?");
        if (index > 0) {
            final String path = url.substring(0, index);
            String param = url.substring(index + 1, url.length());
            param = param + "&sid" + request.getSession().getId();
            final String encrypted_params = DesEncrypter.encrypt(param, request
                    .getSession().getId());
            url = path + "?enryptedParams=" + encrypted_params;
        }

        return url;
    }
}
