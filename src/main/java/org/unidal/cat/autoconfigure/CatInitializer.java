package org.unidal.cat.autoconfigure;

import com.dianping.cat.Cat;
import org.apiguardian.api.API;

import java.io.File;

import static org.apiguardian.api.API.Status.INTERNAL;

@API(status = INTERNAL, since = "1.0")
public class CatInitializer {
   private CatProperties m_properties;

   public CatInitializer(CatProperties properties) {
      m_properties = properties;

      boolean enabled = m_properties.isEnabled();

      if (enabled) {
         System.out.println("Trying to initialize CAT with " + m_properties);
         initialize();
      } else {
         System.out.println("CAT is DISABLED explicitly. use ${cat.enabled} to turn on.");
      }
   }

   private void initialize() {
      CatProperties.Server server = m_properties.getServer();
      String domain = m_properties.getDomain();
      int tcpPort = server.getTcpPort();
      int httpPort = server.getHttpPort();

      // bring CAT up
      String userHome = System.getProperty("user.home");
      File catHome = new File(userHome, ".cat");

      catHome.mkdirs();
      System.setProperty("CAT_HOME", catHome.getAbsolutePath());
      Cat.getBootstrap().initializeByDomain(domain, tcpPort, httpPort, server.getAddresses());
   }
}
