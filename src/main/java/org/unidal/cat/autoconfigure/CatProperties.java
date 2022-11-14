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
   private String m_token;

   @API(status = STABLE, since = "1.0")
   private Server m_server = new Server();

   public String getDomain() {
      return m_domain;
   }

   public Server getServer() {
      return m_server;
   }

   public String getToken() {
      return m_token;
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

   public void setToken(String token) {
      m_token = token;
   }

   @Override
   public String toString() {
      return String.format("Properties[enabled=%s, domain=%s, server=%s]", m_enabled, m_domain, m_server);
   }

   static class Server {
      private String m_hosts;

      public String[] getEndpoints() {
         List<String> list = new ArrayList<>();

         if (m_hosts != null) {
            String[] parts = m_hosts.split(",");

            for (String part : parts) {
               if (part.trim().length() > 0) {
                  list.add(part.trim());
               }
            }
         }

         return list.toArray(new String[0]);
      }

      public String getHosts() {
         return m_hosts;
      }

      public void setHosts(String hosts) {
         m_hosts = hosts;
      }

      @Override
      public String toString() {
         return String.format("[hosts: %s]", m_hosts);
      }
   }
}
