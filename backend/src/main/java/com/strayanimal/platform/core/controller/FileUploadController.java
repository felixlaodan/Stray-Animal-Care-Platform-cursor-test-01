package com.strayanimal.platform.core.controller;

import com.strayanimal.platform.core.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("上传失败，请选择文件");
        }

        try {
            // 获取项目根路径
            String projectRoot = System.getProperty("user.dir");
            // 定义上传目录相对于项目根路径的位置
            File uploadDir = new File(projectRoot, "uploads");

            // 确保上传目录存在
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 获取原始文件名并提取文件扩展名
            String originalFileName = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFileName != null && originalFileName.contains(".")) {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }

            // 生成唯一文件名
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

            // 创建目标文件路径
            File dest = new File(uploadDir, uniqueFileName);

            // 将上传的文件保存到目标位置
            file.transferTo(dest);

            // 构建并返回文件的可访问URL
            // 修正：在生产环境中，这应该是一个完整的、可公开访问的URL
            String fileUrl = "/uploads/" + uniqueFileName;
            return Result.success(fileUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }
} 