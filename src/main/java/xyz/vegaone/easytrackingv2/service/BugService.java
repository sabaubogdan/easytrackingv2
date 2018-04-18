package xyz.vegaone.easytrackingv2.service;

import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv2.domain.BugEntity;
import xyz.vegaone.easytrackingv2.dto.Bug;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.repo.BugRepo;

import java.util.Optional;

@Service
@Slf4j
public class BugService {
    
    private BugRepo bugRepo;

    private Mapper mapper;

    @Autowired
    public BugService(BugRepo bugRepo,
                      Mapper mapper) {
        this.bugRepo = bugRepo;
        this.mapper = mapper;
    }

    public Bug createBug(Bug bug){
        BugEntity bugEntity = mapper.map(bug, BugEntity.class);
        BugEntity savedBugEntity = bugRepo.save(bugEntity);

        return mapper.map(savedBugEntity, Bug.class);
    }

    public Bug getBug(Long id){
        Optional<BugEntity> bugEntityOptional = bugRepo.findById(id);

        BugEntity bugEntity = bugEntityOptional.orElseThrow(()->
                            new EntityNotFoundException("Bug with id: " + id + " not found"));

        return mapper.map(bugEntity, Bug.class);
    }

    public void deleteBug(Long id){bugRepo.deleteById(id);}

    public Bug updateBug(Bug bug){
        BugEntity bugEntity = mapper.map(bug, BugEntity.class);

        BugEntity savedBugEntity = bugRepo.save(bugEntity);

        return mapper.map(savedBugEntity, Bug.class);
    }

    public Long getUserStatisticsBug(Long id){
        return bugRepo.countByUserId(id);
    }
}
