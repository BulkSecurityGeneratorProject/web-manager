package blog.service.impl;

import blog.service.MannagerService;
import blog.domain.Mannager;
import blog.repository.MannagerRepository;
import blog.service.dto.MannagerDTO;
import blog.service.mapper.MannagerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Mannager.
 */
@Service
@Transactional
public class MannagerServiceImpl implements MannagerService{

    private final Logger log = LoggerFactory.getLogger(MannagerServiceImpl.class);

    private final MannagerRepository mannagerRepository;

    private final MannagerMapper mannagerMapper;

    public MannagerServiceImpl(MannagerRepository mannagerRepository, MannagerMapper mannagerMapper) {
        this.mannagerRepository = mannagerRepository;
        this.mannagerMapper = mannagerMapper;
    }

    /**
     * Save a mannager.
     *
     * @param mannagerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MannagerDTO save(MannagerDTO mannagerDTO) {
        log.debug("Request to save Mannager : {}", mannagerDTO);
        Mannager mannager = mannagerMapper.toEntity(mannagerDTO);
        mannager = mannagerRepository.save(mannager);
        return mannagerMapper.toDto(mannager);
    }

    /**
     *  Get all the mannagers.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MannagerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Mannagers");
        return mannagerRepository.findAll(pageable)
            .map(mannagerMapper::toDto);
    }

    /**
     *  Get one mannager by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MannagerDTO findOne(Long id) {
        log.debug("Request to get Mannager : {}", id);
        Mannager mannager = mannagerRepository.findOne(id);
        return mannagerMapper.toDto(mannager);
    }

    /**
     *  Delete the  mannager by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mannager : {}", id);
        mannagerRepository.delete(id);
    }
}
