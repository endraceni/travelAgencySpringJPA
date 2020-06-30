package ceni.endra.micrometer.metrics;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;

@Configuration
public class MicrometerConfiguration {

	
	@Bean
	MeterRegistryCustomizer meterRegistry(MeterRegistry meterRegistry) {
		
		return meterRegistry1 -> {
	          meterRegistry.config()
	          .commonTags("application", "micrometer-youtube-example");
	        };
	    }
		
	}

