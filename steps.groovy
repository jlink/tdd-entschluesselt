#!/usr/bin/env groovyclient

String command = this.args.first()
def args = this.args.size() == 1 ? [] : this.args[1..-1]
def cwd = new File("./")

switch (command.toLowerCase()) {
    case "ls":
    case "listscenarios":
        listScenarios(cwd, args)
        return
    case "ns":
    case "new":
    case "newscenario":
        newScenario(cwd, args)
        return
    case "push":
    case "pushstep":
        pushStep(cwd, args)
        return
    case "sc":
    case "set":
    case "setcurrent":
        setCurrent(cwd, args)
        return
    case "pull":
    case "pullCurrent":
        pullCurrent(cwd, args)
        return
    default:
        println("Command '$command' is unknown.")
}

def pullCurrent(File cwd, args) {
    if (args.size() > 0)
        setCurrent(cwd, args)

    def currentScenario = currentScenarioAndStep(cwd)
    def scenarioName = currentScenario.scenario
    File scenariosDir = scenariosDir(cwd)
    File scenarioDir = new File(scenariosDir, scenarioName)
    File stepsDir = stepsDir(scenarioDir)
    def stepToPull = currentScenario.step
    File stepToPullDir = new File(stepsDir, stepToPull)
    if (!stepToPullDir.exists()) {
        println("Step '$scenarioName:$stepToPull' does not exist in $stepToPullDir.canonicalPath.")
        return
    }
    copyOver(stepToPullDir, currentDir(cwd))
}

def setCurrent(File cwd, args) {
    def scenarioName = args[0]
    def step = args[1] ?: "0"
    writeCurrentScenario(cwd, scenarioName, step)
    println("Current scenario set to $scenarioName:$step")
}

def pushStep(File cwd, args) {
    def currentScenario = currentScenarioAndStep(cwd)
    def scenarioName = currentScenario.scenario
    File scenariosDir = scenariosDir(cwd)
    File scenarioDir = new File(scenariosDir, scenarioName)
    if (!scenarioDir.exists()) {
        println("Scenario '$scenarioName' does not exist in $scenariosDir.canonicalPath.")
        return
    }
    File stepsDir = stepsDir(scenarioDir)
    def stepToPush = currentScenario.step
    boolean forceOverwrite = false
    if (args[0]) {
        stepToPush = args[0]
        forceOverwrite = true
    }
    File stepToPushDir = new File(stepsDir, stepToPush)
    if (stepToPushDir.exists() && !forceOverwrite) {
        println("Use explicit step name to overwrite $stepToPushDir.canonicalPath")
        return
    } else {
        stepToPushDir.mkdir()
    }
    copyOver(currentDir(cwd), stepToPushDir)

    if (!forceOverwrite) {
        String nextStep = stepToPush.toInteger() + 1
        writeCurrentScenario(cwd, scenarioName, nextStep)
    }
    println("Pushed current files to '$scenarioName' in $scenariosDir.canonicalPath.\"")
}

private File currentDir(File cwd) {
    new File(cwd, "current")
}

def newScenario(File cwd, args) {
    def scenarioName = args[0]
    File scenariosDir = scenariosDir(cwd)
    if (!scenariosDir.isDirectory()) {
        println("Scenarios dir $scenariosDir.canonicalPath does not exist.")
        return
    }
    File scenarioDir = new File(scenariosDir, scenarioName)
    if (scenarioDir.exists()) {
        println("Scenario '$scenarioName' already exist in $scenariosDir.canonicalPath.")
        return
    }
    File stepsDir = stepsDir(scenarioDir)
    stepsDir.mkdirs()
    def initialStep = "0"
    writeCurrentScenario(cwd, scenarioName, initialStep)
    deleteCurrentContents(currentDir(cwd))
    println("Created scenario '$scenarioName' in $scenariosDir.canonicalPath.\"")
}

def listScenarios(File cwd, args) {
    File scenariosDir = scenariosDir(cwd)
    if (!scenariosDir.isDirectory()) {
        println("Scenario dir $scenariosDir.canonicalPath does not exist.")
        return
    }
    def nameFilter = args[0] ?: ""
    def scenariosDirs = scenariosDir.listFiles({ File file -> file.isDirectory() && (file.name =~ nameFilter) } as FileFilter)
    def allScenarios = scenariosDirs.collect { File file ->
        def name = file.name
        def scenario = [name: name]
        def current = currentScenarioAndStep(cwd)
        scenario.steps = stepsDir(file).list().sort {
            if (it.isNumber())
                return it.toInteger()
            else
                return it
        }
        scenario.isCurrent = (current.scenario == name)
        if (scenario.isCurrent && current.step)
            scenario.currentStep = current.step
        return scenario
    }
    allScenarios.each { listAScenario(it) }
}

private File stepsDir(File scenarioDir) {
    new File(scenarioDir, "steps")
}

private File scenariosDir(File cwd) {
    new File(cwd, "scenarios")
}

def listAScenario(scenario) {
    def isCurrentMarker = scenario.isCurrent ? "> " : "  "
    def currentStep = (scenario.currentStep ? ":$scenario.currentStep" : "").padRight(3)
    print "$isCurrentMarker$scenario.name".padRight(30)
    println "$currentStep $scenario.steps"
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

private copyOver(File from, File to) {
    deleteDirContents(to)
    new AntBuilder().copy(todir: to) {
        fileset(dir: from)
    }


}

private void deleteDirContents(File to) {
    to.eachDir { it.deleteDir() };
    to.eachFile { it.delete() }
}


private deleteCurrentContents(File currentDir) {
    deleteDirContents(new File("src/main"))
    deleteDirContents(new File("src/test"))
}