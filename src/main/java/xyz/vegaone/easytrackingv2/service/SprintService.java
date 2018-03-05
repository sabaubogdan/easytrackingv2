package xyz.vegaone.easytrackingv2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv2.domain.SprintEntity;
import xyz.vegaone.easytrackingv2.dto.Sprint;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.mapper.ProjectMapper;
import xyz.vegaone.easytrackingv2.mapper.SprintMapper;
import xyz.vegaone.easytrackingv2.repo.ProjectRepo;
import xyz.vegaone.easytrackingv2.repo.SprintRepo;

@Service
public class SprintService {

    private SprintRepo sprintRepo;

    private SprintMapper sprintMapper;

    @Autowired
    public SprintService(SprintRepo sprintRepo, SprintMapper sprintMapper) {
        this.sprintRepo = sprintRepo;
        this.sprintMapper = sprintMapper;
    }

    public Sprint createSprint(Sprint sprint) {
        SprintEntity sprintEntity = sprintMapper.dtoToDomain(sprint);
        SprintEntity savedSprintEntity = sprintRepo.save(sprintEntity);

        return sprintMapper.domainToDto(savedSprintEntity);
    }

    public Sprint getSprint(Long id) {
        SprintEntity sprintEntity = sprintRepo.findOne(id);

        if (sprintEntity == null) {
            throw new EntityNotFoundException("Sprint with id " + id + " not found");
        }

        return sprintMapper.domainToDto(sprintEntity);
    }

    public void deleteSprint(Long id) {
        sprintRepo.delete(id);
    }

    public Sprint updateSprint(Sprint sprint) {
        SprintEntity sprintEntity = sprintMapper.dtoToDomain(sprint);
        SprintEntity updatedSprintEntity = sprintRepo.save(sprintEntity);
        Sprint savedSprint = sprintMapper.domainToDto(updatedSprintEntity);

        return savedSprint;
    }
}
