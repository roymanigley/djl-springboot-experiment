package ch.bytecrowd.djlspringbootexperiment;

import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.TranslateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@SpringBootApplication
public class DjlSpringbootExperimentApplication implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(DjlSpringbootExperimentApplication.class);
	public static final ImageFactory IMAGE_FACTORY = ImageFactory.getInstance();

	public static void main(String[] args) {
		SpringApplication.run(DjlSpringbootExperimentApplication.class, args);
	}

	@Resource
	private Supplier<Predictor<Image, DetectedObjects>> predictorProvider;

	// Bean configuration is not compatible with .yml configuration
	// @Autowired
	// private ZooModel<Image, DetectedObjects> model;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List.of(
			"images/puppy-in-white-and-red-polka.jpg"
			,"images/street-car-bus-truck.jpg"
			,"images/various-objects.png"
		).stream()
			.map(this::imageFromResource)
			.map(this::predictObjects)
			.forEach(this::logItems);
	}

	private Image imageFromResource(String path) {
		try (final var imageStream = DjlSpringbootExperimentApplication.class.getClassLoader().getResourceAsStream(path)) {
			return IMAGE_FACTORY.fromInputStream(imageStream);
		} catch (IOException e) {
			throw new RuntimeException(String.format("Could not load image from resource: %", path), e);
		}
	}

	private Classifications predictObjects(Image image) {
		try {
			return predictorProvider.get().predict(image);
		} catch (TranslateException e) {
			throw new RuntimeException("image processing during prediction failed", e);
		}
	}

	private void logItems(Classifications classifications) {
		LOG.info("ITEMS:");
		classifications.topK(10).stream()
				.map(Objects::toString)
				.forEach(LOG::info);
	}
}
