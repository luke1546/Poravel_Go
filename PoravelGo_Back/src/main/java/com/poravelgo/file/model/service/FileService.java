package com.poravelgo.file.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.poravelgo.file.model.FileUpload;


public interface FileService {
	FileUpload uploadFile(MultipartFile file, String transaction, String roomId);

	void deleteFileDir(String path);

	default File convertMultipartFileToFile(MultipartFile mfile, String tmpPath) throws IOException {
		File file = new File(tmpPath);

		if (file.createNewFile()) {
			try (FileOutputStream fos = new FileOutputStream(file)) {
				fos.write(mfile.getBytes());
			}
			return file;
		}
		throw new IOException();
	}

	default void removeFile(File file) {
		file.delete();
	}

	ResponseEntity<byte[]> getObject(String fileDir, String fileName) throws IOException;
}
