package utez.edu.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

import utez.edu.ecommerce.entity.Asset;

public interface AWSS3Service {

    String uploadFile(MultipartFile file);

    Asset getObject(String key);

    void deleteObject(String key);

    String getObjectUrl(String key);
}