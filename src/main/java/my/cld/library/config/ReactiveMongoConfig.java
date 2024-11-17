package my.cld.library.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadConcern;
import com.mongodb.WriteConcern;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.lang.NonNull;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "my.cld.library.repository")
public class ReactiveMongoConfig extends AbstractReactiveMongoConfiguration {

    private final MongoProperties mongoProperties;

    public ReactiveMongoConfig(final MongoProperties mongoProperties) {
        this.mongoProperties = mongoProperties;
    }

    @Override
    @NonNull
    protected String getDatabaseName() {
        return mongoProperties.getDatabase();
    }

    @Override
    protected void configureClientSettings(final MongoClientSettings.Builder builder) {
        builder.applyConnectionString(new ConnectionString(mongoProperties.getUri()))
                .readConcern(ReadConcern.SNAPSHOT)
                .writeConcern(WriteConcern.MAJORITY);
    }

    @Bean
    public ReactiveMongoTransactionManager transactionManager(final ReactiveMongoDatabaseFactory dbFactory) {
        return new ReactiveMongoTransactionManager(dbFactory);
    }

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

}
