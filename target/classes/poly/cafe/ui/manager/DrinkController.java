/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.ui.manager;

import poly.cafe.entity.Drink;
import poly.cafe.ui.manager.CrudController;

/**
 *
 * @author lengh
 */
public interface DrinkController extends CrudController<Drink>{
    void fillCategories();
    void chooseFile();
}