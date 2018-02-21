/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.Enumerations.BodyType;
import models.Enumerations.MaritalStatus;
import models.Enumerations.Religion;

/**
 *
 * @author Elyes
 */
public class Filter {
    private int ageMin;
    private int ageMax;
    private int heightMin;
    private int heightMax;
    private BodyType bodyType;
    private Religion religion;
    private MaritalStatus maritalStatus;
    
}
