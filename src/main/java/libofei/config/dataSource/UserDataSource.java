package libofei.config.dataSource;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


@Configuration // 注册到springboot容器中
@MapperScan(basePackages = "libofei.user.dao", sqlSessionFactoryRef = "userSqlSessionFactory")
public class UserDataSource {

	private final MybatisProperties properties;

	public UserDataSource(MybatisProperties properties) {
		this.properties = properties;
	}

	@Bean(name = "userDataSourceBean")
	@ConfigurationProperties(prefix = "spring.datasource.user")
	public DataSource userDataSource() {
		return DataSourceBuilder.create().build();
	}


	@Bean(name = "userSqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("userDataSourceBean") DataSource dataSource,
												   MybatisProperties mybatisProperties)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		/***
		 * 当配置多数据源时
		 * 多个sqlSessionFactory配置会导致原来的配置无效
		 * 所以要sessionFactory要set一下原来的配置
		 * */
		bean.setConfiguration(mybatisProperties.getConfiguration());
		//mybatis写配置文件
		 bean.setMapperLocations(
		 new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/user/**/*.xml"));
		return bean.getObject();
	}


	@Bean(name = "userTransactionManager")
	public DataSourceTransactionManager testTransactionManager(@Qualifier("userDataSourceBean") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "userSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("userSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}




}
