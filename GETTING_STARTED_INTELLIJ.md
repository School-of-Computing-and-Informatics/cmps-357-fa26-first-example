# Getting Started with IntelliJ IDEA

Use these steps after cloning the repository if IntelliJ IDEA does not immediately recognize or run the `Main` class.

## 1. Open the repository root

Open the repository directory itself in IntelliJ IDEA. Do not open only the `src` directory.

## 2. Select Java 17

1. Open **File → Project Structure**.
2. Under **Project**:
   - set **SDK** to an installed Java 17 JDK;
   - set **Language level** to `17`.
3. If no Java 17 JDK is listed, use IntelliJ's JDK download option or install a Java 17 JDK first, then select it here.

The repository specifies Java 17, but the exact local JDK name is machine-specific. For example, one machine may show `azul-17`, while another may show a different vendor or installation name.

## 3. Confirm the source root

1. In the Project pane, locate `src`.
2. Confirm that IntelliJ treats it as a **Sources Root**.
3. If it is not marked as a source root, right-click `src` and choose **Mark Directory as → Sources Root**.

The repository's shared IntelliJ module configuration already identifies `src` as the source folder. If IntelliJ generates a separate local module file, do not replace the shared project configuration with that generated file.

## 4. Run `Main`

Open `src/Main.java`.

IntelliJ should display a green run triangle beside the `main` method. Click it and choose **Run 'Main.main()'**.

If that option does not appear, continue with the next section.

## 5. Check the run configuration

Open **Run → Edit Configurations...** and select or create an **Application** configuration with:

```text
Name: Main
Main class: Main
Use classpath of module: cmps-fa26-first-example
JRE: Project SDK (Java 17)
```

Then apply the configuration and run it again.

## What belongs in source control

Commit project settings only when they describe behavior that should be shared by everyone using the repository. For this project, that includes:

- the shared module definition that marks `src` as a source root;
- the Java 17 language-level setting, expressed without a machine-specific JDK installation name;
- the shared `Main` run configuration, including `MAIN_CLASS_NAME=Main` and the shared module name;
- other repository-wide build or IDE settings intentionally provided for all students.

A useful test is: **would this setting still make sense on another student's computer?** If yes, it may belong in source control.

## What should remain local

Do not commit IntelliJ changes that identify your particular installation or that IntelliJ regenerates for your local workspace. Examples include:

- a specific local JDK name such as `azul-17`;
- `.idea/workspace.xml`;
- `.idea/tasks.xml`;
- `.idea/shelf/`;
- locally generated `.idea/*.iml` module files;
- other per-user workspace state.

The repository `.gitignore` excludes these local files where practical.

Some IntelliJ files, such as `.idea/misc.xml`, are intentionally tracked because they also contain shared project settings. If IntelliJ changes a tracked file only to insert a machine-specific JDK name, **do not commit that local change**. Restore the shared version before committing.

For example, avoid committing a change like:

```xml
project-jdk-name="azul-17"
```

Likewise, do not replace the shared run configuration with a local rewrite that removes the `Main` class setting or changes the shared module name.

## Before committing

Review the diff before each commit:

```bash
git diff
```

If IntelliJ has modified project files, separate shared project changes from local-machine changes before committing. Source-code changes under `src/` should normally be committed; local IDE state should not.
