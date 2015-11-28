/**
 *
 */
package com.wsheng.aggregator.bean;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Josh Wang(Sheng)
 *         <p/>
 *         The class used to build the parameters of the query
 */
public class QueryParam {
    private Map<String, Object> params = null;

    public QueryParam() {
        this.params = new HashMap<String, Object>();
    }

    public QueryParam(String paramName, Object param) {
        this.params = new HashMap<String, Object>();
        this.addParam(paramName, param);
    }

    public QueryParam(Map<String, Object> map) {
        this.params = new HashMap<String, Object>();
        if (map == null)
            return;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            this.addParam(entry.getKey(), entry.getValue());
        }
    }

    public void addParam(String paramName, Object param) {
        this.params.put(paramName, param);
    }

    public Object getParam(String paramName) {
        return this.params.get(paramName);
    }

    public Set<String> getParamNames() {
        return Collections.unmodifiableSet(this.params.keySet());
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
