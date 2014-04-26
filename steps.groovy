#!/usr/bin/env groovyclient

String command = this.args.first()
def args = this.args.size() == 1 ? [] : this.args[1..-1]
def cwd = new File("./")

switch(command.toLowerCase()) {
    case "ls":
    case "listscenarios":
        listScenarios(cwd, args)
        return
}

println("Command '$command' is unknown.")


def listScenarios(File cwd, args) {
    File scenarioDir = new File(cwd, "scenarios")
    if(!scenarioDir.isDirectory()) {
        println("Scenario dir $scenarioDir.absoluteFile does not exist.")
        return
    }
    def nameFilter = args[0] ?: ""
    def scenariosDirs = scenarioDir.listFiles({File file -> file.isDirectory() && (file.name =~ nameFilter)} as FileFilter)
    def allScenarios = scenariosDirs.collect {File file ->
        def name = file.name
        def scenario = [name: name]
        def current = currentScenarioAndStep(cwd)
        scenario.steps = new File(file, "steps").list().sort {it.toInteger()}
        scenario.isCurrent = (current.scenario == name)
        if (scenario.isCurrent && current.step)
            scenario.currentStep = current.step
        return scenario
    }
    allScenarios.each {listAScenario(it)}
}

def listAScenario(scenario) {
    def isCurrentMarker = scenario.isCurrent ? "> " : "  "
    def currentStep = scenario.currentStep ? ":$scenario.currentStep" : ""
    println "$isCurrentMarker$scenario.name$currentStep  $scenario.steps"
}

def currentScenarioAndStep(File cwd) {
    File currentFile = new File(cwd, ".current")
    if (!currentFile.exists())
        return [scenario: null, step: null]
    else {
        def scenarioAndStep = currentFile.readLines()
        return [scenario: scenarioAndStep[0], step: scenarioAndStep[1]]
    }
}