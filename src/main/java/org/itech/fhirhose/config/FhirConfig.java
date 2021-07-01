package org.itech.fhirhose.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;
import ca.uhn.fhir.rest.client.apache.ApacheRestfulClientFactory;
import ca.uhn.fhir.rest.client.api.IRestfulClientFactory;

@Configuration
public class FhirConfig {

	private CloseableHttpClient httpClient;
	private FhirConfigProperties fhirConfigProperties;

	public FhirConfig(CloseableHttpClient httpClient, FhirConfigProperties configProperties) {
		this.httpClient = httpClient;
		this.fhirConfigProperties = fhirConfigProperties;
	}

    @Bean
    public FhirContext fhirContext() {
        FhirContext fhirContext = new FhirContext(FhirVersionEnum.R4);
        configureFhirHttpClient(fhirContext);
        return fhirContext;
    }

    public void configureFhirHttpClient(FhirContext fhirContext) {
        IRestfulClientFactory clientFactory = new ApacheRestfulClientFactory(fhirContext);

        clientFactory.setHttpClient(httpClient);
        fhirContext.setRestfulClientFactory(clientFactory);

    }
}
