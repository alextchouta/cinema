package org.sid.importexcelindatabase.service;

import org.sid.importexcelindatabase.model.Tutorial;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelService {
    public void save(MultipartFile file);
    public List<Tutorial> getAllTutorials();
    public ByteArrayInputStream load();
}
