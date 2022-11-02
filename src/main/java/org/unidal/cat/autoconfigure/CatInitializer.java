package org.unidal.cat.autoconfigure;

import com.dianping.cat.Cat;
import org.apiguardian.api.API;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apiguardian.api.API.Status.INTERNAL;

@API(status = INTERNAL, since = "1.0")
public class CatInitializer {
   private CatProperties m_properties;

   public CatInitializer(CatProperties properties) {
      m_properties = properties;

      boolean enabled = m_properties.isEnabled();
      String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

      if (enabled) {
         System.out.println(String.format("[%s] [INFO] Trying to initialize CAT with %s", timestamp, m_properties));
         initialize();
      } else {
         System.out.println(String.format("[%s] [INFO] CAT is DISABLED explicitly. use ${cat.enabled} to turn on.", timestamp));
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
