package at.bbrz.buecherei.model;

public class Author {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}
