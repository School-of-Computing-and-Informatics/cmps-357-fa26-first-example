# IDE Setup

All supported configurations use Java 17, treat `src` as the [source root](TECHNICAL-REFERENCE.md#source-root-and-output-directory), and write generated [class files](TECHNICAL-REFERENCE.md#class-file-and-classpath) to `out`. The `out` directory is intentionally ignored by Git.

## Prerequisite

Install a Java 17 or later [JDK](TECHNICAL-REFERENCE.md#jdk-and-sdk) and confirm both commands are available:

```text
javac -version
java -version
```

## Visual Studio Code

1. Open the repository folder in VS Code.
2. Install the recommended Extension Pack for Java when prompted.
3. Open **Terminal → Run Task**.
4. Choose **Run Main**.

The task first invokes `javac` to [compile](TECHNICAL-REFERENCE.md#compile-build-and-run) the three source files into `out`, then invokes `java -cp out Main`. The `-cp` option supplies the [classpath](TECHNICAL-REFERENCE.md#class-file-and-classpath). **Compile Java** is also available as the default build task.

## IntelliJ IDEA

1. Choose **Open** and select the repository folder.
2. Select a Java 17 or later project [SDK](TECHNICAL-REFERENCE.md#jdk-and-sdk) if IntelliJ requests one.
3. Choose the shared **Main** run configuration.
4. Click **Run**.

The committed [module](TECHNICAL-REFERENCE.md#project-metadata-module-and-run-configuration) file marks `src` as the source directory and uses `out` for compiler output.

## Eclipse

1. Choose **File → Import → General → Existing Projects into Workspace**.
2. Select the repository folder as the root directory.
3. Finish the import.
4. Run the shared `Main.launch` configuration or run `Main.java` as a Java application.

The Eclipse project targets the JavaSE-17 execution environment.

## NetBeans

1. Choose **File → Open Project**.
2. Select the repository folder.
3. Choose **Run Project**.

NetBeans opens the project as an [Ant](TECHNICAL-REFERENCE.md#ant-and-build-file) free-form project. Its build, clean, and run actions use the targets defined in `build.xml`.

## Direct [command line](TECHNICAL-REFERENCE.md#cli)

From the repository root, the same project can be run without an IDE:

```text
javac -d out src/Main.java src/Supply.java src/Workshop.java
java -cp out Main
```

The initial empty [driver](TECHNICAL-REFERENCE.md#main-method-driver-and-entry-point) exits successfully without displaying output.

