package com.team3.tamagochi.files;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FileManager {

	public String fileSave(MultipartFile files, String path) throws Exception {

		File file = new File(path);

		if (!file.exists()) {
			file.mkdirs();
		}

		String filename = UUID.randomUUID().toString() + "_" + files.getOriginalFilename();

		file = new File(file, filename);

		files.transferTo(file);

		return filename;
	}

	public void fileSave (MultipartFile mf, FileDTO fileDTO, String path)throws Exception {
		
		File file = new File(path);
		
		//1. 파일 디렉토리 확인
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//2. 파일명 지정
		String fileName = UUID.randomUUID().toString();
		
		String oriName = mf.getOriginalFilename();
		fileName = fileName + "_" + oriName; 
		
		//3. HDD에 파일 저장
		File f = new File(file, fileName);
		mf.transferTo(f);
		
		fileDTO.setFile_name(fileName);
		fileDTO.setOri_name(oriName);

	}
	
}
