class HumanBeing {
    def name

    public HumanBeing(name) {
        this.name = name
    }

    def computeFromName(code) {
        code.withName(name)
    }

    public static void main(args) {
        HumanBeing son = new HumanBeing('Jannek')
        son.name = 'Niklas'
        def code = new DoWithName() {
            @Override
            def withName(name) {
                name + name
            }
        }
        println(son.computeFromName(code))
    }

    interface DoWithName {
        def withName(name)
    }
}
