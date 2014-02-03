package forces;

import jgame.JGObject;

import java.util.HashMap;
import java.util.Vector;


public abstract class Force {

    private HashMap<String, Double> myProperties;
    private String myName;
    private Vector myDefaultVector;

    public Force () {
        myProperties = new HashMap<String, Double>();
        myDefaultVector = new Vector();
    }

    /**
     * Adds a new property to the force, or updates its value if it exists.
     */
    public void addProperty (String name, Double value) {
        myProperties.put(name, value);
    }

    /**
     * Gets a property from the force.
     */
    public Double getProperty (String name) {
        if (myProperties.containsKey(name))
            return myProperties.get(name);
        else return null;
    }

    /**
     * Checks if a property exists for this force.
     */
    public boolean checkProperty (String name) {
        return myProperties.containsKey(name);
    }

    /**
     * Sets the name of the force.
     */
    public void setName (String name) {
        myName = name;
    }

    /**
     * Returns the name of the force.
     */
    public String getName () {
        return myName;
    }


    /**
     * Sets the default vector for the force.
     */
    public void setDefaultVector () {
        Vector vector = new Vector();
        if (getProperty(Keywords.MAGNITUDE) != null) {
            vector.setMagnitude(getProperty(Keywords.MAGNITUDE));
        }
        else {
            vector.setMagnitude(0);
        }
        if (getProperty(Keywords.DIRECTION) != null) {
            vector.setAngle(getProperty(Keywords.DIRECTION));
        }
        else {
            vector.setAngle(0);
        }
        myDefaultVector = vector;
    }

    /**
     * Overwrites equals such that forces with the same name are considered equal.
     */
    public boolean equals (Force other) {
        return myName.equals(other.getName());
    }


    /**
     * Returns a string representation of this force.
     */
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append(myName);
        result.append(": ");
        for (String key : myProperties.keySet()) {
            result.append(key);
            result.append("=");
            result.append(myProperties.get(key));
            result.append(" ");
        }
        return result.toString();
    }

    /**
     * Returns the default vector for the force.
     */
    public Vector getDefaultVector () {
        return myDefaultVector;
    }

    /**
     * Returns the vector for this force specific to a mass.
     */
    public abstract Vector getForceOnMass (Mass m);
}

