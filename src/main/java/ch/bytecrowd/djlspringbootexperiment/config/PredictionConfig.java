package ch.bytecrowd.djlspringbootexperiment.config;

import ai.djl.Application;
import ai.djl.MalformedModelException;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;

@Configuration
public class PredictionConfig {
/*
    @Bean
    @Primary
    @Qualifier("OBJECT_DETECTION")
    ZooModel<Image, DetectedObjects> createModelForObjectDetection() throws MalformedModelException, ModelNotFoundException, IOException {
        final var criteria = Criteria.builder()
                .setTypes(Image.class, DetectedObjects.class)
                .optApplication(Application.CV.OBJECT_DETECTION)
                .optFilter("size", "512")
                .build();
        return ModelZoo
                .loadModel(criteria);
    }

    @Bean
    @Qualifier("ACTION_RECOGNITION")
    ZooModel<Image, Classifications> createModelForActionRecognition() throws MalformedModelException, ModelNotFoundException, IOException {
        final var criteria = Criteria.builder()
                .setTypes(Image.class, Classifications.class)
                .optApplication(Application.CV.ACTION_RECOGNITION)
                //.optApplication(Application.CV.OBJECT_DETECTION)
                .build();
        return ModelZoo
                .loadModel(criteria);
    }
 */
}
