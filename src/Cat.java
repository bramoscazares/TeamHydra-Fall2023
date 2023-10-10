public class Cat {

    private String name;
    private boolean violent;

    public void speak() {
        System.out.println(this.name + "meows at you.");
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isViolent() {
        return violent;
    }
}
