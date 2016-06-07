package demo.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nguyen.dang on 6/7/16.
 */
public class UrlEncryptRequest extends HttpServletRequestWrapper {
    HashMap overridenParameters;

    public UrlEncryptRequest(HttpServletRequest request) {
        super(request);
    }

    public UrlEncryptRequest(HttpServletRequest request,
                             HashMap overridenParameters) {
        super(request);
        this.overridenParameters = overridenParameters;
    }

    @Override
    public Enumeration getParameterNames() {
        if (overridenParameters != null) {
            final List keys = Collections.list(super.getParameterNames());
            keys.addAll(overridenParameters.keySet());
            return Collections.enumeration(keys);
        }
        return super.getParameterNames();
    }

    @Override
    public Map getParameterMap() {
        if (overridenParameters != null) {
            final Map superMap = super.getParameterMap();
            superMap.putAll(overridenParameters);
            return superMap;
        }
        return super.getParameterMap();
    }

    @Override
    public String[] getParameterValues(String s) {
        if (overridenParameters != null && overridenParameters.containsKey(s)) {
            return new String[]{(String) overridenParameters.get(s)};
        }
        return super.getParameterValues(s);
    }

    @Override
    public String getParameter(String s) {
        if (overridenParameters != null && overridenParameters.containsKey(s)) {
            return (String) overridenParameters.get(s);
        }
        return super.getParameter(s);
    }
}
