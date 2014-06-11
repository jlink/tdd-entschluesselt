class HumanBeing {
    def name

    def computeFromName(DoWithName code) {
        code.withName(name)
    }

    public static void main(args) {
        HumanBeing son = new HumanBeing(name: 'Jannek')
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