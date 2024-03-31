package com.poravelgo.file.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileUpload {
	private MultipartFile file;
	private String originFileName;
	private String transaction;
	private String chatRoom;
	private String s3DataUrl;
	private String fileDir;
}
