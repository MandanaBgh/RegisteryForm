package Anisa.Practice.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "Anisa.Practice")
@EnableTransactionManagement
@PropertySource("classPath:persistance-mysql.properties")
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }


    @Bean
    public DataSource appDataSource() {
        ComboPooledDataSource myAppDataSource = new ComboPooledDataSource();
        try {
            myAppDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        logger.info(">>>jdbc:url " + env.getProperty("jdbc.url"));
        logger.info(">>>jdbc:user " + env.getProperty("jdbc.user"));

        myAppDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        myAppDataSource.setUser(env.getProperty("jdbc.user"));
        myAppDataSource.setPassword(env.getProperty("jdbc.password"));
        myAppDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        myAppDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        myAppDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        myAppDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdelTime"));


        return myAppDataSource;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    private int getIntProperty(String propName) {
        String propVal = env.getProperty(propName);
        int propIntVal = Integer.parseInt(propVal);

        return propIntVal;
    }

    private Properties getHibernateProperties() {

        // set hibernate properties
        Properties props = new Properties();

        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

        return props;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        // create session factorys
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set the properties
        sessionFactory.setDataSource(appDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }


}