package org.unidal.cat.autoconfigure;

import com.dianping.cat.Cat;
import org.apiguardian.api.API;

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
      String token = m_properties.getToken();
      String home = m_properties.getHome();
      int httpPort = m_properties.getHttpPort();

      // bring CAT up
      if (home != null) {
         System.setProperty("cat.home", home);
      }

      if (token != null) {
         System.setProperty("cat.token", token);
      }

      if (httpPort > 0) {
         System.setProperty("cat.node", m_properties.getNode());
      }

      Cat.getBootstrap().initialize(domain, server.getEndpoints());
   }
}
