package Pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryPost {
    public String name;
    public ArrayList<State> states;

    public CountryPost(String name, ArrayList<State> states) {
        this.name = name;
        this.states = states;
    }

    public CountryPost() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public void setStates(ArrayList<State> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "CountryPost{" +
                "name='" + name + '\'' +
                ", states=" + states +
                '}';
    }
}
