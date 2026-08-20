# Validation Library

This IntelliJ IDEA project implements the required Java validation library in
the `il.ac.hit.validation` package.

## Requirements

- IntelliJ IDEA Community or Ultimate
- JDK 24

## Open and run in IntelliJ IDEA

1. Choose **File > Open** and select this project folder.
2. If IntelliJ asks whether to trust the project, choose **Trust Project**.
3. Open **File > Project Structure > Project** and select JDK 24.
4. Confirm that the language level is **24**.
5. Open `src/test/java/il/ac/hit/validation/ValidationLibraryDemo.java`.
6. Click the green run arrow next to `main`.

The Run window should print:

```text
All validation library checks passed.
```

## Create validation.jar

The Maven configuration sets the final library name to `validation.jar`.
In IntelliJ, open the Maven tool window and run **Lifecycle > package**.
The file will be created at `target/validation.jar`.


