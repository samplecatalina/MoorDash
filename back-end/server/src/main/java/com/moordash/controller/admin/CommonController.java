package com.moordash.controller.admin;

import com.moordash.constant.MessageConstant;
import com.moordash.result.Result;
import com.moordash.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Common interface
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "Common Interface")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("File upload")
    public Result<String> upload(MultipartFile file) {
        log.info("Uploading file：{}", file);

        try {
            // original file name
            String originalFilename = file.getOriginalFilename();

            // Slice the extension name (.png, .jpg, etc.) of the original file name
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // Construct a new file name with UUID to avoid repeated file names
            String objectName = UUID.randomUUID().toString() + extension;

            // File's request path
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("Fail to delete file：{}", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

}
