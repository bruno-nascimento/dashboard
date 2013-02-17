package br.com.bsitecnologia.dashboard.controller.admin;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CargoBeanTest {
	
//	private static final String WEBAPP_SRC = "src/main/webapp";
//	
//	@Drone
//    GrapheneSelenium driver;
//	
//	@ArquillianResource
//	URL contextPath;
//	
//	@Deployment
//    public static WebArchive createDeployment() {
//		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "br.com.bsitecnologia.dashboard");
//		war.merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
//			    .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
//			    "/", Filters.includeAll());
//		war.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
//        .setWebXML(new File("src/main/webapp/WEB-INF/web.xml")).addAsResource("WEB-INF/beans.xml", "WEB-INF/beans.xml");
//		return war;
//    }
//
//	@Test
//	@RunAsClient()
//	public void test() throws MalformedURLException {
//		driver.open(new URL(contextPath.toString()+"/admin/cargo/cargo.jsf"));
//		Graphene.waitModel.until(Graphene.elementPresent.locator(Graphene.id("formz:nome")));
//	}

}
