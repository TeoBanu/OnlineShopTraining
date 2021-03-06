package ro.msg.learning.shop.odata.jpa;

import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class OdataJpaServiceFactory extends ODataJPAServiceFactory {
	private final LocalContainerEntityManagerFactoryBean factory;

	@Autowired
	public OdataJpaServiceFactory(LocalContainerEntityManagerFactoryBean factory) {
		this.factory = factory;
	}

	@Override
	public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
		ODataJPAContext context = this.getODataJPAContext();
		context.setEntityManagerFactory(factory.getObject());
		context.setPersistenceUnitName("local");
		return context;
	}
}
