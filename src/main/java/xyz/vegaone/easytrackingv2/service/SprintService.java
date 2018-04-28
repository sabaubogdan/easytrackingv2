package xyz.vegaone.easytrackingv2.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv2.domain.SprintEntity;
import xyz.vegaone.easytrackingv2.dto.Sprint;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.repo.SprintRepo;
import xyz.vegaone.easytrackingv2.util.MapperUtil;

import java.util.List;
import java.util.Optional;

@Service
public class SprintService {

    private SprintRepo sprintRepo;

    private Mapper mapper;

    private MapperUtil mapperUtil;

    @Autowired
    public SprintService(SprintRepo sprintRepo,
                         Mapper mapper,
                         MapperUtil mapperUtil) {
        this.sprintRepo = sprintRepo;
        this.mapper = mapper;
        this.mapperUtil = mapperUtil;
    }

    public Sprint createSprint(Sprint sprint) {
        SprintEntity sprintEntity = mapper.map(sprint, SprintEntity.class);
        SprintEntity savedSprintEntity = sprintRepo.save(sprintEntity);

        return mapper.map(savedSprintEntity, Sprint.class);
    }

    public List<Sprint> getSprintByProjectid(Long projectId){
        List<SprintEntity> sprintEntityList = sprintRepo.findAllByProjectId(projectId);

        return mapperUtil.mapList(sprintEntityList, Sprint.class);
    }

    public Sprint getSprint(Long id) {

        Optional<SprintEntity> sprintOptional = sprintRepo.findById(id);

        SprintEntity sprintEntity = sprintOptional.orElseThrow(() -> new EntityNotFoundException("Sprint with id " + id + " not found"));

        return mapper.map(sprintEntity, Sprint.class);
    }

    public void deleteSprint(Long id) {
        sprintRepo.deleteById(id);
    }

    public Sprint updateSprint(Sprint sprint) {
        SprintEntity sprintEntity = mapper.map(sprint, SprintEntity.class);
        SprintEntity updatedSprintEntity = sprintRepo.save(sprintEntity);

        return mapper.map(updatedSprintEntity, Sprint.class);
    }
}
