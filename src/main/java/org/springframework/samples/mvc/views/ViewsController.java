package org.springframework.samples.mvc.views;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/views/*")
public class ViewsController {

	@RequestMapping(value="html", method=RequestMethod.GET)
	public String prepare(Model model) {
		model.addAttribute("foo", "bar");
		model.addAttribute("fruit", "apple");
		return "views/html";
	}
	
	@RequestMapping(value="/viewName", method=RequestMethod.GET)
	public void usingRequestToViewNameTranslator(Model model) {
		model.addAttribute("foo", "bar");
		model.addAttribute("fruit", "apple");
	}

	@RequestMapping(value="pathVariables/{foo}/{fruit}", method=RequestMethod.GET)
	public String pathVars(@PathVariable String foo, @PathVariable String fruit) {
		// No need to add @PathVariables "foo" and "fruit" to the model
		// They will be merged in the model before rendering
		return "views/html";
	}

	@RequestMapping(value="dataBinding/{foo}/{fruit}", method=RequestMethod.GET)
	public String dataBinding(@Valid JavaBean javaBean, Model model) {
		// JavaBean "foo" and "fruit" properties populated from URI variables 
		return "views/dataBinding";
	}

	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(Model model) {
		Set<TestModel> modelList = new HashSet<>();
		modelList.add(new TestModel(1, "nguyen", "sydney"));
		modelList.add(new TestModel(2, "dang", "nsw"));
		model.addAttribute("modelList", modelList);
		return "views/list";
	}

	@RequestMapping(value="detail", method=RequestMethod.GET)
	public String viewModel(@RequestParam(required = false) String name,
							@RequestParam(required = false) String add,
							@RequestParam(required = false) Integer id,
							Model model) {
		TestModel testModel = new TestModel(id, name, add);
		model.addAttribute(testModel);
		return "views/detail";
	}

	public class TestModel {
		private final Integer id;
		private final String name;
		private final String add;

		public TestModel(Integer id, String name, String add) {
			this.name = name;
			this.add = add;
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public String getAdd() {
			return add;
		}

		public Integer getId() {
			return id;
		}
	}

}
