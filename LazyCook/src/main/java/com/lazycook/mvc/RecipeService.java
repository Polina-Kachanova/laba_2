package com.lazycook.mvc;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service for processing Recipe
 * Сервис для класса Recipe
 */
@Service("recipeService")
@Transactional
public class RecipeService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Retrieves all recipes
     * Получает лист всех рецептов
     */
    public List<Recipe> getAll() {
        logger.debug("Retrieving all persons");

        // Retrieve session from Hibernate
        // Получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Create a Hibernate query (HQL)
        // Создаем запрос
        Query query = session.createQuery("FROM  Recipe");//!!!!!!!!!!!!!!!!!!!!//

        // Retrieve all
        // получаем всех
        return  query.list();
    }

    /**
     * Retrieves a single recipe
     * Получение одного рецепта
     */
    public Recipe get( Integer id ) {
        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing recipe first
        // получаем рецепт по id
        Recipe recipe = (Recipe) session.get(Recipe.class, id);

        return recipe;
    }
    /**
     * Adds a new recipe
     *  Добавление рецепта
     */
    public void add(Recipe recipe) {
        logger.debug("Adding new recipe");

        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Save
        // сохраняем
        session.save(recipe);
    }

    /**
     * Deletes an existing recipe
     * Удаление существующего рецепта
     */
    public void delete(Integer id) {
        logger.debug("Deleting existing recipe");

        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing person first
        // получаем существующую персону
        Recipe recipe = (Recipe) session.get(Recipe.class, id);

        // Delete
        // удаляем
        session.delete(recipe);
    }

    /**
     * Edits an existing recipe
     * Правка рецепта
     */
    public void edit(Recipe recipe) {
        logger.debug("Editing existing recipe");

        // Retrieve session from Hibernate
        // как всегда получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing person via id
        // получаем существующую персону по id
        Recipe existingRecipe = (Recipe) session.get(Recipe.class, recipe.getId());

        // Assign updated values to this person
        // обновляем значения
        existingRecipe.setName(recipe.getName());
        existingRecipe.setDescription(existingRecipe.getDescrition());
        existingRecipe.setNum_Likes(existingRecipe.getNum_Likes());
        existingRecipe.setPhoto(existingRecipe.getPhoto());
        existingRecipe.setType(existingRecipe.getType());
        existingRecipe.setTag(existingRecipe.getTag());

        // Save updates
        // сохраняем изменения
        session.save(existingRecipe);
    }
}
