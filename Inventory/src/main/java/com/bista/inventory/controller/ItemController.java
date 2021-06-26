package com.bista.inventory.controller;




import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.bista.inventory.model.Item;
import com.bista.inventory.services.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/item")
	public ModelAndView showAddItemPage(@ModelAttribute("item") Item item, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addItem");
		
		return mv;
	}
	
	@PostMapping("/item")
	public String saveItem(@Valid @ModelAttribute("item") Item item, BindingResult result, @RequestParam("picture") MultipartFile file) {
		
		if(result.hasErrors()) {
			return "editItem";
		}else {
			try {
				itemService.saveItem(item, file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/item?success";
		}
			
	}
	
	
	@GetMapping("/getItem/{id}")
	public ModelAndView getItemById(@PathVariable(value="id") long id) {
		ModelAndView mv = new ModelAndView();
		Item item = itemService.findItemById(id);
		mv.setViewName("editItem");
		mv.addObject("item", item);
		
		return mv;
	}
	
	@GetMapping("/deleteItem/{id}")
	public String deleteItemById(@PathVariable(value="id") long id) {
		itemService.deleteItemById(id);	
		
		return "redirect:/";
	}
	
}
