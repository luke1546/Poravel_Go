package com.poravelgo.file.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.poravelgo.file.model.FileUpload;
import com.poravelgo.file.model.service.S3FileService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/s3")
@Slf4j
public class FileController {
	@Autowired
	private S3FileService fileService;

	@PostMapping("/upload")
	public FileUpload uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("roomId") String roomId) {
		FileUpload fileReq = fileService.uploadFile(file, UUID.randomUUID().toString(), roomId);
		log.info("LAST UPLOAD DATA {}", fileReq);

		return fileReq;
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<byte[]> download(@PathVariable String fileName, @RequestParam("fileDir") String fileDir) {
		log.info("FILE DIR : FILE NAME [{} : {}]", fileDir, fileName);

		try {
			return fileService.getObject(fileDir, fileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
