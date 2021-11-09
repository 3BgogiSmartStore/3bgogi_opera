package com.gogi.proj.util.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author : Jeon KiChan
 * @date : 2021. 11. 9.
 * @클래스설명 : 로컬 파일 다운로드를 위한 컨트롤러
 */
@Controller
public class FileController {

	@javax.annotation.Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	/**
	 * 
	 * @MethodName : fileRes
	 * @date : 2021. 11. 9.
	 * @author : Jeon KiChan
	 * @param filePath
	 * @param fileName
	 * @return
	 * @메소드설명 : 로컬에서 파일 가져오기
	 */
	@RequestMapping(value="/files.do")
	public ResponseEntity<Resource> fileRes(@RequestParam String filePath, @RequestParam String fileName){
		
		Resource resource = new FileSystemResource(filePath+fileName);
		
		if(!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
			
		}
		
		HttpHeaders header = new HttpHeaders();
		
		Path path = null;
		
		try {
			path = Paths.get(filePath+fileName);
			
			header.add("Content-Type", Files.probeContentType(path));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
}
