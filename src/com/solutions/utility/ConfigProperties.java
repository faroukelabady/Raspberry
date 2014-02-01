/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.utility;

/**
 *
 * @author farouk
 */
public enum ConfigProperties {
    
    LIGHTONEOPEN("open light one"),
    LIGHTONECLOSE("close light one"),
    LIGHTTWOOPEN("open light two"),
    LIGHTTWOCLOSE("close light two"),
    LIGHTTHREEOPEN("open light three"),
    LIGHTTHREECLOSE("close light three"),
    LIGHTFOUROPEN("open light four"),
    LIGHTFOURCLOSE("close light four"),
    LIGHTFIVEOPEN("open light five"),
    LIGHTFIVECLOSE("close light five"),
    LIGHSIXOPEN("open light six"),
    LIGHTSIXCLOSE("close light six"),
    LIGHTSEVENOPEN("open light seven"),
    LIGHTSEVENCLOSE("close light seven");
    
    
    String lightValue;
    
    ConfigProperties(String property) {
        this.lightValue = property;
    }
    
    public String getLigTValue() {
        return lightValue;
    }
}
