public abstract class Pet { 
    protected String name; 

    private String classOfAnimal;

    private boolean canFly;

    public void setName(String aName) { name=aName; } 
  
    public String getName() { return name; } 

    public String classOfAnimal() { return classOfAnimal; }

    public boolean canFly() { return canFly; }

    public Pet(String classOfAnimal, boolean canFly) {
        this.classOfAnimal = classOfAnimal;
        this.canFly = canFly;
    }
} 
