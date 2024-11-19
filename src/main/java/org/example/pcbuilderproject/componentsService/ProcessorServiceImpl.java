package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.componentsDomain.Processor;
import org.example.pcbuilderproject.componentsDomain.ProcessorDTO;
import org.example.pcbuilderproject.mapper.ProcessorMapper;
import org.example.pcbuilderproject.componentsRepository.ProcessorRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessorServiceImpl implements ProcessorService{
    private final ProcessorRepository processorRepository;

    public ProcessorServiceImpl(ProcessorRepository processorRepository) {
        this.processorRepository = processorRepository;
    }
    @Override
    public List<ProcessorDTO> getAllProcessors() {
        return processorRepository.findAll()
                .stream()
                .map(ProcessorMapper::mapToProcessorDTO)
                .collect(Collectors.toList());
    }
    @Override
    public ProcessorDTO getProcessorById(Long id) {
        return processorRepository.findById(id)
                .map(ProcessorMapper::mapToProcessorDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Processor not found"));
    }
    @Override
    public ProcessorDTO createProcessor(ProcessorDTO processordto) {
        Processor processor=ProcessorMapper.mapToProcessor(processordto);
        Processor savedProcessor=processorRepository.save(processor);
        ProcessorDTO savedProcessorDTO=ProcessorMapper.mapToProcessorDTO(savedProcessor);
        return savedProcessorDTO;
    }
    @Override
    public void deleteProcessor(Long id) {
        processorRepository.deleteById(id);
    }
}
