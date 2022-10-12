function buildArgs(jarName: string, jvmArgs: string[], appArgs: string[]): string[] {
    return [...jvmArgs, "-jar", jarName, ...appArgs];
}

export default {
    buildArgs
}