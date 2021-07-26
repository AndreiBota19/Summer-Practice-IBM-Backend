package com.example.checkin.feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FeatureService {

    private final FeatureRepository featureRepository;

    @Autowired
    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public Feature addFeature(Feature feature){
        Optional<Feature> featureOptional = featureRepository.findFeatureByName(feature.getName());
        if (featureOptional.isPresent()){
            throw new IllegalStateException("Feature with name: " + feature.getName() + " already exists");
        }
        return  featureRepository.save(feature);
    }

    public Feature findFeatureById(Long id){
        return featureRepository.findFeatureById(id).orElseThrow(
                () -> new IllegalStateException("Feature with id: " + id + " not found!")
        );
    }

    public List<Feature> findAllFeatures(){
        return featureRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public void deleteFeature(Long id){
        if (featureRepository.existsById(id)){
            featureRepository.deleteFeatureById(id);
        }
        else {
            throw new IllegalStateException("Feature with id: " + id + " not found!");
        }
    }

}
