package me.djamelkorei.projecttaskmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Datatable configuration.
 *
 * @author Djamel Eddine Korei
 */
@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, basePackages = "me.djamelkorei.projecttaskmanagement")
public class DataTablesConfig {
}
