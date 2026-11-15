# Knockback Displacement (Fabric 1.21.11)

A Fabric mod project with configurable horizontal and vertical knockback displacement.

## Features
- Independent horizontal and vertical multipliers (0.00x–5.00x).
- In-game config screen opened with **K**.
- Enable/disable knockback modification.
- Settings saved to `config/knockback-displacement.json`.

## Requirements
- Minecraft Java Edition **1.21.11**
- Fabric Loader 0.18.0 or newer
- Fabric API 0.141.1+1.21.11
- Java 21
- Gradle 8.14+ (or import the project in IntelliJ IDEA)

## Build
From this folder run:

```bash
gradle build
```

The mod JAR should be created in `build/libs/`.

## Install
1. Install Fabric Loader for Minecraft 1.21.11.
2. Put the built `knockback-displacement-1.0.0.jar` in `.minecraft/mods`.
3. Also put the matching Fabric API JAR in `.minecraft/mods`.
4. Launch the Fabric 1.21.11 profile.
5. Press **K** in-game to open the config menu.

## Notes
This is a source project, not a precompiled or game-tested JAR. The mixin targets the 1.21.11 Mojang-mapped `LivingEntity.knockback(double, double, double)` method. If another mod also overwrites this method, resolve the mixin conflict or adjust mixin priority.
