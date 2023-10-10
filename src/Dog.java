public class Dog {

    private String name;

    public Dog(String name){
        this.name = name;

    }

    public void bark(){
        System.out.println(this.name + " barks at you.");
    }

    public void berserkir(Cat target){
//        Upon activation, the dog that this command was activated on will bark at the closest feline threat to it's owner.
        while (target.isViolent() == false) {
            bark();
        }
    }

}
