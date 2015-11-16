package com.lazycook.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}
}*/

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


/**
 * Handles and retrieves person request
 * Управляет и возвращает запрос
 */
@Controller
@RequestMapping("/main")
public class MainController {

	protected static Logger logger = Logger.getLogger("controller");

	@Resource(name="recipeService")
	private RecipeService recipeService;

	/**
	 * Handles and retrieves all recipes and show it in a JSP page
	 * Получает все рецепты и показывает их на jsp
	 * @return the name of the JSP page
	 */
		@RequestMapping(value = "/recipes", method = RequestMethod.GET)
	public String getRecipes(Model model) {

		logger.debug("Received request to show all recipes");

		// Retrieve all recipes by delegating the call to RecipeService
		// Получает всех персон вызовом PersonService
		List<Recipe> recipes = recipeService.getAll();

		// Attach recipes to the Model
		// Прикрепляет рецепты к модели
		model.addAttribute("recipes", recipes);

		// This will resolve to /WEB-INF/jsp/recipespage.jsp
		// Перенаправляет на /WEB-INF/jsp/recipespage.jsp
		return "recipePage";
	}

	/**
	 * Retrieves the add page
	 * Возвращает страницу Добавления
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/recipes/add", method = RequestMethod.GET)
	public String getAdd(Model model) {
		logger.debug("Received request to show add page");

		// Create new Recipe and add to model
		// This is the formBackingOBject
		// Создает новый рецепт и добавляет его в модель
		// Это возвращающая форма
		model.addAttribute("recipeAttribute", new Recipe());

		// This will resolve to /WEB-INF/jsp/addpage.jsp
		// перенаправление на страницу Добавления
		return "addpage";
	}

	/**
	 * Adds a new recipe by delegating the processing to RecipeService.
	 * Displays a confirmation JSP page
	 * Добавляет новый рецепт через RecipeService
	 * Показывает подтверждающую JSP
	 * @return  the name of the JSP page
	 */
	@RequestMapping(value = "/recipes/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("recipeAttribute") Recipe recipe) {
		logger.debug("Received request to add new person");

		// The "recipeAttribute" model has been passed to the controller from the JSP
		// We use the name "recipeAttribute" because the JSP uses that name
		// Call RecipeService to do the actual adding
		// модель "recipeAttribute" передана контроллеру из JSP
		// мы используем имя "recipeAttribute" потому что JSP  страница использует его
		// Вызов RecipeService для совершения добавления
		recipeService.add(recipe);

		// This will resolve to /WEB-INF/jsp/addedpage.jsp
		return "addedpage";
	}

	/**
	 * Deletes an existing person by delegating the processing to PersonService.
	 * Displays a confirmation JSP page
	 *
	 * @return  the name of the JSP page
	 */
	@RequestMapping(value = "/recipes/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value="id", required=true) Integer id,
						 Model model) {

		logger.debug("Received request to delete existing person");

		// Call PersonService to do the actual deleting
		recipeService.delete(id);

		// Add id reference to Model
		model.addAttribute("id", id);

		// This will resolve to /WEB-INF/jsp/deletedpage.jsp
		return "deletedpage";
	}

	/**
	 * Retrieves the edit page
	 *
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/recipes/edit", method = RequestMethod.GET)
	public String getEdit(@RequestParam(value="id", required=true) Integer id,
						  Model model) {
		logger.debug("Received request to show edit page");

		// Retrieve existing Person and add to model
		// This is the formBackingOBject
		model.addAttribute("recipeAttribute", recipeService.get(id));

		// This will resolve to /WEB-INF/jsp/editpage.jsp
		return "editpage";
	}

	/**
	 * Edits an existing person by delegating the processing to RecipeService.
	 * Displays a confirmation JSP page
	 *
	 * @return  the name of the JSP page
	 */
	@RequestMapping(value = "/recipes/edit", method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute("recipeAttribute") Recipe recipe,
						   @RequestParam(value="id", required=true) Integer id,
						   Model model) {
		logger.debug("Received request to update recipe");

		// The "recipeAttribute" model has been passed to the controller from the JSP
		// We use the name "recipeAttribute" because the JSP uses that name

		// We manually assign the id because we disabled it in the JSP page
		// When a field is disabled it will not be included in the ModelAttribute
		recipe.setId(id);

		// Delegate to PersonService for editing
		recipeService.edit(recipe);

		// Add id reference to Model
		model.addAttribute("id", id);

		// This will resolve to /WEB-INF/jsp/editedpage.jsp
		return "editedpage";
	}

}