package utez.edu.ecommerce.serviceImpl;

import java.io.IOException;
import java.util.UUID;

import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;

import utez.edu.ecommerce.entity.Asset;
import utez.edu.ecommerce.service.AWSS3Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;

@Service
public class AWSS3ServiceImp implements AWSS3Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(AWSS3ServiceImp.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) {
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = String.format("%s.%s", UUID.randomUUID(), extension);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file.getInputStream(), objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);
            return key;
        }catch (IOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public Asset getObject(String key) {
        S3Object s3Object = amazonS3.getObject(bucketName, key);
        ObjectMetadata metadata = s3Object.getObjectMetadata();
        try {
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return new Asset(bytes, metadata.getContentType());
        }catch (IOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public void deleteObject(String key) {
        amazonS3.deleteObject(bucketName, key);
    }

    @Override
    public String getObjectUrl(String key) {
        return String.format("https://%s.s3.amazonaws.com/%s", bucketName, key);
    }

}