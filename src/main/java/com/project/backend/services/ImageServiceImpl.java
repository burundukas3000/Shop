package com.project.backend.services;

import com.project.backend.models.Image;
import com.project.backend.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepo;
    public Image getById(Long id) {
        return imageRepo.getById(id);
    }
}
