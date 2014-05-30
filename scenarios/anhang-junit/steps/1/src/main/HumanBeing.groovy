class HumanBeing {

    String name

    public HumanBeing(String name) {
        this.name = name
    }

    String computeFromName(DoWithName code) {
        code.withName(name)
    }

    public static void main(String[] args) {
        HumanBeing son = new HumanBeing('Jannek')
        son.name = 'Niklas'
        DoWithName code = new DoWithName() {
            @Override
            String withName(String name) {
                name + name
            }
        }
        System.out.println(son.computeFromName(code))
    }

    interface DoWithName {
        String withName(String name)
    }
}
