package org.unidal.cat.autoconfigure;

import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

import static org.apiguardian.api.API.Status.STABLE;

@ConfigurationProperties(prefix = "cat")
@API(status = STABLE, since = "1.0")
public class CatProperties {
   @API(status = STABLE, since = "1.0")
   private boolean m_enabled = true;

   @Value("${spring.application.name:Unknown}")
   @API(status = STABLE, since = "1.0")
   private String m_domain;

   @API(status = STABLE, since = "1.0")
   private Server m_server = new Server();

   public String getDomain() {
      return m_domain;
   }

   public Server getServer() {
      return m_server;
   }

   public boolean isEnabled() {
      return m_enabled;
   }

   public void setEnabled(boolean enabled) {
      m_enabled = enabled;
   }

   public void setServer(Server server) {
      m_server = server;
   }

   @Override
   public String toString() {
      return String.format("Properties[enabled=%s, domain=%s, server=%s]", m_enabled, m_domain, m_server);
   }

   static class Server {
      private String m_ips;

      private int m_tcpPort = 2280;

      private int m_httpPort = 8080;

      public String[] getAddresses() {
         List<String> list = new ArrayList<>();

         if (m_ips != null) {
            String[] parts = m_ips.split(",");

            for (String part : parts) {
               if (part.trim().length() > 0) {
                  list.add(part.trim());
               }
            }
         }

         return list.toArray(new String[0]);
      }

      public int getHttpPort() {
         return m_httpPort;
      }

      public String getIps() {
         return m_ips;
      }

      public int getTcpPort() {
         return m_tcpPort;
      }

      public void setHttpPort(int httpPort) {
         m_httpPort = httpPort;
      }

      public void setIps(String ips) {
         m_ips = ips;
      }

      public void setTcpPort(int tcpPort) {
         m_tcpPort = tcpPort;
      }

      @Override
      public String toString() {
         return String.format("[ips: %s, tcp: %s, http: %s]", m_ips, m_tcpPort, m_httpPort);
      }
   }
}
