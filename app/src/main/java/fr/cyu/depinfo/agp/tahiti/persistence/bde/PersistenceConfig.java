package fr.cyu.depinfo.agp.tahiti.persistence.bde;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {
    @Bean
    public DataSource dataSource() throws NamingException {
        JndiTemplate jndi = new JndiTemplate();
        return (DataSource) jndi.lookup("java:comp/env/jdbc/postgres");
    }
}
