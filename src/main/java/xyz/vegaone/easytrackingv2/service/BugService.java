package xyz.vegaone.easytrackingv2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv2.domain.BugEntity;
import xyz.vegaone.easytrackingv2.dto.Bug;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.mapper.BugMapper;
import xyz.vegaone.easytrackingv2.repo.BugRepo;

import java.util.Optional;

@Service
@Slf4j
public class BugService {
    
    private BugRepo bugRepo;

    private BugMapper bugMapper;

    @Autowired
    public BugService(BugRepo bugRepo, BugMapper bugMapper) {
        this.bugRepo = bugRepo;
        this.bugMapper = bugMapper;
    }

    public Bug createBug(Bug bug){
        BugEntity bugEntity = bugMapper.dtoToDomain(bug);
        BugEntity savedBugEntity = bugRepo.save(bugEntity);

        return bugMapper.domainToDto(savedBugEntity);
    }

    public Bug getBug(Long id){
        Optional<BugEntity> bugEntityOptional = bugRepo.findById(id);

        BugEntity bugEntity = bugEntityOptional.orElseThrow(()->
                            new EntityNotFoundException("Bug with id: " + id + " not found"));
        Bug bug = bugMapper.domainToDto(bugEntity);

        return bug;
    }

    public void deleteBug(Long id){bugRepo.deleteById(id);}

    public Bug updateBug(Bug bug){
        BugEntity bugEntity = bugMapper.dtoToDomain(bug);

        BugEntity savedBugEntity = bugRepo.save(bugEntity);

        Bug savedBug = bugMapper.domainToDto(savedBugEntity);

        return savedBug;
    }

    public Long getUserStatisticsBug(Long id){
        return bugRepo.countByUserId(id);
    }
}
