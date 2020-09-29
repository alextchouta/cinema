package org.sid.importexcelindatabase.service;

import lombok.AllArgsConstructor;
import org.sid.importexcelindatabase.helper.ExcelHelper;
import org.sid.importexcelindatabase.model.Tutorial;
import org.sid.importexcelindatabase.repository.TutorialRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ExcelServiceImpl implements ExcelService {

    TutorialRepository repository;

    @Override
    public void save(MultipartFile file) {
        try {
            List<Tutorial> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    @Override
    public List<Tutorial> getAllTutorials() {
        return repository.findAll();
    }
}
