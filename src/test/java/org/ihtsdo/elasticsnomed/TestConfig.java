package org.ihtsdo.elasticsnomed;

import io.kaicode.elasticvc.domain.Branch;

import org.ihtsdo.elasticsnomed.config.Config;
import org.ihtsdo.elasticsnomed.core.data.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import javax.annotation.PostConstruct;

import java.net.UnknownHostException;

@PropertySource("application-test.properties")
@TestConfiguration
public class TestConfig extends Config {

	@Autowired
	private ElasticsearchOperations elasticsearchTemplate;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void cleanUp() throws UnknownHostException {
		logger.info("Deleting all existing entities before tests start");
		elasticsearchTemplate.deleteIndex(Concept.class);
		elasticsearchTemplate.deleteIndex(Description.class);
		elasticsearchTemplate.deleteIndex(Relationship.class);
		elasticsearchTemplate.deleteIndex(ReferenceSetMember.class);
		elasticsearchTemplate.deleteIndex(QueryConcept.class);
		elasticsearchTemplate.deleteIndex(Branch.class);
	}

}
