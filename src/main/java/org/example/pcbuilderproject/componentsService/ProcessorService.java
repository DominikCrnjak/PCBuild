package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.componentsDomain.ProcessorDTO;

import java.util.List;

public interface ProcessorService {
    List<ProcessorDTO> getAllProcessors();

    ProcessorDTO getProcessorById(Long id);

    ProcessorDTO createProcessor(ProcessorDTO processor);

    void deleteProcessor(Long id);


}
