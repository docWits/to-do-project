package com.romanyuta.todoproject.service;

import com.romanyuta.todoproject.model.Release;
import com.romanyuta.todoproject.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ReleaseService {

    private final ReleaseRepository releaseRepository;

    @Autowired
    public ReleaseService(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }


    public List<Release> getRelease(){
        return releaseRepository.findAll();
    }

    public Release getReleaseById(Long id){
        return releaseRepository.getById(id);
    }

    public void addNewRelease(Release release){
        releaseRepository.save(release);
    }

    public void deleteRelease(Long userId) {
        if (!releaseRepository.existsById(userId)) {
            throw new IllegalStateException("release with id=" + userId + " does not exist");
        }
        releaseRepository.deleteById(userId);
    }

    @Transactional
    public void updateRelease(Long releaseId, String version, LocalDate date_start, LocalDate date_finish) {
        Release release = releaseRepository.findById(releaseId)
                .orElseThrow(() -> new IllegalStateException(
                        "release with id=" + releaseId + " does not exist"
                ));
        if(version != null && version.length()>0 && !Objects.equals(release.getVersion(),version)){
            release.setVersion(version);
        }
        if(date_start != null && !Objects.equals(release.getDate_start(),date_start)){
            release.setDate_start(date_start);
        }
        if(date_finish != null && !Objects.equals(release.getDate_finish(),date_finish)){
            release.setDate_finish(date_finish);
        }
    }

}
