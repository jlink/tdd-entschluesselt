public class HumanBeing {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public HumanBeing(String name) {
        this.name = name;
    }

    public String computeFromName(DoWithName code) {
        return code.withName(name);
    }

    public static void main(String[] args) {
        HumanBeing son = new HumanBeing("Jannek");
        son.setName("Niklas");
        DoWithName code = new DoWithName() {
            @Override
            public String withName(String name) {
                return name + name;
            }
        };
        System.out.println(son.computeFromName(code));
    }

    interface DoWithName {
        String withName(String name);
    }
}
