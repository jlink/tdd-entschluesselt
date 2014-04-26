#!/usr/bin/env groovyclient

String command = this.args.first()
def args = this.args.size() == 1 ? [] : this.args[1..-1]
def cwd = new File("./")

switch(command.toLowerCase()) {
    case "ls":
    case "listscenarios":
        listScenarios(cwd, args)
        return
    case "ns":
    case "newscenario":
        newScenario(cwd, args)
        return
    default:
        println("Command '$command' is unknown.")
}

def newScenario(File cwd, args) {
    File scenariosDir = scenariosDir(cwd)
    if(!scenariosDir.isDirectory()) {
        println("Scenarios dir $scenariosDir.canonicalPath does not exist.")
        return
    }
    def scenarioName = args[0]
    File scenarioDir = new File(scenariosDir, scenarioName)
    if(scenarioDir.exists()) {
        println("Scenario '$scenarioName' already exist in $scenariosDir.canonicalPath.")
        return
    }
    File stepsDir = stepsDir(scenarioDir)
    def initialStep = "0"
    File firstStepDir = new File(stepsDir, initialStep)
    firstStepDir.mkdirs()
    writeCurrentScenario(cwd, scenarioName, initialStep)
}

def listScenarios(File cwd, args) {
    File scenariosDir = scenariosDir(cwd)
    if(!scenariosDir.isDirectory()) {
        println("Scenario dir $scenariosDir.canonicalPath does not exist.")
        return
    }
    def nameFilter = args[0] ?: ""
    def scenariosDirs = scenariosDir.listFiles({File file -> file.isDirectory() && (file.name =~ nameFilter)} as FileFilter)
    def allScenarios = scenariosDirs.collect {File file ->
        def name = file.name
        def scenario = [name: name]
        def current = currentScenarioAndStep(cwd)
        scenario.steps = stepsDir(file).list().sort {it.toInteger()}
        scenario.isCurrent = (current.scenario == name)
        if (scenario.isCurrent && current.step)
            scenario.currentStep = current.step
        return scenario
    }
    allScenarios.each {listAScenario(it)}
}

private File stepsDir(File scenarioDir) {
    new File(scenarioDir, "steps")
}

private File scenariosDir(File cwd) {
    new File(cwd, "scenarios")
}

def listAScenario(scenario) {
    def isCurrentMarker = scenario.isCurrent ? "> " : "  "
    def currentStep = scenario.currentStep ? ":$scenario.currentStep" : ""
    println "$isCurrentMarker$scenario.name$currentStep  $scenario.steps"
}

def currentScenarioAndStep(File cwd) {
    File currentFile = currentConfigFile(cwd)
    if (!currentFile.exists())
        return [scenario: null, step: null]
    else {
        def scenarioAndStep = currentFile.readLines()
        return [scenario: scenarioAndStep[0], step: scenarioAndStep[1]]
    }
}

private File currentConfigFile(File cwd) {
    new File(cwd, ".current")
}

def writeCurrentScenario(File cwd, String scenario, String step) {
    File currentFile = currentConfigFile(cwd)
    currentFile.delete()
    currentFile.withPrintWriter { writer ->
        writer.println(scenario)
        writer.println(step)
    }

}