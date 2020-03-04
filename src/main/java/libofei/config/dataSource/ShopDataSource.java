package libofei.config.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration // 注册到springboot容器中
@MapperScan(basePackages = "libofei.shop.dao", sqlSessionFactoryRef = "shopSqlSessionFactory")
public class ShopDataSource {




	@Bean(name = "shopDataSourceBean")
	@ConfigurationProperties(prefix = "spring.datasource.shop")
	@Primary  //默认数据库，必须定义一个，否则会报错 只给一个数据源加
	public DataSource shopDataSource() {
		return DataSourceBuilder.create().build();
	}


	@Bean(name = "shopSqlSessionFactory")
	@Primary
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("shopDataSourceBean") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);

		//如若mybatis写配置xml，需要读取配置文件
//		mybatis写配置文件
		 bean.setMapperLocations(
		 new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/shop/**/*.xml"));
		return bean.getObject();
	}

	//事务管理
	@Bean(name = "shopTransactionManager")
	@Primary
	public DataSourceTransactionManager testTransactionManager(@Qualifier("shopDataSourceBean") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "shopSqlSessionFactory")
	@Primary
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("shopSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}




}
