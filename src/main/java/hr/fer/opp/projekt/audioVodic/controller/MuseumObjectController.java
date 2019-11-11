package hr.fer.opp.projekt.audioVodic.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hr.fer.opp.projekt.audioVodic.model.MuseumObject;
import hr.fer.opp.projekt.audioVodic.service.MuseumObjectService;

@Controller
public class MuseumObjectController {

	@Autowired
	private MuseumObjectService museumObjectService;
	
	@RequestMapping({"/","/museumObjects"})
	public ModelAndView getMuseumObjects(HttpServletRequest req) {
		List<MuseumObject> museumObjects = museumObjectService.getAllObjects();
		System.out.println(req.getContextPath());
		List<List<MuseumObject>> museumObjectsInRows = new ArrayList<List<MuseumObject>>();
		int br = 0;
		List<MuseumObject> list = new ArrayList<MuseumObject>();
		for (MuseumObject museumObject : museumObjects) {
			if (br!=0 && br%3==0) {
				museumObjectsInRows.add(list);
				list = new ArrayList<MuseumObject>();
			}
			br++;
			list.add(museumObject);
		}
		if (br%3!=0) {
			museumObjectsInRows.add(list);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("museumObjectsInRows",museumObjectsInRows);
		
		String imagesPath = req.getRealPath("/WEB-INF/images");
		mv.addObject("imagesPath",imagesPath);
		System.out.println(imagesPath);
		
		mv.setViewName("museumObjects");
		return mv;
	}
	
	@RequestMapping("/museumObject/{id}")
	public ModelAndView getMuseumObject(@PathVariable("id") int id) {
		MuseumObject museumObject = museumObjectService.getMuseumObject(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("museumObject",museumObject);
		mv.setViewName("museumObject");
		return mv;
	}
	
	@RequestMapping("/museumObject/audio/{id}")
	public ModelAndView getAudio(@PathVariable("id") int id) {
		MuseumObject museumObject = museumObjectService.getMuseumObject(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("museumObject",museumObject);
		mv.setViewName("audio");
		return mv;
	}
	
	@RequestMapping("/slika/{id}")
	public ModelAndView getImage(@PathVariable("id") int id, HttpServletResponse resp, HttpServletRequest req) {
		MuseumObject museumObject = museumObjectService.getMuseumObject(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("museumObject",museumObject);
		mv.setViewName("audio");
		
//		BufferedImage bim = new BufferedImage(600, 500, BufferedImage.TYPE_3BYTE_BGR);
//		Graphics2D g = bim.createGraphics();
//
//		g.setColor(Color.WHITE);
//		g.fillRect(0, 0, 300, 330);
//
//		OutputStream out = resp.getOutputStream();
//		ImageIO.write(bim, "jpg", out);
		
		String path = req.getRealPath("/WEB-INF/images");
		String path2 = req.getContextPath();
		System.out.println(path2);
		
		System.out.println(path + "/" + museumObject.getImageName());

		File file1 = new File(path + "/" + museumObject.getImageName());
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		BufferedImage img1;
		try {
			img1 = ImageIO.read(file1);
			ImageIO.write(img1, "jpg", stream);
			resp.getOutputStream().write(stream.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}
	
}
