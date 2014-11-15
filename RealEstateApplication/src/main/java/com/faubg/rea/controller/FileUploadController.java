package com.faubg.rea.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.faubg.rea.connections.FileUpload;

@Controller
public class FileUploadController extends SimpleFormController {

	public FileUploadController() {
		setCommandClass(FileUpload.class);
		setCommandName("uploadFile");
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		FileUpload file = (FileUpload) command;

		MultipartFile multipartFile = file.getFile();

		String fileName = "";

		if (multipartFile != null) {
			fileName = multipartFile.getOriginalFilename();
			// do whatever you want
		}

		return new ModelAndView("FileUploadSuccess", "fileName", fileName);
	}

	@RequestMapping(value = "/upload")
	public String upload(Model model, HttpServletRequest request) {
		Check.Login(model, request);
		return "upload";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(Locale locale, Model model, HttpServletRequest request, @RequestParam String name, @RequestParam MultipartFile file) {
		Check.Login(model, request);
		String message;
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "uploads");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				message = "You successfully uploaded file=" + name;
			} catch (Exception e) {
				message = "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			message = "You failed to upload " + name + " because the file was empty.";
		}
		return "upload";
	}
}
