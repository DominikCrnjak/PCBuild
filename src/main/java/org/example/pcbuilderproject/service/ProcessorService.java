package org.example.pcbuilderproject.service;

import org.example.pcbuilderproject.domainComponents.ProcessorDTO;

import java.util.List;

public interface ProcessorService {
    List<ProcessorDTO> getAllProcessors();

    ProcessorDTO getProcessorById(Long id);

    ProcessorDTO createProcessor(ProcessorDTO processor);

    void deleteProcessor(Long id);


}
