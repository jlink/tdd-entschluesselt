class HumanBeing {
    def name

    def computeFromName(code) {
        code(name)
    }

    public static void main(args) {
        HumanBeing son = new HumanBeing(name: 'Jannek')
        son.name = 'Niklas'
        println(son.computeFromName() { name -> name + name } )
    }
}