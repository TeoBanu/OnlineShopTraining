package ro.msg.learning.shop.odata.annotations;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.servlet.ODataServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class OdataAnnotationServlet extends ODataServlet {

    private final ApplicationContext context;

    @Autowired
    public OdataAnnotationServlet(ApplicationContext context) {
        this.context = context;
    }

    @Override
    protected ODataServiceFactory getServiceFactory(HttpServletRequest request) {
        return context.getBean(OdataAnnotationServiceFactory.class);
    }

}
