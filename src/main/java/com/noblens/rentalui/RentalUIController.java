	package com.noblens.rentalui;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
@RequestMapping("/")
public class RentalUIController {
	private StorageService storageService;

	@Autowired
	public void FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@Autowired
	private RentalProxy RentalProxy;
	
	@GetMapping("")
	public ModelAndView hom() {
		return new ModelAndView("accueil");
	}
	@GetMapping("error")
	public ModelAndView error() {
		return new ModelAndView("home");
	}
	
	
	@GetMapping("home")
	public ModelAndView home() {
		return new ModelAndView("home");
	}
	
	@GetMapping("creerbien")
	public ModelAndView creerbien(Bien bien) {
		return new ModelAndView("creerbien");
	}
	
	@PostMapping(path = "creerbien")
	public ModelAndView creerbien(@Valid Bien bien, BindingResult result, RedirectAttributes redirect) {
		RentalProxy.creerbien(bien);
		return new ModelAndView("redirect:bienlist");

	}
		
	
	@GetMapping("biendetaille/{id}")
	public ModelAndView biendetaille(@PathVariable("id") int id) {
		Bien bien = RentalProxy.biendetaille(id);
		return new ModelAndView("biendetaille", "bien", bien);
	}
	
	@GetMapping("bienlist")
	public ModelAndView myforest() {
		/* Sort test = new Sort(Sort.Direction.ASC, "id"); */
		Iterable<Bien> biens = RentalProxy.bienlist();
		return new ModelAndView("bienlist", "biens", biens);
	}
	@GetMapping(path = "dataloader")
	public ModelAndView dataloaderupload() {

		return new ModelAndView("dataloader", "test",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(RentalUIController.class, "serveFile", path.getFileName().toString())
								.build().toString())
						.collect(Collectors.toList()));
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@PostMapping("dataloader")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		// BufferedReader br = null;
		// StringBuilder sb = new StringBuilder();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION FOREST
			Sheet datatypeSheet1 = workbook.getSheet("biens");
			Iterator<Row> iterator1 = datatypeSheet1.iterator();
			iterator1.next();
			while (iterator1.hasNext()) {
				Row currentRow1 = iterator1.next();
				if (!currentRow1.getCell(1).getStringCellValue().isEmpty()) {

					Bien bien = new Bien();
					bien.setNom(currentRow1.getCell(1).getStringCellValue()); // Nom
					bien.setNumero((int) currentRow1.getCell(2).getNumericCellValue()); // Numéro
					bien.setAdresse(currentRow1.getCell(3).getStringCellValue()); // Adresse
					bien.setVille(currentRow1.getCell(4).getStringCellValue()); // Ville
					bien.setPostal_code((int)currentRow1.getCell(5).getNumericCellValue()); // Code Postal
					bien.setEtage(currentRow1.getCell(6).getStringCellValue()); // Etage
				//	bien.setSuperficie(currentRow1.getCell(7).getNumericCellValue()); // Superficie
					bien.setNbr_piece((int)currentRow1.getCell(8).getNumericCellValue()); // Nbr de piece
					bien.setNbr_chambre((int)currentRow1.getCell(9).getNumericCellValue()); // Nbr de chambre);
					bien.setCreated_dttm(new Date());
					bien.setCreated_source("DATALOADER");
					bien.setLast_updated_dttm(new Date());
					bien.setLast_updated_source("DATALOADER");
					RentalProxy.creerbien(bien);
					// forest = this.forestRepository.save(forest);
				}

			}

			     
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/bienlist";
	}
	
 
}
